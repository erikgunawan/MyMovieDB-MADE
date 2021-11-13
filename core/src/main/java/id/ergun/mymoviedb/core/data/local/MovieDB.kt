package id.ergun.mymoviedb.core.data.local

import id.ergun.mymoviedb.core.data.local.model.MovieLocal
import javax.inject.Inject

/**
 * Created by alfacart on 21/10/20.
 */
class MovieDB @Inject constructor() {

  fun getMovies(): MutableList<MovieLocal> {
    return getDummyMovies()
  }

  private fun getDummyMovies(): MutableList<MovieLocal> {
    val list: MutableList<MovieLocal> = mutableListOf()
    list.add(
        MovieLocal(
            id = 400160,
            title = "The SpongeBob Movie: Sponge on the Run",
            overview = "When his best friend Gary is suddenly snatched away, SpongeBob takes Patrick on a madcap mission far beyond Bikini Bottom to save their pink-shelled pal.",
            voteAverage = 8.2,
            tagLine = "They're Not in Bikini Bottom Anymore."
        )
    )
    list.add(
        MovieLocal(
            id = 297802,
            title = "Aquaman",
            overview = "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
            voteAverage = 6.9,
            tagLine = "Home Is Calling"
        )
    )
    list.add(
        MovieLocal(
            id = 299536,
            title = "Avengers: Infinity War",
            overview = "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
            voteAverage = 8.3,
            tagLine = "An entire universe. Once and for all."
        )
    )
    list.add(
        MovieLocal(
            id = 405774,
            title = "Bird Box",
            overview = "Five years after an ominous unseen presence drives most of society to suicide, a survivor and her two children make a desperate bid to reach safety.",
            voteAverage = 6.9,
            tagLine = "Never lose sight of survival"
        )
    )
    list.add(
        MovieLocal(
            id = 424694,
            title = "Bohemian Rapsody",
            overview = "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
            voteAverage = 8.0,
            tagLine = "Fearless lives forever"
        )
    )
    list.add(
        MovieLocal(
            id = 424783,
            title = "Bumblebee",
            overview = "On the run in the year 1987, Bumblebee finds refuge in a junkyard in a small Californian beach town. Charlie, on the cusp of turning 18 and trying to find her place in the world, discovers Bumblebee, battle-scarred and broken.  When Charlie revives him, she quickly learns this is no ordinary yellow VW bug.",
            voteAverage = 6.6,
            tagLine = "Every Hero Has a Beginning"
        )
    )
    list.add(
        MovieLocal(
            id = 480530,
            title = "Creed II",
            overview = "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
            voteAverage = 6.9,
            tagLine = "There's More to Lose than a Title"
        )
    )
    list.add(
        MovieLocal(
            id = 293660,
            title = "Deadpool",
            overview = "Deadpool tells the origin story of former Special Forces operative turned mercenary Wade Wilson, who after being subjected to a rogue experiment that leaves him with accelerated healing powers, adopts the alter ego Deadpool. Armed with his new abilities and a dark, twisted sense of humor, Deadpool hunts down the man who nearly destroyed his life.",
            voteAverage = 7.6,
            tagLine = "Witness the beginning of a happy ending"
        )
    )
    list.add(
        MovieLocal(
            id = 166428,
            title = "How to Train Your Dragon: The Hidden World",
            overview = "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
            voteAverage = 7.8,
            tagLine = "The friendship of a lifetime"
        )
    )
    list.add(
        MovieLocal(
            id = 503314,
            title = "Dragon Ball Super: Broly",
            overview = "Earth is peaceful following the Tournament of Power. Realizing that the universes still hold many more strong people yet to see, Goku spends all his days training to reach even greater heights. Then one day, Goku and Vegeta are faced by a Saiyan called 'Broly' who they've never seen before. The Saiyans were supposed to have been almost completely wiped out in the destruction of Planet Vegeta, so what's this one doing on Earth? This encounter between the three Saiyans who have followed completely different destinies turns into a stupendous battle, with even Frieza (back from Hell) getting caught up in the mix",
            voteAverage = 7.7,
            tagLine = "A new adventure to become the strongest begins."
        )
    )
    list.add(
        MovieLocal(
            id = 450465,
            title = "Glass",
            overview = "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
            voteAverage = 6.6,
            tagLine = "You Cannot Contain What You Are"
        )
    )
    list.add(
        MovieLocal(
            id = 399402,
            title = "Hunter Killer",
            overview = "Captain Glass of the USS Arkansas discovers that a coup d'état is taking place in Russia, so he and his crew join an elite group working on the ground to prevent a war.",
            voteAverage = 6.7,
            tagLine = "Courage runs deep"
        )
    )
    return list
  }
}