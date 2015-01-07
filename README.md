# PointOfSale

<h3>Sample task for Java Developer candidate<h3>

Implement a simple point of sale (PoS).

Assume you have:
<ul>
    <li>
        one input device: bar codes scanner
    </li>
    <li>
        two output devices: LCD display and printer
    </li>
</ul>

Implement:
<ul>
    <li>
        single product sale: products bar code is scanned and:
        <ul>
            <li>
                if the product is found in products database than it's name and price is printed on LCD display
            </li>
            <li>
                if the product is not found than error message 'Product not found' is printed on LCD display
            </li>
            <li>
                if the code scanned is empty than error message 'Invalid barcode' is printed on LCD display
            </li>
        </ul>
    </li>
    <li>
        when 'exit' is input than receipt is printed on printer containing a list of all previously scanned items names 
        and prices as well as total sum to be paid for all items; the total sum is also printed on LCD display
    </li>
</ul>

Rules:
<ul>
    <li>
        use only SDK classes and your favorite test libraries
    </li>
    <li>
        mock/stub the database and IO devices
    </li>
    <li>
        concentrate on proper design and clean code, rather than supplying fully functioning application
    </li>
    <li>
        if necessary describe your assumptions in dedicated file
    </li>
</ul>
