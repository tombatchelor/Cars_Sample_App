using System;
namespace CarLoan
{
    public class LoanQuote
    {
        public float rate { get; set; }
        public float payment { get; set; }
        public int term { get; set; }

        public LoanQuote()
        {
        }
    }
}
