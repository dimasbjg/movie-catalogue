package com.example.moviecatalogue.data

import com.example.moviecatalogue.R

object DataMovie {

    private val title = arrayOf(
        "Cars",
        "Finding Nemo",
        "Forrest Gump",
        "Godzilla King of the Monster",
        "Godzolla vs Kong",
        "Mortal Kombat",
        "Shaolin Soccer",
        "3 Idiots",
        "UP",
        "Your Name"
    )

    private val description = arrayOf(
        "A hot-shot race-car named Lightning McQueen gets waylaid in Radiator Springs, where he finds the true meaning of friendship and family.",
        "After his son is captured in the Great Barrier Reef and taken to Sydney, a timid clownfish sets out on a journey to bring him home.",
        "The presidencies of Kennedy and Johnson, the Vietnam War, the Watergate scandal and other historical events unfold from the perspective of an Alabama man with an IQ of 75, whose only desire is to be reunited with his childhood sweetheart.",
        "The crypto-zoological agency Monarch faces off against a battery of god-sized monsters, including the mighty Godzilla, who collides with Mothra, Rodan, and his ultimate nemesis, the three-headed King Ghidorah.",
        "The epic next chapter in the cinematic Monsterverse pits two of the greatest icons in motion picture history against one another - the fearsome Godzilla and the mighty Kong - with humanity caught in the balance.",
        "MMA fighter Cole Young seeks out Earth\'s greatest champions in order to stand against the enemies of Outworld in a high stakes battle for the universe.",
        "A young Shaolin follower reunites with his discouraged brothers to form a soccer team using their martial art skills to their advantage.",
        "Two friends are searching for their long lost companion. They revisit their college days and recall the memories of their friend who inspired them to think differently, even as the rest of the world called them \"idiots\".",
        "78-year-old Carl Fredricksen travels to Paradise Falls in his house equipped with balloons, inadvertently taking a young stowaway.",
        "Two strangers find themselves linked in a bizarre way. When a connection forms, will distance be the only thing to keep them apart?"
    )

    private val imgPoster = arrayOf(
        R.drawable.poster_cars,
        R.drawable.poster_finding_nemo,
        R.drawable.poster_forrest_gump,
        R.drawable.poster_godzilla_king_of_the_monster,
        R.drawable.poster_godzilla_vs_kong,
        R.drawable.poster_mortal_kombat,
        R.drawable.poster_shaolin_soccer,
        R.drawable.poster_three_idiots,
        R.drawable.poster_up,
        R.drawable.poster_your_name
    )

    private val directior = arrayOf(
        "John Lasseter, Joe Ranft (co-director)",
        "Andrew Stanton, Lee Unkrich (co-director)",
        "Robert Zemeckis",
        "Michael Dougherty",
        "Adam Wingard",
        "Simon McQuoid",
        "Stephen Chow",
        "Rajkumar Hirani",
        "Pete Docter, Bob Peterson (co-director)",
        "Makoto Shinkai"
    )

    private val writter = arrayOf(
        "John Lasseter, Joe Ranft (co-director)",
        "Andrew Stanton, Lee Unkrich (co-director)",
        "Robert Zemeckis",
        "Michael Dougherty",
        "Adam Wingard",
        "Simon McQuoid",
        "Stephen Chow",
        "Rajkumar Hirani",
        "Pete Docter, Bob Peterson (co-director)",
        "Makoto Shinkai"
    )

    private val starring = arrayOf(
        "Owen Wilson, Bonnie Hunt, Paul Newman",
        "Albert Brooks, Ellen DeGeneres, Alexander Gould",
        "Tom Hanks, Robin Wright, Gary Sinise",
        "Kyle Chandler, Vera Farmiga, Millie Bobby Brown",
        "Alexander Skarsgård, Millie Bobby Brown, Rebecca Hall",
        "Lewis Tan, Jessica McNamee, Josh Lawson",
        "Stephen Chow, Wei Zhao, Yat-Fei Wong",
        "Aamir Khan, Madhavan, Mona Singh",
        "Edward Asner, Jordan Nagai, John Ratzenberger",
        "Ryûnosuke Kamiki, Mone Kamishiraishi, Ryô Narita"
    )

    private val ratting = arrayOf(
        "7.1/10",
        "8.1/10",
        "8.8/10",
        "6.0/10",
        "6.5/10",
        "7.0/10",
        "7.3/10",
        "8.4/10",
        "8.2/10",
        "8.4/10"
    )

    val listData: ArrayList<MovieEntity>
        get() {
            val list = arrayListOf<MovieEntity>()
            for (position in title.indices) {
                val movieEntity = MovieEntity(
                    title = title[position],
                    description = description[position],
                    director = directior[position],
                    imgPoster = imgPoster[position],
                    writter = writter[position],
                    starring = starring[position],
                    rating = ratting[position]
                )

                list.add(movieEntity)
            }
            return list
        }


}