package com.contentside.tech

import com.contentside.tech.domain.Dictionary
import com.contentside.tech.domain.DictionaryService
import com.contentside.tech.domain.Entity
import com.contentside.tech.domain.Variant
import com.contentside.tech.test.AcceptanceTest
import com.contentside.tech.test.StepDefinitions
import io.cucumber.datatable.DataTable
import io.cucumber.java8.Scenario
import io.cucumber.spring.CucumberContextConfiguration
import junit.framework.Assert.*
import org.opentest4j.TestAbortedException
import org.springframework.beans.factory.annotation.Autowired

@CucumberContextConfiguration
class AcceptanceClassTest : AcceptanceTest()

data class DictionaryData(val dictionary: String, val entity: String, val variant1: String, val variant2: String)

class SuppressionVarianteTest : StepDefinitions {
    @Autowired
    lateinit var dictionaryService: DictionaryService

    lateinit var dictionary: Dictionary


    init {

        Given("Le dictionnaire {string} existe et contient une entité {string} qui contient 2 variantes {string} et {string}") { dictionaryId: String, entity: String, variant1: String, variant2: String ->
            dictionary = Dictionary(
                dictionaryId, listOf(
                    Entity(entity, listOf(Variant(variant1), Variant(variant2)))
                )
            )
            dictionaryService.saveDictionary(dictionary)
        }
        When("On supprime la variante {string}") { variant: String -> dictionaryService.deleteDictionary(variant) }
        Then("La variante {string} a été supprimée de la base de données.") { variant: String ->
            dictionaryService.getVariant(
                variant
            )
        }

        Given("this dictionary data table:") { dictionaryTable: DataTable ->
            val data: DictionaryData = dictionaryTable.asList<DictionaryData>(DictionaryData::class.java)[0]
            val dictionary = Dictionary(
                data.dictionary, listOf(
                    Entity(
                        data.entity, listOf(
                            Variant(data.variant1), Variant(data.variant2)
                        )
                    )
                )
            )
            println("Got this dictionary: $dictionary")
        }

        When("GET \\/dictionaries\\/toto\\/entities\\/toto1\\/variants") {}
        Then("200, body:") { message: String -> println(message) }
    }
}

var lastInstance: StepDefinitions? = null

class KotlinDemo : StepDefinitions {
    init {
        DataTableType { entry: Map<String, String> ->
            Person(entry["first"], entry["last"])
        }

        Before { _: Scenario ->
            assertNotSame(this, lastInstance)
            lastInstance = this
        }

        BeforeStep { _: Scenario ->
            assertSame(this, lastInstance)
            lastInstance = this
        }

        AfterStep { _: Scenario ->
            assertSame(this, lastInstance)
            lastInstance = this
        }

        After { _: Scenario ->
            assertSame(this, lastInstance)
            lastInstance = this
        }

        Given("this data table:") { peopleTable: DataTable ->
            val people: List<Person> = peopleTable.asList(Person::class.java)
            assertEquals("Aslak", people[0].first)
            assertEquals("Hellesøy", people[0].last)
        }

        val alreadyHadThisManyCukes = 1
        Given("I have {long} cukes in my belly") { n: Long ->
            assertEquals(1, alreadyHadThisManyCukes)
            assertEquals(42L, n)
        }

        val localState = "hello"
        Then("I really have {int} cukes in my belly") { i: Int ->
            assertEquals(42, i)
            assertEquals("hello", localState)
        }

        Given("A statement with a body expression") { assertTrue(true) }

        Given("A statement with a simple match") { assertTrue(true) }

        Given("something that is skipped") { throw TestAbortedException("skip this!") }

        val localInt = 1
        Given("A statement with a scoped argument") { assertEquals(2, localInt + 1) }

        Given("I will give you {int} and {float} and {word} and {int}") { a: Int, b: Float, c: String, d: Int ->
            assertEquals(1, a)
            assertEquals(2.2f, b)
            assertEquals("three", c)
            assertEquals(4, d)
        }
    }
}

data class Person(val first: String?, val last: String?)