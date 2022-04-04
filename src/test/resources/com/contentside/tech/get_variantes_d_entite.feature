Feature: Recherche des variantes d'un dictionnaire

  Scenario: Recherche d'une variante
    Given Le dictionnaire "toto" existe et contient une entité "toto1" qui contient 2 variantes "toto11" et " toto12"
    When GET /dictionaries/toto/entities/toto1/variants
    Then 200, body:
        """
          [
            {
              "spelling": "toto11"
            },
            {
              "spelling": "toto12"
            }
          ]
        """

  Scenario: Recherche d'une entité sans variante
    Given Le dictionnaire toto existe et contient une entité toto1 qui ne contient aucune variante
    When GET /dictionaries/toto/entities/toto1/variants
    Then 200, body: []

  Scenario: Recherche d'une entité pour un dictionnaire qui n'existe pas
    Given le dictionnaire toto n'existe pas
    When DELETE /dictionaries/toto/entities/toto1/variants
    Then 404 : API-298: 4xx et 5xx : body de  la réponse

  Scenario: Recherche
    Given le dictionnaire toto existe pas mais n’a aucune entité associée
    When DELETE /dictionaries/toto/entities/toto1/variants
    Then 404 : API-298: 4xx et 5xx : body de  la ré
