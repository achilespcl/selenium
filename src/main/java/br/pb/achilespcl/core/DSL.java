package br.pb.achilespcl.core;
import java.util.List;
import static br.pb.achilespcl.core.DriverFactory.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;


public class DSL {
	

	public WebElement findById(String _id){
		return getDriver().findElement(By.id(_id));
	}
	
	public WebElement findByTag(String tagName){
		return getDriver().findElement(By.tagName(tagName));
	}
	
	public WebElement findByClass(String className){
		return getDriver().findElement(By.className(className));
	}
	
	public WebElement findByXpath(String xpath){
		return getDriver().findElement(By.xpath(xpath));
	}

	public void write(String _id, String text){
		findById(_id).sendKeys(text);
	}
	
	public String getValue(String _id){
		return findById(_id).getAttribute("value");
	}
	
	public String getText(String _id){
		return findById(_id).getText();
	}
	
	public String getTextByXpath(String xpath){
		return findByXpath(xpath).getText();
	}
	
	public void click(String _id){
		findById(_id).click();
	}
	
	public boolean isChecked(String _id){
		return findById(_id).isSelected();
	}
	
	public void comboSelect(String _id, String search){
		WebElement element = findById(_id);
		Select combo = new Select(element);
		combo.selectByVisibleText(search);	
	}
	
	public String getSelected(String _id){
		WebElement element = findById(_id);
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public void goToLink(String link){
		getDriver().findElement(By.linkText(link)).click();
	}
	
	public void clickBtnTable(String searchColumn, String value, String buttonColumn, String tableId){
		//Procurar coluna do registro
		WebElement table = getDriver().findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
		int column = getColumnIndex(searchColumn, table);
		
		//Procurar a linha do registro
		int row = getRowIndex(value, table, column);
		
		//Procurar coluna do botão
		int button = getColumnIndex(buttonColumn, table);
		
		//clicar botão da célula encontrada
		WebElement cell = table.findElement(By.xpath(".//tr["+row+"]/td["+button+"]"));
		cell.findElement(By.xpath(".//input")).click();
	}

	private int getRowIndex(String value, WebElement table, int columnId) {
		List<WebElement> rows = table.findElements(By.xpath("./tbody/tr/td["+columnId+"]"));
		int rowId = -1;
		for (int i = 0; i < rows.size(); i++) {
			if(rows.get(i).getText().equals(value)){
				rowId = i+1;
				break;
			}
		}
		return rowId;
	}

	private int getColumnIndex(String column, WebElement table) {
		List<WebElement> columns = table.findElements(By.xpath(".//th"));
		
		int columnId = -1;
		for (int i = 0; i < columns.size(); i++) {
			if(columns.get(i).getText().equals(column)){
				columnId = i+1;
				break;
			}
		}
		return columnId;
		
	}
}
