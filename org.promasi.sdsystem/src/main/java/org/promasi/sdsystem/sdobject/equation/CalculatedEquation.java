/**
 * 
 */
package org.promasi.sdsystem.sdobject.equation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.nfunk.jep.JEP;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;
import org.nfunk.jep.SymbolTable;
import org.nfunk.jep.function.PostfixMathCommandI;
import org.promasi.sdsystem.serialization.ISerializableEquation;
import org.promasi.utilities.exceptions.NullArgumentException;
import org.promasi.utilities.serialization.SerializationException;

/**
 * @author m1cRo
 *
 */
public class CalculatedEquation implements IEquation{
	/**
	 * 
	 */
	protected String _equationString;
	
	/**
     * The lower value of a double in order to be considered as 0.
     */
    private static final double CONST_ACTUAL_ZERO = 1.0 * Math.pow( Math.E, -6.0 );
	
    /**
     * 
     */
    private SymbolTable _symbolTable;
    
    /**
     * 
     */
    private JEP _jep;
    
    /**
     * A list with all the constant variables.
     */
    private List<String> _constantVariableNames;
    
    /**
     * The name of the pi variable as defined in JEP
     */
    private static final String CONST_PI_VARIABLE = "pi";
    
    /**
     * The name of the e variable as defined in JEP
     */
    private static final String CONST_E_VARIABLE = "e";
    
	/**
	 * 
	 * @param equationString
	 */
	public CalculatedEquation(final String equationString)throws NullArgumentException, IllegalArgumentException
	{
		if(equationString==null){
			throw new NullArgumentException("Wrong argument equationString==null");
		}
		
		_constantVariableNames = new ArrayList<String>();
		_constantVariableNames.add(CONST_PI_VARIABLE);
		_constantVariableNames.add(CONST_E_VARIABLE);
			
		_jep = new JEP( );
		_jep.addStandardFunctions( );
		_jep.addStandardConstants( );
	    _jep.addFunction( "Max", new PostfixMathCommandI() {

				@Override
				public int getNumberOfParameters() {
					return 2;
				}

				@Override
				public void run(Stack stack) throws ParseException {
			        double var1 = (Double) stack.pop( );
			        double var2 = (Double) stack.pop( );
			        stack.push( Math.max( var1, var2 ) );
				}

				@Override
				public void setCurNumberOfParameters(int arg0) {
					// TODO Auto-generated method stub
					
				};
	        
	        });

	   _jep.addFunction( "Min", new PostfixMathCommandI(){

				@Override
				public int getNumberOfParameters() {
					return 2;
				}

				@Override
				public void run(Stack stack) throws ParseException {
			        double var1 = (Double) stack.pop( );
			        double var2 = (Double) stack.pop( );
			        stack.push( Math.min( var1, var2 ) );
				}

				@Override
				public void setCurNumberOfParameters(int arg0) {
					// TODO Auto-generated method stub
					
				};
	        
	        });
	        
	  _jep.addFunction( "Zidz", new PostfixMathCommandI(){

				@Override
				public int getNumberOfParameters() {
					return 2;
				}

				@Override
				public void run(Stack stack) throws ParseException {
			        double var2 = (Double) stack.pop( );
			        double var1 = (Double) stack.pop( );

			        double val;
			        if ( var2 < CONST_ACTUAL_ZERO )
			        {
			            val = 0.0;
			        }
			        else
			        {
			            val = var1 / var2;
			        }
			        stack.push( val );
					
				}

				@Override
				public void setCurNumberOfParameters(int arg0) {
					// TODO Auto-generated method stub
					
				}
	        });

	  _jep.setAllowUndeclared( true );
	  _jep.parseExpression( equationString );
	  Node topNode = _jep.getTopNode();
	  if(topNode==null)
	  {
	       throw new IllegalArgumentException("Wrong argument equationString");
	  }
	        
	  _symbolTable = _jep.getSymbolTable( );
	  _equationString=equationString;
	}

	@Override
	public Double calculateEquation(Map<String, Double> systemValues)throws NullArgumentException, IllegalArgumentException, CalculationException, IllegalArgumentException {
		if(systemValues==null){
			throw new NullArgumentException("Wrong argument systemValues==null");
		}
		
		for(Map.Entry<String, Double> entry : systemValues.entrySet()){
			if(entry.getKey()==null || entry.getValue()==null){
				throw new IllegalArgumentException("Wrong argument systemValues contains null");
			}
		}
		
		try {			
			for(Object key : _symbolTable.keySet()){
				if(!_constantVariableNames.contains(key)) {
					if(systemValues.containsKey(key)){

						Double value= systemValues.get(key);
						if(value==null || value.isNaN()){
							value=0.0;
						}
						
						_jep.addVariable((String)key,value);
					}else{
						throw new IllegalArgumentException("Calculation failed value for object named " + key + " not found");
					}
				}
			}
			
			Double value = _jep.getValue();
			
			if(value.isNaN()){
				return 0.0;
			}
			
			return value;
		} catch (Exception e) {
			throw new CalculationException("Calculation error JEP failed");
		}
	}

	@Override
	public ISerializableEquation getSerializableEquation()throws SerializationException {
		return new SerializableCalculatedEquation(this);
	}
}
