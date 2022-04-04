Feature: Création variantes
  Fonctionnalités relatives à la création de variantes

  Scenario: Création d'un dictionnaire
    Given Le dictionnaire toto n’existe pas
    When POST /dictionaries/toto/entities/toto1/variants
    Then { "spelling": "TOTO1" } 404 avec le message customisé adapté (voir API-298: 4xx et 5xx : body de  la réponseDONE

  Scenario: Création variante d'une entité qui n'existe pas
    Given Le dictionnaire toto existe, mais l’entité toto1 n’existe pas
    When POST /dictionaries/toto/entities/toto1/variants { "spelling": "TOTO1" }
    Then 404 avec le message customisé adapté (voir API-298: 4xx et 5xx : body de  la réponseDONE

  Scenario: Création d'une variante d'une entité
    Given le dictionnaire toto existe, l’entité toto1 existe
    When POST /dictionaries/toto/entities/toto1/variants { "spelling": "TOTO1" }
    Then 201 : Created La variante "spelling”: "TOTO1” est ajoutée à la base et associée à l’entité toto1 du dictionnaire toto.

  Scenario: Création d'une variante qui existe déjà
    Given le dictionnaire toto existe, l’entité toto1 existe, avec une variante TOTO1
    When POST /dictionaries/toto/entities/toto1/variants { "spelling": "TOTO1" }
    Then 201 : Created La variante "spelling”: "TOTO1” est ajoutée à la base et associée à l’entité toto1 du dictionnaire toto. L’entité toto1 contient 2 variantes avec des spelling identiques.

  Scenario:
    Given le dictionnaire toto existe, l’entité toto2 existe et a déjà 3 variantes associées
    When POST /dictionaries/toto/entities/toto2/variants [ { "spelling": "TOTO21" }, { "spelling": "TOTO22" } ]
    Then 201 : Created Les variantes "spelling”: "TOTO21” et "spelling": "TOTO22" sont ajoutées à la base et associée à l'entité toto2 du dictionnaire toto. L’entité toto2 a maintenant 5 variantes associées.

  Scenario:
    Given peu importe
    When POST /dictionaries/toto/entities/toto2/variants {"nimporte": "quoi"}
    Then 400 : voir API-298: 4xx et 5xx : body de  la réponseDONE