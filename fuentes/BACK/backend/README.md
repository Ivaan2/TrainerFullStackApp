# Trainer

Este README.md recoge todos los requerimientos técnicos que detallan el propósito, las funcionalidades y el objetivo real de este proyecto para trackear, generar y personalizar dietas individuales marcadas en un plazo y forma definidas. El objetivo es que el usuario logre sus propósitos según unas métricas calculadas y bien definidas según criterios, evolución e historial del cliente. Dándose por sobre entendido que se pueden lograr cambios 100% identificables sin necesidad de cumplir con una dieta estricta. Lograremos el objetivo de alcanzar nuestra mejor forma física en un plazo amplio de meses, con aproximandamente el 70-80% del seguimiento recomendado. 
Hacemos uso de peticiones a herramientas de IA generativa para ayudar al Software durante el proceso, por lo cual existen restricciones a nivel de usuario, planes de suscripción y políticas de uso responsable.

## ✨ Funcionalidad principal

### 1. Autenticación y Registro con Google

- Los usuarios acceden a la app mediante su cuenta de Google (OAuth2).
- Tras autenticarse, se les asocia un **token JWT** que permite acceder al resto de funcionalidades.
- El backend verifica y almacena la identidad del usuario para posteriores operaciones.

### 2. Gestión de Usuarios y Tokens

- Cada usuario registrado dispone de una cuenta en la base de datos.
- Se realiza seguimiento de:
  - Tokens disponibles.
  - Historial de generación de dietas.
  - Fecha de registro, correo y metadatos clave.
- Los tokens controlan el número de veces que un usuario puede solicitar dietas. Cuando se agotan, debe recargar mediante pago.

### 3. Generación de Dietas por IA

- Los usuarios pueden generar dietas personalizadas según sus objetivos:
  - Ejemplo: "Bajar 10 kg en 8 meses sin perder masa muscular".
- El sistema genera un **prompt estructurado** y lo envía a un modelo de lenguaje (LLM) como OpenAI.
- La respuesta es parseada y entregada en formato estructurado (lista de comidas, calorías, distribución de macronutrientes...).
- Cada llamada consume un token.

### 4. Control de Uso y Seguridad

- Antes de generar una dieta, el backend:
  - Verifica el JWT del usuario.
  - Comprueba si dispone de tokens disponibles.
  - Registra el uso para trazabilidad y seguridad.
- Todas las llamadas a la IA están protegidas por lógica de negocio y autenticación.

### 5. Sistema de Pago y Recarga de Tokens

- Los usuarios pueden adquirir más tokens mediante pago.
- La integración con **Stripe** permite:
  - Pagos únicos.
  - Subscripciones mensuales.
  - Webhooks para actualizar el saldo de tokens automáticamente.
- Se registra el historial de pagos y tokens recargados.

Use the package manager [pip](https://pip.pypa.io/en/stable/) to install foobar.

```bash
pip install foobar
```

## Usage

```python
import foobar

# returns 'words'
foobar.pluralize('word')

# returns 'geese'
foobar.pluralize('goose')

# returns 'phenomenon'
foobar.singularize('phenomena')
```

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

Todos los derechos reservados.