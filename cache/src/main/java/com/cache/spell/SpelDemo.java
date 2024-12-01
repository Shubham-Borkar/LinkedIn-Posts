package com.cache.spell;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;



@Component
public class SpelDemo {
	
	@Value("#{T(com.cache.spell.UtilsClass).a}")
	private  int extractedA;
	
	@Value("#{systemProperties['my.property']}")
	private   String myProperty;
	
	@Value("#{T(java.lang.Math).max(200, 100)}")
	private  int maxFeatureValue;
	
	public  void testMethode() {
		System.err.println(new SpelDemo().extractedA);
		System.err.println(new SpelDemo().myProperty);
		System.err.println(new SpelDemo().maxFeatureValue);

	}
	public static void main(String[] args) {
        SpelExpressionParser parser = new SpelExpressionParser();

        StandardEvaluationContext context = new StandardEvaluationContext();

        Expression expression = parser.parseExpression("T(com.cache.spell.UtilsClass).a");
        int extractedA = expression.getValue(context, Integer.class);
        System.out.println("Value of extractedA: " + extractedA);
        
//        Expression expression2 = parser.parseExpression("systemProperties['my.property']");
//        String extractedA2 = expression2.getValue(context, String.class);
//        System.out.println("Value of extractedA: " + extractedA2);
	}
	

    public SpelDemo() {
        //System.out.println("SpelDemo bean is being instantiated by Spring.");
    }

}

class UtilsClass{
	public static int a;
	static{
		a=10;
	}
	
}