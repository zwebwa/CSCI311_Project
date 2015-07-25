<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
    <center>
        <h1>Ak Search</h1>
        <form action="aksearch.php" method="get">
            <input type="text" name="search_term" size="50"/>
            <input type="submit" value="Search"/>
            
        </form>
        <hr />
        
        <?php
        error_reporting(0);
         $search_term = $_GET['search_term'];
         
         if(empty($search_term)){
             
         }
         else{
         $search = explode(" ",$search_term);
         
         $query = "Select * from record where ";
         $i=0;
         foreach ($search as $each){
             $i++;
             if($i == 1){
                 $query .= "keywords like '%$each%'";
             }
             else{
                 $query .= "OR keywords like '%$each%'";
             }
         }
         
         mysql_connect("localhost","root","");
         mysql_select_db("crawler");
         $query = mysql_query($query);
         
         $num_row = mysql_num_rows($query);
         
         if($num_row>0){
             while($row = mysql_fetch_array($query)){
                 $URL = $row['URL'];
                 $keyword = $row['keywords'];
                 
                // echo 'URL is '.$URL."keyword is ".$keyword;
                 echo 'URL is '.$URL;
                 echo "\r\n".PHP_EOL;
             }
             echo "\r\n";
         }
         else{
             echo 'no result';
         } 
         }
         
        
        ?>
        
    </center>
    </body>
</html>