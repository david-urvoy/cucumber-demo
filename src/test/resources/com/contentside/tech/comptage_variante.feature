Feature: Comptage des variantes
  Fonctionnalités relatives au comptage d'une variante

  Scenario: Comptage d'une variante
    Given Une entité avec une seule variante existe dans un dictionnaire toto
    When GET /dictionaries/toto
    Then 200 : { "name": "toto", "count_entities": 1, "count_variants": 1 }

  Scenario: Comptage plusieurs variantes et entités
    Given Le dictionnaire tata existe et contient 2 entités: tata1 : 1 seule variante tata1 | tata2 : 2 variantes tata2 et TATA2
    When GET /dictionaries/tata
    Then 200 : { "name": "tata", "count_entities": 2, "count_variants": 3 }

  Scenario: Comptage aucune variante
    Given Le dictionnaire titi existe mais ne contient aucune entité et donc aucune variante
    When GET  /dictionaries/titi
    Then 200 : { "name": "titi", "count_entities": 0, "count_variants": 0 }

  Scenario: Comptage dictionnaire n'existe pas
    Given Le dictionnaire tutu n'existe pas.
    When GET /dictionaries/tutu
    Then 404 : avec le message classique