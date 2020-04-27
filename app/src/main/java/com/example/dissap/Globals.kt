package com.example.dissap

class Global {

    /**
     * Global variable store.
     */
    companion object Variables {

        var appDB: AppDB? = null //Initialize Database.
        var loggedUser: User? = null
        //var waypoint :Waypoint? = null
        //var foundWayPoints :MutableList<Waypoint>? = ArrayList<Waypoint>()
        var gameOver :Boolean = false


        /**
         * Initalize game global variables for a fresh game.
         */
        fun initGameVariables() {

            //randSong = null
           // foundWayPoints = ArrayList<Waypoint>()
            gameOver = false

        }
    }
}