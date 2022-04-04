package com.contentside.tech.domain

import org.springframework.stereotype.Service

@Service
class DictionaryService {

    fun getDictionary(name: String) = Dictionary(
        "toto1",
        listOf(
            Entity(
                "toto1", listOf(
                    Variant("toto11"),
                    Variant("toto12")
                )
            )
        )
    )

    fun getVariant(name: String) = println("Recherche de la variante $name")

    fun saveDictionary(dictionary: Dictionary) {
        println("Saving $dictionary")
    }

    fun deleteDictionary(id: String) {
        println("Suppression du dictionnaire $id")
    }

}