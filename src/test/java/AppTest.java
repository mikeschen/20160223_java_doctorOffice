import java.util.ArrayList;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Task list");
  }

  // @Test
  // public void checkIfTaskDisplayed() {
  //     goTo("http://localhost:4567/");
  //     fill("#description").with("Wash the dishes");
  //     submit(".btn");
  //     click("a", withText("Go Back"));
  //     assertThat(pageSource()).contains("Wash the dishes");
  // }
  @Test
  public void multipleTasksAreDisplayedTest() {
    goTo("http://localhost:4567/tasks/new");
    fill("#description").with("Wash the dishes");
    submit(".btn");
    click("a", withText("Go Back"));
    click("a", withText("Add a new task"));
    fill("#description").with("Buy groceries");
    submit(".btn");
    click("a", withText("Go Back"));
    click("a", withText("View all tasks"));
    assertThat(pageSource()).contains("Wash the dishes");
    assertThat(pageSource()).contains("Buy groceries");
  }
  // @Test
  // public void taskShowPageDisplaysDescription() {
  //   goTo("http://localhost:4567/tasks/new");
  //   fill("#description").with("Do the dishes");
  //   submit(".btn");
  //   click("a", withText("View tasks"));
  //   click("a", withText("Do the dishes"));
  //   assertThat(pageSource()).contains("Do the dishes");
  // }
  @Test
public void taskNotFoundMessageShown() {
  goTo("http://localhost:4567/tasks/999");
  assertThat(pageSource()).contains("Task not found");
}
}
