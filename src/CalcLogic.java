import java.awt.List;
import java.util.LinkedList;
import java.util.Vector;
public class CalcLogic {
	
	public CalcResult Calculate(String input){
		FloatWrapper value = new FloatWrapper();
		Perform(input,value);
		return new CalcResult(value.Value,CalcState);
	}
	
	private final int maxBufferLength = 256;
	private final int minBufferLength = 1;
	private PerformState CalcState = PerformState.COMPLETE;
	
	private void Perform(String input, FloatWrapper value)
    {
        String allowedChar = "1234567890+-=/()*^#%&$?.,";
        input = DeleteAllSpaces(input);
        PerformState status = IsCorrectLength(input) ? PerformState.COMPLETE : PerformState.ERROR;
        status = IsContainOnlyAllowedChar(input, allowedChar) ? PerformState.COMPLETE : PerformState.ERROR;
        status = IsCorrectBracketSequence(input) ? PerformState.COMPLETE : PerformState.ERROR;
        CalcState = status;
        value.Value = Calc(input);
        
    }
	
    private String BracketCalc(String input)
    {
        LinkedList<String> tokens = new LinkedList<String>();

        for (int i = input.length() - 1; i >= 0; i--)
        {
            if (input.charAt(i) == '(')
            {
                int endBracked = 0;
                for (int k = i; endBracked == 0; k++)
                {
                    if (input.charAt(k) == ')')
                    {
                        endBracked = k;
                    }
                }
                String token = input.substring(i, endBracked+1);
                
                String temp = "";

                for (int k = 0; k < i; ++k)
                {
                    temp += input.charAt(k);
                }
                temp += "@t"+tokens.size();
                for (int k = endBracked + 1; k < input.length(); ++k)
                {
                    temp += input.charAt(k);
                }
                input = temp;
                tokens.add(token);
            }
        }

        for (int i = 0; i < tokens.size(); ++i)
        {
            String tmp = tokens.get(i).substring(1, tokens.get(i).length()-1);
            for (int j = 0; j < tokens.size(); ++j)
            {
            	String replaceStr = tokens.get(j).replace("@t"+i, SimplyCalc(tmp)); 
                tokens.set(j, replaceStr);
            }
        }

        String result = tokens.get(tokens.size()-1);
        result = result.substring(1, result.length() - 1);
        result = SimplyCalc(result);

        return result;
    }

    private String ParseAndPerform(String input, char operationSymbol)
    {
        for (int i = 0; i < input.length() - 1; ++i)
        {
            if (input.charAt(i) == operationSymbol)
            {
                String temp = "";
                float leftOperand = 0;
                float rightOperand = 0;
                int k = 0;
                int j = 0;
                for (k = i - 1; k >= 0 && IsDigit(input.charAt(k)); --k)
                {
                    temp += input.charAt(k);
                }
                if (k >= 0)
                {
                    if (input.charAt(k) == '-')
                    {
                        temp += '-';
                        if (k > 0)
                        {
                            --k;
                        }
                    }
                }
                
                if (temp.length() > 0)
                {
                    temp = Reverse(temp);
                    leftOperand = Float.parseFloat(temp);
                    temp = "";
                }
                for (j = i + 1; j < input.length() && IsDigit(input.charAt(j)); ++j)
                {
                    temp += input.charAt(j);
                }
                if ( temp.length() > 0)
                {
                    rightOperand = Float.parseFloat(temp);
                }
                temp = "";
                if (k != 0)
                {
                    for (int t = 0; t <= k; ++t)
                    {
                        temp += input.charAt(t);
                    }
                }
                temp += PerformOperation(leftOperand, rightOperand, operationSymbol);
                for (int t = j; t < input.length(); ++t)
                {
                    temp += input.charAt(t);
                }
                input = temp;
            }
        }
        return input;
    }
	
    private String Reverse(String input)
    {
        String temp = "";
        for (int i = input.length()- 1; i >= 0; --i)
        {
            temp += input.charAt(i);
        }
        return temp;
    }

    private String SimplyCalc(String input)
    {    	
        String temp = input;
    	String checkTmp = temp;
    	while (!isResultComplete(temp))
        {
            temp = ParseAndPerform(temp, '$');
            temp = ParseAndPerform(temp, '&');
            temp = ParseAndPerform(temp, '%');
            temp = ParseAndPerform(temp, '?');
            temp = ParseAndPerform(temp, '#');
            temp = ParseAndPerform(temp, '^');
            temp = ParseAndPerform(temp, '/');
            temp = ParseAndPerform(temp, '*');
            temp = ParseAndPerform(temp, '-');
            temp = ParseAndPerform(temp, '+');
            if (temp.equals(checkTmp)){
            	if (temp.charAt(0) == '-'){
            		return temp;
            	}
            	CalcState = PerformState.ERROR;
            	return "0.0";
            }else{
            	checkTmp = temp;
            }
        }
        return temp;
    }

    private float PerformOperation(float lo, float ro, char operationSymbol)
    {
        switch (operationSymbol)
        {
            case '/': return lo / ro;
            case '*': return lo * ro;
            case '+': return lo + ro;
            case '-': return lo - ro;
            case '^': return (float)Math.pow(lo, ro);
            case '#': return (float)Math.sqrt(ro);     //sqrt
            case '$': return (float)Math.log(ro);      //log
            case '&': return (float)Math.sin(ro);      //sin
            case '%': return (float)Math.tan(ro);      //tan
            default: return 0;
        }
    }

    private float Calc(String input)
    {
        float result = 0;
        if (isContainsBracket(input))
        {
            try
            {
                String wrappedInput = '(' + input + ')';
                String tmp = BracketCalc(wrappedInput);
                result = Float.parseFloat(tmp);
            }
            catch (Exception ex)
            {
                result = 0;
                CalcState = PerformState.ERROR;
            }
        }
        else
        {
            try
            {
                String tmp = SimplyCalc(input);
                result = Float.parseFloat(tmp);
            }
            catch (Exception ex)
            {
                result = 0;
                CalcState = PerformState.ERROR;
            }
        }
        return result;
    }

    private boolean isResultComplete(String input)
    {
        String unavailable = "*+-^/#$%&?";
        for  (int i=0; i<input.length(); ++i)
        {
            if (unavailable.indexOf(input.charAt(i)) !=-1)
            {
                return false;
            }
        }
        return true;
    }
    
    private boolean isContainsBracket(String input)
    {
        for (int i=0; i<input.length(); ++i)
        {
            if (input.charAt(i) == '(' || input.charAt(i) == ')')
            {
                return true;
            }
        }
        return false;
    }
    
    private boolean IsDigit(char c)
    {
        String digits = "0123456789.,";
        if (digits.indexOf(c) != -1)
        {
            return true;
        }
        return false;
    }
    
    private boolean IsCorrectLength(String input)
    {
        return input.length() > minBufferLength && input.length() < maxBufferLength;
    }
    
    private boolean IsCorrectBracketSequence(String input)
    {
        int balance = 0;

        for (int i=0;i<input.length();++i)
        {
            if (input.charAt(i) == '(')
            {
                ++balance;
            }
            else if (input.charAt(i) == ')')
            {
                --balance;
            }
        }

        return balance == 0;
    }
    
    private boolean IsContainOnlyAllowedChar(String input, String allowedChar)
    {
        for (int i=0;i<input.length();++i)
        {
            if (allowedChar.indexOf(input.charAt(i)) == -1)
            {
                return false;
            }
        }
        return true;
    }

    private String DeleteAllSpaces(String input)
    {
        if (!input.contains(" "))
        {
            return input;
        }

        String temp = "";

        for (int i=0;i<input.length();++i)
        {
            if (input.charAt(i) != ' ')
            {
                temp += input.charAt(i);
            }
        }

        return temp;
    }

}
