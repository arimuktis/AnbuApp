package com.arimukti.anbuapp.data.model


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("author")
    val author: String, // garethmb
    @SerializedName("author_details")
    val authorDetails: AuthorDetails,
    @SerializedName("content")
    val content: String, // The artists at Disney and Pixar have returned with a new animated film “Luca” which will debut on Disney+ on June 18th.The film introduces audiences to Luca (Jacob Tremblay); a young Sea Monster who lives with his family and fellow fish off the coast of a small town in Italy. Luca is warned not to go on the surface and to avoid humans at all costs by his parents.When a rebellious Sea Monster named Alberto (Jack Dylan Grazer); happens upon Luca and gets him to the surface; Luca discovers that he and Alberto appear as human boys when their skin is no longer wet.Fascinated by the rebellious life Alberto leads and amazed by what he sees upon his brief land excursions; Luca looks for new ways to find time on the surface which leads to him being more and more deceptive to his parents.In time Luca and Alberto head to the village and meet a spunky local girl named Giulia (Emma Berman); who is on a break from school and longs to win a local race in order to get back at the reigning champion and bully.Luca and Alberto see the race as a chance to win money to buy their own Vespa which they see as the key to exploring the surface world which sets a chain of events into motion as their two worlds are about to collide leading up to the race.The film is visually appealing, but the story for me dragged and did not have the spark and heart that have made countless PIXAR films enduring classics. While the characters were fine; they did not have the appeal or charisma that I have come to expect with the PIXAR brand.There have been reports that after “SOUL” was moved from a theatrical release to streaming during the Pandemic that some at PIXAR were upset with the decision to make “Luca” a streaming option.  My take is that it was 100% the correct decision as while it is an entertaining film; it is not one that is likely to light up the Box Office and is better suited for a streaming debut.The biggest issue with “Luca” is that coming from a studio with such a long line of classics; it fails to reach the levels previously set and while entertaining comes up lacking.3 stars out of 5
    @SerializedName("created_at")
    val createdAt: String, // 2021-06-16T16:08:48.678Z
    @SerializedName("id")
    val id: String, // 60ca22102fccee003fb40e51
    @SerializedName("updated_at")
    val updatedAt: String, // 2021-06-23T15:58:56.937Z
    @SerializedName("url")
    val url: String // https://www.themoviedb.org/review/60ca22102fccee003fb40e51
)