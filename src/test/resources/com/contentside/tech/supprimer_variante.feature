Feature: Suppression des variantes

  Scenario: Reprise des données d'un dictionnaire
    Given Le dictionnaire "toto" existe et contient une entité "toto1" qui contient 2 variantes "toto11" et "toto12"
    When On supprime la variante "toto11"
    Then La variante "toto11" a été supprimée de la base de données.

  Scenario: initialize data
    Given this dictionary data table:
      | dictionary | entity | variant |
      | toto       | toto1  | toto11  |
      | toto       | toto1  | toto12  |


  Scenario:
    Given Le dictionnaire toto existe et contient une entité toto1 qui contient 2 variantes toto11 et toto12
    When DELETE /dictionaries/toto/entities/toto1/variants
    Then 200 : body empty Toutes les variantes de l’entité toto1ont été supprimées de la base de données.

  Scenario:
    Given Le dictionnaire toto existe et contient une entité toto1 qui contient 2 variantes toto11 et toto12
    When DELETE /dictionaries/toto/entities/toto1/variants [  "spelling": "toto11" ,  "spelling": "toto12"  ]
    Then 200 : body empty Les variantes “toto11” et “toto12” ont été supprimées de la base de données.
