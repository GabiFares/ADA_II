export interface Direccion {
    pais: string;
    departamento: string;
    ciudad: string;
    calle: string;
    numPuerta: string;
  }
  
  export interface Persona {
    telefono: string;
    email: string;
    direccion: Direccion;
  }
  
  export interface Huesped {
    id?: number;
    ci: string;
    nombre: string;
    apellido: string;
    personaId: number; 
  }
  
  