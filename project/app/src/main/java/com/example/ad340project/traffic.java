package com.example.ad340project;

public class traffic {

   String label;
   String image;
   String owner;
   double[] coordinates;

   public traffic(String label, String owner, String image, double[] coordinates) {
       this.label = label;
       this.owner = owner;
       this.image = image;
       this.coordinates = coordinates;
   }
}
