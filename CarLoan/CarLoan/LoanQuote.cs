using System;
namespace CarLoan
{
    public class LoanQuote
    {
        public double rate { get; set; }
        public double payment { get; set; }
        public int term { get; set; }

        public LoanQuote()
        {
        }
    }
}
