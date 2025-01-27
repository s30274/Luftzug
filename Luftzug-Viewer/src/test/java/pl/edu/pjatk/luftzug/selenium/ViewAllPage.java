package pl.edu.pjatk.luftzug.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ViewAllPage {
    private WebDriver webDriver;

    @FindBy(tagName = "h1")
    private WebElement header;

    @FindBy(id="alltable")
    private WebElement tableElement;

    private List<WebElement> rows = new ArrayList<>();

    private List<WebElement> editButtons = new ArrayList<>();

    private List<WebElement> deleteButtons = new ArrayList<>();

    public ViewAllPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isHeaderVisible() {
        return this.header.isDisplayed();
    }

    public ViewAllPage open() {
        this.webDriver.get("http://localhost:8082/view/all");
        return this;
    }

    public List<String> getData() {
        List<String> result = new ArrayList<>();
        StringBuilder tmp;
        this.rows = tableElement.findElements(By.tagName("tr"));
        for (WebElement rowElement : rows) {
            List<WebElement> cells = rowElement.findElements(By.tagName("td"));

            tmp = new StringBuilder();

            for (WebElement cellElement : cells) {
                String cellData = cellElement.getText();
                tmp.append(cellData).append(" ");
            }

            result.add(String.valueOf(tmp).trim());
        }

        result.removeFirst();

        return result;
    }

    public EditFormPage editLastSchedule(){
        this.editButtons = tableElement.findElements(By.id("edit"));
        this.editButtons.getLast().click();
        return new EditFormPage(webDriver);
    }

    public ViewAllPage deleteLastSchedule(){
        this.deleteButtons = tableElement.findElements(By.id("delete"));
        this.deleteButtons.getLast().click();
        return this;
    }
}