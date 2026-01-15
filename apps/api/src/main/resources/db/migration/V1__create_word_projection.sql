CREATE TABLE IF NOT EXISTS word_search_view (
  id UUID PRIMARY KEY,
  term VARCHAR(255),
  lemma VARCHAR(255),
  part_of_speech VARCHAR(10),
  language_level VARCHAR(10),
  spanish_translation VARCHAR(255),
);
