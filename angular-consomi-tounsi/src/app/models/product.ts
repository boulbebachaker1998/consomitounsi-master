import { CategoryProduit } from "./categoryProduit";
import { UploadFile } from "./uploadFile";

export  class Product{
       constructor(
       public idProduit:number,       
       public categoryProduit:CategoryProduit,
       public  prixProduit:number,
       public  nomProduit:string,
       public  codeabarraProduit:number,
       public  dateCreation:Date,
       public quantity:number, 
       public note:number
       ){}
       
       public image:UploadFile;
       }
