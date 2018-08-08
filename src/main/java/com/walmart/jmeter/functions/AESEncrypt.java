package com.walmart.jmeter.functions;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;
import org.apache.jmeter.threads.JMeterVariables;

public class AESEncrypt extends AbstractFunction {
	public static String functionName = "__encryptAES";
	private static final List<String> desc = new LinkedList<String>();

	static {
	  desc.add("String");
	  desc.add("SecretKey");
	  desc.add("Name of variable in which to store the result (optional)");
	}
	
	private Object[] values;
	
	public List<String> getArgumentDesc() {
		return desc;
	}

	@Override
	public String execute(SampleResult previousResult, Sampler currentSampler) throws InvalidVariableException {
		JMeterVariables vars = getVariables();

		String strToEncrypt = ((CompoundVariable) values[0]).execute().trim();  //parameter 1
		String secret = ((CompoundVariable) values[1]).execute().trim();  //parameter 2

		String result = AES.encrypt(strToEncrypt, secret);

		//user might want the result in a variable
		if( null!=vars && values.length>2){
			String userVariable = ((CompoundVariable) values[2]).execute().trim();
			vars.put(userVariable, result);  //store the result in the user defined variable
		}
		return result;
	}

	@Override
	public void setParameters(Collection<CompoundVariable> parameters) throws InvalidVariableException {
		values = parameters.toArray();
	}

	@Override
	public String getReferenceKey() {
		return functionName;
	}

}
