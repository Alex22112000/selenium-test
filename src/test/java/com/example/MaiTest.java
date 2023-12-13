package com.example;

import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MaiTest {
    private WebDriver driver;
    WebElement checkButton;

    private void delay(int seconds){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    private WebElement find(String xpath){
        return driver.findElement(By.xpath(xpath));
    }

    private void inputEmail(String email){
        WebElement inputField = find("//input[@name='username']");
        inputField.clear();
        inputField.sendKeys(email);
        WebElement nextButton = find("//*[@data-test-id='next-button']");
        nextButton.click();
    }

    private void inputPassword(String password){
        WebElement inputField = find("//input[@name='password']");
        inputField.clear();
        inputField.sendKeys(password);
        WebElement nextButton = find("//button[@data-test-id='submit-button']");
        nextButton.click();
    }
    
    private void openProfileMenu(){
        WebElement profile = find("//*[@class='ph-project__user-icon-mask svelte-1osmzf1']");
        profile.click();
    }

    private void checkProfileName(String name){
        String nameField = find("//*[@class='ph-name svelte-1popff4']").getText();
        Assert.assertEquals(name, nameField);
    }

    private void exit(){
        WebElement exitButton = find("//*[@data-testid='whiteline-account-exit']");
        exitButton.click();
    }

    private void checkExit(){
        checkButton = find("//*[@class='resplash-btn resplash-btn_outlined-themed resplash-btn_mailbox-big caa__dcaf__de8k2m']");
        if(!checkButton.isDisplayed()) Assert.fail();
    }
    
    @Test
    public void FirstTest(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alex\\Desktop\\Selenium\\selenium-test\\src\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 1025));

        driver.get("https://account.mail.ru/");

        delay(15);
        inputEmail("professionaltesteremailnow.ru");
        delay(15);

        inputPassword("toptester123");
        delay(15);
        
        openProfileMenu();
        delay(120);
        checkProfileName("Тестич Тестовый");
        delay(30);

        exit();
        checkExit();
    }
    
}
