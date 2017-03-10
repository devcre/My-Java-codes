/*
 * (C) 2016 CSE2010 HW #2
 * 
 * Complete the codes ...
 */
import java.util.*;
import java.lang.Math;

/*
 * class DLinkedPolynomial
 */
public class DLinkedPolynomial implements Polynomial {

    private DLinkedList<Term> poly;

	DLinkedPolynomial() {
		//this.poly = new DLinkedList<Term>();
    }

    @Override
    public int getDegree() {
    	int Topexpnt = 0;
    	int compare = 0;
    	
    	DNode<Term> cur = poly.getFirstNode();
    	Topexpnt = cur.getInfo().expo;
    	while(poly.getNextNode(cur) != null){
    		compare = cur.getNext().getInfo().expo;
    		if(Topexpnt < compare){
    			Topexpnt = compare;
    		}
    	}
    	return Topexpnt;
    }

    @Override
    public double getCoefficient(int exponent) {
    	double curCoeff;
    	int curExpo;
    	int breakint = 1;
    	
    	DNode<Term> cur = poly.getFirstNode();
    	while(breakint != 0){
    		curCoeff = cur.getInfo().coeff;
    		curExpo = cur.getInfo().expo;
    		if(curExpo == exponent){
    			breakint = 0;
    			return curCoeff;
    		}
    		cur = poly.getNextNode(cur);
    	}
    	return 0;
    }

    @Override
    public Polynomial padd(Polynomial p) {
    	DLinkedPolynomial pol = (DLinkedPolynomial)p;
    	
    	DLinkedList<Term> polyadd = this.poly;
    	
    	int polyaddsize;
    	int polsize;
    	
    	DNode<Term> anode = pol.poly.getFirstNode();
    	DNode<Term> node = polyadd.getFirstNode();
    	while((polyaddsize = polyadd.getSize()) != 0){
    		for(polsize = pol.poly.getSize(); polsize < 0; polsize--){
    			if(node.getInfo().expo == anode.getInfo().expo){
    				node.getInfo().coeff += anode.getInfo().coeff; 
    			}
    			else{
    				DNode<Term> valnode = polyadd.getFirstNode();
    				while(true){
    					double ancoeff = anode.getInfo().coeff;
    					int anexpo = anode.getInfo().expo;
    					
    					if((valnode.getInfo().expo > anexpo) &&(anexpo > valnode.getNext().getInfo().expo)){
    						polyadd.addAfter(valnode, anode);
    						break;
    					}
    					valnode = valnode.getNext();
    				}
    			}
    			anode = anode.getNext();
    		}
    		node = node.getNext();
    		polyaddsize -= 1;
    	}
    	return (Polynomial) polyadd;
    }

    Term multTerms(Term x, Term y) {
        return new Term(x.coeff * y.coeff, x.expo + y.expo);
    }

    @Override
    public Polynomial pmult(Polynomial p) {
      DLinkedPolynomial pol = (DLinkedPolynomial)p;
      DLinkedList<Term> polymult = this.poly;
      DLinkedPolynomial result = new DLinkedPolynomial();
      
      DNode<Term> polnode = pol.poly.getFirstNode();
      DNode<Term> polymultnode = polymult.getFirstNode();
      for(int q=0; q<pol.poly.getSize();q++){
    	  for(int r=0; r<polymult.getSize(); r++){
    		  result.addTerm(multTerms(polnode.getInfo(), polymultnode.getInfo()));
    	  }
    	  polnode = polnode.getNext();
      }
      return result;
    }
    

    @Override
    public void addTerm(Term term) {
    	DNode<Term> n = new DNode<Term>(term,null,null);
    	if(poly.isEmpty() == true){
    		poly.addFirst(n);
    	}
    	else{
    		DNode<Term> p = poly.getFirstNode();
    		for(int j=0;j<poly.getSize(); j++){
    			if(p.getInfo().expo < term.expo){
    				poly.addBefore(p, n); //첫번째 노드의 지수보다 term의 지수가 더 크면 addBefore 함수를 실행한다.
    			}
    			else if(p.getInfo().expo == term.expo){
    				p.getInfo().coeff += term.coeff; //poly에 있는 지수와 term의 지수와 같으면 계수를 더한다.
    			}
    			else if(p.getInfo().expo > term.expo){ //addAfter함수를 호출하기위한 조건
    				if(poly.getSize() == 2){
        				poly.addAfter(p, n);
    				}
//    				else if((p.getNext() != null) && (term.expo > p.getNext().getInfo().expo)){
//        				poly.addAfter(p, n);
//    				}
    				else{
        				poly.addAfter(p, n);
    				}
    			}
    			if(p.getNext() != null){
    				p = p.getNext();
    			}
    		}
    	}
    }

    @Override
    public double evaluate(double val) {
        double sum = 0;

        DNode<Term> current = poly.getFirstNode();
        do {
            sum += current.getInfo().coeff * Math.pow(val, current.getInfo().expo);
            current = poly.getNextNode(current);
        } while (current != null);
        return sum;
    }

    @Override
    public String dump() {
        if (poly.isEmpty())
            return "Empty Polynomial";
        else {
            StringBuilder builder = new StringBuilder();
            DNode<Term> current = poly.getFirstNode();
            do {
                builder.append("(" + current.getInfo().coeff + ", " + current.getInfo().expo + ") ");
                current = poly.getNextNode(current);
            } while (current != null);
            builder.append("\n");
            return builder.toString();
        }
    }
}
