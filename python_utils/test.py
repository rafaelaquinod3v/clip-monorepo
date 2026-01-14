# import kagglehub
# https://www.kaggle.com/datasets/nezahatkk/10-000-english-words-cerf-labelled
# Download latest version
# path = kagglehub.dataset_download("nezahatkk/10-000-english-words-cerf-labelled")

# Pandas
# SpaCy
# NLTK
# Hugging Face Transformers

import pandas as pd
import spacy
from deep_translator import GoogleTranslator


def obtener_lemma_pos_translation(texto, nlp):
  doc = nlp(str(texto))
  traducion = GoogleTranslator(source="en", target="es").translate(texto)
  token = doc[0] if len(doc) > 0 else None
  if token:
    return token.lemma_, token.pos_, traducion
  return None, None

def procesar_vocabulario(input_file, output_file):
  nlp = spacy.load('en_core_web_sm')
  df = pd.read_csv(input_file)
  resultados = df['headword'].map(lambda x: obtener_lemma_pos_translation(x, nlp))
  df[['lemma', 'pos', 'spanish']] = pd.DataFrame(resultados.tolist(), index=df.index)
  df.to_csv(output_file, index=False)
  print("Proceso Completado")

def main():
    # print("Hello World")
    # print("Path to dataset files:", path)
    archivo_entrada = "ENGLISH_CERF_WORDS.csv"
    archivo_salida = "ENGLISH_CERF_WORDS_LEMMA_POS_TRANSLATION_.csv"
    procesar_vocabulario(archivo_entrada, archivo_salida)

if __name__ == '__main__':
    main()
