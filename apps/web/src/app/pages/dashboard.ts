import { Component, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FileOmwService } from '../services/file-omw-service';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { ProgressOmwService } from '../services/progress-omw-service';

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard {
  selectedFile: File | null = null;
  progress: number = 0;
  mensaje: string = '';
  constructor(private fileService: FileOmwService, private cd: ChangeDetectorRef, private progressOmwService: ProgressOmwService) {}

  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.selectedFile = file;
      this.progress = 0; // Reinicia para la nueva selección
      this.mensaje = ''; 
    }
  }

  onUpload() {
    if (!this.selectedFile) {
      this.mensaje = 'Por favor, selecciona un archivo primero.';
      return;
    }
    this.progress = 1; // Inicia en 1 para que el *ngIf muestre la barra inmediatamente
    this.mensaje = 'Subiendo...';
    this.progressOmwService.connect();
    if (this.selectedFile) {
      this.fileService.upload(this.selectedFile).subscribe({
        next: (event: any) => {
          if (event.type === HttpEventType.UploadProgress) {
            // Calcular el porcentaje
            if (event.total) {
              this.progress = Math.round((100 * event.loaded) / event.total);
            }
            this.cd.detectChanges(); // FUERZA a Angular a pintar el progreso en el HTML
          }
          else if (event instanceof HttpResponse || event.type === HttpEventType.Response) {
            this.progress = 100; // Forzamos el 100% al recibir respuesta
            this.mensaje = '¡Archivo subido y procesado con éxito!';
            this.selectedFile = null; // Limpiamos la selección
            this.cd.detectChanges();
          }
        },
        error: (err) => {
          this.mensaje = 'Error al subir el archivo';
          this.progress = 0;
        }
      });
      this.progressOmwService.progressSubject.subscribe((data : any) => {
        //this.progress = data.percentage;
        console.log(`Procesado ${data.current} de ${data.total}`);
      });



    }
  }
}
