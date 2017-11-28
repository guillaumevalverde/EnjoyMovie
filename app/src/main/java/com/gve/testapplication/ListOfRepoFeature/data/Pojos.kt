package com.gve.testapplication.ListOfRepoFeature.data

/**
 * Created by gve on 28/11/2017.
 */

data class OwnerRaw(
    val login: String,
    val id: Long,
    val avatar_url: String?,
    val avatar_id: String?,
    val url: String?,
    val html_url: String?,
    val followers_url: String?,
    val following_url: String?,
    val gists_url: String?,
    val starred_url: String?,
    val subscriptions_url: String?,
    val organizations_url: String?,
    val epos_url: String?,
    val events_url: String?,
    val received_events_url: String?,
    val type: String?,
    val site_admin: Boolean?)

data class RepoRaw(
        val id: Long,
        val name: String?,
        val owner: OwnerRaw,
        val fork: Boolean,
        val description: String?,
        val url: String?,
        val html_url: String?)


data class Repository(val id: Long,
                val name: String,
                val description: String,
                val loginOwner: String,
                val repoUrl: String?,
                val ownerUrl: String?,
                val fork: Boolean)