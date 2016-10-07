package controllers

import play.api._
import play.api.mvc._
import play.api.Play.current
import play.api.libs.json._
import play.api.libs.functional.syntax._

import collection.JavaConversions._

import twitter4j.TwitterFactory
import twitter4j.Paging
import twitter4j.Status
import twitter4j.Twitter
import twitter4j.conf.ConfigurationBuilder

object Application extends Controller {
  def index = Action {
    val cb = new ConfigurationBuilder()
    val page = new Paging (1, 50)
    cb.setDebugEnabled(false)
        .setOAuthConsumerKey("TRtT8qih3a6bSKcyXrjVtSdg8")
        .setOAuthConsumerSecret("fITOY2jQsWm4IpNid3ItloDlq0zoNyOBAMdmXgX92696rDoVN6")
        .setOAuthAccessToken("96369337-PLCiXx2WIUQynygg5VsfPkdd5DBwwLLSlfRUoTh9z")
        .setOAuthAccessTokenSecret("E2hViGNE5pi9S6Qgtf3FCZ2aSkSczM9qKagTMY1A6yWuF")

    val tf = new TwitterFactory(cb.build)
    val twitter = tf.getInstance
    val statuses = twitter.getUserTimeline("MakerFaireRome", page)
    val groups = statuses.groupBy( status => if (status.isRetweet) status.getRetweetedStatus.getUser.getScreenName else status.getUser.getScreenName)
    val result = groups.map(list => (list._1, list._2.size))
    val json = Json.toJson(result)
    Ok(json)
  }
}
