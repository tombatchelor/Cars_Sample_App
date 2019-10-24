using System;
namespace CarLoan
{
    public class LoanQuoteRequest
    {
        public int price {get; set;}
        public int loanAmount { get; set; }
        public int term { get; set; }

        public LoanQuoteRequest()
        {
        }
    }
}
