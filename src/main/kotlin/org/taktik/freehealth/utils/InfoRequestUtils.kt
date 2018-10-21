package org.taktik.freehealth.utils

import org.taktik.freehealth.middleware.dto.InfoRequest.OutputReferences

public class InfoRequestUtils {

    private fun getTextFromRegex(source:String, regex:String): String{
        val regexMather = regex.toRegex();
        val matchResult = regexMather.find(source.toString())
        return matchResult!!.groups[1]!!.value;
    }

    public fun getCommonOutput(source:String): String{
        try{
            return getTextFromRegex(source, """<.+:CommonOutput>(.*)</.+:CommonOutput>""");
        }catch (e:NullPointerException){
            return getTextFromRegex(source, """<CommonOutput>(.*)</CommonOutput>""");
        }
    }

    public fun getInputReference(source:String): String{
        try{
            return getTextFromRegex(source, """<.+InputReference>(.*)</.+InputReference>""");
        }catch (e:NullPointerException){
            return getTextFromRegex(source, """<InputReference>(.*)</InputReference>""");
        }
    }

    public fun getOutputReference(source:String): String{
        try{
            return getTextFromRegex(source, """<.+OutputReference>(.*)</.+OutputReference>""");
        }catch (e:NullPointerException){
            return getTextFromRegex(source, """<OutputReference>(.*)</OutputReference>""");
        }
    }

    public fun getNIPReference(source:String): String{
        try{
            return getTextFromRegex(source, """<.+NIPReference>(.*)</.+NIPReference>""");
        }catch (e:NullPointerException){
            return getTextFromRegex(source, """<NIPReference>(.*)</NIPReference>""");
        }
    }

    public fun getOutputReferences(source:String): OutputReferences{
        val outputReferences = OutputReferences();
        var commonOutput = getCommonOutput(source);
        outputReferences.inputReference = getInputReference(commonOutput);
        outputReferences.outputReference = getOutputReference(commonOutput);
        outputReferences.nipReference = getNIPReference(commonOutput);
        return outputReferences;
    }

}
