package com.ipt;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.List;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sample {
		public static void CheckURL(String href) throws IOException, URISyntaxException {
			URL url = new URI(href).toURL();
			URLConnection connection = url.openConnection();
			HttpURLConnection httpURLConnection=(HttpURLConnection) connection;
			httpURLConnection.setRequestMethod("HEAD");
			httpURLConnection.connect();
			
			int responseCode = httpURLConnection.getResponseCode();
			if(responseCode>=400) {
				System.out.println("URL" + href +" - With Error Code :" + responseCode);
			}
			else {
				System.out.println("URL" + href +" - With Success Code :" + responseCode);
		
			}			
		}
		
		public static void main(String[] args) throws IOException, URISyntaxException {
			
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://demoqa.com/broken");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			System.out.println("========Check AnchorTag==========");
			
			List<WebElement> alink = driver.findElements(By.tagName("a"));
			for(WebElement ele:alink) {
				@Nullable
				String url = ele.getDomProperty("href");
				if(url.trim().length()>=1) {
					CheckURL(url);
					
				}
	
			}
			List<WebElement> imageLinks = driver.findElements(By.tagName("img"));
			for(WebElement ele: imageLinks) {
				@Nullable
				String url = ele.getDomProperty("src");
				
				if(url.trim().length()>=1) {
					CheckURL(url);
					
				}				
			}
			driver.quit();
			System.out.println("====================Done=====================");
		
		}
		
}
