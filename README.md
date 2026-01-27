# App Interrapidisimo – Reto Móvil

Esta es una **aplicación móvil Android desarrollada en Kotlin**, creada como parte del reto de Interrapidisimo. La app cumple con todos los criterios solicitados, incluyendo **login, control de versiones, sincronización de tablas y consulta de localidades**, siguiendo buenas prácticas de desarrollo y arquitectura por capas.

---

## 📌 Funcionalidades principales

### 1. Seguridad
- **Control de versiones:**  
  Consulta la versión del aplicativo desde la API y la compara con la versión local. Muestra un mensaje si la app está desactualizada o si la versión local es superior.
  
- **Login:**  
  - Consume el endpoint de autenticación con los headers y body requeridos.
  - Almacena localmente los datos del usuario (`Usuario`, `Identificacion`, `Nombre`) si la autenticación es exitosa.
  - Maneja errores de respuesta HTTP distintos a 200 mostrando alertas al usuario.

### 2. Datos
- **Base de datos SQLite local** utilizando Room.
- Se almacenan:
  - Información del usuario.
  - Tablas sincronizadas desde la API.
- Sincronización y persistencia implementadas siguiendo principios SOLID.

### 3. Presentación
- **HomeActivity:**  
  Muestra datos del usuario y dos botones:
  1. **Tablas:** sincroniza tablas y abre `TableActivity`.
  2. **Localidades:** abre `LocalidadesActivity`.

- **TableActivity:**  
  Muestra la lista de tablas obtenidas desde la API en un `RecyclerView`.

- **LocalidadesActivity:**  
  - Consume la API de localidades:  
    `https://apitesting.interrapidisimo.co/apicontrollerpruebas/api/ParametrosFramework/ObtenerLocalidadesRecogidas`
  - Muestra:
    - `AbreviacionCiudad`
    - `NombreCompleto`
  - Maneja errores y respuestas vacías.
  - No requiere almacenamiento en la base de datos.

### 4. Manejo de errores y buenas prácticas
- Todas las llamadas a API están envueltas en `try/catch`.
- Se notifican errores mediante `Toast`.
- Arquitectura separada por capas: **Seguridad, Datos, Presentación**.
- Código documentado y estructurado siguiendo principios SOLID.

---
