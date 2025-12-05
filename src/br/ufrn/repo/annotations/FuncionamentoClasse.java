package br.ufrn.repo.annotations;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)
public @interface FuncionamentoClasse {
	String funcionamento();

}
