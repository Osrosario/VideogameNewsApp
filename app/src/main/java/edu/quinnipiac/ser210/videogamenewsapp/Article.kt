package edu.quinnipiac.ser210.videogamenewsapp

/**
 * @author Michael Ruocco, Omar Rosario
 * @date 4/3/2023
 *
 * A data class to assign data retrieved from the API service.
 */

data class Article (
    val title: String?,
    val date: String?,
    val image: String?,
    val description: String?,
    val link: String?
)
