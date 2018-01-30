package selenium_;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Start {
	
	WebDriver driver = new ChromeDriver();
	Actions action = new Actions(driver);
	
	public boolean connect() {
		try { driver.get("https://www.naver.com"); }
		catch (Exception e) { return false; }
		return true;
	}
	
	public boolean Login(String id, String password){
		try { 
			driver.findElement(By.name("id")).sendKeys(id);
			driver.findElement(By.name("pw")).sendKeys(password);
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(1000);
			
			try { driver.findElement(By.className("mask")); } 
			catch (Exception e) { return false; }
				
		} catch (Exception e1) {
			return false;
		}
		return true;
	}
	
	public boolean Send_write(String recv, String point, String title, String content) {
		try {
			driver.get("https://mail.naver.com");
			Thread.sleep(1000);
			driver.findElement(By.linkText("메일쓰기")).click();
			Thread.sleep(1000);
			driver.findElement(By.className("addr_input")).click();
			action.sendKeys(recv).perform();
			
			action.sendKeys(Keys.TAB).perform();
			action.sendKeys(point).perform();
			
			driver.findElement(By.id("subject")).sendKeys(title);
			action.sendKeys(Keys.TAB).perform();
			action.sendKeys(content).perform();
			
			driver.findElement(By.id("sendBtn")).click();
		
			return true;
		} catch (InterruptedException e) { return false; }
	}
	
	public void Result_Exit(String result) {
		JOptionPane.showConfirmDialog(null, result, 
				result, !(result.equals("발송성공")) ? JOptionPane.WARNING_MESSAGE : JOptionPane.PLAIN_MESSAGE); 
	}
	
}
