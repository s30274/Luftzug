package pl.edu.pjatk.luftzug.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class ViewAllPage {
    private WebDriver webDriver;

    @FindBy(tagName = "h1")
    private WebElement header;

    @FindBy(id="alltable")
    private WebElement tableElement;
    private List<WebElement> rows = new ArrayList<>();

    public ViewAllPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        this.rows = tableElement.findElements(By.tagName("tr"));
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
}