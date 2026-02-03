# Modelo de Clases - DTOs

## Resumen de Cambios

Se han introducido nuevos tipos DTO/record para normalizar y mejorar la reutilización de los modelos de datos en el backend, siguiendo principios de separación de responsabilidades y reutilización de código.

---

## 1. Usuario

### `UsuarioAbstractDTO` (abstract class)

**Propósito**: Clase base abstracta que contiene los campos comunes y obligatorios para todas las representaciones de usuario.

**Campos**:
```java
- nombre: String
- apellido1: String  
- apellido2: String
- email: String
- fechaNacimiento: Date
- pais: String
- sexo: Sexo (enum)
- fechaRegistro: Timestamp
- objetivos: List<ObjetivoDTO> (inicializada vacía)
```

**Uso**: Sirve como base para todas las representaciones de usuario (request y response).

---

### `UsuarioRegistroRequestDTO` (class)

**Hereda de**: `UsuarioAbstractDTO`

**Propósito**: DTO para peticiones de creación y actualización de usuario.

**Campos adicionales**:
```java
- rutaAvatar: String
- fechaActualizacion: Timestamp
```

**Ejemplo de uso**:
```java
// Crear nuevo usuario
UsuarioRegistroRequestDTO request = UsuarioRegistroRequestDTO.builder()
    .nombre("Juan")
    .apellido1("Pérez")
    .email("juan@example.com")
    .fechaNacimiento(new Date())
    .pais("España")
    .sexo(Sexo.MASCULINO)
    .rutaAvatar("/avatars/default.png")
    .build();
```

---

### `UsuarioRegistroResponseDTO` (class)

**Hereda de**: `UsuarioAbstractDTO`

**Propósito**: DTO para respuestas que incluyen información completa del usuario.

**Campos adicionales**:
```java
- idUsuarioRegistro: Long
- nombreUsuario: String
- fechaActualizacion: Timestamp
- fechaBaja: Date
```

**Ejemplo de uso**:
```java
// Respuesta al cliente
UsuarioRegistroResponseDTO response = UsuarioRegistroResponseDTO.builder()
    .idUsuarioRegistro(1L)
    .nombreUsuario("juanperez")
    .nombre("Juan")
    .apellido1("Pérez")
    .email("juan@example.com")
    // ... otros campos
    .build();
```

---

## 2. Objetivo

### `ObjetivoRecord` (record)

**Propósito**: Record inmutable que representa la estructura básica de un objetivo.

**Campos**:
```java
- id: Long
- descripcion: String
- fechaRegistro: String (formato yyyy-MM-dd)
- fechaFin: String (formato yyyy-MM-dd)
- cumplido: Boolean
- usuario: UsuarioRegistroResponseDTO
- informes: List<InformeDTO>
```

**Ejemplo de uso**:
```java
ObjetivoRecord objetivo = new ObjetivoRecord(
    1L,
    "Perder 5kg en 2 meses",
    "2026-02-01",
    "2026-04-01",
    false,
    usuarioResponse,
    listaInformes
);
```

---

### `ObjetivoAggregateDto` (class)

**Propósito**: Clase que agrupa un objetivo con su informe inicial, útil para endpoints que crean objetivos con el primer registro de medidas.

**Campos**:
```java
- id: Long
- descripcion: String
- fechaRegistro: String (yyyy-MM-dd)
- fechaFin: String (yyyy-MM-dd)
- cumplido: Boolean
- usuario: UsuarioRegistroResponseDTO
- informes: List<InformeDTO>
- informeInicialDto: InformeInicialDTO (campo adicional)
```

**Ejemplo de uso**:
```java
// Crear objetivo con informe inicial
ObjetivoAggregateDto aggregate = ObjetivoAggregateDto.builder()
    .descripcion("Ganar masa muscular")
    .fechaRegistro("2026-02-03")
    .fechaFin("2026-05-03")
    .cumplido(false)
    .informeInicialDto(informeInicial)
    .build();
```

---

## 3. Informe

### `InformeAbstractDTO` (record)

**Propósito**: Record con los campos básicos de medición corporal y hábitos, sin metadatos adicionales.

**Campos**:
```java
- peso: Double
- cadera: Double
- gemelos: Double
- cuadriceps: Double
- abdomen: Double
- pecho: Double
- hombros: Double
- antebrazo: Double
- biceps: Double
- gluteos: Double
- porcentajeGraso: Double
- porcentajeMusculo: Double
- nivelActividad: NivelActividad (enum)
- seguimientoDieta: SeguimientoDieta (enum)
- diasEntreno: Integer
```

**Uso**: Base conceptual para otros DTOs de informe (no se usa directamente en endpoints).

---

### `InformeRequestDTO` (record)

**Propósito**: DTO para peticiones de creación/actualización de informes. Incluye todos los campos de medición más metadatos.

**Campos**:
```java
// Todos los campos de InformeAbstractDTO, más:
- id: Long
- objetivoId: Long
- fechaRegistro: String (yyyy-MM-dd)
- dietaDiaria: List<DietaDiariaDTO> (inicializada vacía si es null)
- imc: Double
- tmb: Double
```

**Constructor compacto**: Inicializa automáticamente `dietaDiaria` como lista vacía si es null.

**Ejemplo de uso**:
```java
InformeRequestDTO request = new InformeRequestDTO(
    null, // id (null para crear nuevo)
    1L,   // objetivoId
    75.5, // peso
    98.0, // cadera
    // ... resto de medidas
    NivelActividad.MODERADO,
    SeguimientoDieta.BUENO,
    3,    // diasEntreno
    "2026-02-03",
    null, // dietaDiaria (se inicializa automáticamente)
    24.5, // imc
    2100.0 // tmb
);
```

---

### `InformeInicialDTO` (record)

**Propósito**: DTO específico para el primer informe que se crea al iniciar un objetivo. Contiene las medidas esenciales y la fecha de registro.

**Campos**:
```java
- peso: Double
- cadera: Double
- gemelos: Double
- cuadriceps: Double
- abdomen: Double
- pecho: Double
- hombros: Double
- antebrazo: Double
- biceps: Double
- gluteos: Double
- porcentajeGraso: Double
- porcentajeMusculo: Double
- nivelActividad: NivelActividad
- seguimientoDieta: SeguimientoDieta
- diasEntreno: Integer
- fechaRegistro: String (yyyy-MM-dd)
```

**Ejemplo de uso**:
```java
InformeInicialDTO informeInicial = new InformeInicialDTO(
    75.5,  // peso
    98.0,  // cadera
    // ... resto de medidas
    NivelActividad.MODERADO,
    SeguimientoDieta.BUENO,
    3,
    "2026-02-03"
);
```

---

### `InformeSimplifiedDTO` (record)

**Propósito**: DTO simplificado para registro de informes de **usuarios autónomos** (rol `USUARIO_AUTONOMO`). Permite un seguimiento básico sin la complejidad de medidas corporales detalladas.

**Rol destinado**: `USUARIO_AUTONOMO` - Usuarios que llevan su propio seguimiento sin entrenador.

**Campos**:
```java
- fechaRegistro: String (yyyy-MM-dd)
- diasEntreno: Integer (0-7, días de entrenamiento a la semana)
- intensidadEntreno: NivelActividad (nivel de intensidad)
- peso: Double (peso corporal en kg)
- seguimientoDieta: SeguimientoDieta (adherencia a la dieta)
```

**Validaciones integradas**:
- `diasEntreno` debe estar entre 0 y 7
- `peso` debe ser positivo

**Ejemplo de uso**:
```java
// Usuario autónomo registra su progreso semanal
InformeSimplifiedDTO registroSemanal = new InformeSimplifiedDTO(
    "2026-02-03",
    4,  // 4 días de entrenamiento esta semana
    NivelActividad.MODERADO,
    74.8,  // peso actual
    SeguimientoDieta.BUENO
);
```

**Diferencias con InformeRequestDTO**:
- ❌ No incluye medidas corporales detalladas (cadera, gemelos, etc.)
- ❌ No incluye porcentajes (graso, músculo)
- ❌ No incluye TMB ni IMC calculados
- ✅ Enfoque en datos básicos de seguimiento
- ✅ Más rápido de completar para usuarios finales
- ✅ Ideal para seguimiento diario/semanal simple

---

## 4. Formato de Fecha

**Estándar adoptado**: `yyyy-MM-dd` (compatible con SQL DATE)

**Razón**: 
- Formato nativo SQL DATE
- Evita problemas de parsing entre base de datos y cliente
- Compatible con ISO 8601

**Implementación**:
- MapStruct mappers configurados con `dateFormat = "yyyy-MM-dd"`
- Conversión automática entre `java.util.Date`/`java.sql.Timestamp` ↔ `String`

**Ejemplos**:
```java
// En DTOs: "2026-02-03"
// En entidades: Date/Timestamp
// MapStruct convierte automáticamente
```

---

## 5. Compatibilidad hacia atrás

Los DTOs antiguos (`UsuarioRegistroDTO`, `ObjetivoDTO`, `InformeDTO`) **siguen existiendo** y funcionando en el código actual.

**Estrategia de migración gradual**:
1. Los nuevos DTOs son **aditivos**, no reemplazan los existentes
2. Endpoints existentes siguen funcionando sin cambios
3. Nuevos endpoints o refactorizaciones pueden usar los nuevos tipos
4. Migración recomendada por endpoint/funcionalidad

---

## 6. Guía de Uso

### Creación de Usuario
```java
// Controller recibe UsuarioRegistroRequestDTO
@PostMapping("/usuarios")
public ResponseEntity<UsuarioRegistroResponseDTO> crear(
    @RequestBody UsuarioRegistroRequestDTO request) {
    
    // Servicio procesa y retorna UsuarioRegistroResponseDTO
    UsuarioRegistroResponseDTO response = usuarioService.crear(request);
    return ResponseEntity.ok(response);
}
```

### Creación de Objetivo con Informe Inicial
```java
// Controller recibe ObjetivoAggregateDto
@PostMapping("/objetivos/con-informe-inicial")
public ResponseEntity<ObjetivoAggregateDto> crearConInforme(
    @RequestBody ObjetivoAggregateDto aggregate) {
    
    // Servicio descompone y persiste objetivo + informe inicial
    ObjetivoAggregateDto resultado = objetivoService.crearConInformeInicial(aggregate);
    return ResponseEntity.ok(resultado);
}
```

### Creación de Informe
```java
// Controller recibe InformeRequestDTO
@PostMapping("/informes")
public ResponseEntity<InformeDTO> crear(
    @RequestBody InformeRequestDTO request) {
    
    InformeDTO response = informeService.crear(request);
    return ResponseEntity.ok(response);
}
```

---

## 7. Diagrama de Modelo

```
Usuario:
    UsuarioAbstractDTO (abstract)
        ├── UsuarioRegistroRequestDTO
        └── UsuarioRegistroResponseDTO

Objetivo:
    ObjetivoRecord (record básico)
    ObjetivoAggregateDto (con InformeInicialDTO)

Informe:
    InformeAbstractDTO (record base conceptual)
        ├── InformeRequestDTO (request completo - entrenadores)
        ├── InformeInicialDTO (primer informe - inicio objetivo)
        └── InformeSimplifiedDTO (seguimiento básico - usuarios autónomos)
```

### Matriz de Uso por Rol

| DTO | Entrenador | Usuario Autónomo | Cliente con Entrenador |
|-----|------------|------------------|------------------------|
| `InformeRequestDTO` | ✅ Completo | ❌ Demasiado complejo | ✅ Vía entrenador |
| `InformeInicialDTO` | ✅ Inicio objetivo | ✅ Inicio objetivo | ✅ Inicio objetivo |
| `InformeSimplifiedDTO` | ❌ Muy básico | ✅ Seguimiento diario | ❌ Necesita detalle |

---

## 8. Próximos Pasos

### Inmediatos
- [ ] Compilación verificada ✅
- [ ] Documentación creada ✅
- [ ] DTOs completados ✅

### Recomendados
- [ ] Migrar `UsuarioRegistroController` a nuevos DTOs
- [ ] Crear endpoint `POST /objetivos/con-informe-inicial` que use `ObjetivoAggregateDto`
- [ ] Actualizar mappers para conversión automática
- [ ] Añadir tests unitarios para nuevos DTOs
- [ ] Validar serialización/deserialización JSON

### Opcionales
- [ ] Deprecar DTOs antiguos gradualmente
- [ ] Crear mappers entre DTOs antiguos y nuevos
- [ ] Documentar con OpenAPI/Swagger

---

**Fecha de creación**: 2026-02-03  
**Versión**: 1.0  
**Estado**: ✅ Completado y verificado
