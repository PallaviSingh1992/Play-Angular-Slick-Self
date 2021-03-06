package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import repository.InternRepo
import scala.concurrent.ExecutionContext.Implicits.global
import repository.Interns

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(service:InternRepo) extends Controller {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def list = Action.async { implicit request =>
    val list = service.getAll()
    list.map {
      list => Ok(views.html.interns(list))
    }
  }

}
