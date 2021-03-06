/* Generated By:JavaCC: Do not edit this line. RuleParserTokenManager.java */
package descryptor.jworldgen.parser;
import java.io.StringReader;
import java.util.Stack;
import java.util.LinkedList;
import descryptor.jworldgen.parser.parseStructure.*;
import descryptor.common.parser.parseStructure.*;
import descryptor.common.ale.ALEQueueElement;
import descryptor.common.ale.ALEElementType;
import descryptor.common.exceptionHandler.ExceptionLogger;
import descryptor.common.exceptionHandler.LoggerLevel;
import descryptor.common.exceptionHandler.exceptions.CriticalFailure;

/** Token Manager. */
public class RuleParserTokenManager implements RuleParserConstants
{

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x800000L) != 0L)
            return 10;
         if ((active0 & 0x2000000000000L) != 0L)
            return 1;
         if ((active0 & 0x1ff00003fff00L) != 0L)
         {
            jjmatchedKind = 52;
            return 2;
         }
         return -1;
      case 1:
         if ((active0 & 0x20000L) != 0L)
            return 2;
         if ((active0 & 0x1ff00003dff00L) != 0L)
         {
            if (jjmatchedPos != 1)
            {
               jjmatchedKind = 52;
               jjmatchedPos = 1;
            }
            return 2;
         }
         return -1;
      case 2:
         if ((active0 & 0x17800003dff00L) != 0L)
         {
            jjmatchedKind = 52;
            jjmatchedPos = 2;
            return 2;
         }
         if ((active0 & 0x870000000000L) != 0L)
            return 2;
         return -1;
      case 3:
         if ((active0 & 0x100000018fe00L) != 0L)
         {
            if (jjmatchedPos != 3)
            {
               jjmatchedKind = 52;
               jjmatchedPos = 3;
            }
            return 2;
         }
         if ((active0 & 0x780000250100L) != 0L)
            return 2;
         return -1;
      case 4:
         if ((active0 & 0x1000000000000L) != 0L)
            return 2;
         if ((active0 & 0x38fe00L) != 0L)
         {
            jjmatchedKind = 52;
            jjmatchedPos = 4;
            return 2;
         }
         return -1;
      case 5:
         if ((active0 & 0x38fe00L) != 0L)
         {
            jjmatchedKind = 52;
            jjmatchedPos = 5;
            return 2;
         }
         return -1;
      case 6:
         if ((active0 & 0x80200L) != 0L)
            return 2;
         if ((active0 & 0x30fc00L) != 0L)
         {
            jjmatchedKind = 52;
            jjmatchedPos = 6;
            return 2;
         }
         return -1;
      case 7:
         if ((active0 & 0x200c00L) != 0L)
            return 2;
         if ((active0 & 0x10f000L) != 0L)
         {
            if (jjmatchedPos != 7)
            {
               jjmatchedKind = 52;
               jjmatchedPos = 7;
            }
            return 2;
         }
         return -1;
      case 8:
         if ((active0 & 0x1000L) != 0L)
            return 2;
         if ((active0 & 0x10e800L) != 0L)
         {
            jjmatchedKind = 52;
            jjmatchedPos = 8;
            return 2;
         }
         return -1;
      case 9:
         if ((active0 & 0x10a000L) != 0L)
            return 2;
         if ((active0 & 0x4800L) != 0L)
         {
            jjmatchedKind = 52;
            jjmatchedPos = 9;
            return 2;
         }
         return -1;
      case 10:
         if ((active0 & 0x4800L) != 0L)
         {
            jjmatchedKind = 52;
            jjmatchedPos = 10;
            return 2;
         }
         return -1;
      case 11:
         if ((active0 & 0x4000L) != 0L)
            return 2;
         if ((active0 & 0x800L) != 0L)
         {
            jjmatchedKind = 52;
            jjmatchedPos = 11;
            return 2;
         }
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 32:
         jjmatchedKind = 1;
         return jjMoveStringLiteralDfa1_0(0xc0000000L);
      case 33:
         jjmatchedKind = 36;
         return jjMoveStringLiteralDfa1_0(0x20000000L);
      case 36:
         return jjStartNfaWithStates_0(0, 49, 1);
      case 37:
         return jjStopAtPos(0, 26);
      case 38:
         jjmatchedKind = 37;
         return jjMoveStringLiteralDfa1_0(0x400000000L);
      case 40:
         return jjStopAtPos(0, 58);
      case 41:
         return jjStopAtPos(0, 60);
      case 42:
         return jjStopAtPos(0, 24);
      case 43:
         return jjStopAtPos(0, 22);
      case 44:
         return jjStopAtPos(0, 59);
      case 45:
         return jjStartNfaWithStates_0(0, 23, 10);
      case 47:
         jjmatchedKind = 25;
         return jjMoveStringLiteralDfa1_0(0x20L);
      case 58:
         return jjStopAtPos(0, 27);
      case 59:
         return jjStopAtPos(0, 54);
      case 60:
         return jjMoveStringLiteralDfa1_0(0x300000000L);
      case 61:
         jjmatchedKind = 55;
         return jjMoveStringLiteralDfa1_0(0x10000000L);
      case 65:
         return jjMoveStringLiteralDfa1_0(0x200100L);
      case 66:
         return jjMoveStringLiteralDfa1_0(0x8000L);
      case 67:
         return jjMoveStringLiteralDfa1_0(0x104000L);
      case 68:
         return jjMoveStringLiteralDfa1_0(0x10000L);
      case 70:
         return jjMoveStringLiteralDfa1_0(0x2000L);
      case 77:
         return jjMoveStringLiteralDfa1_0(0x80c00L);
      case 83:
         return jjMoveStringLiteralDfa1_0(0x200L);
      case 84:
         return jjMoveStringLiteralDfa1_0(0x40000L);
      case 87:
         return jjMoveStringLiteralDfa1_0(0x1000L);
      case 97:
         return jjMoveStringLiteralDfa1_0(0xb80000000000L);
      case 99:
         return jjMoveStringLiteralDfa1_0(0x20000000000L);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x1000000000000L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x20000L);
      case 115:
         return jjMoveStringLiteralDfa1_0(0x410000000000L);
      case 116:
         return jjMoveStringLiteralDfa1_0(0x40000000000L);
      case 123:
         return jjStopAtPos(0, 56);
      case 124:
         jjmatchedKind = 38;
         return jjMoveStringLiteralDfa1_0(0x800000000L);
      case 125:
         return jjStopAtPos(0, 57);
      case 126:
         return jjStopAtPos(0, 39);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 32:
         if ((active0 & 0x100000000L) != 0L)
            return jjStopAtPos(1, 32);
         break;
      case 38:
         if ((active0 & 0x400000000L) != 0L)
            return jjStopAtPos(1, 34);
         break;
      case 42:
         if ((active0 & 0x20L) != 0L)
            return jjStopAtPos(1, 5);
         break;
      case 61:
         if ((active0 & 0x10000000L) != 0L)
            return jjStopAtPos(1, 28);
         else if ((active0 & 0x20000000L) != 0L)
            return jjStopAtPos(1, 29);
         else if ((active0 & 0x200000000L) != 0L)
            return jjStopAtPos(1, 33);
         break;
      case 62:
         if ((active0 & 0x40000000L) != 0L)
         {
            jjmatchedKind = 30;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0x80000000L);
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x40000009000L);
      case 98:
         return jjMoveStringLiteralDfa2_0(active0, 0x800000000000L);
      case 99:
         return jjMoveStringLiteralDfa2_0(active0, 0x100000000000L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x4000L);
      case 102:
         if ((active0 & 0x20000L) != 0L)
            return jjStartNfaWithStates_0(1, 17, 2);
         break;
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x100000L);
      case 105:
         return jjMoveStringLiteralDfa2_0(active0, 0x10000000000L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0x1000000002000L);
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0x20000080c00L);
      case 113:
         return jjMoveStringLiteralDfa2_0(active0, 0x400000000000L);
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0x210100L);
      case 115:
         return jjMoveStringLiteralDfa2_0(active0, 0x80000000000L);
      case 116:
         return jjMoveStringLiteralDfa2_0(active0, 0x200000000000L);
      case 117:
         return jjMoveStringLiteralDfa2_0(active0, 0x200L);
      case 121:
         return jjMoveStringLiteralDfa2_0(active0, 0x40000L);
      case 124:
         if ((active0 & 0x800000000L) != 0L)
            return jjStopAtPos(1, 35);
         break;
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 61:
         if ((active0 & 0x80000000L) != 0L)
            return jjStopAtPos(2, 31);
         break;
      case 97:
         return jjMoveStringLiteralDfa3_0(active0, 0x200000110000L);
      case 98:
         return jjMoveStringLiteralDfa3_0(active0, 0x200L);
      case 99:
         return jjMoveStringLiteralDfa3_0(active0, 0x8000L);
      case 100:
         return jjMoveStringLiteralDfa3_0(active0, 0x80c00L);
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x200100L);
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x80000004000L);
      case 108:
         return jjMoveStringLiteralDfa3_0(active0, 0x1000L);
      case 110:
         if ((active0 & 0x10000000000L) != 0L)
            return jjStartNfaWithStates_0(2, 40, 2);
         else if ((active0 & 0x40000000000L) != 0L)
            return jjStartNfaWithStates_0(2, 42, 2);
         break;
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x1100000002000L);
      case 112:
         return jjMoveStringLiteralDfa3_0(active0, 0x40000L);
      case 114:
         return jjMoveStringLiteralDfa3_0(active0, 0x400000000000L);
      case 115:
         if ((active0 & 0x20000000000L) != 0L)
            return jjStartNfaWithStates_0(2, 41, 2);
         else if ((active0 & 0x800000000000L) != 0L)
            return jjStartNfaWithStates_0(2, 47, 2);
         break;
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa4_0(active0, 0x200L);
      case 84:
         return jjMoveStringLiteralDfa4_0(active0, 0x80000L);
      case 97:
         if ((active0 & 0x100L) != 0L)
         {
            jjmatchedKind = 8;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active0, 0x200000L);
      case 101:
         if ((active0 & 0x40000L) != 0L)
            return jjStartNfaWithStates_0(3, 18, 2);
         break;
      case 105:
         return jjMoveStringLiteralDfa4_0(active0, 0xc00L);
      case 107:
         return jjMoveStringLiteralDfa4_0(active0, 0x8000L);
      case 108:
         return jjMoveStringLiteralDfa4_0(active0, 0x5000L);
      case 110:
         if ((active0 & 0x80000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 43, 2);
         else if ((active0 & 0x200000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 45, 2);
         return jjMoveStringLiteralDfa4_0(active0, 0x100000L);
      case 111:
         return jjMoveStringLiteralDfa4_0(active0, 0x1000000002000L);
      case 115:
         if ((active0 & 0x100000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 44, 2);
         break;
      case 116:
         if ((active0 & 0x400000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 46, 2);
         break;
      case 119:
         if ((active0 & 0x10000L) != 0L)
            return jjStartNfaWithStates_0(3, 16, 2);
         break;
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 66:
         return jjMoveStringLiteralDfa5_0(active0, 0x1000L);
      case 84:
         return jjMoveStringLiteralDfa5_0(active0, 0x200000L);
      case 102:
         return jjMoveStringLiteralDfa5_0(active0, 0xc00L);
      case 103:
         return jjMoveStringLiteralDfa5_0(active0, 0x108000L);
      case 105:
         return jjMoveStringLiteralDfa5_0(active0, 0x4000L);
      case 114:
         if ((active0 & 0x1000000000000L) != 0L)
            return jjStartNfaWithStates_0(4, 48, 2);
         return jjMoveStringLiteralDfa5_0(active0, 0x2200L);
      case 121:
         return jjMoveStringLiteralDfa5_0(active0, 0x80000L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 66:
         return jjMoveStringLiteralDfa6_0(active0, 0x2000L);
      case 101:
         return jjMoveStringLiteralDfa6_0(active0, 0x100200L);
      case 105:
         return jjMoveStringLiteralDfa6_0(active0, 0xc00L);
      case 108:
         return jjMoveStringLiteralDfa6_0(active0, 0x1000L);
      case 110:
         return jjMoveStringLiteralDfa6_0(active0, 0x4000L);
      case 112:
         return jjMoveStringLiteralDfa6_0(active0, 0x80000L);
      case 114:
         return jjMoveStringLiteralDfa6_0(active0, 0x8000L);
      case 121:
         return jjMoveStringLiteralDfa6_0(active0, 0x200000L);
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 84:
         return jjMoveStringLiteralDfa7_0(active0, 0x100000L);
      case 97:
         if ((active0 & 0x200L) != 0L)
            return jjStartNfaWithStates_0(6, 9, 2);
         break;
      case 101:
         if ((active0 & 0x80000L) != 0L)
            return jjStartNfaWithStates_0(6, 19, 2);
         return jjMoveStringLiteralDfa7_0(active0, 0xc00L);
      case 103:
         return jjMoveStringLiteralDfa7_0(active0, 0x4000L);
      case 108:
         return jjMoveStringLiteralDfa7_0(active0, 0x2000L);
      case 111:
         return jjMoveStringLiteralDfa7_0(active0, 0x9000L);
      case 112:
         return jjMoveStringLiteralDfa7_0(active0, 0x200000L);
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 66:
         return jjMoveStringLiteralDfa8_0(active0, 0x4000L);
      case 99:
         return jjMoveStringLiteralDfa8_0(active0, 0x1000L);
      case 101:
         if ((active0 & 0x200000L) != 0L)
            return jjStartNfaWithStates_0(7, 21, 2);
         break;
      case 111:
         return jjMoveStringLiteralDfa8_0(active0, 0x2000L);
      case 114:
         if ((active0 & 0x400L) != 0L)
         {
            jjmatchedKind = 10;
            jjmatchedPos = 7;
         }
         return jjMoveStringLiteralDfa8_0(active0, 0x800L);
      case 117:
         return jjMoveStringLiteralDfa8_0(active0, 0x8000L);
      case 121:
         return jjMoveStringLiteralDfa8_0(active0, 0x100000L);
      default :
         break;
   }
   return jjStartNfa_0(6, active0);
}
private int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0);
      return 8;
   }
   switch(curChar)
   {
      case 71:
         return jjMoveStringLiteralDfa9_0(active0, 0x800L);
      case 99:
         return jjMoveStringLiteralDfa9_0(active0, 0x2000L);
      case 107:
         if ((active0 & 0x1000L) != 0L)
            return jjStartNfaWithStates_0(8, 12, 2);
         break;
      case 108:
         return jjMoveStringLiteralDfa9_0(active0, 0x4000L);
      case 110:
         return jjMoveStringLiteralDfa9_0(active0, 0x8000L);
      case 112:
         return jjMoveStringLiteralDfa9_0(active0, 0x100000L);
      default :
         break;
   }
   return jjStartNfa_0(7, active0);
}
private int jjMoveStringLiteralDfa9_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(7, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(8, active0);
      return 9;
   }
   switch(curChar)
   {
      case 100:
         if ((active0 & 0x8000L) != 0L)
            return jjStartNfaWithStates_0(9, 15, 2);
         break;
      case 101:
         if ((active0 & 0x100000L) != 0L)
            return jjStartNfaWithStates_0(9, 20, 2);
         break;
      case 107:
         if ((active0 & 0x2000L) != 0L)
            return jjStartNfaWithStates_0(9, 13, 2);
         break;
      case 111:
         return jjMoveStringLiteralDfa10_0(active0, 0x4000L);
      case 114:
         return jjMoveStringLiteralDfa10_0(active0, 0x800L);
      default :
         break;
   }
   return jjStartNfa_0(8, active0);
}
private int jjMoveStringLiteralDfa10_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(8, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(9, active0);
      return 10;
   }
   switch(curChar)
   {
      case 99:
         return jjMoveStringLiteralDfa11_0(active0, 0x4000L);
      case 111:
         return jjMoveStringLiteralDfa11_0(active0, 0x800L);
      default :
         break;
   }
   return jjStartNfa_0(9, active0);
}
private int jjMoveStringLiteralDfa11_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(9, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(10, active0);
      return 11;
   }
   switch(curChar)
   {
      case 107:
         if ((active0 & 0x4000L) != 0L)
            return jjStartNfaWithStates_0(11, 14, 2);
         break;
      case 117:
         return jjMoveStringLiteralDfa12_0(active0, 0x800L);
      default :
         break;
   }
   return jjStartNfa_0(10, active0);
}
private int jjMoveStringLiteralDfa12_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(10, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(11, active0);
      return 12;
   }
   switch(curChar)
   {
      case 112:
         if ((active0 & 0x800L) != 0L)
            return jjStartNfaWithStates_0(12, 11, 2);
         break;
      default :
         break;
   }
   return jjStartNfa_0(11, active0);
}
private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 10;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 10:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(5, 6);
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 50)
                        kind = 50;
                     jjCheckNAdd(4);
                  }
                  break;
               case 0:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 50)
                        kind = 50;
                     jjCheckNAddStates(0, 2);
                  }
                  else if (curChar == 45)
                     jjCheckNAddTwoStates(4, 5);
                  else if (curChar == 36)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 2:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 52)
                     kind = 52;
                  jjstateSet[jjnewStateCnt++] = 2;
                  break;
               case 3:
                  if (curChar == 45)
                     jjCheckNAddTwoStates(4, 5);
                  break;
               case 4:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 50)
                     kind = 50;
                  jjCheckNAdd(4);
                  break;
               case 5:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(5, 6);
                  break;
               case 6:
                  if (curChar == 46)
                     jjCheckNAddTwoStates(7, 8);
                  break;
               case 7:
                  if (curChar == 45)
                     jjCheckNAdd(8);
                  break;
               case 8:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 53)
                     kind = 53;
                  jjCheckNAdd(8);
                  break;
               case 9:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 50)
                     kind = 50;
                  jjCheckNAddStates(0, 2);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
               case 1:
               case 2:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 52)
                     kind = 52;
                  jjCheckNAdd(2);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 10 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private int jjMoveStringLiteralDfa0_1()
{
   switch(curChar)
   {
      case 42:
         return jjMoveStringLiteralDfa1_1(0x80L);
      default :
         return 1;
   }
}
private int jjMoveStringLiteralDfa1_1(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 1;
   }
   switch(curChar)
   {
      case 47:
         if ((active0 & 0x80L) != 0L)
            return jjStopAtPos(1, 7);
         break;
      default :
         return 2;
   }
   return 2;
}
static final int[] jjnextStates = {
   4, 5, 6, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, "\101\162\145\141", 
"\123\165\142\101\162\145\141", "\115\157\144\151\146\151\145\162", 
"\115\157\144\151\146\151\145\162\107\162\157\165\160", "\127\141\154\154\102\154\157\143\153", 
"\106\154\157\157\162\102\154\157\143\153", "\103\145\151\154\151\156\147\102\154\157\143\153", 
"\102\141\143\153\147\162\157\165\156\144", "\104\162\141\167", "\151\146", "\124\171\160\145", 
"\115\157\144\124\171\160\145", "\103\150\141\156\147\145\124\171\160\145", 
"\101\162\145\141\124\171\160\145", "\53", "\55", "\52", "\57", "\45", "\72", "\75\75", "\41\75", "\40\76", 
"\40\76\75", "\74\40", "\74\75", "\46\46", "\174\174", "\41", "\46", "\174", "\176", 
"\163\151\156", "\143\157\163", "\164\141\156", "\141\163\151\156", "\141\143\157\163", 
"\141\164\141\156", "\163\161\162\164", "\141\142\163", "\146\154\157\157\162", "\44", null, null, 
null, null, "\73", "\75", "\173", "\175", "\50", "\54", "\51", };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
   "IN_COMMENT",
};

/** Lex State array. */
public static final int[] jjnewLexState = {
   -1, -1, -1, -1, -1, 1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
};
static final long[] jjtoToken = {
   0x1ff7ffffffffff01L, 
};
static final long[] jjtoSkip = {
   0xfeL, 
};
protected SimpleCharStream input_stream;
private final int[] jjrounds = new int[10];
private final int[] jjstateSet = new int[20];
protected char curChar;
/** Constructor. */
public RuleParserTokenManager(SimpleCharStream stream){
   if (SimpleCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public RuleParserTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 10; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 2 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   switch(curLexState)
   {
     case 0:
       try { input_stream.backup(0);
          while (curChar <= 13 && (0x2600L & (1L << curChar)) != 0L)
             curChar = input_stream.BeginToken();
       }
       catch (java.io.IOException e1) { continue EOFLoop; }
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_0();
       break;
     case 1:
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_1();
       if (jjmatchedPos == 0 && jjmatchedKind > 6)
       {
          jjmatchedKind = 6;
       }
       break;
   }
     if (jjmatchedKind != 0x7fffffff)
     {
        if (jjmatchedPos + 1 < curPos)
           input_stream.backup(curPos - jjmatchedPos - 1);
        if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
        {
           matchedToken = jjFillToken();
       if (jjnewLexState[jjmatchedKind] != -1)
         curLexState = jjnewLexState[jjmatchedKind];
           return matchedToken;
        }
        else
        {
         if (jjnewLexState[jjmatchedKind] != -1)
           curLexState = jjnewLexState[jjmatchedKind];
           continue EOFLoop;
        }
     }
     int error_line = input_stream.getEndLine();
     int error_column = input_stream.getEndColumn();
     String error_after = null;
     boolean EOFSeen = false;
     try { input_stream.readChar(); input_stream.backup(1); }
     catch (java.io.IOException e1) {
        EOFSeen = true;
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
        if (curChar == '\n' || curChar == '\r') {
           error_line++;
           error_column = 0;
        }
        else
           error_column++;
     }
     if (!EOFSeen) {
        input_stream.backup(1);
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
     }
     throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
