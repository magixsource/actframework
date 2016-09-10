# Meta data inspection

This page inspect the current Meta data structures

## Why do we use Meta data

### Controller

Scanner need the following information to register route mapping:

* Action annotations (`GetAction`, `PostAction` etc)
* Action handler method name and controller class name
* Context path for full URL concatenation. This will refer to the parent context path

Proxy will use the meta information to construct action handlers and interceptors

* Method meta information
* With list to find out delegate interceptors

Enhancer will attach local variable meta info to method meta info for render arg populating enhancement

### Commander

Scanner collect the following information and register command to `CliDispatcher`

* Option annotations
* Command method name and commander class name

Proxy will use the meta information to constructor command executor

Help will use the meta information to display command helps and the command list as well

### Mailer

TBD


## Controller and Action Handler

### ControllerClassMetaInfo

Fields:

* type: Type
* superType: Type
* isAbstract: boolean
* ctxField: String
* ctxFieldIsPrivate: boolean
* withList: `Set<String>`
* actions: `List<ActionMethodMetaInfo>`
* actionLookup: `Map<String, ActionMethodMetaInfo>`
* handlerLookup: `Map<String, HandlerMethodMetaInfo>`
* interceptors: GroupInterceptorMetaInfo
* parent: ControllerClassMetaInfo
* isController: boolean
* possibleController: boolean
* contextPath: String

### ActionMethodMetaInfo

Inherit from: Handler Method

Fields:

* interceptors: GroupInterceptorMetaInfo


### GroupInterceptorMetaInfo

Fields:

* beforeList: `List<InterceptorMethodMetaInfo>`
* afterList: `List<InterceptorMethodMetaInfo>`
* catchList: `List<InterceptorMethodMetaInfo>`
* finallyList: `List<InterceptorMethodMetaInfo>`

### InterceptorMethodMetaInfo
 
Inherited from: HandlerMethodMetaInfo

Fields:

* whiteList: `Set<String>`
* blackList: `Set<String>`
* priority: int

### HandlerMethodMetaInfo

Fields:

* name: String
* invokeType: InvokeType
* actContextInjection: ActContextInjection
* clsInfo: ControllerClassMetaInfo
* params: `List<HandlerParamMetaInfo>`
* returnType: ReturnTypeInfo
* propertySpec: PropertySpec.MetaInfo
* disableJsonCircularRefDetect: boolean
* locals: `Map<Label, Map<Integer, LocalVariableMetaInfo>>`
* appCtxLVT_id: int
* ctxParamCnt: int

### HandlerParamMetaInfo

Fields:

* name: String
* type: Type
* componentType: Type
* context: boolean
* paramAnno: ParamAnnoInfo
* bindAnno: BindAnnoInfo
* generalAnnoInfoList: `List<GeneralAnnoInfo>`

### ParamAnnoInfo

Inherited from: ParamAnnoInfoTraitBase

Fields:

* bindName: String
* resolver: `Class<? extends StringValueResolver>`
* defValMap: `Map<Class, Object>`

### BindAnnoInfo

Inherited from: ParamAnnoInfoTraitBase

Fields:

* binder: Class<? extends Binder>
* model: String

### ParamAnnoInfoTraitBase

Fields:

* index: int


## Commander and command handler

### CommanderClassMetaInfo

Fields:

* type: Type
* superType: Type
* isAbstract: boolean
* commands: `List<CommandMethodMetaInfo>`
* fieldOptionAnnoInfoMap: `Map<String, FieldOptionAnnoInfo>`
* fieldSessionVariableAnnoInfoMap: `Map<String, SessionVariableAnnoInfo>`
* commandLookup: `Map<String, CommandMethodMetaInfo>`
* parent: CommanderClassMetaInfo
* contextPath: String

### CommandMethodMetaInfo

Fields:

* methodName: String
* commandName: String
* helpMsg: String
* invokeType: InvokeType
* clsInfo: CommanderClassMetaInfo
* propertySpec: PropertySpec.MetaInfo
* params: `List<CommandParamMetaInfo>`
* returnType: ReturnTypeInfo
* optionLeads: `Set<String>`
* view: CliView
* mode: Act.Mode
* ctxParamCnt: int

### FieldOptionAnnoInfo

Extends from: OptionAnnoInfoBase

Fields:

* fieldName: String
* type: Type
* readFileContent: boolean

### SessionVariableAnnoInfo

Fields:

* name: String

### CommandParamMetaInfo

Fields:

* name: String
* type: Type
* componentType: Type
* readFileContent: boolean
* context: boolean
* cliSessionAttributeKey: String
* optionInfo: ParamOptionAnnoInfo

### ParamOptionAnnoInfo

Extends from: OptionAnnoInfoBase

Fields:

* index: int

### OptionAnnoInfoBase

Fields:

* lead1: String
* lead2: String
* defVal: String
* group: String
* help: String
* required: boolean
* param: String

## Mailer and send mail methods

### MailerClassMetaInfo

Fields:

* type: Type
* configId: String
* isAbstract: boolean
* ctxField: String
* ctxFieldIsPrivate: boolean
* senders: `List<SenderMethodMetaInfo>`
* mailerLookup: `Map<String, SenderMethodMetaInfo>`
* isMailer: boolean
* contextPath: String

## Job and job methods

### JobClassMetaInfo

Fields

* type: Type
* superType: Type
* isAbstract: boolean
* actions: `List<JobMethodMetaInfo>`
* actionLookup: `Map<String, JobMethodMetaInfo>`
* isJob: boolean


## Class Meta Info Summary

| Field | Type | Controller | Commander | Mailer | Job | Usage | Keep | Note | 
| --- | --- | --- | --- | --- | --- | ---| --- | --- |
| type | Type | Yes | Yes | Yes | Yes | key to access the meta info from registry | all | n/a |
| superType | Type | Yes | Yes | No | Yes | (controller only) fetch super controller meta info for interceptor processing | ? | n/a |
| isAbstract | boolean | Yes | Yes | Yes | Yes | Job registration use it (probably is a fault way) | ? | n/a |
| ctxField | String | Yes | No | Yes | No | Obsoleted | No | n/a |
| ctxFieldIsPrivate | boolean | Yes | NO | Yes | No | Obsoleted | No | n/a |
| withList | `Set<String>` | Yes | No | No | No | Keep with list for interceptor delegation | Controller only | n/a |
| method meta list | `List<Method Meta Info>` | actions | commands | senders | actions | keep track meta info of methods in this class | all | n/a |
| contextPath | String | Yes | Yes (by mistake) | Yes (by mistake) | No | Stores controller context path info to contatenate full URL | controller only | n/a |

