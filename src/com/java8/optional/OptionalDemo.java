package com.java8.optional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalDemo {

	
	public static Customer getCustomerByEmailId(String email) throws Exception {
		List<Customer> customers = Database.getAll();
		
		return customers.stream()
		         .filter(c -> c.getEmail().equals(email))
		         .findAny().orElseThrow(() -> new Exception("no customer present with this email id"));
	}
	
	public static void main(String[] args) throws Exception {
		
		 Customer customer = new Customer(1,"sree","abc@gmail.com",Arrays.asList("123","456"));
		// Customer customer = new Customer(1,"sree",null,Arrays.asList("123","456"));
		
		//Optional<Object> emptyOptional = Optional.empty();
		//System.out.println(emptyOptional);
		
		
		//Optional<String> emailOptinal1 = Optional.of(customer.getEmail());
		//System.out.println(emailOptinal1);
		
		
		 Optional<String> emailOptional2 = Optional.ofNullable(customer.getEmail());
	    // if(emailOptional2.isPresent()){
		// System.out.println(emailOptional2.get());
		// }
	
			 
	//System.out.println(emailOptional2.orElse("default@email.com"));
		
		 
    //System.out.println(emailOptional2.orElseThrow(() -> new IllegalArgumentException("email not present")));
		
	System.out.println(emailOptional2.map(String::toUpperCase).orElseGet(() -> "default email"));

	 getCustomerByEmailId("xyz");
	
	}
}







/*

-->
first create customer object

Customer customer = new Customer(1,"sree","abc@gmail.com",Arrays.asList("123","456"));


-->
and make one field as null

Customer customer = new Customer(1,"sree",null,Arrays.asList("123","456"));




---> there are 3 ways to create Optinal object



//empty()
// of()
//ofNullable()

Static methods
--------------->
Static <T> Optional <T>      empty()

Static <T> Optional <T>      of(T value)

Static <T> Optional <T>     ofNullable(T value)

--------------->




--> 

empty is a static method

we can empty() with a class name


Optional.empty();


-->

it will return empty Optional object

i.e:  

public static <T> Optional<T> empty(){
Optional<T> t = (Optional <T>)EMPTY;
return t;
}

-->


private static final Optional<?> EMPTY = new Optional<>();



-->
it is creating one optional object and returning it





--> go to optional demo class and print this

and define local variable


Optional<Object> emptyOptional = Optional.empty();







CAse: 1
---------------------------------->

public class OptionalDemo {

	public static void main(String args[]) {
		
		Customer customer = new Customer(1,"sree","abc@gmail.com",Arrays.asList("123","456"));
		
		Optional<Object> emptyOptional = Optional.empty();
		
		System.out.println(emptyOptional);
		
		
	}
}
---------------------------------->
Output:

Optional.empty







2) 

another way to create a optinal


Optional.Of()   --> and pass the object

and make one field as null,

make email field as null...

Customer customer = new Customer(1,"sree",null,Arrays.asList("123","456"));



--> get email is type string




--> if we go to Optional of method



public static <T> Optional<T> of (T value){
return new Optional<T> (value);
}







CAse: 2
---------------------------------->
	public class OptionalDemo {

	public static void main(String[] args) {
		
		Customer customer = new Customer(1,"sree",null,Arrays.asList("123","456"));
		
		Optional<String> emailOptinal1 = Optional.of(customer.getEmail());
		System.out.println(emailOptinal1);	
	}
}
---------------------------------->

Output: 

Exception in thread "main" java.lang.NullPointerException
	at java.base/java.util.Objects.requireNonNull(Objects.java:208)
	at java.base/java.util.Optional.of(Optional.java:111)
	at com.java8.optional.OptionalDemo.main(OptionalDemo.java:18)



we are getting null pointer exception





--> because if we go inside 

if(obj == null)
throw new NullPointerException();
return obj




------>

so when can we go for  of()  ?


when  you know that the object that you are passing is not null 
then you can use 

Optional.of()

--> because it does the null check internally


to avoid that, there is another method to create optional object





--->



3) Optional.ofNullable()


--> pass the same object

--> return the local variable


Optional<String> emailOptional2 = Optional.ofNullable(customer.getEmail());
 System.out.println(emailOptional2);



--> it wont return the null pointer exception

--> if we go inside, of nullable method

i.e:   

    return value == null ? empty() : of(value)



its combination of empty method  & of method

  either empty object or... of method
  inside of method.. its again check for null condition
  
  
  
-->

so if object is null

return the empty object 
rather than throwing the NullPointer exception


if object is present

then evaluate the of(value) logic




CAse: 3
---------------------------------->
public class OptionalDemo {

	public static void main(String[] args) {
		
		//Customer customer = new Customer(1,"sree","abc@gmail.com",Arrays.asList("123","456"));
		Customer customer = new Customer(1,"sree",null,Arrays.asList("123","456"));
				
		Optional<String> emailOptional2 = Optional.ofNullable(customer.getEmail());
	    System.out.println(emailOptional2);
		
	}
}

---------------------------------->
Output:

Optional.empty


--> here above we are passing email as null..
we should expect empty optional object as output







-->pass some value



CAse: 4
---------------------------------->
public class OptionalDemo {

	public static void main(String[] args) {
		
		Customer customer = new Customer(1,"sree","abc@gmail.com",Arrays.asList("123","456"));
				
		Optional<String> emailOptional2 = Optional.ofNullable(customer.getEmail());
	    System.out.println(emailOptional2);
		
	}
}

---------------------------------->
Output:

Optional[abc@gmail.com]






--> 
Conclusion:
---------

when to use of & ofNullable ?




of 
---
if your object that that you are passing to this of method. is never null

then go for of


ofNullable
-------
if your obj, may or may not null.. then go for ofNullable

















Other inbuilt methods
------------------------

how we can get the value from this Optional, if we print this

i.e: 
Optional[abc@gmail.com]


how to get the exact value

-->
there is direct method ---> get()







CAse: 6  
---------------------------------->
public class OptionalDemo {

	public static void main(String[] args) {
		
		 Customer customer = new Customer(1,"sree","abc@gmail.com",Arrays.asList("123","456"));
		
		Optional<String> emailOptional2 = Optional.ofNullable(customer.getEmail());
	    System.out.println(emailOptional2.get());
		
		
	}
}
---------------------------------->
Output: abc@gmail.com









CAse: 5   --> Before
---------------------------------->
public class OptionalDemo {

	public static void main(String[] args) {
		
		 Customer customer = new Customer(1,"sree",null,Arrays.asList("123","456"));

		Optional<String> emailOptional2 = Optional.ofNullable(customer.getEmail());
	    System.out.println(emailOptional2);
		
		
	}
}
---------------------------------->
Optional.empty





CAse: 5   --> After
---------------------------------->
public class OptionalDemo {

	public static void main(String[] args) {
		
		 Customer customer = new Customer(1,"sree",null,Arrays.asList("123","456"));

		Optional<String> emailOptional2 = Optional.ofNullable(customer.getEmail());
	    System.out.println(emailOptional2.get());
		
		
	}
}
---------------------------------->
Output:
--------
Exception in thread "main" java.util.NoSuchElementException: No value present
	at java.base/java.util.Optional.get(Optional.java:141)
	at com.java8.optional.OptionalDemo.main(OptionalDemo.java:22)





Note: 
---> even though field is null...

instead of getting NullpointerException...we are getting. java.util.NoSuchElementException


how we can avoid that ?


usually we should not call directly get method



before we should call get method, what we can do is ..



i
Optional<String> emailOptional2 = Optional.ofNullable(customer.getEmail());
if(emailOptional2.isPresent()){
System.out.println(emailOptional2.get());
}







CAse: 7
---------------------------------->
public class OptionalDemo {

	public static void main(String[] args) {
		
		 Customer customer = new Customer(1,"sree","abc@gmail.com",Arrays.asList("123","456"));

		 Optional<String> emailOptional2 = Optional.ofNullable(customer.getEmail());
		 if(emailOptional2.isPresent()){
		 System.out.println(emailOptional2.get());
		 }
		
		
	}
}
---------------------------------->
abc@gmail.com





CAse: 7  --> No output
---------------------------------->
public class OptionalDemo {

	public static void main(String[] args) {
		
		 Customer customer = new Customer(1,"sree",null,Arrays.asList("123","456"));

		 Optional<String> emailOptional2 = Optional.ofNullable(customer.getEmail());
		 if(emailOptional2.isPresent()){
		 System.out.println(emailOptional2.get());
		 }
		
		
	}
}
---------------------------------->

No output



-->  emailOptional2.isPresent()  is returns false 
--> because emailOptional2 doesn't contain any value
so if statement won't execute








-->
there are multiple ways to handle if the value is getting null or something like that

lets try with another approach





--> we want to return some default value, if that optional doesn't contain any value


suppose --> if customer.getEmail(), is not there

then we want to return some default value





---------------------------------->
Other Approaches
---------------------------------->

CAse: 8
---------------------------------->
public class OptionalDemo {

	public static void main(String[] args) {
		

		 Customer customer = new Customer(1,"sree",null,Arrays.asList("123","456"));
		
		 Optional<String> emailOptional2 = Optional.ofNullable(customer.getEmail());
	
		 System.out.println(emailOptional2.orElse("default@email.com"));
		
		
	}
}
---------------------------------->
default@email.com








CAse: 8 - other
---------------------------------->
public class OptionalDemo {

	public static void main(String[] args) {
		
		 Customer customer = new Customer(1,"sree","abc@gmail.com",Arrays.asList("123","456"));
		
		 Optional<String> emailOptional2 = Optional.ofNullable(customer.getEmail());
		 
		 System.out.println(emailOptional2.orElse("default@email.com"));
		
		
	}
}
---------------------------------->
Output:
abc@gmail.com






--> Here .orElse()

gets the value if the value is present, if not , returns the default value;







Throw some Customized exception
--------->--------->--------->
CAse: 9 - Fail case
---------------------------------->


public class OptionalDemo {

	public static void main(String[] args) {
		
		 Customer customer = new Customer(1,"sree",null,Arrays.asList("123","456"));

		 Optional<String> emailOptional2 = Optional.ofNullable(customer.getEmail());

		 
  System.out.println(emailOptional2.orElseThrow(() -> new IllegalArgumentException("email not present")));
		
	}
}
---------------------------------->
Exception in thread "main" java.lang.IllegalArgumentException: email not present
	at com.java8.optional.OptionalDemo.lambda$0(OptionalDemo.java:32)
	at java.base/java.util.Optional.orElseThrow(Optional.java:401)
	at com.java8.optional.OptionalDemo.main(OptionalDemo.java:32)








--------->

orElseThrow(() ->

--> it takses the supplier..




--------->--------->--------->
CAse: 9 - Success CAse
---------------------------------->

public class OptionalDemo {

	public static void main(String[] args) {
		
		 Customer customer = new Customer(1,"sree","abc@gmail.com",Arrays.asList("123","456"));

			
	Optional<String> emailOptional2 = Optional.ofNullable(customer.getEmail());		 
  System.out.println(emailOptional2.orElseThrow(() -> new IllegalArgumentException("email not present")));
		
	}
}
---------------------------------->
abc@gmail.com






-->
this is one of the advantage of using orElseThrow(()


there is a way to handle null
if there is null or  if there is empty response.
or you can customize exception








Alternative
------------->



I just want to get the value from the email optional
and make it upper case


--> 
Optional.map(Function<? super String, ? extends Object> mapper)




--> if there is no value return the default value using 

orElseGet()



--> which takes supplier and return T








without orElseget
-------------------------------->
public class OptionalDemo {

	public static void main(String[] args) {
		
		 Customer customer = new Customer(1,"sree","abc@gmail.com",Arrays.asList("123","456"));
		
		 Optional<String> emailOptional2 = Optional.ofNullable(customer.getEmail());

		
System.out.println(emailOptional2.map(String::toUpperCase));

	
	
	}
}

---------------------------------->
Optional[ABC@GMAIL.COM]










--------->--------->--------->
CAse: 10 - Success CAse
---------------------------------->
public class OptionalDemo {

	public static void main(String[] args) {
		
		 Customer customer = new Customer(1,"sree","abc@gmail.com",Arrays.asList("123","456"));
		
		 Optional<String> emailOptional2 = Optional.ofNullable(customer.getEmail());

System.out.println(emailOptional2.map(String::toUpperCase).orElseGet(() -> "default email"));

	
	
	}
}
---------------------------------->
ABC@GMAIL.COM





--------->--------->--------->
CAse: 10 - FAil CAse
---------------------------------->
public class OptionalDemo {

	public static void main(String[] args) {
		
		 Customer customer = new Customer(1,"sree",null,Arrays.asList("123","456"));
		
		 Optional<String> emailOptional2 = Optional.ofNullable(customer.getEmail());

System.out.println(emailOptional2.map(String::toUpperCase).orElseGet(() -> "default email"));

	
	
	}
}
---------------------------------->
default email







Difference b/w --> orElse()    & orElseGet(Supplier)




both are similar  but method arguments are different



one is taking supplier & another is having default return type

















---->

Now implement this Optional concept with STream API



--> add Database

--> then we filter using some logic


--> go to optional Demo and write one method


-->




---------------------------------->
	public static Customer getCustomerByEmailId(String email) {
		List<Customer> customers = Database.getAll();
		
		return customers.stream()
		         .filter(c -> c.getEmail().equals(email))
		         .findAny().get();
		

	}
	
---------------------------------->
	



--> get customer by email id

-->  pass email id as argument

->  get list of customer .. from database

-->  from this list of customers we need to find one customer based on the input email id

-->  we can convert these customers to stream

--> then we can use filter .. nothing but ..if else... it takes predicate


--> from this stream. if the email you are giving

    if it matches . then we can directly get the object.. using  findAny or  findFirst
    they return Optional<customer>
    
    
    
-->  if there is no customer object present with the input email 
     we definitely get  no such element excpetion.. so to verify that just print it using get()
     
     
     
 --> return 
 
     
 --> make it static so that we can directly call without creating object
   
             

  

--> in our db , there is email with xyz
--> so we definitely get the , exception







CAse: 11
---------------------------------->

public class OptionalDemo {

	
	public static Customer getCustomerByEmailId(String email) {
		List<Customer> customers = Database.getAll();
		
		return customers.stream()
		         .filter(c -> c.getEmail().equals(email))
		         .findAny().get();
		

	}
	
	public static void main(String[] args) {
		

 Customer customer = new Customer(1,"sree",null,Arrays.asList("123","456"));

Optional<String> emailOptional2 = Optional.ofNullable(customer.getEmail());

	
System.out.println(emailOptional2.map(String::toUpperCase).orElseGet(() -> "default email"));

	getCustomerByEmailId("xyz");
	
	}
}

}
---------------------------------->
default email
Exception in thread "main" java.util.NoSuchElementException: No value present
	at java.base/java.util.Optional.get(Optional.java:141)
	at com.java8.optional.OptionalDemo.getCustomerByEmailId(OptionalDemo.java:15)
	at com.java8.optional.OptionalDemo.main(OptionalDemo.java:48)









--> because in db there is no xyz email


--> instead of returning directly get..

    we can use orElse








CAse: 12
---------------------------------->

public class OptionalDemo {

	
	public static Customer getCustomerByEmailId(String email) {
		List<Customer> customers = Database.getAll();
		
		return customers.stream()
		         .filter(c -> c.getEmail().equals(email))
		         .findAny().get();
		

	}
	
	public static void main(String[] args) {
		

 Customer customer = new Customer(1,"sree",null,Arrays.asList("123","456"));

Optional<String> emailOptional2 = Optional.ofNullable(customer.getEmail());

	
System.out.println(emailOptional2.map(String::toUpperCase).orElseGet(() -> "default email"));

	getCustomerByEmailId("xyz");
	
	}
}

}
---------------------------------->



	public static Customer getCustomerByEmailId(String email) {
		List<Customer> customers = Database.getAll();
		
		return customers.stream()
		         .filter(c -> c.getEmail().equals(email))
		         .findAny().orElse(new Customer());
	}
		        
		         
		         
--> returns empty customer object

		         
--> we won't  get any expection but .. output is empty.

	---------------------------------->	         
		         
		         
		         
		         
--> if email is not found ... orElseThrows		         

 --> you can customize  your own exception... here as example


---------------------------------->

		List<Customer> customers = Database.getAll();
		
		return customers.stream()
		         .filter(c -> c.getEmail().equals(email))
		         .findAny().orElseThrow(() -> new Exception("no customer present with this email id"));
	}
	
	
	
	
----> the above checked exception

	so use throws.... add method signature
	

--> add to the main method as well






--> if we run this









CAse: 12
---------------------------------->
public class OptionalDemo {

	
	public static Customer getCustomerByEmailId(String email) throws Exception {
		List<Customer> customers = Database.getAll();
		
		return customers.stream()
		         .filter(c -> c.getEmail().equals(email))
		         .findAny().orElseThrow(() -> new Exception("no customer present with this email id"));
	}
	
	public static void main(String[] args) throws Exception {
		
		 Customer customer = new Customer(1,"sree","abc@gmail.com",Arrays.asList("123","456"));
		
		 Optional<String> emailOptional2 = Optional.ofNullable(customer.getEmail());

		
	System.out.println(emailOptional2.map(String::toUpperCase).orElseGet(() -> "default email"));

	 getCustomerByEmailId("xyz");
	
	}
}


-------->
Output


ABC@GMAIL.COM
Exception in thread "main" java.lang.Exception: no customer present with this email id
	at com.java8.optional.OptionalDemo.lambda$1(OptionalDemo.java:15)
	at java.base/java.util.Optional.orElseThrow(Optional.java:401)
	at com.java8.optional.OptionalDemo.getCustomerByEmailId(OptionalDemo.java:15)
	at com.java8.optional.OptionalDemo.main(OptionalDemo.java:46)








--> 
now we are getting our own exception, even thought it is predefined exception class














Best practise
---->


go to customer



rather than return primitive return type



we can directly add the optional


Before
---------->
	    public String getEmail() {
            return email;
	    }

---------->




After
---------->
	    public Optional<String> getEmail() {
            return Optional.ofNullable(email);
	    }

---------->




this is more secure 
than hard code the  return type as string or integer or long



ofNullable(email)


--> it returns the empty optional object , if the email is null




--> similar way you can go with all the getters method with this approach



recommended if you are using stream api or reactive programming.









*/