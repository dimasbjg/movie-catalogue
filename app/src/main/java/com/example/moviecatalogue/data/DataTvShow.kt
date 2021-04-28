package com.example.moviecatalogue.data

import com.example.moviecatalogue.R

object DataTvShow {

    private val title = arrayOf(
        "Band of Brothers",
        "Blue Planet II",
        "Breaking Bad",
        "Chernobyl",
        "Cosmos",
        "Cosmos: A Spacetime Odyssey",
        "Our Planet",
        "Planet Earth",
        "Planet Earth II",
        "The Wire"
    )

    private val description = arrayOf(
        "The story of Easy Company of the U.S. Army 101st Airborne Division, and their mission in World War II Europe, from Operation Overlord, through V-J Day.",
        "David Attenborough returns to the world's oceans in this sequel to the acclaimed documentary filming rare and unusual creatures of the deep, as well as documenting the problems our oceans face.",
        "A high school chemistry teacher diagnosed with inoperable lung cancer turns to manufacturing and selling methamphetamine in order to secure his family's future.",
        "In April 1986, an explosion at the Chernobyl nuclear power plant in the Union of Soviet Socialist Republics becomes one of the world's worst man-made catastrophes.",
        "Astronomer Carl Sagan leads us on an engaging guided tour of the various elements and cosmological theories of the universe.",
        "An exploration of our discovery of the laws of nature and coordinates in space and time.",
        "Documentary series focusing on the breadth of the diversity of habitats around the world, from the remote Arctic wilderness and mysterious deep oceans to the vast landscapes of Africa and diverse jungles of South America.",
        "Emmy Award-winning, 11 episodes, five years in the making, the most expensive nature documentary series ever commissioned by the BBC, and the first to be filmed in high definition.",
        "Wildlife documentary series with David Attenborough, beginning with a look at the remote islands which offer sanctuary to some of the planet's rarest creatures, to the beauty of cities, which are home to humans, and animals..",
        "The Baltimore drug scene, as seen through the eyes of drug dealers and law enforcement.",
    )

    private val imgPoster = arrayOf(
        R.drawable.poster_band_of_brothers,
        R.drawable.poster_blue_planet_ii,
        R.drawable.poster_breaking_bad,
        R.drawable.poster_chernobyl,
        R.drawable.poster_cosmos,
        R.drawable.poster_cosmos_a_spacetime_odyssey,
        R.drawable.poster_our_planet,
        R.drawable.poster_planet_earth,
        R.drawable.poster_planet_earth_ii,
        R.drawable.poster_the_wire
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

    val listData: ArrayList<TvShowEntity>
        get() {
            val list = arrayListOf<TvShowEntity>()
            for (position in title.indices) {
                val tvShowEntity = TvShowEntity(
                    title = title[position],
                    description = description[position],
                    imgPoster = imgPoster[position],
                    starring = starring[position],
                    rating = ratting[position]
                )

                list.add(tvShowEntity)
            }
            return list
        }

}