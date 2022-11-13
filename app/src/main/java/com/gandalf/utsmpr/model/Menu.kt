package com.gandalf.utsmpr.model

class Menu {
    var id = 0
    var imageRes = 0
    var title: String? = null
    var activity: String? = null

    constructor()
    constructor(id: Int, imageRes: Int, title: String?, activity: String?) {
        this.id = id
        this.imageRes = imageRes
        this.title = title
        this.activity = activity
    }
}