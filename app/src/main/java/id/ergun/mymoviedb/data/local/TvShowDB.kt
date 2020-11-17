package id.ergun.mymoviedb.data.local

import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.local.model.TvShow
import javax.inject.Inject

/**
 * Created by alfacart on 21/10/20.
 */

class TvShowDB @Inject constructor()  {

    fun getTvShows(): MutableList<TvShow> {
        return getDummyTvShows()
    }

    private fun getDummyTvShows(): MutableList<TvShow> {
        val list: MutableList<TvShow> = mutableListOf()
        list.add(
            TvShow(
                id = 1412,
                name = "Arrow",
                image = R.drawable.tv_poster_arrow,
                overview = "After being double-crossed by Lyla, Oliver finds himself facing a life or death situation that seems very familiar. Laurel has the opportunity to make amends with the past.",
                voteAverage = 6.5,
                tagLine = "Heroes fall. Legends rise."
            )
        )
        list.add(
            TvShow(
                id = 82856,
                name = "The Mandalorian",
                image = R.drawable.tv_poster_the_mandalorian,
                overview = "After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.",
                voteAverage = 8.5,
                tagLine = "Bounty hunting is a complicated profession."
            )
        )
        list.add(
            TvShow(
                id = 46261,
                name = "The Good Doctor",
                image = R.drawable.tv_poster_the_good_doctor,
                overview = "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives?",
                voteAverage = 8.6,
                tagLine = "His mind is a mystery, his methods are a miracle."
            )
        )
        TvShow(
            id = 1434,
            name = "Family Guy",
            image = R.drawable.tv_poster_family_guy,
            overview = "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
            voteAverage = 6.8,
            tagLine = "Parental Discretion Advised, that's how you know it's good"
        )
        list.add(
            TvShow(
                id = 60735,
                name = "Flash",
                image = R.drawable.tv_poster_flash,
                overview = "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                voteAverage = 7.6,
                tagLine = "The fastest man alive."
            )
        )
        list.add(
            TvShow(
                id = 60708,
                name = "Gotham",
                image = R.drawable.tv_poster_gotham,
                overview = "Before there was Batman, there was GOTHAM. Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                voteAverage = 7.4,
                tagLine = "Before Batman, there was Gotham."
            )
        )
        list.add(
            TvShow(
                id = 1416,
                name = "Grey Anatomy",
                image = R.drawable.tv_poster_grey_anatomy,
                overview = "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                voteAverage = 8.0,
                tagLine = "The life you save may be your own."
            )
        )
        list.add(
            TvShow(
                id = 37854,
                name = "One Piece",
                image = R.drawable.tv_poster_one_piece,
                overview = "Years ago, the fearsome pirate king Gol D. Roger was executed, leaving a huge pile of treasure and the famous \"One Piece\" behind. Whoever claims the \"One Piece\" will be named the new pirate king. Monkey D. Luffy, a boy who consumed one of the \"Devil's Fruits\", has it in his head that he'll follow in the footsteps of his idol, the pirate Shanks, and find the One Piece. It helps, of course, that his body has the properties of rubber and he's surrounded by a bevy of skilled fighters and thieves to help him along the way. Monkey D. Luffy brings a bunch of his crew followed by, Roronoa Zoro, Nami, Usopp, Sanji, Tony-Tony Chopper, Nico Robin, Franky, and Brook. They will do anything to get the One Piece and become King of the Pirates!..",
                voteAverage = 8.0,
                tagLine = "The Mermen have gone too far!"
            )
        )
        list.add(
            TvShow(
                id = 68507,
                name = "His Dark Materials",
                image = R.drawable.tv_poster_his_dark_materials,
                overview = "Lyra is an orphan who lives in a parallel universe in which science, theology and magic are entwined. Lyra's search for a kidnapped friend uncovers a sinister plot involving stolen children, and turns into a quest to understand a mysterious phenomenon called Dust. She is later joined on her journey by Will, a boy who possesses a knife that can cut windows between worlds. As Lyra learns the truth about her parents and her prophesied destiny, the two young people are caught up in a war against celestial powers that ranges across many worlds.",
                voteAverage = 8.1,
                tagLine = "One girl will change worlds."
            )
        )
        list.add(
            TvShow(
                id = 62286,
                name = "Fear the Walking Dead",
                image = R.drawable.tv_poster_fear_the_walking_dead,
                overview = "What did the world look like as it was transforming into the horrifying apocalypse depicted in \"The Walking Dead\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.",
                voteAverage = 7.4,
                tagLine = "Every decision is life or death."
            )
        )
        list.add(
            TvShow(
                id = 63174,
                name = "Lucifer",
                image = R.drawable.tv_poster_lucifer,
                overview = "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                voteAverage = 8.5,
                tagLine = "It's good to be bad."
            )
        )
        return list
    }
}