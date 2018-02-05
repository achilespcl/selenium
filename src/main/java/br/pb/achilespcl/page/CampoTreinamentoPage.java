package br.pb.achilespcl.page;
import br.pb.achilespcl.core.BasePage;
import br.pb.achilespcl.exception.SexInvalidException;


public class CampoTreinamentoPage extends BasePage{
	
	
	private final String NAME_ID, SURNAME_ID, SEX_M, SEX_F,
							FAVORITE_FOOD, SCHOOLING, SPORTS, SUBMIT;
	
	public CampoTreinamentoPage() {
				
		/**************Setting elements id*********************/
		NAME_ID       =  "elementosForm:nome";
		SURNAME_ID    =  "elementosForm:sobrenome";
		SEX_M         =  "elementosForm:sexo:0";
		SEX_F         =  "elementosForm:sexo:1";
		FAVORITE_FOOD =  "elementosForm:comidaFavorita:";
		SCHOOLING     =  "elementosForm:escolaridade";
		SPORTS        =  "elementosForm:esportes";
		SUBMIT        =  "elementosForm:cadastrar";
	}
	
	public void setName(String name){
		dsl.write(NAME_ID, name);
	}

	public void setSurname(String surname) {
		dsl.write(SURNAME_ID, surname);
	}
	
	public void setSex(char sex) throws SexInvalidException{
		if (sex == 'm') {
			 dsl.click(SEX_M);
		} else if (sex == 'f'){
			 dsl.click(SEX_F);
		} else {
			throw new SexInvalidException("Sexo Inválido!");
		}
	}
	
	public void setFavoriteFood(String food){
		dsl.click(FAVORITE_FOOD+food);
	}
	
	public void setSchooling(String schooling){
		dsl.comboSelect(SCHOOLING, schooling);
	}
	
	public void setSports(String sport){
		dsl.comboSelect(SPORTS, sport);
	}

	public void submit() {
		dsl.click(SUBMIT);
	}
}
