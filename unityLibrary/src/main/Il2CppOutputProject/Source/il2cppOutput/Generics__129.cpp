#include "pch-cpp.hpp"





template <typename R>
struct VirtualFuncInvoker0
{
	typedef R (*Func)(void*, const RuntimeMethod*);

	static inline R Invoke (Il2CppMethodSlot slot, RuntimeObject* obj)
	{
		const VirtualInvokeData& invokeData = il2cpp_codegen_get_virtual_invoke_data(slot, obj);
		return ((Func)invokeData.methodPtr)(obj, invokeData.method);
	}
};
template <typename T1, typename T2>
struct InvokerActionInvoker2;
template <typename T1, typename T2>
struct InvokerActionInvoker2<T1, T2*>
{
	static inline void Invoke (Il2CppMethodPointer methodPtr, const RuntimeMethod* method, void* obj, T1 p1, T2* p2)
	{
		void* params[2] = { &p1, p2 };
		method->invoker_method(methodPtr, method, obj, params, params[1]);
	}
};
template <typename T1, typename T2, typename T3>
struct InvokerActionInvoker3;
template <typename T1, typename T2, typename T3>
struct InvokerActionInvoker3<T1*, T2, T3*>
{
	static inline void Invoke (Il2CppMethodPointer methodPtr, const RuntimeMethod* method, void* obj, T1* p1, T2 p2, T3* p3)
	{
		void* params[3] = { p1, &p2, p3 };
		method->invoker_method(methodPtr, method, obj, params, params[2]);
	}
};

struct SpanAction_2_t65B015FEFE1F64814AC2EFA0E19A38B1CFC53178;
struct SpanAction_2_t84FDFFEECCC96A9A407DCB490E60340E38185947;
struct SpanAction_2_t5907E59A3FE410EE3FC9FC29F5E9418DF5894C8B;
struct HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C;
struct Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43;
struct ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3;
struct AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61;
struct ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F;
struct AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1;
struct BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80;
struct BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963;
struct BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4;
struct BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E;
struct BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B;
struct BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515;
struct BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A;
struct BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8;
struct BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F;
struct BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A;
struct BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651;
struct BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4;
struct BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47;
struct BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5;
struct ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031;
struct CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB;
struct DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771;
struct IntPtrU5BU5D_tFD177F8C806A6921AD7150264CCC62FA00CAD832;
struct StackTraceU5BU5D_t32FBCB20930EAF5BAE3F450FF75228E5450DA0DF;
struct TypeU5BU5D_t97234E1129B564EB38B8D85CAC2AD8B5B9522FFB;
struct BatchDrawCommand_t9DAB9B739DA9EBC6B1BB0D2DD51AF65B12F66BF0;
struct BatchDrawCommandIndirect_t06F3B5DC4AF7D4B115CEECDB0A1E4E92065BC705;
struct BatchDrawCommandProcedural_t5780BD7745B5772AA54BA6E6021292F6A6553B86;
struct BatchDrawCommandProceduralIndirect_t8417CDF3495237CD8C9204B3DADC6BA4A1488B50;
struct BatchDrawRange_t736D745E918A629658B80A0F7D2D17DAFBA6172F;
struct Binder_t91BFCE95A7057FADF4D8A1A342AFE52872246235;
struct Delegate_t;
struct DelegateData_t9B286B493293CD2D23A5B2B5EF0E5B1324C2B77E;
struct EntityId_t982FBD037EAC5CA077B1602A7EA40E3523AA0FC8;
struct IDictionary_t6D03155AF1FA9083817AA5B6AD7DEEACC26AB220;
struct Int32_t680FF22E76F6EFAD4375103CBBFFA0421349384C;
struct MemberFilter_tF644F1AE82F611B677CE1964D5A3277DDA21D553;
struct MethodInfo_t;
struct NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A;
struct SafeSerializationManager_tCBB85B95DFD1634237140CD892E82D06ECB3F5E6;
struct Single_t4530F2FF86FCB0DC29F35385CA1BD21BE294761C;
struct String_t;
struct Type_t;
struct Void_t4861ACF8F4594C3437BB48B6E56783494B843915;

IL2CPP_EXTERN_C RuntimeClass* NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C String_t* _stringLiteral0DB46164953228904843938099AF66650313FEE5;
IL2CPP_EXTERN_C String_t* _stringLiteral69508A540AFD085A745316DD7D6345B1C8CC662D;
IL2CPP_EXTERN_C String_t* _stringLiteralECE618215BAC99C6FD12D8A273CC2118945EDCC8;
struct Delegate_t_marshaled_com;
struct Delegate_t_marshaled_pinvoke;
struct Exception_t_marshaled_com;
struct Exception_t_marshaled_pinvoke;

struct HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C;
struct Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43;
struct ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3;
struct AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61;
struct ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F;
struct AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1;
struct BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80;
struct BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963;
struct BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4;
struct BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E;
struct BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B;
struct BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515;
struct BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A;
struct BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8;
struct BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F;
struct BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A;
struct BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651;
struct BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4;
struct BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47;
struct BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5;
struct ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031;
struct DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771;

IL2CPP_EXTERN_C_BEGIN
IL2CPP_EXTERN_C_END

#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
struct EmptyArray_1_tB1385D6D2663B69CA2DB28A3D0AE3106C328A232  : public RuntimeObject
{
};
struct EmptyArray_1_t5DEA403BAFDC5AC2F35C8826395BF35F76EAE720  : public RuntimeObject
{
};
struct EmptyArray_1_t3066E86777F40E3D88AD86B8F23145AC18BD3029  : public RuntimeObject
{
};
struct EmptyArray_1_tBFDECE1BC8155A63C1B370E203BBB8DF09C7751F  : public RuntimeObject
{
};
struct EmptyArray_1_t910BE047C1A6E9E334831BFBF9773D3C3852ED95  : public RuntimeObject
{
};
struct EmptyArray_1_t01488E30FA2A207693FD84D9BAB7B4A73493EF80  : public RuntimeObject
{
};
struct EmptyArray_1_tA6E339CF983886C190886835190061C2E3B679F2  : public RuntimeObject
{
};
struct EmptyArray_1_t4A2E4F90BFE6F5BF33923E2991998A37CB5A4291  : public RuntimeObject
{
};
struct EmptyArray_1_t84EDA74A3D98A25186F3A07E5EA5775E4D2941B0  : public RuntimeObject
{
};
struct EmptyArray_1_t6468C707526910A9B109C3B1C7EE4E98C7890339  : public RuntimeObject
{
};
struct EmptyArray_1_tD61E793B4EDBEE4AA2790494DEAA1AFF267A1F35  : public RuntimeObject
{
};
struct EmptyArray_1_t080248E0CE94C9EDF86D6BBE84F395794E5E43C3  : public RuntimeObject
{
};
struct EmptyArray_1_tA290AEF3927090408C0E0B2EC14CB3DB2C5163A6  : public RuntimeObject
{
};
struct EmptyArray_1_t353CE6E273731A6220E1AD1A702E6AF00E235055  : public RuntimeObject
{
};
struct EmptyArray_1_tF2E6E6E2B72D0BF7ECB89ECF4FD5717E4EDE97BF  : public RuntimeObject
{
};
struct EmptyArray_1_tA656C73B1CB77366F398AB7451929D79699A6181  : public RuntimeObject
{
};
struct EmptyArray_1_t200AEA0094BFCA9414824E328EC31EAB52F2142C  : public RuntimeObject
{
};
struct EmptyArray_1_t7902087DD0C0221C9C7362ABAB7CB57D6D519A99  : public RuntimeObject
{
};
struct EmptyArray_1_tD654E393754C56C1AAE25F6EE3FF0DD03AE5E291  : public RuntimeObject
{
};
struct EmptyArray_1_t42D5099F7FCE2834141926DB372392B8C75ADBEC  : public RuntimeObject
{
};
struct EmptyArray_1_t7187E746F328254739F076CFBCAABB28D4B4554C  : public RuntimeObject
{
};
struct SpanDebugView_1_t6B249F4737457563D0548242B2E940C385BF66E5  : public RuntimeObject
{
};
struct MemberInfo_t  : public RuntimeObject
{
};
struct String_t  : public RuntimeObject
{
	int32_t ____stringLength;
	Il2CppChar ____firstChar;
};
struct ValueType_t6D9B272BD21782F0A9A14F2E41F85A50E97A986F  : public RuntimeObject
{
};
struct ValueType_t6D9B272BD21782F0A9A14F2E41F85A50E97A986F_marshaled_pinvoke
{
};
struct ValueType_t6D9B272BD21782F0A9A14F2E41F85A50E97A986F_marshaled_com
{
};
struct HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5 
{
	int32_t ___hash;
	int32_t ___lastFrameUsed;
	RuntimeObject* ___compiledGraph;
};
struct Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 
{
	bool ___hasValue;
	int32_t ___value;
};
struct ValueTuple_3_tFD2ADB3DA89E958885034AAFEF1ABDA8C814D987 
{
	RuntimeObject* ___Item1;
	int32_t ___Item2;
	int32_t ___Item3;
};
struct ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA 
{
	uint8_t ___Item1;
	uint8_t ___Item2;
	uint8_t ___Item3;
	uint8_t ___Item4;
};
struct BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D 
{
	BatchDrawCommand_t9DAB9B739DA9EBC6B1BB0D2DD51AF65B12F66BF0* ___drawCommands;
	BatchDrawCommandIndirect_t06F3B5DC4AF7D4B115CEECDB0A1E4E92065BC705* ___indirectDrawCommands;
	BatchDrawCommandProcedural_t5780BD7745B5772AA54BA6E6021292F6A6553B86* ___proceduralDrawCommands;
	BatchDrawCommandProceduralIndirect_t8417CDF3495237CD8C9204B3DADC6BA4A1488B50* ___proceduralIndirectDrawCommands;
	int32_t* ___visibleInstances;
	BatchDrawRange_t736D745E918A629658B80A0F7D2D17DAFBA6172F* ___drawRanges;
	float* ___instanceSortingPositions;
	EntityId_t982FBD037EAC5CA077B1602A7EA40E3523AA0FC8* ___drawCommandPickingEntityIds;
	int32_t ___drawCommandCount;
	int32_t ___indirectDrawCommandCount;
	int32_t ___proceduralDrawCommandCount;
	int32_t ___proceduralIndirectDrawCommandCount;
	int32_t ___visibleInstanceCount;
	int32_t ___drawRangeCount;
	int32_t ___instanceSortingPositionFloatCount;
};
struct BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770 
{
	uint32_t ___value;
};
struct BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C 
{
	uint32_t ___value;
};
struct BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0 
{
	uint32_t ___value;
};
struct BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F 
{
	float ___m_Weight0;
	float ___m_Weight1;
	float ___m_Weight2;
	float ___m_Weight3;
	int32_t ___m_BoneIndex0;
	int32_t ___m_BoneIndex1;
	int32_t ___m_BoneIndex2;
	int32_t ___m_BoneIndex3;
};
struct Boolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22 
{
	bool ___m_value;
};
struct Byte_t94D9231AC217BE4D2E004C4CD32DF6D099EA41A3 
{
	uint8_t ___m_value;
};
struct Char_t521A6F19B456D956AF452D926C32709DC03D6B17 
{
	Il2CppChar ___m_value;
};
struct Color_tD001788D726C3A7F1379BEED0260B9591F440C1F 
{
	float ___r;
	float ___g;
	float ___b;
	float ___a;
};
struct Enum_t2A1A94B24E3B776EEF4E5E485E290BB9D4D072E2  : public ValueType_t6D9B272BD21782F0A9A14F2E41F85A50E97A986F
{
};
struct Enum_t2A1A94B24E3B776EEF4E5E485E290BB9D4D072E2_marshaled_pinvoke
{
};
struct Enum_t2A1A94B24E3B776EEF4E5E485E290BB9D4D072E2_marshaled_com
{
};
struct Int32_t680FF22E76F6EFAD4375103CBBFFA0421349384C 
{
	int32_t ___m_value;
};
struct IntPtr_t 
{
	void* ___m_value;
};
struct UInt32_t1833D51FFA667B18A5AA4B8D34DE284F8495D29B 
{
	uint32_t ___m_value;
};
struct UInt64_t8F12534CC8FC4B5860F2A2CD1EE79D322E7A41AF 
{
	uint64_t ___m_value;
};
struct Vector3_t24C512C7B96BBABAD472002D0BA2BDA40A5A80B2 
{
	float ___x;
	float ___y;
	float ___z;
};
struct Void_t4861ACF8F4594C3437BB48B6E56783494B843915 
{
	union
	{
		struct
		{
		};
		uint8_t Void_t4861ACF8F4594C3437BB48B6E56783494B843915__padding[1];
	};
};
struct float3_t4AB5D88249ADB24F69FFD0793E8ED25E1CC3745E 
{
	float ___x;
	float ___y;
	float ___z;
};
struct float4_t89D9A294E7A79BD81BFBDD18654508532958555E 
{
	float ___x;
	float ___y;
	float ___z;
	float ___w;
};
struct int4_tBA77D4945786DE82C3A487B33955EA1004996052 
{
	int32_t ___x;
	int32_t ___y;
	int32_t ___z;
	int32_t ___w;
};
struct ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 
{
	intptr_t ____value;
};
struct ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C 
{
	intptr_t ____value;
};
struct ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E 
{
	intptr_t ____value;
};
struct ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 
{
	intptr_t ____value;
};
struct ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 
{
	intptr_t ____value;
};
struct ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 
{
	intptr_t ____value;
};
struct ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 
{
	intptr_t ____value;
};
struct ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E 
{
	intptr_t ____value;
};
struct ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 
{
	intptr_t ____value;
};
struct ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B 
{
	intptr_t ____value;
};
struct ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F 
{
	intptr_t ____value;
};
struct ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 
{
	intptr_t ____value;
};
struct ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF 
{
	intptr_t ____value;
};
struct ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 
{
	intptr_t ____value;
};
struct ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 
{
	intptr_t ____value;
};
struct ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 
{
	intptr_t ____value;
};
struct ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 
{
	intptr_t ____value;
};
struct ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 
{
	intptr_t ____value;
};
struct ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A 
{
	intptr_t ____value;
};
struct ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B 
{
	intptr_t ____value;
};
struct ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC 
{
	intptr_t ____value;
};
struct ByReference_1_t7BA5A6CA164F770BC688F21C5978D368716465F5 
{
	intptr_t ____value;
};
struct ByReference_1_t607C1F3BC28B0E21B969461CDB0720FB01A82141 
{
	intptr_t ____value;
};
struct ValueTuple_5_t558B9F95CA55DE5694FC58A3BEAE441BF728FB57 
{
	intptr_t ___Item1;
	int32_t ___Item2;
	intptr_t ___Item3;
	int32_t ___Item4;
	bool ___Item5;
};
struct AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A 
{
	float3_t4AB5D88249ADB24F69FFD0793E8ED25E1CC3745E ___center;
	float3_t4AB5D88249ADB24F69FFD0793E8ED25E1CC3745E ___extents;
};
struct ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E 
{
	intptr_t ___m_TextElementInfosPtr;
	int32_t ___m_TextElementCount;
	int32_t ___textAssetId;
};
struct BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54 
{
	float4_t89D9A294E7A79BD81BFBDD18654508532958555E ____data;
};
struct BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010 
{
	Vector3_t24C512C7B96BBABAD472002D0BA2BDA40A5A80B2 ___position;
	float ___radius;
};
struct Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3 
{
	Vector3_t24C512C7B96BBABAD472002D0BA2BDA40A5A80B2 ___m_Center;
	Vector3_t24C512C7B96BBABAD472002D0BA2BDA40A5A80B2 ___m_Extents;
};
struct BuiltinRenderTextureType_t3D56813CAC7C6E4AC3B438039BD1CE7E62FE7C4E 
{
	int32_t ___value__;
};
struct CubemapFace_t300D6E2CD7DF60D44AA28338748B607677ED1D1B 
{
	int32_t ___value__;
};
struct Delegate_t  : public RuntimeObject
{
	intptr_t ___method_ptr;
	intptr_t ___invoke_impl;
	RuntimeObject* ___m_target;
	intptr_t ___method;
	intptr_t ___delegate_trampoline;
	intptr_t ___extra_arg;
	intptr_t ___method_code;
	intptr_t ___interp_method;
	intptr_t ___interp_invoke_impl;
	MethodInfo_t* ___method_info;
	MethodInfo_t* ___original_method_info;
	DelegateData_t9B286B493293CD2D23A5B2B5EF0E5B1324C2B77E* ___data;
	bool ___method_is_virtual;
};
struct Delegate_t_marshaled_pinvoke
{
	intptr_t ___method_ptr;
	intptr_t ___invoke_impl;
	Il2CppIUnknown* ___m_target;
	intptr_t ___method;
	intptr_t ___delegate_trampoline;
	intptr_t ___extra_arg;
	intptr_t ___method_code;
	intptr_t ___interp_method;
	intptr_t ___interp_invoke_impl;
	MethodInfo_t* ___method_info;
	MethodInfo_t* ___original_method_info;
	DelegateData_t9B286B493293CD2D23A5B2B5EF0E5B1324C2B77E* ___data;
	int32_t ___method_is_virtual;
};
struct Delegate_t_marshaled_com
{
	intptr_t ___method_ptr;
	intptr_t ___invoke_impl;
	Il2CppIUnknown* ___m_target;
	intptr_t ___method;
	intptr_t ___delegate_trampoline;
	intptr_t ___extra_arg;
	intptr_t ___method_code;
	intptr_t ___interp_method;
	intptr_t ___interp_invoke_impl;
	MethodInfo_t* ___method_info;
	MethodInfo_t* ___original_method_info;
	DelegateData_t9B286B493293CD2D23A5B2B5EF0E5B1324C2B77E* ___data;
	int32_t ___method_is_virtual;
};
struct Exception_t  : public RuntimeObject
{
	String_t* ____className;
	String_t* ____message;
	RuntimeObject* ____data;
	Exception_t* ____innerException;
	String_t* ____helpURL;
	RuntimeObject* ____stackTrace;
	String_t* ____stackTraceString;
	String_t* ____remoteStackTraceString;
	int32_t ____remoteStackIndex;
	RuntimeObject* ____dynamicMethods;
	int32_t ____HResult;
	String_t* ____source;
	SafeSerializationManager_tCBB85B95DFD1634237140CD892E82D06ECB3F5E6* ____safeSerializationManager;
	StackTraceU5BU5D_t32FBCB20930EAF5BAE3F450FF75228E5450DA0DF* ___captured_traces;
	IntPtrU5BU5D_tFD177F8C806A6921AD7150264CCC62FA00CAD832* ___native_trace_ips;
	int32_t ___caught_in_unmanaged;
};
struct Exception_t_marshaled_pinvoke
{
	char* ____className;
	char* ____message;
	RuntimeObject* ____data;
	Exception_t_marshaled_pinvoke* ____innerException;
	char* ____helpURL;
	Il2CppIUnknown* ____stackTrace;
	char* ____stackTraceString;
	char* ____remoteStackTraceString;
	int32_t ____remoteStackIndex;
	Il2CppIUnknown* ____dynamicMethods;
	int32_t ____HResult;
	char* ____source;
	SafeSerializationManager_tCBB85B95DFD1634237140CD892E82D06ECB3F5E6* ____safeSerializationManager;
	StackTraceU5BU5D_t32FBCB20930EAF5BAE3F450FF75228E5450DA0DF* ___captured_traces;
	Il2CppSafeArray* ___native_trace_ips;
	int32_t ___caught_in_unmanaged;
};
struct Exception_t_marshaled_com
{
	Il2CppChar* ____className;
	Il2CppChar* ____message;
	RuntimeObject* ____data;
	Exception_t_marshaled_com* ____innerException;
	Il2CppChar* ____helpURL;
	Il2CppIUnknown* ____stackTrace;
	Il2CppChar* ____stackTraceString;
	Il2CppChar* ____remoteStackTraceString;
	int32_t ____remoteStackIndex;
	Il2CppIUnknown* ____dynamicMethods;
	int32_t ____HResult;
	Il2CppChar* ____source;
	SafeSerializationManager_tCBB85B95DFD1634237140CD892E82D06ECB3F5E6* ____safeSerializationManager;
	StackTraceU5BU5D_t32FBCB20930EAF5BAE3F450FF75228E5450DA0DF* ___captured_traces;
	Il2CppSafeArray* ___native_trace_ips;
	int32_t ___caught_in_unmanaged;
};
struct GraphicsFormat_tC3D1898F3F3F1F57256C7F3FFD6BA9A37AE7E713 
{
	int32_t ___value__;
};
struct RenderBufferLoadAction_t3333B2CABABAC39DA0CDC25602E5E4FD93C2CB0E 
{
	int32_t ___value__;
};
struct RenderBufferStoreAction_t87683F22C09634E24A574F21F42037C953A2C8B7 
{
	int32_t ___value__;
};
struct RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B 
{
	intptr_t ___value;
};
struct float3x3_tB318DB8C7E54B6CA9E14EB9AC7F5964C1189FC79 
{
	float3_t4AB5D88249ADB24F69FFD0793E8ED25E1CC3745E ___c0;
	float3_t4AB5D88249ADB24F69FFD0793E8ED25E1CC3745E ___c1;
	float3_t4AB5D88249ADB24F69FFD0793E8ED25E1CC3745E ___c2;
};
struct float4x3_t06008E654CCB12CD786C3E335A1E4C89A8AA13EC 
{
	float4_t89D9A294E7A79BD81BFBDD18654508532958555E ___c0;
	float4_t89D9A294E7A79BD81BFBDD18654508532958555E ___c1;
	float4_t89D9A294E7A79BD81BFBDD18654508532958555E ___c2;
};
struct float4x4_t7EDD16F7F57DC7F61A6302535F7C19FB97915DF2 
{
	float4_t89D9A294E7A79BD81BFBDD18654508532958555E ___c0;
	float4_t89D9A294E7A79BD81BFBDD18654508532958555E ___c1;
	float4_t89D9A294E7A79BD81BFBDD18654508532958555E ___c2;
	float4_t89D9A294E7A79BD81BFBDD18654508532958555E ___c3;
};
struct int4x2_t4B98454F51F5E8A8F4201FA33A01D2692B692996 
{
	int4_tBA77D4945786DE82C3A487B33955EA1004996052 ___c0;
	int4_tBA77D4945786DE82C3A487B33955EA1004996052 ___c1;
};
struct RawData_t37CAF2D3F74B7723974ED7CEEE9B297D8FA64ED0  : public RuntimeObject
{
	intptr_t ___Bounds;
	intptr_t ___Count;
	uint8_t ___Data;
};
struct RawData_t37CAF2D3F74B7723974ED7CEEE9B297D8FA64ED0_marshaled_pinvoke
{
	intptr_t ___Bounds;
	intptr_t ___Count;
	uint8_t ___Data;
};
struct RawData_t37CAF2D3F74B7723974ED7CEEE9B297D8FA64ED0_marshaled_com
{
	intptr_t ___Bounds;
	intptr_t ___Count;
	uint8_t ___Data;
};
struct ReadOnlySpan_1_t4990792F01A3EE12CEEC65526A813F1C0A67283E 
{
	ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 ____pointer;
	int32_t ____length;
};
struct ReadOnlySpan_1_t38A9F2C8804739CCDFBD7062CA1C47C44BAE5AC2 
{
	ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C ____pointer;
	int32_t ____length;
};
struct ReadOnlySpan_1_t4757BCCBBFA890A355792A3EB923FE9C101A9C76 
{
	ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E ____pointer;
	int32_t ____length;
};
struct ReadOnlySpan_1_t32C8835D73D5F2ADCC5ED93FA71F63C5E9763632 
{
	ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 ____pointer;
	int32_t ____length;
};
struct ReadOnlySpan_1_tAE84767975672BD032424E74DD54F304DED0E2A7 
{
	ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 ____pointer;
	int32_t ____length;
};
struct ReadOnlySpan_1_tBA07559B531DF8D3C488A21E738503C11F05E17A 
{
	ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 ____pointer;
	int32_t ____length;
};
struct ReadOnlySpan_1_tE73282FE39C46EDD2EE2F61A9E3E69BE943784B7 
{
	ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 ____pointer;
	int32_t ____length;
};
struct ReadOnlySpan_1_t3D56DFE8743E7EF0665F38A59DA5B99E0F9425B0 
{
	ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E ____pointer;
	int32_t ____length;
};
struct ReadOnlySpan_1_t92B77B310CC53A58C3C04B20E0B7F18C2EEFB130 
{
	ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 ____pointer;
	int32_t ____length;
};
struct ReadOnlySpan_1_tD9DB60FA9C52DCE56999EB3FEEA0CDE0FDFFFFE2 
{
	ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B ____pointer;
	int32_t ____length;
};
struct ReadOnlySpan_1_t3D251E6627DBB106D7D34F8DC234D102B691FD49 
{
	ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F ____pointer;
	int32_t ____length;
};
struct ReadOnlySpan_1_tD81D75B0E0400301DC302920BCCBB9FBD97CA2A8 
{
	ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 ____pointer;
	int32_t ____length;
};
struct ReadOnlySpan_1_t70882B83461088DC23C3DB9AF59753CF91E1F3AA 
{
	ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF ____pointer;
	int32_t ____length;
};
struct ReadOnlySpan_1_t2EFE9320FC985EB4C740E0FD04D094F25024C51D 
{
	ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 ____pointer;
	int32_t ____length;
};
struct ReadOnlySpan_1_t5EE3C4B6EA25BB8F3F956DFD28820F5666342758 
{
	ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 ____pointer;
	int32_t ____length;
};
struct ReadOnlySpan_1_t9DCA2EC81EE91A02D7C140CF3376183B663C483A 
{
	ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 ____pointer;
	int32_t ____length;
};
struct ReadOnlySpan_1_t29FEC1E8134CF98858AC01DEE01F4B20811F583F 
{
	ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 ____pointer;
	int32_t ____length;
};
struct ReadOnlySpan_1_t1CABD9AC48FDD39B6D2C26BCA3D87FCA5F94F1C2 
{
	ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 ____pointer;
	int32_t ____length;
};
struct ReadOnlySpan_1_t5D1B06A3C861E766B5669E3742152A803D0F24AA 
{
	ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A ____pointer;
	int32_t ____length;
};
struct ReadOnlySpan_1_tCA4391229D70967C5F52C769440BB78CBF80BE99 
{
	ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B ____pointer;
	int32_t ____length;
};
struct ReadOnlySpan_1_tA850A6C0E88ABBA37646A078ACBC24D6D5FD9B4D 
{
	ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC ____pointer;
	int32_t ____length;
};
struct Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B 
{
	ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 ____pointer;
	int32_t ____length;
};
struct Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3 
{
	ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C ____pointer;
	int32_t ____length;
};
struct Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019 
{
	ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E ____pointer;
	int32_t ____length;
};
struct Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352 
{
	ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 ____pointer;
	int32_t ____length;
};
struct Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948 
{
	ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 ____pointer;
	int32_t ____length;
};
struct Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F 
{
	ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 ____pointer;
	int32_t ____length;
};
struct Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428 
{
	ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 ____pointer;
	int32_t ____length;
};
struct Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4 
{
	ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E ____pointer;
	int32_t ____length;
};
struct Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF 
{
	ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 ____pointer;
	int32_t ____length;
};
struct Span_1_t3A087F8D965C32C63C3853E38E112343685765A1 
{
	ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B ____pointer;
	int32_t ____length;
};
struct Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671 
{
	ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F ____pointer;
	int32_t ____length;
};
struct Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C 
{
	ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 ____pointer;
	int32_t ____length;
};
struct Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B 
{
	ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF ____pointer;
	int32_t ____length;
};
struct Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF 
{
	ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 ____pointer;
	int32_t ____length;
};
struct Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C 
{
	ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 ____pointer;
	int32_t ____length;
};
struct Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1 
{
	ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 ____pointer;
	int32_t ____length;
};
struct Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F 
{
	ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 ____pointer;
	int32_t ____length;
};
struct Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51 
{
	ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 ____pointer;
	int32_t ____length;
};
struct Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68 
{
	ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A ____pointer;
	int32_t ____length;
};
struct Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634 
{
	ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B ____pointer;
	int32_t ____length;
};
struct Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305 
{
	ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC ____pointer;
	int32_t ____length;
};
struct Span_1_tEDDF15FCF9EC6DEBA0F696BAACDDBAB9D92C252D 
{
	ByReference_1_t7BA5A6CA164F770BC688F21C5978D368716465F5 ____pointer;
	int32_t ____length;
};
struct Span_1_tDEB40BEFA77B5E4BB49B058CD3050EEA4DD36C54 
{
	ByReference_1_t607C1F3BC28B0E21B969461CDB0720FB01A82141 ____pointer;
	int32_t ____length;
};
struct BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF 
{
	float3x3_tB318DB8C7E54B6CA9E14EB9AC7F5964C1189FC79 ____data;
};
struct BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6 
{
	float4x3_t06008E654CCB12CD786C3E335A1E4C89A8AA13EC ____data;
};
struct BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C 
{
	float4x4_t7EDD16F7F57DC7F61A6302535F7C19FB97915DF2 ____data;
};
struct BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901 
{
	int4x2_t4B98454F51F5E8A8F4201FA33A01D2692B692996 ____data;
};
struct BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472 
{
	float4x3_t06008E654CCB12CD786C3E335A1E4C89A8AA13EC ____localData;
	float4x4_t7EDD16F7F57DC7F61A6302535F7C19FB97915DF2 ____globalData;
};
struct MulticastDelegate_t  : public Delegate_t
{
	DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* ___delegates;
};
struct MulticastDelegate_t_marshaled_pinvoke : public Delegate_t_marshaled_pinvoke
{
	Delegate_t_marshaled_pinvoke** ___delegates;
};
struct MulticastDelegate_t_marshaled_com : public Delegate_t_marshaled_com
{
	Delegate_t_marshaled_com** ___delegates;
};
struct RenderTargetIdentifier_tA528663AC6EB3911D8E91AA40F7070FA5455442B 
{
	int32_t ___m_Type;
	int32_t ___m_NameID;
	int32_t ___m_InstanceID;
	intptr_t ___m_BufferPointer;
	int32_t ___m_MipLevel;
	int32_t ___m_CubeFace;
	int32_t ___m_DepthSlice;
};
struct SystemException_tCC48D868298F4C0705279823E34B00F4FBDB7295  : public Exception_t
{
};
struct Type_t  : public MemberInfo_t
{
	RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B ____impl;
};
struct SpanAction_2_t65B015FEFE1F64814AC2EFA0E19A38B1CFC53178  : public MulticastDelegate_t
{
};
struct SpanAction_2_t84FDFFEECCC96A9A407DCB490E60340E38185947  : public MulticastDelegate_t
{
};
struct SpanAction_2_t5907E59A3FE410EE3FC9FC29F5E9418DF5894C8B  : public MulticastDelegate_t
{
};
struct AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E 
{
	int32_t ___m_LoadAction;
	int32_t ___m_StoreAction;
	int32_t ___m_Format;
	RenderTargetIdentifier_tA528663AC6EB3911D8E91AA40F7070FA5455442B ___m_LoadStoreTarget;
	RenderTargetIdentifier_tA528663AC6EB3911D8E91AA40F7070FA5455442B ___m_ResolveTarget;
	Color_tD001788D726C3A7F1379BEED0260B9591F440C1F ___m_ClearColor;
	float ___m_ClearDepth;
	uint32_t ___m_ClearStencil;
};
struct NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A  : public SystemException_tCC48D868298F4C0705279823E34B00F4FBDB7295
{
};
struct EmptyArray_1_tB1385D6D2663B69CA2DB28A3D0AE3106C328A232_StaticFields
{
	HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C* ___Value;
};
struct EmptyArray_1_t5DEA403BAFDC5AC2F35C8826395BF35F76EAE720_StaticFields
{
	Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* ___Value;
};
struct EmptyArray_1_t3066E86777F40E3D88AD86B8F23145AC18BD3029_StaticFields
{
	ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3* ___Value;
};
struct EmptyArray_1_tBFDECE1BC8155A63C1B370E203BBB8DF09C7751F_StaticFields
{
	AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61* ___Value;
};
struct EmptyArray_1_t910BE047C1A6E9E334831BFBF9773D3C3852ED95_StaticFields
{
	ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F* ___Value;
};
struct EmptyArray_1_t01488E30FA2A207693FD84D9BAB7B4A73493EF80_StaticFields
{
	AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1* ___Value;
};
struct EmptyArray_1_tA6E339CF983886C190886835190061C2E3B679F2_StaticFields
{
	BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80* ___Value;
};
struct EmptyArray_1_t4A2E4F90BFE6F5BF33923E2991998A37CB5A4291_StaticFields
{
	BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963* ___Value;
};
struct EmptyArray_1_t84EDA74A3D98A25186F3A07E5EA5775E4D2941B0_StaticFields
{
	BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4* ___Value;
};
struct EmptyArray_1_t6468C707526910A9B109C3B1C7EE4E98C7890339_StaticFields
{
	BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E* ___Value;
};
struct EmptyArray_1_tD61E793B4EDBEE4AA2790494DEAA1AFF267A1F35_StaticFields
{
	BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B* ___Value;
};
struct EmptyArray_1_t080248E0CE94C9EDF86D6BBE84F395794E5E43C3_StaticFields
{
	BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515* ___Value;
};
struct EmptyArray_1_tA290AEF3927090408C0E0B2EC14CB3DB2C5163A6_StaticFields
{
	BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A* ___Value;
};
struct EmptyArray_1_t353CE6E273731A6220E1AD1A702E6AF00E235055_StaticFields
{
	BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8* ___Value;
};
struct EmptyArray_1_tF2E6E6E2B72D0BF7ECB89ECF4FD5717E4EDE97BF_StaticFields
{
	BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F* ___Value;
};
struct EmptyArray_1_tA656C73B1CB77366F398AB7451929D79699A6181_StaticFields
{
	BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A* ___Value;
};
struct EmptyArray_1_t200AEA0094BFCA9414824E328EC31EAB52F2142C_StaticFields
{
	BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651* ___Value;
};
struct EmptyArray_1_t7902087DD0C0221C9C7362ABAB7CB57D6D519A99_StaticFields
{
	BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4* ___Value;
};
struct EmptyArray_1_tD654E393754C56C1AAE25F6EE3FF0DD03AE5E291_StaticFields
{
	BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47* ___Value;
};
struct EmptyArray_1_t42D5099F7FCE2834141926DB372392B8C75ADBEC_StaticFields
{
	BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5* ___Value;
};
struct EmptyArray_1_t7187E746F328254739F076CFBCAABB28D4B4554C_StaticFields
{
	ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* ___Value;
};
struct String_t_StaticFields
{
	String_t* ___Empty;
};
struct BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770_StaticFields
{
	BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770 ___Null;
};
struct BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C_StaticFields
{
	BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C ___Null;
};
struct BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0_StaticFields
{
	BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0 ___Null;
};
struct Boolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_StaticFields
{
	String_t* ___TrueString;
	String_t* ___FalseString;
};
struct Char_t521A6F19B456D956AF452D926C32709DC03D6B17_StaticFields
{
	ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* ___s_categoryForLatin1;
};
struct IntPtr_t_StaticFields
{
	intptr_t ___Zero;
};
struct Type_t_StaticFields
{
	Binder_t91BFCE95A7057FADF4D8A1A342AFE52872246235* ___s_defaultBinder;
	Il2CppChar ___Delimiter;
	TypeU5BU5D_t97234E1129B564EB38B8D85CAC2AD8B5B9522FFB* ___EmptyTypes;
	RuntimeObject* ___Missing;
	MemberFilter_tF644F1AE82F611B677CE1964D5A3277DDA21D553* ___FilterAttribute;
	MemberFilter_tF644F1AE82F611B677CE1964D5A3277DDA21D553* ___FilterName;
	MemberFilter_tF644F1AE82F611B677CE1964D5A3277DDA21D553* ___FilterNameIgnoreCase;
};
#ifdef __clang__
#pragma clang diagnostic pop
#endif
struct DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771  : public RuntimeArray
{
	ALIGN_FIELD (8) Delegate_t* m_Items[1];

	inline Delegate_t* GetAt(il2cpp_array_size_t index) const
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items[index];
	}
	inline Delegate_t** GetAddressAt(il2cpp_array_size_t index)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items + index;
	}
	inline void SetAt(il2cpp_array_size_t index, Delegate_t* value)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		m_Items[index] = value;
		Il2CppCodeGenWriteBarrier((void**)m_Items + index, (void*)value);
	}
	inline Delegate_t* GetAtUnchecked(il2cpp_array_size_t index) const
	{
		return m_Items[index];
	}
	inline Delegate_t** GetAddressAtUnchecked(il2cpp_array_size_t index)
	{
		return m_Items + index;
	}
	inline void SetAtUnchecked(il2cpp_array_size_t index, Delegate_t* value)
	{
		m_Items[index] = value;
		Il2CppCodeGenWriteBarrier((void**)m_Items + index, (void*)value);
	}
};
struct HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C  : public RuntimeArray
{
	ALIGN_FIELD (8) HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5 m_Items[1];

	inline HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5 GetAt(il2cpp_array_size_t index) const
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items[index];
	}
	inline HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* GetAddressAt(il2cpp_array_size_t index)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items + index;
	}
	inline void SetAt(il2cpp_array_size_t index, HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5 value)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		m_Items[index] = value;
		Il2CppCodeGenWriteBarrier((void**)&((m_Items + index)->___compiledGraph), (void*)NULL);
	}
	inline HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5 GetAtUnchecked(il2cpp_array_size_t index) const
	{
		return m_Items[index];
	}
	inline HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* GetAddressAtUnchecked(il2cpp_array_size_t index)
	{
		return m_Items + index;
	}
	inline void SetAtUnchecked(il2cpp_array_size_t index, HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5 value)
	{
		m_Items[index] = value;
		Il2CppCodeGenWriteBarrier((void**)&((m_Items + index)->___compiledGraph), (void*)NULL);
	}
};
struct Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43  : public RuntimeArray
{
	ALIGN_FIELD (8) Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 m_Items[1];

	inline Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 GetAt(il2cpp_array_size_t index) const
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items[index];
	}
	inline Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* GetAddressAt(il2cpp_array_size_t index)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items + index;
	}
	inline void SetAt(il2cpp_array_size_t index, Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 value)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		m_Items[index] = value;
	}
	inline Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 GetAtUnchecked(il2cpp_array_size_t index) const
	{
		return m_Items[index];
	}
	inline Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* GetAddressAtUnchecked(il2cpp_array_size_t index)
	{
		return m_Items + index;
	}
	inline void SetAtUnchecked(il2cpp_array_size_t index, Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 value)
	{
		m_Items[index] = value;
	}
};
struct ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3  : public RuntimeArray
{
	ALIGN_FIELD (8) ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA m_Items[1];

	inline ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA GetAt(il2cpp_array_size_t index) const
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items[index];
	}
	inline ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* GetAddressAt(il2cpp_array_size_t index)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items + index;
	}
	inline void SetAt(il2cpp_array_size_t index, ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA value)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		m_Items[index] = value;
	}
	inline ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA GetAtUnchecked(il2cpp_array_size_t index) const
	{
		return m_Items[index];
	}
	inline ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* GetAddressAtUnchecked(il2cpp_array_size_t index)
	{
		return m_Items + index;
	}
	inline void SetAtUnchecked(il2cpp_array_size_t index, ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA value)
	{
		m_Items[index] = value;
	}
};
struct AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61  : public RuntimeArray
{
	ALIGN_FIELD (8) AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A m_Items[1];

	inline AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A GetAt(il2cpp_array_size_t index) const
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items[index];
	}
	inline AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* GetAddressAt(il2cpp_array_size_t index)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items + index;
	}
	inline void SetAt(il2cpp_array_size_t index, AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A value)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		m_Items[index] = value;
	}
	inline AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A GetAtUnchecked(il2cpp_array_size_t index) const
	{
		return m_Items[index];
	}
	inline AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* GetAddressAtUnchecked(il2cpp_array_size_t index)
	{
		return m_Items + index;
	}
	inline void SetAtUnchecked(il2cpp_array_size_t index, AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A value)
	{
		m_Items[index] = value;
	}
};
struct ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F  : public RuntimeArray
{
	ALIGN_FIELD (8) ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E m_Items[1];

	inline ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E GetAt(il2cpp_array_size_t index) const
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items[index];
	}
	inline ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* GetAddressAt(il2cpp_array_size_t index)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items + index;
	}
	inline void SetAt(il2cpp_array_size_t index, ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E value)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		m_Items[index] = value;
	}
	inline ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E GetAtUnchecked(il2cpp_array_size_t index) const
	{
		return m_Items[index];
	}
	inline ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* GetAddressAtUnchecked(il2cpp_array_size_t index)
	{
		return m_Items + index;
	}
	inline void SetAtUnchecked(il2cpp_array_size_t index, ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E value)
	{
		m_Items[index] = value;
	}
};
struct AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1  : public RuntimeArray
{
	ALIGN_FIELD (8) AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E m_Items[1];

	inline AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E GetAt(il2cpp_array_size_t index) const
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items[index];
	}
	inline AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* GetAddressAt(il2cpp_array_size_t index)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items + index;
	}
	inline void SetAt(il2cpp_array_size_t index, AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E value)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		m_Items[index] = value;
	}
	inline AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E GetAtUnchecked(il2cpp_array_size_t index) const
	{
		return m_Items[index];
	}
	inline AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* GetAddressAtUnchecked(il2cpp_array_size_t index)
	{
		return m_Items + index;
	}
	inline void SetAtUnchecked(il2cpp_array_size_t index, AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E value)
	{
		m_Items[index] = value;
	}
};
struct BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80  : public RuntimeArray
{
	ALIGN_FIELD (8) BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D m_Items[1];

	inline BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D GetAt(il2cpp_array_size_t index) const
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items[index];
	}
	inline BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* GetAddressAt(il2cpp_array_size_t index)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items + index;
	}
	inline void SetAt(il2cpp_array_size_t index, BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D value)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		m_Items[index] = value;
	}
	inline BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D GetAtUnchecked(il2cpp_array_size_t index) const
	{
		return m_Items[index];
	}
	inline BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* GetAddressAtUnchecked(il2cpp_array_size_t index)
	{
		return m_Items + index;
	}
	inline void SetAtUnchecked(il2cpp_array_size_t index, BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D value)
	{
		m_Items[index] = value;
	}
};
struct BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963  : public RuntimeArray
{
	ALIGN_FIELD (8) BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770 m_Items[1];

	inline BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770 GetAt(il2cpp_array_size_t index) const
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items[index];
	}
	inline BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* GetAddressAt(il2cpp_array_size_t index)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items + index;
	}
	inline void SetAt(il2cpp_array_size_t index, BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770 value)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		m_Items[index] = value;
	}
	inline BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770 GetAtUnchecked(il2cpp_array_size_t index) const
	{
		return m_Items[index];
	}
	inline BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* GetAddressAtUnchecked(il2cpp_array_size_t index)
	{
		return m_Items + index;
	}
	inline void SetAtUnchecked(il2cpp_array_size_t index, BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770 value)
	{
		m_Items[index] = value;
	}
};
struct BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4  : public RuntimeArray
{
	ALIGN_FIELD (8) BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C m_Items[1];

	inline BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C GetAt(il2cpp_array_size_t index) const
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items[index];
	}
	inline BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* GetAddressAt(il2cpp_array_size_t index)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items + index;
	}
	inline void SetAt(il2cpp_array_size_t index, BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C value)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		m_Items[index] = value;
	}
	inline BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C GetAtUnchecked(il2cpp_array_size_t index) const
	{
		return m_Items[index];
	}
	inline BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* GetAddressAtUnchecked(il2cpp_array_size_t index)
	{
		return m_Items + index;
	}
	inline void SetAtUnchecked(il2cpp_array_size_t index, BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C value)
	{
		m_Items[index] = value;
	}
};
struct BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E  : public RuntimeArray
{
	ALIGN_FIELD (8) BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0 m_Items[1];

	inline BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0 GetAt(il2cpp_array_size_t index) const
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items[index];
	}
	inline BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* GetAddressAt(il2cpp_array_size_t index)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items + index;
	}
	inline void SetAt(il2cpp_array_size_t index, BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0 value)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		m_Items[index] = value;
	}
	inline BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0 GetAtUnchecked(il2cpp_array_size_t index) const
	{
		return m_Items[index];
	}
	inline BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* GetAddressAtUnchecked(il2cpp_array_size_t index)
	{
		return m_Items + index;
	}
	inline void SetAtUnchecked(il2cpp_array_size_t index, BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0 value)
	{
		m_Items[index] = value;
	}
};
struct BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B  : public RuntimeArray
{
	ALIGN_FIELD (8) BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF m_Items[1];

	inline BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF GetAt(il2cpp_array_size_t index) const
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items[index];
	}
	inline BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* GetAddressAt(il2cpp_array_size_t index)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items + index;
	}
	inline void SetAt(il2cpp_array_size_t index, BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF value)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		m_Items[index] = value;
	}
	inline BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF GetAtUnchecked(il2cpp_array_size_t index) const
	{
		return m_Items[index];
	}
	inline BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* GetAddressAtUnchecked(il2cpp_array_size_t index)
	{
		return m_Items + index;
	}
	inline void SetAtUnchecked(il2cpp_array_size_t index, BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF value)
	{
		m_Items[index] = value;
	}
};
struct BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515  : public RuntimeArray
{
	ALIGN_FIELD (8) BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6 m_Items[1];

	inline BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6 GetAt(il2cpp_array_size_t index) const
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items[index];
	}
	inline BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* GetAddressAt(il2cpp_array_size_t index)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items + index;
	}
	inline void SetAt(il2cpp_array_size_t index, BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6 value)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		m_Items[index] = value;
	}
	inline BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6 GetAtUnchecked(il2cpp_array_size_t index) const
	{
		return m_Items[index];
	}
	inline BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* GetAddressAtUnchecked(il2cpp_array_size_t index)
	{
		return m_Items + index;
	}
	inline void SetAtUnchecked(il2cpp_array_size_t index, BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6 value)
	{
		m_Items[index] = value;
	}
};
struct BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A  : public RuntimeArray
{
	ALIGN_FIELD (8) BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C m_Items[1];

	inline BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C GetAt(il2cpp_array_size_t index) const
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items[index];
	}
	inline BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* GetAddressAt(il2cpp_array_size_t index)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items + index;
	}
	inline void SetAt(il2cpp_array_size_t index, BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C value)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		m_Items[index] = value;
	}
	inline BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C GetAtUnchecked(il2cpp_array_size_t index) const
	{
		return m_Items[index];
	}
	inline BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* GetAddressAtUnchecked(il2cpp_array_size_t index)
	{
		return m_Items + index;
	}
	inline void SetAtUnchecked(il2cpp_array_size_t index, BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C value)
	{
		m_Items[index] = value;
	}
};
struct BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8  : public RuntimeArray
{
	ALIGN_FIELD (8) BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54 m_Items[1];

	inline BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54 GetAt(il2cpp_array_size_t index) const
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items[index];
	}
	inline BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* GetAddressAt(il2cpp_array_size_t index)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items + index;
	}
	inline void SetAt(il2cpp_array_size_t index, BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54 value)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		m_Items[index] = value;
	}
	inline BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54 GetAtUnchecked(il2cpp_array_size_t index) const
	{
		return m_Items[index];
	}
	inline BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* GetAddressAtUnchecked(il2cpp_array_size_t index)
	{
		return m_Items + index;
	}
	inline void SetAtUnchecked(il2cpp_array_size_t index, BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54 value)
	{
		m_Items[index] = value;
	}
};
struct BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F  : public RuntimeArray
{
	ALIGN_FIELD (8) BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901 m_Items[1];

	inline BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901 GetAt(il2cpp_array_size_t index) const
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items[index];
	}
	inline BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* GetAddressAt(il2cpp_array_size_t index)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items + index;
	}
	inline void SetAt(il2cpp_array_size_t index, BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901 value)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		m_Items[index] = value;
	}
	inline BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901 GetAtUnchecked(il2cpp_array_size_t index) const
	{
		return m_Items[index];
	}
	inline BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* GetAddressAtUnchecked(il2cpp_array_size_t index)
	{
		return m_Items + index;
	}
	inline void SetAtUnchecked(il2cpp_array_size_t index, BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901 value)
	{
		m_Items[index] = value;
	}
};
struct BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A  : public RuntimeArray
{
	ALIGN_FIELD (8) BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472 m_Items[1];

	inline BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472 GetAt(il2cpp_array_size_t index) const
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items[index];
	}
	inline BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* GetAddressAt(il2cpp_array_size_t index)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items + index;
	}
	inline void SetAt(il2cpp_array_size_t index, BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472 value)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		m_Items[index] = value;
	}
	inline BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472 GetAtUnchecked(il2cpp_array_size_t index) const
	{
		return m_Items[index];
	}
	inline BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* GetAddressAtUnchecked(il2cpp_array_size_t index)
	{
		return m_Items + index;
	}
	inline void SetAtUnchecked(il2cpp_array_size_t index, BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472 value)
	{
		m_Items[index] = value;
	}
};
struct BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651  : public RuntimeArray
{
	ALIGN_FIELD (8) BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F m_Items[1];

	inline BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F GetAt(il2cpp_array_size_t index) const
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items[index];
	}
	inline BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* GetAddressAt(il2cpp_array_size_t index)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items + index;
	}
	inline void SetAt(il2cpp_array_size_t index, BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F value)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		m_Items[index] = value;
	}
	inline BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F GetAtUnchecked(il2cpp_array_size_t index) const
	{
		return m_Items[index];
	}
	inline BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* GetAddressAtUnchecked(il2cpp_array_size_t index)
	{
		return m_Items + index;
	}
	inline void SetAtUnchecked(il2cpp_array_size_t index, BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F value)
	{
		m_Items[index] = value;
	}
};
struct BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4  : public RuntimeArray
{
	ALIGN_FIELD (8) bool m_Items[1];

	inline bool GetAt(il2cpp_array_size_t index) const
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items[index];
	}
	inline bool* GetAddressAt(il2cpp_array_size_t index)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items + index;
	}
	inline void SetAt(il2cpp_array_size_t index, bool value)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		m_Items[index] = value;
	}
	inline bool GetAtUnchecked(il2cpp_array_size_t index) const
	{
		return m_Items[index];
	}
	inline bool* GetAddressAtUnchecked(il2cpp_array_size_t index)
	{
		return m_Items + index;
	}
	inline void SetAtUnchecked(il2cpp_array_size_t index, bool value)
	{
		m_Items[index] = value;
	}
};
struct BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47  : public RuntimeArray
{
	ALIGN_FIELD (8) BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010 m_Items[1];

	inline BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010 GetAt(il2cpp_array_size_t index) const
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items[index];
	}
	inline BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* GetAddressAt(il2cpp_array_size_t index)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items + index;
	}
	inline void SetAt(il2cpp_array_size_t index, BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010 value)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		m_Items[index] = value;
	}
	inline BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010 GetAtUnchecked(il2cpp_array_size_t index) const
	{
		return m_Items[index];
	}
	inline BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* GetAddressAtUnchecked(il2cpp_array_size_t index)
	{
		return m_Items + index;
	}
	inline void SetAtUnchecked(il2cpp_array_size_t index, BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010 value)
	{
		m_Items[index] = value;
	}
};
struct BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5  : public RuntimeArray
{
	ALIGN_FIELD (8) Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3 m_Items[1];

	inline Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3 GetAt(il2cpp_array_size_t index) const
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items[index];
	}
	inline Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* GetAddressAt(il2cpp_array_size_t index)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items + index;
	}
	inline void SetAt(il2cpp_array_size_t index, Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3 value)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		m_Items[index] = value;
	}
	inline Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3 GetAtUnchecked(il2cpp_array_size_t index) const
	{
		return m_Items[index];
	}
	inline Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* GetAddressAtUnchecked(il2cpp_array_size_t index)
	{
		return m_Items + index;
	}
	inline void SetAtUnchecked(il2cpp_array_size_t index, Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3 value)
	{
		m_Items[index] = value;
	}
};
struct ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031  : public RuntimeArray
{
	ALIGN_FIELD (8) uint8_t m_Items[1];

	inline uint8_t GetAt(il2cpp_array_size_t index) const
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items[index];
	}
	inline uint8_t* GetAddressAt(il2cpp_array_size_t index)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items + index;
	}
	inline void SetAt(il2cpp_array_size_t index, uint8_t value)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		m_Items[index] = value;
	}
	inline uint8_t GetAtUnchecked(il2cpp_array_size_t index) const
	{
		return m_Items[index];
	}
	inline uint8_t* GetAddressAtUnchecked(il2cpp_array_size_t index)
	{
		return m_Items + index;
	}
	inline void SetAtUnchecked(il2cpp_array_size_t index, uint8_t value)
	{
		m_Items[index] = value;
	}
};


IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m1D64B90D223BD596E1C2DA8966AC4ED57C8E4589_gshared_inline (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Buffer_Memmove_TisHashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5_mBF1724579F1B56AE7C6BDF919C304D2369837AA9_gshared (HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* ___0_destination, HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m15AA8CF0C1EB395F786CC37F5D7AF29EA7C10709_gshared_inline (ReadOnlySpan_1_t4990792F01A3EE12CEEC65526A813F1C0A67283E* __this, HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mA74DDDE530919E8BC3D60C7CBD981ED0F11DD14F_gshared_inline (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B* __this, HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C* Array_Empty_TisHashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5_mA6305FE1F89FEF2783F9DE478AFB982C8BA1EDC2_gshared_inline (const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m2646A3BCF55B82973A1E7845EE19BC641CD4D35E_gshared_inline (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B* __this, HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C* ___0_array, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m92912F015428BEA4C4E33A78B846E6B3CDB11751_gshared_inline (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Buffer_Memmove_TisNullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28_mBCF551834A5B6D371531B7F25078FA3DFB1B5E97_gshared (Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* ___0_destination, Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m4868E2A05AB294488A5AF17BC94F000C5A0F68F7_gshared_inline (ReadOnlySpan_1_t38A9F2C8804739CCDFBD7062CA1C47C44BAE5AC2* __this, Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mDA8E6598A3E9F174C4024C9915ACA9463BA35F22_gshared_inline (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3* __this, Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* Array_Empty_TisNullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28_mFB61E6C1D57945549B403C13B1652F8CE5324A3C_gshared_inline (const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m809249502A9C502A89D571353424C3BAC9610EFA_gshared_inline (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3* __this, Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* ___0_array, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mE6A401C96B3DFE2B3B68A13B3AE43400879A9981_gshared_inline (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Buffer_Memmove_TisValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA_mCB14434333DCB75A5FD671D9A992747A378043D9_gshared (ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* ___0_destination, ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_mFB782BAD5E6881410F56403A2B56A0ED54E3A1D8_gshared_inline (ReadOnlySpan_1_t4757BCCBBFA890A355792A3EB923FE9C101A9C76* __this, ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m12E72689FB9ED51748E944C2B3CF2E15EF0EB168_gshared_inline (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019* __this, ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3* Array_Empty_TisValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA_mB7B36B66111D0BD07DEA806DA065B3F1EB37F96C_gshared_inline (const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mF27289AA0FF72C4ED5AEE3D24C8A1052C9522A5F_gshared_inline (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019* __this, ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3* ___0_array, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mFBFB62752766092200E6EFBA8017B4DD3A3CF901_gshared_inline (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Buffer_Memmove_TisAABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A_m47E56668B77DFD297A10C77037500A873C750328_gshared (AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* ___0_destination, AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m00664DD373A36D0E1E71A6B15C893AFB29E09686_gshared_inline (ReadOnlySpan_1_t32C8835D73D5F2ADCC5ED93FA71F63C5E9763632* __this, AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m62F7DD7486AA2DC1886916B354DF7CB30B2D12CC_gshared_inline (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352* __this, AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61* Array_Empty_TisAABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A_mE73376DEDDDFB934E9003663820D201F6C9B9C41_gshared_inline (const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m9F49684FE52CEB46E463E4333810DF0E97299A75_gshared_inline (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352* __this, AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61* ___0_array, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mAB8B8F731C3753B681689724D8C10D0F6706324A_gshared_inline (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Buffer_Memmove_TisATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E_mF7B64A7A3AED5F75779174F1164B6D60E51A6215_gshared (ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* ___0_destination, ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m61974A48F0249F9BF95E2B30196D22127E7AD9CB_gshared_inline (ReadOnlySpan_1_tAE84767975672BD032424E74DD54F304DED0E2A7* __this, ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m51AEBDDF4FA3F8681B44273910D1645414D5B4AB_gshared_inline (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948* __this, ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F* Array_Empty_TisATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E_m5293579F263F9A3750FDFA1AA45718B28873BFBB_gshared_inline (const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m41429CDEFEBC1254E9655F1E8E1020C0609E0919_gshared_inline (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948* __this, ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F* ___0_array, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m2275D4270FBF5A9F745DB11CE6CCE148D8C4672E_gshared_inline (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Buffer_Memmove_TisAttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E_mE722009CF3369F14C5504F191D0EA30479F00E86_gshared (AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* ___0_destination, AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_mB2FA851A491C5709D4BAB63C5DA5958FFFEEAA07_gshared_inline (ReadOnlySpan_1_tBA07559B531DF8D3C488A21E738503C11F05E17A* __this, AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mFFA3823A31B573A005E307DD54A3C8EA4704B552_gshared_inline (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F* __this, AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1* Array_Empty_TisAttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E_m0F7B255CF739B8C78F6D0663FFFD44753100FCAE_gshared_inline (const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m9923F08D09F1AF1763CE22C041DC55410B5A05B1_gshared_inline (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F* __this, AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1* ___0_array, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m739A39BB426B19B7583557F308B2BDA52E2C3DD9_gshared_inline (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Buffer_Memmove_TisBatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D_m9074142CF2F0828232AFEBB926F490D33E285B9B_gshared (BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* ___0_destination, BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m729E93C65964D6955F3A0794056B0A8FA3E1FB44_gshared_inline (ReadOnlySpan_1_tE73282FE39C46EDD2EE2F61A9E3E69BE943784B7* __this, BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mB2713B2424259A6D18038A2E29BD96DA081378D6_gshared_inline (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428* __this, BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80* Array_Empty_TisBatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D_m652D1F78F0DEF31A60A1D09589A89DE06F12C3E4_gshared_inline (const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m7126F92024E73EE43580A7A9D64C39CD973406CC_gshared_inline (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428* __this, BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80* ___0_array, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m9535293C1FDA720739072391EDE6F574C67C905D_gshared_inline (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Buffer_Memmove_TisBatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770_m30599D9EEC5CBA92A026E19AFB3510603F2595EB_gshared (BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* ___0_destination, BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m0D6A1AA77CFA40D8A9849AA4338A5A43DC6CF0C0_gshared_inline (ReadOnlySpan_1_t3D56DFE8743E7EF0665F38A59DA5B99E0F9425B0* __this, BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m38FB4416138CB97C429799865EA7C7156E442FCD_gshared_inline (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4* __this, BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963* Array_Empty_TisBatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770_mEFC02ED7475540CF71CD66D177FEDA1EA4155C01_gshared_inline (const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mF34E7D8B1563ED8C47501C1BF37B17509D397F30_gshared_inline (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4* __this, BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963* ___0_array, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m8E10CA1F785F77C56E8D665A3CE6AF1DC31200FA_gshared_inline (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Buffer_Memmove_TisBatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C_m8557E6B1B48B7EB426E6CCFA83223FDCB8F16135_gshared (BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* ___0_destination, BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m89203DEBD91443BD65C0CCC9EAFA52A1CDE4B184_gshared_inline (ReadOnlySpan_1_t92B77B310CC53A58C3C04B20E0B7F18C2EEFB130* __this, BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mA2CE8DBE18F6BB54702DAA56720F89DD968A5271_gshared_inline (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF* __this, BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4* Array_Empty_TisBatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C_m0154337C8713B2FE58E471FD10F37CFF0E4D1537_gshared_inline (const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mCDB221C7D73874741828DA34B70D3B0AADC00136_gshared_inline (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF* __this, BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4* ___0_array, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mDDD127BFC8F4A5DEEE8DBC6779C72A75229E2A08_gshared_inline (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Buffer_Memmove_TisBatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0_m9EE961D328A463A3B89EBC9E2B20D70A09D560A9_gshared (BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* ___0_destination, BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m9A03AF77602C387B913C41E787C615B3D1C1610F_gshared_inline (ReadOnlySpan_1_tD9DB60FA9C52DCE56999EB3FEEA0CDE0FDFFFFE2* __this, BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m0B727F1A38BD125BA88E22B9518BBEA69CD9F192_gshared_inline (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1* __this, BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E* Array_Empty_TisBatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0_m30D7C90FCBAF09BB4E8C21ED5FE267CFF2F47BA4_gshared_inline (const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m81637CB567960AF0B3F0471290B423B290FE1830_gshared_inline (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1* __this, BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E* ___0_array, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mF5DDBBF4783D0A55C88BFD79E1A1F8B5DDCF60C5_gshared_inline (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Buffer_Memmove_TisBlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF_m3ED952566857853E08E1F43BA48367A61065281A_gshared (BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* ___0_destination, BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m1C72072A1F9FFD48A21C4C5F2A81954708D29495_gshared_inline (ReadOnlySpan_1_t3D251E6627DBB106D7D34F8DC234D102B691FD49* __this, BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m4F32D7D5653D612D237DDFD35C9850E16CA11C18_gshared_inline (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671* __this, BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B* Array_Empty_TisBlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF_mCD144DB8231D7EA3AF1DB4FEB94681C9E580CA9D_gshared_inline (const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mCA3E1FB18A1006842617ABD22464535398F4655D_gshared_inline (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671* __this, BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B* ___0_array, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m0A22C7814AA57E51DE4EDC241010E81DA05627F9_gshared_inline (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Buffer_Memmove_TisBlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6_mE178FB3796A6A1A4F519A7BA46829D3A14F94CBD_gshared (BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* ___0_destination, BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m143F99ABFB2902F2BE3AB6725D5F8AEAECDEC5F4_gshared_inline (ReadOnlySpan_1_tD81D75B0E0400301DC302920BCCBB9FBD97CA2A8* __this, BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m6C9685057F182B8896BBE6B894974625E6DA4221_gshared_inline (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C* __this, BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515* Array_Empty_TisBlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6_m7D78EA1928C7379C27FE7337353FB51BEFD7476E_gshared_inline (const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mF80BBD6B1E4103830CA7E538A9EE8ABA983D97EC_gshared_inline (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C* __this, BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515* ___0_array, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m3C5D2034819739D3DDB78A3D890CEE1C94529B01_gshared_inline (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Buffer_Memmove_TisBlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C_m107660B4F4107562042A7A5BD0F5123732E1C1BF_gshared (BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* ___0_destination, BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_mD794B63B35B4B58E5337728A24E1998CFDD74810_gshared_inline (ReadOnlySpan_1_t70882B83461088DC23C3DB9AF59753CF91E1F3AA* __this, BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m773D9E2695DC1A1EAF17FDC94204CB7B1DE21582_gshared_inline (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B* __this, BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A* Array_Empty_TisBlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C_m48CDEF41C2F032666E4312F197041C0757708AE3_gshared_inline (const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mB392265B6A34EA59542E156547D1143AC4B0EBD4_gshared_inline (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B* __this, BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A* ___0_array, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m088CD07D38433F7795ED549A2139E5D41EA16706_gshared_inline (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Buffer_Memmove_TisBlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54_m812A4F9C363B5C0D2AAE7D5E5D180DB7F7FEF824_gshared (BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* ___0_destination, BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m1A260D918B5EB96D17E965BA7BA73DA0C3493361_gshared_inline (ReadOnlySpan_1_t2EFE9320FC985EB4C740E0FD04D094F25024C51D* __this, BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mEA9008985414217BB660044157B6458716A8AB1D_gshared_inline (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF* __this, BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8* Array_Empty_TisBlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54_mDE388119C5F2CA6DED1F8895551BEDC81C379FF9_gshared_inline (const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mD521304CD3352077C22527BDFF5A3A6F5BBA2345_gshared_inline (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF* __this, BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8* ___0_array, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mE19749B8DE0A55279184BDD9C3F394605245F91A_gshared_inline (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Buffer_Memmove_TisBlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901_mCB357A3CD61F6C424F89F1F9C63A4446F5B9C57E_gshared (BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* ___0_destination, BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_mE88D51223E12D11DE487A5E2C7AF2013C6413887_gshared_inline (ReadOnlySpan_1_t5EE3C4B6EA25BB8F3F956DFD28820F5666342758* __this, BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m18D55B2B52C0EE4D379FCBBC6EE16E72CE2928E1_gshared_inline (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C* __this, BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F* Array_Empty_TisBlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901_m75A4A4FD904386887E2E78BA168595529AF13939_gshared_inline (const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mBC0A2A6F732CAD76B8E0B559AAF16DDDC7DA95FB_gshared_inline (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C* __this, BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F* ___0_array, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m27BEF44B2CCD3948DC00D344142E534E36BA7524_gshared_inline (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Buffer_Memmove_TisBlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472_mDE86D51ADDC3C7F13B214DA755964EA5014FC510_gshared (BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* ___0_destination, BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m83EAE3C4FCB23391B66637D0580733B85E12B21A_gshared_inline (ReadOnlySpan_1_t9DCA2EC81EE91A02D7C140CF3376183B663C483A* __this, BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m0635E38BE51343FEA108E4BAC304A66703B9E727_gshared_inline (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1* __this, BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A* Array_Empty_TisBlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472_m6A8620E16588CCAE8CBA423DCB92B7E1D919A942_gshared_inline (const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mDAF271BFB6432EB8FCE7E7E95BB3A4918F1BCC92_gshared_inline (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1* __this, BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A* ___0_array, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mCF6D6F9A859D0DB86629D46B25379F2D9C9B8CBB_gshared_inline (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Buffer_Memmove_TisBoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F_m5ADA1F648D3D4D37DB595FA24A1207E2AFE0FDE5_gshared (BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* ___0_destination, BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m0519BAD7DF271438138DD6AEC8CE28A60D0619CE_gshared_inline (ReadOnlySpan_1_t29FEC1E8134CF98858AC01DEE01F4B20811F583F* __this, BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mB74D4D3C2AD6B802DDB07920C132F554AED832BB_gshared_inline (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F* __this, BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651* Array_Empty_TisBoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F_m327783287506F38234D68882E5BEE202618B5300_gshared_inline (const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m1D909AF584BE28F3A4AD990A5D7BE41287E28B38_gshared_inline (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F* __this, BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651* ___0_array, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mED1253429B93CB6D2928015A22105A16FF64C86B_gshared_inline (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Buffer_Memmove_TisBoolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_m6C1A997DCFEFC3BA96EEB5E75FB8B54DF2D21198_gshared (bool* ___0_destination, bool* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m5818A0EC9B5A3628E69F90A3521BDE96E1FDC74F_gshared_inline (ReadOnlySpan_1_t1CABD9AC48FDD39B6D2C26BCA3D87FCA5F94F1C2* __this, bool* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mC733F92ACB7C0D922180991442FE7A28DCB60772_gshared_inline (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51* __this, bool* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4* Array_Empty_TisBoolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_mAB215C445888719BD89809D99C3DBD3135C2B1E7_gshared_inline (const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m5AF7377F267C08DB0D59CB23F59DF954F7DEE62C_gshared_inline (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51* __this, BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4* ___0_array, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mA3FAE42313871FB8DAC80A4C14DDE4CCE75F1713_gshared_inline (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Buffer_Memmove_TisBoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010_m795273AEE7A248BA5B241E561023F0456EF5DED1_gshared (BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* ___0_destination, BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m4C7DD11D4BCC31C1EC9F0EA7D34388DB0B14A07A_gshared_inline (ReadOnlySpan_1_t5D1B06A3C861E766B5669E3742152A803D0F24AA* __this, BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m58CD191F04E31F33A163B278675E0FA9DF59166D_gshared_inline (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68* __this, BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47* Array_Empty_TisBoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010_mD26FFE584F3D72C1C64FD47A5D59D19E615D8050_gshared_inline (const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m81F10BC6C4033345B53A361FD5777CB15BA51592_gshared_inline (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68* __this, BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47* ___0_array, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mF487895F53310FF6F399B7BD07038C49D29A6EAB_gshared_inline (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Buffer_Memmove_TisBounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3_m4925E1D88272BE2E628F28AFFEC4FF09CC2EC647_gshared (Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* ___0_destination, Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_mB60BC31800C0877424E21575E45AE6C8AA50ADA6_gshared_inline (ReadOnlySpan_1_tCA4391229D70967C5F52C769440BB78CBF80BE99* __this, Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mC3D811BCA73C14AA673ADAB2E2CF39FE1385714B_gshared_inline (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634* __this, Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5* Array_Empty_TisBounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3_mB6CFBB5D8AF33F0BAE72154209AB29B8D52FBCDA_gshared_inline (const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m2F8451EBCA1900A8FF93D17DF8177C41EAA2E94B_gshared_inline (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634* __this, BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5* ___0_array, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m8E944E4954E037877A25B9FF6B901F1F901D4769_gshared_inline (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Buffer_Memmove_TisByte_t94D9231AC217BE4D2E004C4CD32DF6D099EA41A3_mB1465EEEBE0A608FA51B29BC3F145F287AD04190_gshared (uint8_t* ___0_destination, uint8_t* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m0FC0B92549C2968E80B5F75A85F28B96DBFCFD63_gshared_inline (ReadOnlySpan_1_tA850A6C0E88ABBA37646A078ACBC24D6D5FD9B4D* __this, uint8_t* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m947BF95D54571BF3897F96822B7A8FDA5853497B_gshared_inline (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305* __this, uint8_t* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* Array_Empty_TisByte_t94D9231AC217BE4D2E004C4CD32DF6D099EA41A3_m6080CA526758F4FA182A066B2780D1761CD36ED5_gshared_inline (const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m513968BDBFF3CFCE89F3F77FE44CAB22CA474EF9_gshared_inline (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305* __this, ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* ___0_array, const RuntimeMethod* method) ;

IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR uint8_t* Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline (RuntimeArray* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56 (const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Type_t* Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57 (RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B ___0_handle, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void ThrowHelper_ThrowInvalidTypeWithPointersNotSupported_m5707DE408588F6EAC3FC7D10F9520308CF8C8CCF (Type_t* ___0_targetType, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void ThrowHelper_ThrowIndexOutOfRangeException_m86F753A24E2765A35546BA6352A7E4F0BB8A66B5 (const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Unsafe_InitBlockUnaligned_m6F2353EB9ABC9320E61629FAEE23948C80BFF03A (uint8_t* ___0_startAddress, uint8_t ___1_value, uint32_t ___2_byteCount, const RuntimeMethod* method) ;
inline int32_t Span_1_get_Length_m1D64B90D223BD596E1C2DA8966AC4ED57C8E4589_inline (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B* __this, const RuntimeMethod* method)
{
	return ((  int32_t (*) (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B*, const RuntimeMethod*))Span_1_get_Length_m1D64B90D223BD596E1C2DA8966AC4ED57C8E4589_gshared_inline)(__this, method);
}
inline void Buffer_Memmove_TisHashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5_mBF1724579F1B56AE7C6BDF919C304D2369837AA9 (HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* ___0_destination, HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method)
{
	((  void (*) (HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*, HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*, uint64_t, const RuntimeMethod*))Buffer_Memmove_TisHashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5_mBF1724579F1B56AE7C6BDF919C304D2369837AA9_gshared)(___0_destination, ___1_source, ___2_elementCount, method);
}
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void ThrowHelper_ThrowArgumentException_DestinationTooShort_m6468934A3BBB67DBC5BAEF7A64D91BD5BBBB3D4D (const RuntimeMethod* method) ;
inline void ReadOnlySpan_1__ctor_m15AA8CF0C1EB395F786CC37F5D7AF29EA7C10709_inline (ReadOnlySpan_1_t4990792F01A3EE12CEEC65526A813F1C0A67283E* __this, HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (ReadOnlySpan_1_t4990792F01A3EE12CEEC65526A813F1C0A67283E*, HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*, int32_t, const RuntimeMethod*))ReadOnlySpan_1__ctor_m15AA8CF0C1EB395F786CC37F5D7AF29EA7C10709_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Type_op_Equality_m99930A0E44E420A685FABA60E60BA1CC5FA0EBDC (Type_t* ___0_left, Type_t* ___1_right, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* String_CreateString_m3F8794FEB452558B8A68C65E1F0B603B3D94E0E2 (String_t* __this, Il2CppChar* ___0_value, int32_t ___1_startIndex, int32_t ___2_length, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* String_Format_mFB7DA489BD99F4670881FF50EC017BFB0A5C0987 (String_t* ___0_format, RuntimeObject* ___1_arg0, RuntimeObject* ___2_arg1, const RuntimeMethod* method) ;
inline void Span_1__ctor_mA74DDDE530919E8BC3D60C7CBD981ED0F11DD14F_inline (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B* __this, HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B*, HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*, int32_t, const RuntimeMethod*))Span_1__ctor_mA74DDDE530919E8BC3D60C7CBD981ED0F11DD14F_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C* Array_Empty_TisHashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5_mA6305FE1F89FEF2783F9DE478AFB982C8BA1EDC2_inline (const RuntimeMethod* method)
{
	return ((  HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C* (*) (const RuntimeMethod*))Array_Empty_TisHashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5_mA6305FE1F89FEF2783F9DE478AFB982C8BA1EDC2_gshared_inline)(method);
}
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* __this, String_t* ___0_message, const RuntimeMethod* method) ;
inline void Span_1__ctor_m2646A3BCF55B82973A1E7845EE19BC641CD4D35E_inline (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B* __this, HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C* ___0_array, const RuntimeMethod* method)
{
	((  void (*) (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B*, HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C*, const RuntimeMethod*))Span_1__ctor_m2646A3BCF55B82973A1E7845EE19BC641CD4D35E_gshared_inline)(__this, ___0_array, method);
}
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Type_t* Object_GetType_mE10A8FC1E57F3DF29972CCBC026C2DC3942263B3 (RuntimeObject* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Type_op_Inequality_m83209C7BB3C05DFBEA3B6199B0BEFE8037301172 (Type_t* ___0_left, Type_t* ___1_right, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void ThrowHelper_ThrowArrayTypeMismatchException_m781AD7A903FEA43FAE3137977E6BC5F9BAEBC590 (const RuntimeMethod* method) ;
inline int32_t Span_1_get_Length_m92912F015428BEA4C4E33A78B846E6B3CDB11751_inline (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3* __this, const RuntimeMethod* method)
{
	return ((  int32_t (*) (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3*, const RuntimeMethod*))Span_1_get_Length_m92912F015428BEA4C4E33A78B846E6B3CDB11751_gshared_inline)(__this, method);
}
inline void Buffer_Memmove_TisNullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28_mBCF551834A5B6D371531B7F25078FA3DFB1B5E97 (Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* ___0_destination, Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method)
{
	((  void (*) (Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28*, Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28*, uint64_t, const RuntimeMethod*))Buffer_Memmove_TisNullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28_mBCF551834A5B6D371531B7F25078FA3DFB1B5E97_gshared)(___0_destination, ___1_source, ___2_elementCount, method);
}
inline void ReadOnlySpan_1__ctor_m4868E2A05AB294488A5AF17BC94F000C5A0F68F7_inline (ReadOnlySpan_1_t38A9F2C8804739CCDFBD7062CA1C47C44BAE5AC2* __this, Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (ReadOnlySpan_1_t38A9F2C8804739CCDFBD7062CA1C47C44BAE5AC2*, Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28*, int32_t, const RuntimeMethod*))ReadOnlySpan_1__ctor_m4868E2A05AB294488A5AF17BC94F000C5A0F68F7_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline void Span_1__ctor_mDA8E6598A3E9F174C4024C9915ACA9463BA35F22_inline (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3* __this, Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3*, Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28*, int32_t, const RuntimeMethod*))Span_1__ctor_mDA8E6598A3E9F174C4024C9915ACA9463BA35F22_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* Array_Empty_TisNullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28_mFB61E6C1D57945549B403C13B1652F8CE5324A3C_inline (const RuntimeMethod* method)
{
	return ((  Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* (*) (const RuntimeMethod*))Array_Empty_TisNullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28_mFB61E6C1D57945549B403C13B1652F8CE5324A3C_gshared_inline)(method);
}
inline void Span_1__ctor_m809249502A9C502A89D571353424C3BAC9610EFA_inline (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3* __this, Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* ___0_array, const RuntimeMethod* method)
{
	((  void (*) (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3*, Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43*, const RuntimeMethod*))Span_1__ctor_m809249502A9C502A89D571353424C3BAC9610EFA_gshared_inline)(__this, ___0_array, method);
}
inline int32_t Span_1_get_Length_mE6A401C96B3DFE2B3B68A13B3AE43400879A9981_inline (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019* __this, const RuntimeMethod* method)
{
	return ((  int32_t (*) (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019*, const RuntimeMethod*))Span_1_get_Length_mE6A401C96B3DFE2B3B68A13B3AE43400879A9981_gshared_inline)(__this, method);
}
inline void Buffer_Memmove_TisValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA_mCB14434333DCB75A5FD671D9A992747A378043D9 (ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* ___0_destination, ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method)
{
	((  void (*) (ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA*, ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA*, uint64_t, const RuntimeMethod*))Buffer_Memmove_TisValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA_mCB14434333DCB75A5FD671D9A992747A378043D9_gshared)(___0_destination, ___1_source, ___2_elementCount, method);
}
inline void ReadOnlySpan_1__ctor_mFB782BAD5E6881410F56403A2B56A0ED54E3A1D8_inline (ReadOnlySpan_1_t4757BCCBBFA890A355792A3EB923FE9C101A9C76* __this, ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (ReadOnlySpan_1_t4757BCCBBFA890A355792A3EB923FE9C101A9C76*, ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA*, int32_t, const RuntimeMethod*))ReadOnlySpan_1__ctor_mFB782BAD5E6881410F56403A2B56A0ED54E3A1D8_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline void Span_1__ctor_m12E72689FB9ED51748E944C2B3CF2E15EF0EB168_inline (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019* __this, ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019*, ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA*, int32_t, const RuntimeMethod*))Span_1__ctor_m12E72689FB9ED51748E944C2B3CF2E15EF0EB168_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3* Array_Empty_TisValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA_mB7B36B66111D0BD07DEA806DA065B3F1EB37F96C_inline (const RuntimeMethod* method)
{
	return ((  ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3* (*) (const RuntimeMethod*))Array_Empty_TisValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA_mB7B36B66111D0BD07DEA806DA065B3F1EB37F96C_gshared_inline)(method);
}
inline void Span_1__ctor_mF27289AA0FF72C4ED5AEE3D24C8A1052C9522A5F_inline (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019* __this, ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3* ___0_array, const RuntimeMethod* method)
{
	((  void (*) (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019*, ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3*, const RuntimeMethod*))Span_1__ctor_mF27289AA0FF72C4ED5AEE3D24C8A1052C9522A5F_gshared_inline)(__this, ___0_array, method);
}
inline int32_t Span_1_get_Length_mFBFB62752766092200E6EFBA8017B4DD3A3CF901_inline (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352* __this, const RuntimeMethod* method)
{
	return ((  int32_t (*) (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352*, const RuntimeMethod*))Span_1_get_Length_mFBFB62752766092200E6EFBA8017B4DD3A3CF901_gshared_inline)(__this, method);
}
inline void Buffer_Memmove_TisAABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A_m47E56668B77DFD297A10C77037500A873C750328 (AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* ___0_destination, AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method)
{
	((  void (*) (AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A*, AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A*, uint64_t, const RuntimeMethod*))Buffer_Memmove_TisAABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A_m47E56668B77DFD297A10C77037500A873C750328_gshared)(___0_destination, ___1_source, ___2_elementCount, method);
}
inline void ReadOnlySpan_1__ctor_m00664DD373A36D0E1E71A6B15C893AFB29E09686_inline (ReadOnlySpan_1_t32C8835D73D5F2ADCC5ED93FA71F63C5E9763632* __this, AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (ReadOnlySpan_1_t32C8835D73D5F2ADCC5ED93FA71F63C5E9763632*, AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A*, int32_t, const RuntimeMethod*))ReadOnlySpan_1__ctor_m00664DD373A36D0E1E71A6B15C893AFB29E09686_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline void Span_1__ctor_m62F7DD7486AA2DC1886916B354DF7CB30B2D12CC_inline (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352* __this, AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352*, AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A*, int32_t, const RuntimeMethod*))Span_1__ctor_m62F7DD7486AA2DC1886916B354DF7CB30B2D12CC_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61* Array_Empty_TisAABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A_mE73376DEDDDFB934E9003663820D201F6C9B9C41_inline (const RuntimeMethod* method)
{
	return ((  AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61* (*) (const RuntimeMethod*))Array_Empty_TisAABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A_mE73376DEDDDFB934E9003663820D201F6C9B9C41_gshared_inline)(method);
}
inline void Span_1__ctor_m9F49684FE52CEB46E463E4333810DF0E97299A75_inline (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352* __this, AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61* ___0_array, const RuntimeMethod* method)
{
	((  void (*) (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352*, AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61*, const RuntimeMethod*))Span_1__ctor_m9F49684FE52CEB46E463E4333810DF0E97299A75_gshared_inline)(__this, ___0_array, method);
}
inline int32_t Span_1_get_Length_mAB8B8F731C3753B681689724D8C10D0F6706324A_inline (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948* __this, const RuntimeMethod* method)
{
	return ((  int32_t (*) (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948*, const RuntimeMethod*))Span_1_get_Length_mAB8B8F731C3753B681689724D8C10D0F6706324A_gshared_inline)(__this, method);
}
inline void Buffer_Memmove_TisATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E_mF7B64A7A3AED5F75779174F1164B6D60E51A6215 (ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* ___0_destination, ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method)
{
	((  void (*) (ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E*, ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E*, uint64_t, const RuntimeMethod*))Buffer_Memmove_TisATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E_mF7B64A7A3AED5F75779174F1164B6D60E51A6215_gshared)(___0_destination, ___1_source, ___2_elementCount, method);
}
inline void ReadOnlySpan_1__ctor_m61974A48F0249F9BF95E2B30196D22127E7AD9CB_inline (ReadOnlySpan_1_tAE84767975672BD032424E74DD54F304DED0E2A7* __this, ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (ReadOnlySpan_1_tAE84767975672BD032424E74DD54F304DED0E2A7*, ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E*, int32_t, const RuntimeMethod*))ReadOnlySpan_1__ctor_m61974A48F0249F9BF95E2B30196D22127E7AD9CB_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline void Span_1__ctor_m51AEBDDF4FA3F8681B44273910D1645414D5B4AB_inline (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948* __this, ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948*, ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E*, int32_t, const RuntimeMethod*))Span_1__ctor_m51AEBDDF4FA3F8681B44273910D1645414D5B4AB_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F* Array_Empty_TisATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E_m5293579F263F9A3750FDFA1AA45718B28873BFBB_inline (const RuntimeMethod* method)
{
	return ((  ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F* (*) (const RuntimeMethod*))Array_Empty_TisATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E_m5293579F263F9A3750FDFA1AA45718B28873BFBB_gshared_inline)(method);
}
inline void Span_1__ctor_m41429CDEFEBC1254E9655F1E8E1020C0609E0919_inline (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948* __this, ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F* ___0_array, const RuntimeMethod* method)
{
	((  void (*) (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948*, ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F*, const RuntimeMethod*))Span_1__ctor_m41429CDEFEBC1254E9655F1E8E1020C0609E0919_gshared_inline)(__this, ___0_array, method);
}
inline int32_t Span_1_get_Length_m2275D4270FBF5A9F745DB11CE6CCE148D8C4672E_inline (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F* __this, const RuntimeMethod* method)
{
	return ((  int32_t (*) (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F*, const RuntimeMethod*))Span_1_get_Length_m2275D4270FBF5A9F745DB11CE6CCE148D8C4672E_gshared_inline)(__this, method);
}
inline void Buffer_Memmove_TisAttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E_mE722009CF3369F14C5504F191D0EA30479F00E86 (AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* ___0_destination, AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method)
{
	((  void (*) (AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E*, AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E*, uint64_t, const RuntimeMethod*))Buffer_Memmove_TisAttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E_mE722009CF3369F14C5504F191D0EA30479F00E86_gshared)(___0_destination, ___1_source, ___2_elementCount, method);
}
inline void ReadOnlySpan_1__ctor_mB2FA851A491C5709D4BAB63C5DA5958FFFEEAA07_inline (ReadOnlySpan_1_tBA07559B531DF8D3C488A21E738503C11F05E17A* __this, AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (ReadOnlySpan_1_tBA07559B531DF8D3C488A21E738503C11F05E17A*, AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E*, int32_t, const RuntimeMethod*))ReadOnlySpan_1__ctor_mB2FA851A491C5709D4BAB63C5DA5958FFFEEAA07_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline void Span_1__ctor_mFFA3823A31B573A005E307DD54A3C8EA4704B552_inline (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F* __this, AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F*, AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E*, int32_t, const RuntimeMethod*))Span_1__ctor_mFFA3823A31B573A005E307DD54A3C8EA4704B552_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1* Array_Empty_TisAttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E_m0F7B255CF739B8C78F6D0663FFFD44753100FCAE_inline (const RuntimeMethod* method)
{
	return ((  AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1* (*) (const RuntimeMethod*))Array_Empty_TisAttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E_m0F7B255CF739B8C78F6D0663FFFD44753100FCAE_gshared_inline)(method);
}
inline void Span_1__ctor_m9923F08D09F1AF1763CE22C041DC55410B5A05B1_inline (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F* __this, AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1* ___0_array, const RuntimeMethod* method)
{
	((  void (*) (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F*, AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1*, const RuntimeMethod*))Span_1__ctor_m9923F08D09F1AF1763CE22C041DC55410B5A05B1_gshared_inline)(__this, ___0_array, method);
}
inline int32_t Span_1_get_Length_m739A39BB426B19B7583557F308B2BDA52E2C3DD9_inline (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428* __this, const RuntimeMethod* method)
{
	return ((  int32_t (*) (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428*, const RuntimeMethod*))Span_1_get_Length_m739A39BB426B19B7583557F308B2BDA52E2C3DD9_gshared_inline)(__this, method);
}
inline void Buffer_Memmove_TisBatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D_m9074142CF2F0828232AFEBB926F490D33E285B9B (BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* ___0_destination, BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method)
{
	((  void (*) (BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D*, BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D*, uint64_t, const RuntimeMethod*))Buffer_Memmove_TisBatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D_m9074142CF2F0828232AFEBB926F490D33E285B9B_gshared)(___0_destination, ___1_source, ___2_elementCount, method);
}
inline void ReadOnlySpan_1__ctor_m729E93C65964D6955F3A0794056B0A8FA3E1FB44_inline (ReadOnlySpan_1_tE73282FE39C46EDD2EE2F61A9E3E69BE943784B7* __this, BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (ReadOnlySpan_1_tE73282FE39C46EDD2EE2F61A9E3E69BE943784B7*, BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D*, int32_t, const RuntimeMethod*))ReadOnlySpan_1__ctor_m729E93C65964D6955F3A0794056B0A8FA3E1FB44_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline void Span_1__ctor_mB2713B2424259A6D18038A2E29BD96DA081378D6_inline (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428* __this, BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428*, BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D*, int32_t, const RuntimeMethod*))Span_1__ctor_mB2713B2424259A6D18038A2E29BD96DA081378D6_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80* Array_Empty_TisBatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D_m652D1F78F0DEF31A60A1D09589A89DE06F12C3E4_inline (const RuntimeMethod* method)
{
	return ((  BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80* (*) (const RuntimeMethod*))Array_Empty_TisBatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D_m652D1F78F0DEF31A60A1D09589A89DE06F12C3E4_gshared_inline)(method);
}
inline void Span_1__ctor_m7126F92024E73EE43580A7A9D64C39CD973406CC_inline (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428* __this, BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80* ___0_array, const RuntimeMethod* method)
{
	((  void (*) (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428*, BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80*, const RuntimeMethod*))Span_1__ctor_m7126F92024E73EE43580A7A9D64C39CD973406CC_gshared_inline)(__this, ___0_array, method);
}
inline int32_t Span_1_get_Length_m9535293C1FDA720739072391EDE6F574C67C905D_inline (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4* __this, const RuntimeMethod* method)
{
	return ((  int32_t (*) (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4*, const RuntimeMethod*))Span_1_get_Length_m9535293C1FDA720739072391EDE6F574C67C905D_gshared_inline)(__this, method);
}
inline void Buffer_Memmove_TisBatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770_m30599D9EEC5CBA92A026E19AFB3510603F2595EB (BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* ___0_destination, BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method)
{
	((  void (*) (BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770*, BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770*, uint64_t, const RuntimeMethod*))Buffer_Memmove_TisBatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770_m30599D9EEC5CBA92A026E19AFB3510603F2595EB_gshared)(___0_destination, ___1_source, ___2_elementCount, method);
}
inline void ReadOnlySpan_1__ctor_m0D6A1AA77CFA40D8A9849AA4338A5A43DC6CF0C0_inline (ReadOnlySpan_1_t3D56DFE8743E7EF0665F38A59DA5B99E0F9425B0* __this, BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (ReadOnlySpan_1_t3D56DFE8743E7EF0665F38A59DA5B99E0F9425B0*, BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770*, int32_t, const RuntimeMethod*))ReadOnlySpan_1__ctor_m0D6A1AA77CFA40D8A9849AA4338A5A43DC6CF0C0_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline void Span_1__ctor_m38FB4416138CB97C429799865EA7C7156E442FCD_inline (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4* __this, BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4*, BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770*, int32_t, const RuntimeMethod*))Span_1__ctor_m38FB4416138CB97C429799865EA7C7156E442FCD_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963* Array_Empty_TisBatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770_mEFC02ED7475540CF71CD66D177FEDA1EA4155C01_inline (const RuntimeMethod* method)
{
	return ((  BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963* (*) (const RuntimeMethod*))Array_Empty_TisBatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770_mEFC02ED7475540CF71CD66D177FEDA1EA4155C01_gshared_inline)(method);
}
inline void Span_1__ctor_mF34E7D8B1563ED8C47501C1BF37B17509D397F30_inline (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4* __this, BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963* ___0_array, const RuntimeMethod* method)
{
	((  void (*) (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4*, BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963*, const RuntimeMethod*))Span_1__ctor_mF34E7D8B1563ED8C47501C1BF37B17509D397F30_gshared_inline)(__this, ___0_array, method);
}
inline int32_t Span_1_get_Length_m8E10CA1F785F77C56E8D665A3CE6AF1DC31200FA_inline (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF* __this, const RuntimeMethod* method)
{
	return ((  int32_t (*) (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF*, const RuntimeMethod*))Span_1_get_Length_m8E10CA1F785F77C56E8D665A3CE6AF1DC31200FA_gshared_inline)(__this, method);
}
inline void Buffer_Memmove_TisBatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C_m8557E6B1B48B7EB426E6CCFA83223FDCB8F16135 (BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* ___0_destination, BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method)
{
	((  void (*) (BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C*, BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C*, uint64_t, const RuntimeMethod*))Buffer_Memmove_TisBatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C_m8557E6B1B48B7EB426E6CCFA83223FDCB8F16135_gshared)(___0_destination, ___1_source, ___2_elementCount, method);
}
inline void ReadOnlySpan_1__ctor_m89203DEBD91443BD65C0CCC9EAFA52A1CDE4B184_inline (ReadOnlySpan_1_t92B77B310CC53A58C3C04B20E0B7F18C2EEFB130* __this, BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (ReadOnlySpan_1_t92B77B310CC53A58C3C04B20E0B7F18C2EEFB130*, BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C*, int32_t, const RuntimeMethod*))ReadOnlySpan_1__ctor_m89203DEBD91443BD65C0CCC9EAFA52A1CDE4B184_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline void Span_1__ctor_mA2CE8DBE18F6BB54702DAA56720F89DD968A5271_inline (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF* __this, BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF*, BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C*, int32_t, const RuntimeMethod*))Span_1__ctor_mA2CE8DBE18F6BB54702DAA56720F89DD968A5271_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4* Array_Empty_TisBatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C_m0154337C8713B2FE58E471FD10F37CFF0E4D1537_inline (const RuntimeMethod* method)
{
	return ((  BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4* (*) (const RuntimeMethod*))Array_Empty_TisBatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C_m0154337C8713B2FE58E471FD10F37CFF0E4D1537_gshared_inline)(method);
}
inline void Span_1__ctor_mCDB221C7D73874741828DA34B70D3B0AADC00136_inline (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF* __this, BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4* ___0_array, const RuntimeMethod* method)
{
	((  void (*) (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF*, BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4*, const RuntimeMethod*))Span_1__ctor_mCDB221C7D73874741828DA34B70D3B0AADC00136_gshared_inline)(__this, ___0_array, method);
}
inline int32_t Span_1_get_Length_mDDD127BFC8F4A5DEEE8DBC6779C72A75229E2A08_inline (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1* __this, const RuntimeMethod* method)
{
	return ((  int32_t (*) (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1*, const RuntimeMethod*))Span_1_get_Length_mDDD127BFC8F4A5DEEE8DBC6779C72A75229E2A08_gshared_inline)(__this, method);
}
inline void Buffer_Memmove_TisBatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0_m9EE961D328A463A3B89EBC9E2B20D70A09D560A9 (BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* ___0_destination, BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method)
{
	((  void (*) (BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0*, BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0*, uint64_t, const RuntimeMethod*))Buffer_Memmove_TisBatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0_m9EE961D328A463A3B89EBC9E2B20D70A09D560A9_gshared)(___0_destination, ___1_source, ___2_elementCount, method);
}
inline void ReadOnlySpan_1__ctor_m9A03AF77602C387B913C41E787C615B3D1C1610F_inline (ReadOnlySpan_1_tD9DB60FA9C52DCE56999EB3FEEA0CDE0FDFFFFE2* __this, BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (ReadOnlySpan_1_tD9DB60FA9C52DCE56999EB3FEEA0CDE0FDFFFFE2*, BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0*, int32_t, const RuntimeMethod*))ReadOnlySpan_1__ctor_m9A03AF77602C387B913C41E787C615B3D1C1610F_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline void Span_1__ctor_m0B727F1A38BD125BA88E22B9518BBEA69CD9F192_inline (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1* __this, BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1*, BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0*, int32_t, const RuntimeMethod*))Span_1__ctor_m0B727F1A38BD125BA88E22B9518BBEA69CD9F192_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E* Array_Empty_TisBatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0_m30D7C90FCBAF09BB4E8C21ED5FE267CFF2F47BA4_inline (const RuntimeMethod* method)
{
	return ((  BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E* (*) (const RuntimeMethod*))Array_Empty_TisBatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0_m30D7C90FCBAF09BB4E8C21ED5FE267CFF2F47BA4_gshared_inline)(method);
}
inline void Span_1__ctor_m81637CB567960AF0B3F0471290B423B290FE1830_inline (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1* __this, BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E* ___0_array, const RuntimeMethod* method)
{
	((  void (*) (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1*, BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E*, const RuntimeMethod*))Span_1__ctor_m81637CB567960AF0B3F0471290B423B290FE1830_gshared_inline)(__this, ___0_array, method);
}
inline int32_t Span_1_get_Length_mF5DDBBF4783D0A55C88BFD79E1A1F8B5DDCF60C5_inline (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671* __this, const RuntimeMethod* method)
{
	return ((  int32_t (*) (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671*, const RuntimeMethod*))Span_1_get_Length_mF5DDBBF4783D0A55C88BFD79E1A1F8B5DDCF60C5_gshared_inline)(__this, method);
}
inline void Buffer_Memmove_TisBlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF_m3ED952566857853E08E1F43BA48367A61065281A (BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* ___0_destination, BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method)
{
	((  void (*) (BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF*, BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF*, uint64_t, const RuntimeMethod*))Buffer_Memmove_TisBlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF_m3ED952566857853E08E1F43BA48367A61065281A_gshared)(___0_destination, ___1_source, ___2_elementCount, method);
}
inline void ReadOnlySpan_1__ctor_m1C72072A1F9FFD48A21C4C5F2A81954708D29495_inline (ReadOnlySpan_1_t3D251E6627DBB106D7D34F8DC234D102B691FD49* __this, BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (ReadOnlySpan_1_t3D251E6627DBB106D7D34F8DC234D102B691FD49*, BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF*, int32_t, const RuntimeMethod*))ReadOnlySpan_1__ctor_m1C72072A1F9FFD48A21C4C5F2A81954708D29495_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline void Span_1__ctor_m4F32D7D5653D612D237DDFD35C9850E16CA11C18_inline (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671* __this, BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671*, BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF*, int32_t, const RuntimeMethod*))Span_1__ctor_m4F32D7D5653D612D237DDFD35C9850E16CA11C18_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B* Array_Empty_TisBlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF_mCD144DB8231D7EA3AF1DB4FEB94681C9E580CA9D_inline (const RuntimeMethod* method)
{
	return ((  BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B* (*) (const RuntimeMethod*))Array_Empty_TisBlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF_mCD144DB8231D7EA3AF1DB4FEB94681C9E580CA9D_gshared_inline)(method);
}
inline void Span_1__ctor_mCA3E1FB18A1006842617ABD22464535398F4655D_inline (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671* __this, BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B* ___0_array, const RuntimeMethod* method)
{
	((  void (*) (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671*, BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B*, const RuntimeMethod*))Span_1__ctor_mCA3E1FB18A1006842617ABD22464535398F4655D_gshared_inline)(__this, ___0_array, method);
}
inline int32_t Span_1_get_Length_m0A22C7814AA57E51DE4EDC241010E81DA05627F9_inline (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C* __this, const RuntimeMethod* method)
{
	return ((  int32_t (*) (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C*, const RuntimeMethod*))Span_1_get_Length_m0A22C7814AA57E51DE4EDC241010E81DA05627F9_gshared_inline)(__this, method);
}
inline void Buffer_Memmove_TisBlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6_mE178FB3796A6A1A4F519A7BA46829D3A14F94CBD (BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* ___0_destination, BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method)
{
	((  void (*) (BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6*, BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6*, uint64_t, const RuntimeMethod*))Buffer_Memmove_TisBlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6_mE178FB3796A6A1A4F519A7BA46829D3A14F94CBD_gshared)(___0_destination, ___1_source, ___2_elementCount, method);
}
inline void ReadOnlySpan_1__ctor_m143F99ABFB2902F2BE3AB6725D5F8AEAECDEC5F4_inline (ReadOnlySpan_1_tD81D75B0E0400301DC302920BCCBB9FBD97CA2A8* __this, BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (ReadOnlySpan_1_tD81D75B0E0400301DC302920BCCBB9FBD97CA2A8*, BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6*, int32_t, const RuntimeMethod*))ReadOnlySpan_1__ctor_m143F99ABFB2902F2BE3AB6725D5F8AEAECDEC5F4_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline void Span_1__ctor_m6C9685057F182B8896BBE6B894974625E6DA4221_inline (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C* __this, BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C*, BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6*, int32_t, const RuntimeMethod*))Span_1__ctor_m6C9685057F182B8896BBE6B894974625E6DA4221_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515* Array_Empty_TisBlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6_m7D78EA1928C7379C27FE7337353FB51BEFD7476E_inline (const RuntimeMethod* method)
{
	return ((  BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515* (*) (const RuntimeMethod*))Array_Empty_TisBlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6_m7D78EA1928C7379C27FE7337353FB51BEFD7476E_gshared_inline)(method);
}
inline void Span_1__ctor_mF80BBD6B1E4103830CA7E538A9EE8ABA983D97EC_inline (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C* __this, BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515* ___0_array, const RuntimeMethod* method)
{
	((  void (*) (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C*, BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515*, const RuntimeMethod*))Span_1__ctor_mF80BBD6B1E4103830CA7E538A9EE8ABA983D97EC_gshared_inline)(__this, ___0_array, method);
}
inline int32_t Span_1_get_Length_m3C5D2034819739D3DDB78A3D890CEE1C94529B01_inline (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B* __this, const RuntimeMethod* method)
{
	return ((  int32_t (*) (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B*, const RuntimeMethod*))Span_1_get_Length_m3C5D2034819739D3DDB78A3D890CEE1C94529B01_gshared_inline)(__this, method);
}
inline void Buffer_Memmove_TisBlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C_m107660B4F4107562042A7A5BD0F5123732E1C1BF (BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* ___0_destination, BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method)
{
	((  void (*) (BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C*, BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C*, uint64_t, const RuntimeMethod*))Buffer_Memmove_TisBlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C_m107660B4F4107562042A7A5BD0F5123732E1C1BF_gshared)(___0_destination, ___1_source, ___2_elementCount, method);
}
inline void ReadOnlySpan_1__ctor_mD794B63B35B4B58E5337728A24E1998CFDD74810_inline (ReadOnlySpan_1_t70882B83461088DC23C3DB9AF59753CF91E1F3AA* __this, BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (ReadOnlySpan_1_t70882B83461088DC23C3DB9AF59753CF91E1F3AA*, BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C*, int32_t, const RuntimeMethod*))ReadOnlySpan_1__ctor_mD794B63B35B4B58E5337728A24E1998CFDD74810_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline void Span_1__ctor_m773D9E2695DC1A1EAF17FDC94204CB7B1DE21582_inline (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B* __this, BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B*, BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C*, int32_t, const RuntimeMethod*))Span_1__ctor_m773D9E2695DC1A1EAF17FDC94204CB7B1DE21582_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A* Array_Empty_TisBlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C_m48CDEF41C2F032666E4312F197041C0757708AE3_inline (const RuntimeMethod* method)
{
	return ((  BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A* (*) (const RuntimeMethod*))Array_Empty_TisBlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C_m48CDEF41C2F032666E4312F197041C0757708AE3_gshared_inline)(method);
}
inline void Span_1__ctor_mB392265B6A34EA59542E156547D1143AC4B0EBD4_inline (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B* __this, BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A* ___0_array, const RuntimeMethod* method)
{
	((  void (*) (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B*, BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A*, const RuntimeMethod*))Span_1__ctor_mB392265B6A34EA59542E156547D1143AC4B0EBD4_gshared_inline)(__this, ___0_array, method);
}
inline int32_t Span_1_get_Length_m088CD07D38433F7795ED549A2139E5D41EA16706_inline (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF* __this, const RuntimeMethod* method)
{
	return ((  int32_t (*) (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF*, const RuntimeMethod*))Span_1_get_Length_m088CD07D38433F7795ED549A2139E5D41EA16706_gshared_inline)(__this, method);
}
inline void Buffer_Memmove_TisBlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54_m812A4F9C363B5C0D2AAE7D5E5D180DB7F7FEF824 (BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* ___0_destination, BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method)
{
	((  void (*) (BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54*, BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54*, uint64_t, const RuntimeMethod*))Buffer_Memmove_TisBlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54_m812A4F9C363B5C0D2AAE7D5E5D180DB7F7FEF824_gshared)(___0_destination, ___1_source, ___2_elementCount, method);
}
inline void ReadOnlySpan_1__ctor_m1A260D918B5EB96D17E965BA7BA73DA0C3493361_inline (ReadOnlySpan_1_t2EFE9320FC985EB4C740E0FD04D094F25024C51D* __this, BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (ReadOnlySpan_1_t2EFE9320FC985EB4C740E0FD04D094F25024C51D*, BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54*, int32_t, const RuntimeMethod*))ReadOnlySpan_1__ctor_m1A260D918B5EB96D17E965BA7BA73DA0C3493361_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline void Span_1__ctor_mEA9008985414217BB660044157B6458716A8AB1D_inline (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF* __this, BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF*, BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54*, int32_t, const RuntimeMethod*))Span_1__ctor_mEA9008985414217BB660044157B6458716A8AB1D_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8* Array_Empty_TisBlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54_mDE388119C5F2CA6DED1F8895551BEDC81C379FF9_inline (const RuntimeMethod* method)
{
	return ((  BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8* (*) (const RuntimeMethod*))Array_Empty_TisBlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54_mDE388119C5F2CA6DED1F8895551BEDC81C379FF9_gshared_inline)(method);
}
inline void Span_1__ctor_mD521304CD3352077C22527BDFF5A3A6F5BBA2345_inline (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF* __this, BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8* ___0_array, const RuntimeMethod* method)
{
	((  void (*) (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF*, BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8*, const RuntimeMethod*))Span_1__ctor_mD521304CD3352077C22527BDFF5A3A6F5BBA2345_gshared_inline)(__this, ___0_array, method);
}
inline int32_t Span_1_get_Length_mE19749B8DE0A55279184BDD9C3F394605245F91A_inline (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C* __this, const RuntimeMethod* method)
{
	return ((  int32_t (*) (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C*, const RuntimeMethod*))Span_1_get_Length_mE19749B8DE0A55279184BDD9C3F394605245F91A_gshared_inline)(__this, method);
}
inline void Buffer_Memmove_TisBlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901_mCB357A3CD61F6C424F89F1F9C63A4446F5B9C57E (BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* ___0_destination, BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method)
{
	((  void (*) (BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901*, BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901*, uint64_t, const RuntimeMethod*))Buffer_Memmove_TisBlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901_mCB357A3CD61F6C424F89F1F9C63A4446F5B9C57E_gshared)(___0_destination, ___1_source, ___2_elementCount, method);
}
inline void ReadOnlySpan_1__ctor_mE88D51223E12D11DE487A5E2C7AF2013C6413887_inline (ReadOnlySpan_1_t5EE3C4B6EA25BB8F3F956DFD28820F5666342758* __this, BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (ReadOnlySpan_1_t5EE3C4B6EA25BB8F3F956DFD28820F5666342758*, BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901*, int32_t, const RuntimeMethod*))ReadOnlySpan_1__ctor_mE88D51223E12D11DE487A5E2C7AF2013C6413887_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline void Span_1__ctor_m18D55B2B52C0EE4D379FCBBC6EE16E72CE2928E1_inline (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C* __this, BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C*, BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901*, int32_t, const RuntimeMethod*))Span_1__ctor_m18D55B2B52C0EE4D379FCBBC6EE16E72CE2928E1_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F* Array_Empty_TisBlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901_m75A4A4FD904386887E2E78BA168595529AF13939_inline (const RuntimeMethod* method)
{
	return ((  BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F* (*) (const RuntimeMethod*))Array_Empty_TisBlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901_m75A4A4FD904386887E2E78BA168595529AF13939_gshared_inline)(method);
}
inline void Span_1__ctor_mBC0A2A6F732CAD76B8E0B559AAF16DDDC7DA95FB_inline (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C* __this, BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F* ___0_array, const RuntimeMethod* method)
{
	((  void (*) (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C*, BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F*, const RuntimeMethod*))Span_1__ctor_mBC0A2A6F732CAD76B8E0B559AAF16DDDC7DA95FB_gshared_inline)(__this, ___0_array, method);
}
inline int32_t Span_1_get_Length_m27BEF44B2CCD3948DC00D344142E534E36BA7524_inline (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1* __this, const RuntimeMethod* method)
{
	return ((  int32_t (*) (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1*, const RuntimeMethod*))Span_1_get_Length_m27BEF44B2CCD3948DC00D344142E534E36BA7524_gshared_inline)(__this, method);
}
inline void Buffer_Memmove_TisBlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472_mDE86D51ADDC3C7F13B214DA755964EA5014FC510 (BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* ___0_destination, BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method)
{
	((  void (*) (BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472*, BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472*, uint64_t, const RuntimeMethod*))Buffer_Memmove_TisBlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472_mDE86D51ADDC3C7F13B214DA755964EA5014FC510_gshared)(___0_destination, ___1_source, ___2_elementCount, method);
}
inline void ReadOnlySpan_1__ctor_m83EAE3C4FCB23391B66637D0580733B85E12B21A_inline (ReadOnlySpan_1_t9DCA2EC81EE91A02D7C140CF3376183B663C483A* __this, BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (ReadOnlySpan_1_t9DCA2EC81EE91A02D7C140CF3376183B663C483A*, BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472*, int32_t, const RuntimeMethod*))ReadOnlySpan_1__ctor_m83EAE3C4FCB23391B66637D0580733B85E12B21A_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline void Span_1__ctor_m0635E38BE51343FEA108E4BAC304A66703B9E727_inline (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1* __this, BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1*, BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472*, int32_t, const RuntimeMethod*))Span_1__ctor_m0635E38BE51343FEA108E4BAC304A66703B9E727_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A* Array_Empty_TisBlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472_m6A8620E16588CCAE8CBA423DCB92B7E1D919A942_inline (const RuntimeMethod* method)
{
	return ((  BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A* (*) (const RuntimeMethod*))Array_Empty_TisBlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472_m6A8620E16588CCAE8CBA423DCB92B7E1D919A942_gshared_inline)(method);
}
inline void Span_1__ctor_mDAF271BFB6432EB8FCE7E7E95BB3A4918F1BCC92_inline (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1* __this, BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A* ___0_array, const RuntimeMethod* method)
{
	((  void (*) (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1*, BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A*, const RuntimeMethod*))Span_1__ctor_mDAF271BFB6432EB8FCE7E7E95BB3A4918F1BCC92_gshared_inline)(__this, ___0_array, method);
}
inline int32_t Span_1_get_Length_mCF6D6F9A859D0DB86629D46B25379F2D9C9B8CBB_inline (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F* __this, const RuntimeMethod* method)
{
	return ((  int32_t (*) (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F*, const RuntimeMethod*))Span_1_get_Length_mCF6D6F9A859D0DB86629D46B25379F2D9C9B8CBB_gshared_inline)(__this, method);
}
inline void Buffer_Memmove_TisBoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F_m5ADA1F648D3D4D37DB595FA24A1207E2AFE0FDE5 (BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* ___0_destination, BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method)
{
	((  void (*) (BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F*, BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F*, uint64_t, const RuntimeMethod*))Buffer_Memmove_TisBoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F_m5ADA1F648D3D4D37DB595FA24A1207E2AFE0FDE5_gshared)(___0_destination, ___1_source, ___2_elementCount, method);
}
inline void ReadOnlySpan_1__ctor_m0519BAD7DF271438138DD6AEC8CE28A60D0619CE_inline (ReadOnlySpan_1_t29FEC1E8134CF98858AC01DEE01F4B20811F583F* __this, BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (ReadOnlySpan_1_t29FEC1E8134CF98858AC01DEE01F4B20811F583F*, BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F*, int32_t, const RuntimeMethod*))ReadOnlySpan_1__ctor_m0519BAD7DF271438138DD6AEC8CE28A60D0619CE_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline void Span_1__ctor_mB74D4D3C2AD6B802DDB07920C132F554AED832BB_inline (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F* __this, BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F*, BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F*, int32_t, const RuntimeMethod*))Span_1__ctor_mB74D4D3C2AD6B802DDB07920C132F554AED832BB_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651* Array_Empty_TisBoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F_m327783287506F38234D68882E5BEE202618B5300_inline (const RuntimeMethod* method)
{
	return ((  BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651* (*) (const RuntimeMethod*))Array_Empty_TisBoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F_m327783287506F38234D68882E5BEE202618B5300_gshared_inline)(method);
}
inline void Span_1__ctor_m1D909AF584BE28F3A4AD990A5D7BE41287E28B38_inline (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F* __this, BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651* ___0_array, const RuntimeMethod* method)
{
	((  void (*) (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F*, BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651*, const RuntimeMethod*))Span_1__ctor_m1D909AF584BE28F3A4AD990A5D7BE41287E28B38_gshared_inline)(__this, ___0_array, method);
}
inline int32_t Span_1_get_Length_mED1253429B93CB6D2928015A22105A16FF64C86B_inline (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51* __this, const RuntimeMethod* method)
{
	return ((  int32_t (*) (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51*, const RuntimeMethod*))Span_1_get_Length_mED1253429B93CB6D2928015A22105A16FF64C86B_gshared_inline)(__this, method);
}
inline void Buffer_Memmove_TisBoolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_m6C1A997DCFEFC3BA96EEB5E75FB8B54DF2D21198 (bool* ___0_destination, bool* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method)
{
	((  void (*) (bool*, bool*, uint64_t, const RuntimeMethod*))Buffer_Memmove_TisBoolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_m6C1A997DCFEFC3BA96EEB5E75FB8B54DF2D21198_gshared)(___0_destination, ___1_source, ___2_elementCount, method);
}
inline void ReadOnlySpan_1__ctor_m5818A0EC9B5A3628E69F90A3521BDE96E1FDC74F_inline (ReadOnlySpan_1_t1CABD9AC48FDD39B6D2C26BCA3D87FCA5F94F1C2* __this, bool* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (ReadOnlySpan_1_t1CABD9AC48FDD39B6D2C26BCA3D87FCA5F94F1C2*, bool*, int32_t, const RuntimeMethod*))ReadOnlySpan_1__ctor_m5818A0EC9B5A3628E69F90A3521BDE96E1FDC74F_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline void Span_1__ctor_mC733F92ACB7C0D922180991442FE7A28DCB60772_inline (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51* __this, bool* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51*, bool*, int32_t, const RuntimeMethod*))Span_1__ctor_mC733F92ACB7C0D922180991442FE7A28DCB60772_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4* Array_Empty_TisBoolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_mAB215C445888719BD89809D99C3DBD3135C2B1E7_inline (const RuntimeMethod* method)
{
	return ((  BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4* (*) (const RuntimeMethod*))Array_Empty_TisBoolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_mAB215C445888719BD89809D99C3DBD3135C2B1E7_gshared_inline)(method);
}
inline void Span_1__ctor_m5AF7377F267C08DB0D59CB23F59DF954F7DEE62C_inline (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51* __this, BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4* ___0_array, const RuntimeMethod* method)
{
	((  void (*) (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51*, BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4*, const RuntimeMethod*))Span_1__ctor_m5AF7377F267C08DB0D59CB23F59DF954F7DEE62C_gshared_inline)(__this, ___0_array, method);
}
inline int32_t Span_1_get_Length_mA3FAE42313871FB8DAC80A4C14DDE4CCE75F1713_inline (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68* __this, const RuntimeMethod* method)
{
	return ((  int32_t (*) (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68*, const RuntimeMethod*))Span_1_get_Length_mA3FAE42313871FB8DAC80A4C14DDE4CCE75F1713_gshared_inline)(__this, method);
}
inline void Buffer_Memmove_TisBoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010_m795273AEE7A248BA5B241E561023F0456EF5DED1 (BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* ___0_destination, BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method)
{
	((  void (*) (BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010*, BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010*, uint64_t, const RuntimeMethod*))Buffer_Memmove_TisBoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010_m795273AEE7A248BA5B241E561023F0456EF5DED1_gshared)(___0_destination, ___1_source, ___2_elementCount, method);
}
inline void ReadOnlySpan_1__ctor_m4C7DD11D4BCC31C1EC9F0EA7D34388DB0B14A07A_inline (ReadOnlySpan_1_t5D1B06A3C861E766B5669E3742152A803D0F24AA* __this, BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (ReadOnlySpan_1_t5D1B06A3C861E766B5669E3742152A803D0F24AA*, BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010*, int32_t, const RuntimeMethod*))ReadOnlySpan_1__ctor_m4C7DD11D4BCC31C1EC9F0EA7D34388DB0B14A07A_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline void Span_1__ctor_m58CD191F04E31F33A163B278675E0FA9DF59166D_inline (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68* __this, BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68*, BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010*, int32_t, const RuntimeMethod*))Span_1__ctor_m58CD191F04E31F33A163B278675E0FA9DF59166D_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47* Array_Empty_TisBoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010_mD26FFE584F3D72C1C64FD47A5D59D19E615D8050_inline (const RuntimeMethod* method)
{
	return ((  BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47* (*) (const RuntimeMethod*))Array_Empty_TisBoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010_mD26FFE584F3D72C1C64FD47A5D59D19E615D8050_gshared_inline)(method);
}
inline void Span_1__ctor_m81F10BC6C4033345B53A361FD5777CB15BA51592_inline (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68* __this, BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47* ___0_array, const RuntimeMethod* method)
{
	((  void (*) (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68*, BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47*, const RuntimeMethod*))Span_1__ctor_m81F10BC6C4033345B53A361FD5777CB15BA51592_gshared_inline)(__this, ___0_array, method);
}
inline int32_t Span_1_get_Length_mF487895F53310FF6F399B7BD07038C49D29A6EAB_inline (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634* __this, const RuntimeMethod* method)
{
	return ((  int32_t (*) (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634*, const RuntimeMethod*))Span_1_get_Length_mF487895F53310FF6F399B7BD07038C49D29A6EAB_gshared_inline)(__this, method);
}
inline void Buffer_Memmove_TisBounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3_m4925E1D88272BE2E628F28AFFEC4FF09CC2EC647 (Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* ___0_destination, Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method)
{
	((  void (*) (Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3*, Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3*, uint64_t, const RuntimeMethod*))Buffer_Memmove_TisBounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3_m4925E1D88272BE2E628F28AFFEC4FF09CC2EC647_gshared)(___0_destination, ___1_source, ___2_elementCount, method);
}
inline void ReadOnlySpan_1__ctor_mB60BC31800C0877424E21575E45AE6C8AA50ADA6_inline (ReadOnlySpan_1_tCA4391229D70967C5F52C769440BB78CBF80BE99* __this, Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (ReadOnlySpan_1_tCA4391229D70967C5F52C769440BB78CBF80BE99*, Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3*, int32_t, const RuntimeMethod*))ReadOnlySpan_1__ctor_mB60BC31800C0877424E21575E45AE6C8AA50ADA6_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline void Span_1__ctor_mC3D811BCA73C14AA673ADAB2E2CF39FE1385714B_inline (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634* __this, Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634*, Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3*, int32_t, const RuntimeMethod*))Span_1__ctor_mC3D811BCA73C14AA673ADAB2E2CF39FE1385714B_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5* Array_Empty_TisBounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3_mB6CFBB5D8AF33F0BAE72154209AB29B8D52FBCDA_inline (const RuntimeMethod* method)
{
	return ((  BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5* (*) (const RuntimeMethod*))Array_Empty_TisBounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3_mB6CFBB5D8AF33F0BAE72154209AB29B8D52FBCDA_gshared_inline)(method);
}
inline void Span_1__ctor_m2F8451EBCA1900A8FF93D17DF8177C41EAA2E94B_inline (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634* __this, BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5* ___0_array, const RuntimeMethod* method)
{
	((  void (*) (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634*, BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5*, const RuntimeMethod*))Span_1__ctor_m2F8451EBCA1900A8FF93D17DF8177C41EAA2E94B_gshared_inline)(__this, ___0_array, method);
}
inline int32_t Span_1_get_Length_m8E944E4954E037877A25B9FF6B901F1F901D4769_inline (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305* __this, const RuntimeMethod* method)
{
	return ((  int32_t (*) (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305*, const RuntimeMethod*))Span_1_get_Length_m8E944E4954E037877A25B9FF6B901F1F901D4769_gshared_inline)(__this, method);
}
inline void Buffer_Memmove_TisByte_t94D9231AC217BE4D2E004C4CD32DF6D099EA41A3_mB1465EEEBE0A608FA51B29BC3F145F287AD04190 (uint8_t* ___0_destination, uint8_t* ___1_source, uint64_t ___2_elementCount, const RuntimeMethod* method)
{
	((  void (*) (uint8_t*, uint8_t*, uint64_t, const RuntimeMethod*))Buffer_Memmove_TisByte_t94D9231AC217BE4D2E004C4CD32DF6D099EA41A3_mB1465EEEBE0A608FA51B29BC3F145F287AD04190_gshared)(___0_destination, ___1_source, ___2_elementCount, method);
}
inline void ReadOnlySpan_1__ctor_m0FC0B92549C2968E80B5F75A85F28B96DBFCFD63_inline (ReadOnlySpan_1_tA850A6C0E88ABBA37646A078ACBC24D6D5FD9B4D* __this, uint8_t* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (ReadOnlySpan_1_tA850A6C0E88ABBA37646A078ACBC24D6D5FD9B4D*, uint8_t*, int32_t, const RuntimeMethod*))ReadOnlySpan_1__ctor_m0FC0B92549C2968E80B5F75A85F28B96DBFCFD63_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline void Span_1__ctor_m947BF95D54571BF3897F96822B7A8FDA5853497B_inline (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305* __this, uint8_t* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method)
{
	((  void (*) (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305*, uint8_t*, int32_t, const RuntimeMethod*))Span_1__ctor_m947BF95D54571BF3897F96822B7A8FDA5853497B_gshared_inline)(__this, ___0_ptr, ___1_length, method);
}
inline ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* Array_Empty_TisByte_t94D9231AC217BE4D2E004C4CD32DF6D099EA41A3_m6080CA526758F4FA182A066B2780D1761CD36ED5_inline (const RuntimeMethod* method)
{
	return ((  ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* (*) (const RuntimeMethod*))Array_Empty_TisByte_t94D9231AC217BE4D2E004C4CD32DF6D099EA41A3_m6080CA526758F4FA182A066B2780D1761CD36ED5_gshared_inline)(method);
}
inline void Span_1__ctor_m513968BDBFF3CFCE89F3F77FE44CAB22CA474EF9_inline (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305* __this, ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* ___0_array, const RuntimeMethod* method)
{
	((  void (*) (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305*, ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031*, const RuntimeMethod*))Span_1__ctor_m513968BDBFF3CFCE89F3F77FE44CAB22CA474EF9_gshared_inline)(__this, ___0_array, method);
}
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
void SpanAction_2_Invoke_m761FF4012DB09668C6A5AA76774E9F04199D212E_Multicast(SpanAction_2_t65B015FEFE1F64814AC2EFA0E19A38B1CFC53178* __this, Span_1_tEDDF15FCF9EC6DEBA0F696BAACDDBAB9D92C252D ___0_span, ValueTuple_3_tFD2ADB3DA89E958885034AAFEF1ABDA8C814D987 ___1_arg, const RuntimeMethod* method)
{
	il2cpp_array_size_t length = __this->___delegates->max_length;
	Delegate_t** delegatesToInvoke = reinterpret_cast<Delegate_t**>(__this->___delegates->GetAddressAtUnchecked(0));
	for (il2cpp_array_size_t i = 0; i < length; i++)
	{
		SpanAction_2_t65B015FEFE1F64814AC2EFA0E19A38B1CFC53178* currentDelegate = reinterpret_cast<SpanAction_2_t65B015FEFE1F64814AC2EFA0E19A38B1CFC53178*>(delegatesToInvoke[i]);
		typedef void (*FunctionPointerType) (RuntimeObject*, Span_1_tEDDF15FCF9EC6DEBA0F696BAACDDBAB9D92C252D, ValueTuple_3_tFD2ADB3DA89E958885034AAFEF1ABDA8C814D987, const RuntimeMethod*);
		((FunctionPointerType)currentDelegate->___invoke_impl)((Il2CppObject*)currentDelegate->___method_code, ___0_span, ___1_arg, reinterpret_cast<RuntimeMethod*>(currentDelegate->___method));
	}
}
void SpanAction_2_Invoke_m761FF4012DB09668C6A5AA76774E9F04199D212E_OpenInst(SpanAction_2_t65B015FEFE1F64814AC2EFA0E19A38B1CFC53178* __this, Span_1_tEDDF15FCF9EC6DEBA0F696BAACDDBAB9D92C252D ___0_span, ValueTuple_3_tFD2ADB3DA89E958885034AAFEF1ABDA8C814D987 ___1_arg, const RuntimeMethod* method)
{
	typedef void (*FunctionPointerType) (Span_1_tEDDF15FCF9EC6DEBA0F696BAACDDBAB9D92C252D, ValueTuple_3_tFD2ADB3DA89E958885034AAFEF1ABDA8C814D987, const RuntimeMethod*);
	((FunctionPointerType)__this->___method_ptr)(___0_span, ___1_arg, method);
}
void SpanAction_2_Invoke_m761FF4012DB09668C6A5AA76774E9F04199D212E_OpenStatic(SpanAction_2_t65B015FEFE1F64814AC2EFA0E19A38B1CFC53178* __this, Span_1_tEDDF15FCF9EC6DEBA0F696BAACDDBAB9D92C252D ___0_span, ValueTuple_3_tFD2ADB3DA89E958885034AAFEF1ABDA8C814D987 ___1_arg, const RuntimeMethod* method)
{
	typedef void (*FunctionPointerType) (Span_1_tEDDF15FCF9EC6DEBA0F696BAACDDBAB9D92C252D, ValueTuple_3_tFD2ADB3DA89E958885034AAFEF1ABDA8C814D987, const RuntimeMethod*);
	((FunctionPointerType)__this->___method_ptr)(___0_span, ___1_arg, method);
}
// Method Definition Index: 21133
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void SpanAction_2__ctor_m4BBCE8F9C0E8FCC935E1765742799AEAD48606AE_gshared (SpanAction_2_t65B015FEFE1F64814AC2EFA0E19A38B1CFC53178* __this, RuntimeObject* ___0_object, intptr_t ___1_method, const RuntimeMethod* method) 
{
	__this->___method_ptr = (intptr_t)il2cpp_codegen_get_method_pointer((RuntimeMethod*)___1_method);
	__this->___method = ___1_method;
	__this->___m_target = ___0_object;
	Il2CppCodeGenWriteBarrier((void**)(&__this->___m_target), (void*)___0_object);
	int parameterCount = il2cpp_codegen_method_parameter_count((RuntimeMethod*)___1_method);
	__this->___method_code = (intptr_t)__this;
	if (MethodIsStatic((RuntimeMethod*)___1_method))
	{
		bool isOpen = parameterCount == 2;
		if (isOpen)
			__this->___invoke_impl = (intptr_t)&SpanAction_2_Invoke_m761FF4012DB09668C6A5AA76774E9F04199D212E_OpenStatic;
		else
			{
				__this->___invoke_impl = __this->___method_ptr;
				__this->___method_code = (intptr_t)__this->___m_target;
			}
	}
	else
	{
		if (___0_object == NULL)
			il2cpp_codegen_raise_exception(il2cpp_codegen_get_argument_exception(NULL, "Delegate to an instance method cannot have null 'this'."), NULL);
		__this->___invoke_impl = __this->___method_ptr;
		__this->___method_code = (intptr_t)__this->___m_target;
	}
	__this->___extra_arg = (intptr_t)&SpanAction_2_Invoke_m761FF4012DB09668C6A5AA76774E9F04199D212E_Multicast;
}
// Method Definition Index: 21134
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void SpanAction_2_Invoke_m761FF4012DB09668C6A5AA76774E9F04199D212E_gshared (SpanAction_2_t65B015FEFE1F64814AC2EFA0E19A38B1CFC53178* __this, Span_1_tEDDF15FCF9EC6DEBA0F696BAACDDBAB9D92C252D ___0_span, ValueTuple_3_tFD2ADB3DA89E958885034AAFEF1ABDA8C814D987 ___1_arg, const RuntimeMethod* method) 
{
	typedef void (*FunctionPointerType) (RuntimeObject*, Span_1_tEDDF15FCF9EC6DEBA0F696BAACDDBAB9D92C252D, ValueTuple_3_tFD2ADB3DA89E958885034AAFEF1ABDA8C814D987, const RuntimeMethod*);
	((FunctionPointerType)__this->___invoke_impl)((Il2CppObject*)__this->___method_code, ___0_span, ___1_arg, reinterpret_cast<RuntimeMethod*>(__this->___method));
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
void SpanAction_2_Invoke_m65D110A8BF9027F070FA8CF53AF75D47A98C6E2A_Multicast(SpanAction_2_t84FDFFEECCC96A9A407DCB490E60340E38185947* __this, Span_1_tEDDF15FCF9EC6DEBA0F696BAACDDBAB9D92C252D ___0_span, ValueTuple_5_t558B9F95CA55DE5694FC58A3BEAE441BF728FB57 ___1_arg, const RuntimeMethod* method)
{
	il2cpp_array_size_t length = __this->___delegates->max_length;
	Delegate_t** delegatesToInvoke = reinterpret_cast<Delegate_t**>(__this->___delegates->GetAddressAtUnchecked(0));
	for (il2cpp_array_size_t i = 0; i < length; i++)
	{
		SpanAction_2_t84FDFFEECCC96A9A407DCB490E60340E38185947* currentDelegate = reinterpret_cast<SpanAction_2_t84FDFFEECCC96A9A407DCB490E60340E38185947*>(delegatesToInvoke[i]);
		typedef void (*FunctionPointerType) (RuntimeObject*, Span_1_tEDDF15FCF9EC6DEBA0F696BAACDDBAB9D92C252D, ValueTuple_5_t558B9F95CA55DE5694FC58A3BEAE441BF728FB57, const RuntimeMethod*);
		((FunctionPointerType)currentDelegate->___invoke_impl)((Il2CppObject*)currentDelegate->___method_code, ___0_span, ___1_arg, reinterpret_cast<RuntimeMethod*>(currentDelegate->___method));
	}
}
void SpanAction_2_Invoke_m65D110A8BF9027F070FA8CF53AF75D47A98C6E2A_OpenInst(SpanAction_2_t84FDFFEECCC96A9A407DCB490E60340E38185947* __this, Span_1_tEDDF15FCF9EC6DEBA0F696BAACDDBAB9D92C252D ___0_span, ValueTuple_5_t558B9F95CA55DE5694FC58A3BEAE441BF728FB57 ___1_arg, const RuntimeMethod* method)
{
	typedef void (*FunctionPointerType) (Span_1_tEDDF15FCF9EC6DEBA0F696BAACDDBAB9D92C252D, ValueTuple_5_t558B9F95CA55DE5694FC58A3BEAE441BF728FB57, const RuntimeMethod*);
	((FunctionPointerType)__this->___method_ptr)(___0_span, ___1_arg, method);
}
void SpanAction_2_Invoke_m65D110A8BF9027F070FA8CF53AF75D47A98C6E2A_OpenStatic(SpanAction_2_t84FDFFEECCC96A9A407DCB490E60340E38185947* __this, Span_1_tEDDF15FCF9EC6DEBA0F696BAACDDBAB9D92C252D ___0_span, ValueTuple_5_t558B9F95CA55DE5694FC58A3BEAE441BF728FB57 ___1_arg, const RuntimeMethod* method)
{
	typedef void (*FunctionPointerType) (Span_1_tEDDF15FCF9EC6DEBA0F696BAACDDBAB9D92C252D, ValueTuple_5_t558B9F95CA55DE5694FC58A3BEAE441BF728FB57, const RuntimeMethod*);
	((FunctionPointerType)__this->___method_ptr)(___0_span, ___1_arg, method);
}
// Method Definition Index: 21133
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void SpanAction_2__ctor_m52B80F2401AFC1EDA0C92BDEC3320FB33A9FEB85_gshared (SpanAction_2_t84FDFFEECCC96A9A407DCB490E60340E38185947* __this, RuntimeObject* ___0_object, intptr_t ___1_method, const RuntimeMethod* method) 
{
	__this->___method_ptr = (intptr_t)il2cpp_codegen_get_method_pointer((RuntimeMethod*)___1_method);
	__this->___method = ___1_method;
	__this->___m_target = ___0_object;
	Il2CppCodeGenWriteBarrier((void**)(&__this->___m_target), (void*)___0_object);
	int parameterCount = il2cpp_codegen_method_parameter_count((RuntimeMethod*)___1_method);
	__this->___method_code = (intptr_t)__this;
	if (MethodIsStatic((RuntimeMethod*)___1_method))
	{
		bool isOpen = parameterCount == 2;
		if (isOpen)
			__this->___invoke_impl = (intptr_t)&SpanAction_2_Invoke_m65D110A8BF9027F070FA8CF53AF75D47A98C6E2A_OpenStatic;
		else
			{
				__this->___invoke_impl = __this->___method_ptr;
				__this->___method_code = (intptr_t)__this->___m_target;
			}
	}
	else
	{
		if (___0_object == NULL)
			il2cpp_codegen_raise_exception(il2cpp_codegen_get_argument_exception(NULL, "Delegate to an instance method cannot have null 'this'."), NULL);
		__this->___invoke_impl = __this->___method_ptr;
		__this->___method_code = (intptr_t)__this->___m_target;
	}
	__this->___extra_arg = (intptr_t)&SpanAction_2_Invoke_m65D110A8BF9027F070FA8CF53AF75D47A98C6E2A_Multicast;
}
// Method Definition Index: 21134
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void SpanAction_2_Invoke_m65D110A8BF9027F070FA8CF53AF75D47A98C6E2A_gshared (SpanAction_2_t84FDFFEECCC96A9A407DCB490E60340E38185947* __this, Span_1_tEDDF15FCF9EC6DEBA0F696BAACDDBAB9D92C252D ___0_span, ValueTuple_5_t558B9F95CA55DE5694FC58A3BEAE441BF728FB57 ___1_arg, const RuntimeMethod* method) 
{
	typedef void (*FunctionPointerType) (RuntimeObject*, Span_1_tEDDF15FCF9EC6DEBA0F696BAACDDBAB9D92C252D, ValueTuple_5_t558B9F95CA55DE5694FC58A3BEAE441BF728FB57, const RuntimeMethod*);
	((FunctionPointerType)__this->___invoke_impl)((Il2CppObject*)__this->___method_code, ___0_span, ___1_arg, reinterpret_cast<RuntimeMethod*>(__this->___method));
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
void SpanAction_2_Invoke_m4E51CE671BDBB67F3A2E93DA5AA706A80D9F166C_Multicast(SpanAction_2_t5907E59A3FE410EE3FC9FC29F5E9418DF5894C8B* __this, Span_1_tDEB40BEFA77B5E4BB49B058CD3050EEA4DD36C54 ___0_span, Il2CppFullySharedGenericAny ___1_arg, const RuntimeMethod* method)
{
	il2cpp_array_size_t length = __this->___delegates->max_length;
	Delegate_t** delegatesToInvoke = reinterpret_cast<Delegate_t**>(__this->___delegates->GetAddressAtUnchecked(0));
	for (il2cpp_array_size_t i = 0; i < length; i++)
	{
		SpanAction_2_t5907E59A3FE410EE3FC9FC29F5E9418DF5894C8B* currentDelegate = reinterpret_cast<SpanAction_2_t5907E59A3FE410EE3FC9FC29F5E9418DF5894C8B*>(delegatesToInvoke[i]);
		typedef void (*FunctionPointerType) (RuntimeObject*, Span_1_tDEB40BEFA77B5E4BB49B058CD3050EEA4DD36C54, Il2CppFullySharedGenericAny, const RuntimeMethod*);
		((FunctionPointerType)currentDelegate->___invoke_impl)((Il2CppObject*)currentDelegate->___method_code, ___0_span, ___1_arg, reinterpret_cast<RuntimeMethod*>(currentDelegate->___method));
	}
}
void SpanAction_2_Invoke_m4E51CE671BDBB67F3A2E93DA5AA706A80D9F166C_OpenStaticInvoker(SpanAction_2_t5907E59A3FE410EE3FC9FC29F5E9418DF5894C8B* __this, Span_1_tDEB40BEFA77B5E4BB49B058CD3050EEA4DD36C54 ___0_span, Il2CppFullySharedGenericAny ___1_arg, const RuntimeMethod* method)
{
	InvokerActionInvoker2< Span_1_tDEB40BEFA77B5E4BB49B058CD3050EEA4DD36C54, Il2CppFullySharedGenericAny >::Invoke((Il2CppMethodPointer)__this->___method_ptr, method, NULL, ___0_span, ___1_arg);
}
void SpanAction_2_Invoke_m4E51CE671BDBB67F3A2E93DA5AA706A80D9F166C_ClosedStaticInvoker(SpanAction_2_t5907E59A3FE410EE3FC9FC29F5E9418DF5894C8B* __this, Span_1_tDEB40BEFA77B5E4BB49B058CD3050EEA4DD36C54 ___0_span, Il2CppFullySharedGenericAny ___1_arg, const RuntimeMethod* method)
{
	InvokerActionInvoker3< RuntimeObject*, Span_1_tDEB40BEFA77B5E4BB49B058CD3050EEA4DD36C54, Il2CppFullySharedGenericAny >::Invoke((Il2CppMethodPointer)__this->___method_ptr, method, NULL, __this->___m_target, ___0_span, ___1_arg);
}
void SpanAction_2_Invoke_m4E51CE671BDBB67F3A2E93DA5AA706A80D9F166C_ClosedInstInvoker(SpanAction_2_t5907E59A3FE410EE3FC9FC29F5E9418DF5894C8B* __this, Span_1_tDEB40BEFA77B5E4BB49B058CD3050EEA4DD36C54 ___0_span, Il2CppFullySharedGenericAny ___1_arg, const RuntimeMethod* method)
{
	InvokerActionInvoker2< Span_1_tDEB40BEFA77B5E4BB49B058CD3050EEA4DD36C54, Il2CppFullySharedGenericAny >::Invoke((Il2CppMethodPointer)__this->___method_ptr, method, __this->___m_target, ___0_span, ___1_arg);
}
// Method Definition Index: 21133
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void SpanAction_2__ctor_mDA3D51C491A1F13D8CC15EB34D552737FFAE68E4_gshared (SpanAction_2_t5907E59A3FE410EE3FC9FC29F5E9418DF5894C8B* __this, RuntimeObject* ___0_object, intptr_t ___1_method, const RuntimeMethod* method) 
{
	__this->___method_ptr = (intptr_t)il2cpp_codegen_get_method_pointer((RuntimeMethod*)___1_method);
	__this->___method = ___1_method;
	__this->___m_target = ___0_object;
	Il2CppCodeGenWriteBarrier((void**)(&__this->___m_target), (void*)___0_object);
	int parameterCount = il2cpp_codegen_method_parameter_count((RuntimeMethod*)___1_method);
	__this->___method_code = (intptr_t)__this;
	if (MethodIsStatic((RuntimeMethod*)___1_method))
	{
		bool isOpen = parameterCount == 2;
		if (isOpen)
			__this->___invoke_impl = (intptr_t)&SpanAction_2_Invoke_m4E51CE671BDBB67F3A2E93DA5AA706A80D9F166C_OpenStaticInvoker;
		else
			__this->___invoke_impl = (intptr_t)&SpanAction_2_Invoke_m4E51CE671BDBB67F3A2E93DA5AA706A80D9F166C_ClosedStaticInvoker;
	}
	else
	{
		if (___0_object == NULL)
			il2cpp_codegen_raise_exception(il2cpp_codegen_get_argument_exception(NULL, "Delegate to an instance method cannot have null 'this'."), NULL);
		__this->___invoke_impl = (intptr_t)&SpanAction_2_Invoke_m4E51CE671BDBB67F3A2E93DA5AA706A80D9F166C_ClosedInstInvoker;
	}
	__this->___extra_arg = (intptr_t)&SpanAction_2_Invoke_m4E51CE671BDBB67F3A2E93DA5AA706A80D9F166C_Multicast;
}
// Method Definition Index: 21134
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void SpanAction_2_Invoke_m4E51CE671BDBB67F3A2E93DA5AA706A80D9F166C_gshared (SpanAction_2_t5907E59A3FE410EE3FC9FC29F5E9418DF5894C8B* __this, Span_1_tDEB40BEFA77B5E4BB49B058CD3050EEA4DD36C54 ___0_span, Il2CppFullySharedGenericAny ___1_arg, const RuntimeMethod* method) 
{
	typedef void (*FunctionPointerType) (RuntimeObject*, Span_1_tDEB40BEFA77B5E4BB49B058CD3050EEA4DD36C54, Il2CppFullySharedGenericAny, const RuntimeMethod*);
	((FunctionPointerType)__this->___invoke_impl)((Il2CppObject*)__this->___method_code, ___0_span, ___1_arg, reinterpret_cast<RuntimeMethod*>(__this->___method));
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// Method Definition Index: 13818
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m2646A3BCF55B82973A1E7845EE19BC641CD4D35E_gshared (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B* __this, HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C* ___0_array, const RuntimeMethod* method) 
{
	HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5));
		goto IL_0037;
	}

IL_0037:
	{
		HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_4;
		L_4 = il2cpp_unsafe_as_ref<HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5>(L_3);
		ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13819
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mB473CC15720118CB8C323F3D0E776E89DDB2D3F6_gshared (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B* __this, HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C* ___0_array, int32_t ___1_start, int32_t ___2_length, const RuntimeMethod* method) 
{
	HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_0016;
		}
	}
	{
		int32_t L_1 = ___1_start;
		if (L_1)
		{
			goto IL_0009;
		}
	}
	{
		int32_t L_2 = ___2_length;
		if (!L_2)
		{
			goto IL_000e;
		}
	}

IL_0009:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B));
		return;
	}

IL_0016:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5));
		goto IL_0042;
	}

IL_0042:
	{
		int32_t L_4 = ___1_start;
		HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C* L_5 = ___0_array;
		NullCheck(L_5);
		if ((!(((uint32_t)L_4) <= ((uint32_t)((int32_t)(((RuntimeArray*)L_5)->max_length))))))
		{
			goto IL_0050;
		}
	}
	{
		int32_t L_6 = ___2_length;
		HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C* L_7 = ___0_array;
		NullCheck(L_7);
		int32_t L_8 = ___1_start;
		if ((!(((uint32_t)L_6) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(((int32_t)(((RuntimeArray*)L_7)->max_length)), L_8))))))
		{
			goto IL_0055;
		}
	}

IL_0050:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0055:
	{
		HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C* L_9 = ___0_array;
		NullCheck((RuntimeArray*)L_9);
		uint8_t* L_10;
		L_10 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_9, NULL);
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_11;
		L_11 = il2cpp_unsafe_as_ref<HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5>(L_10);
		int32_t L_12 = ___1_start;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_13;
		L_13 = il2cpp_unsafe_add<HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5,int32_t>(L_11, L_12);
		ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 L_14;
		memset((&L_14), 0, sizeof(L_14));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_14), L_13);
		__this->____pointer = L_14;
		int32_t L_15 = ___2_length;
		__this->____length = L_15;
		return;
	}
}
// Method Definition Index: 13820
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mFDEBDB83CE842124FDE9EA4004D4FA9908D841A7_gshared (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B* __this, void* ___0_pointer, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
	}
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_0 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_1;
		L_1 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_0, NULL);
		ThrowHelper_ThrowInvalidTypeWithPointersNotSupported_m5707DE408588F6EAC3FC7D10F9520308CF8C8CCF(L_1, NULL);
	}

IL_0016:
	{
		int32_t L_2 = ___1_length;
		if ((((int32_t)L_2) >= ((int32_t)0)))
		{
			goto IL_001f;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_001f:
	{
		void* L_3 = ___0_pointer;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_4;
		L_4 = il2cpp_unsafe_as_ref<HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5>((uint8_t*)L_3);
		ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		int32_t L_6 = ___1_length;
		__this->____length = L_6;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mA74DDDE530919E8BC3D60C7CBD981ED0F11DD14F_gshared (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B* __this, HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_0 = ___0_ptr;
		ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13822
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* Span_1_get_Item_mAF48B06DF488564ADFAC948D9EDF137DBE64FC90_gshared (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B* __this, int32_t ___0_index, const RuntimeMethod* method) 
{
	ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_index;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) >= ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowIndexOutOfRangeException_m86F753A24E2765A35546BA6352A7E4F0BB8A66B5(NULL);
	}

IL_000e:
	{
		ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 L_2 = __this->____pointer;
		V_0 = L_2;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_index;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_5;
		L_5 = il2cpp_unsafe_add<HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5,int32_t>(L_3, L_4);
		return L_5;
	}
}
// Method Definition Index: 13823
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* Span_1_GetPinnableReference_mBF0DF1197F58ABA99B59075B45F966097D37F9E5_gshared (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B* __this, const RuntimeMethod* method) 
{
	ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_0010;
		}
	}
	{
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_1;
		L_1 = il2cpp_unsafe_as_ref<HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5>((void*)((uintptr_t)0));
		return L_1;
	}

IL_0010:
	{
		ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 L_2 = __this->____pointer;
		V_0 = L_2;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5, (Il2CppByReference*)(&V_0));
		return L_3;
	}
}
// Method Definition Index: 13824
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_Fill_m5C7FACC3C3A1F85847C5E2C9FF54B8383D4BAB60_gshared (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B* __this, HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5 ___0_value, const RuntimeMethod* method) 
{
	uint32_t V_0 = 0;
	HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5 V_1;
	memset((&V_1), 0, sizeof(V_1));
	ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 V_2;
	memset((&V_2), 0, sizeof(V_2));
	uint64_t V_3 = 0;
	HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* V_4 = NULL;
	uint64_t V_5 = 0;
	uint64_t V_6 = 0;
	{
		int32_t L_0;
		L_0 = il2cpp_unsafe_sizeof<HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5>();
		if ((!(((uint32_t)L_0) == ((uint32_t)1))))
		{
			goto IL_0037;
		}
	}
	{
		int32_t L_1 = __this->____length;
		V_0 = (uint32_t)L_1;
		uint32_t L_2 = V_0;
		if (L_2)
		{
			goto IL_0013;
		}
	}
	{
		return;
	}

IL_0013:
	{
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5 L_3 = ___0_value;
		V_1 = L_3;
		ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 L_4 = __this->____pointer;
		V_2 = L_4;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_5;
		L_5 = IL2CPP_BY_REFERENCE_GET_VALUE(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5, (Il2CppByReference*)(&V_2));
		uint8_t* L_6;
		L_6 = il2cpp_unsafe_as_ref<uint8_t>(L_5);
		uint8_t* L_7;
		L_7 = il2cpp_unsafe_as_ref<uint8_t>((&V_1));
		int32_t L_8 = *((uint8_t*)L_7);
		uint32_t L_9 = V_0;
		Unsafe_InitBlockUnaligned_m6F2353EB9ABC9320E61629FAEE23948C80BFF03A(L_6, (uint8_t)L_8, L_9, NULL);
		return;
	}

IL_0037:
	{
		int32_t L_10 = __this->____length;
		V_3 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_10));
		uint64_t L_11 = V_3;
		if (L_11)
		{
			goto IL_0043;
		}
	}
	{
		return;
	}

IL_0043:
	{
		ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 L_12 = __this->____pointer;
		V_2 = L_12;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_13;
		L_13 = IL2CPP_BY_REFERENCE_GET_VALUE(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5, (Il2CppByReference*)(&V_2));
		V_4 = L_13;
		int32_t L_14;
		L_14 = il2cpp_unsafe_sizeof<HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5>();
		V_5 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_14));
		V_6 = (uint64_t)((int64_t)0);
		goto IL_0110;
	}

IL_0064:
	{
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_15 = V_4;
		uint64_t L_16 = V_6;
		uint64_t L_17 = V_5;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_18;
		L_18 = il2cpp_unsafe_add_byte_offset<HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5,uint64_t>(L_15, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_16, (int64_t)L_17)));
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5 L_19 = ___0_value;
		*(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*)L_18 = L_19;
		Il2CppCodeGenWriteBarrier((void**)&(((HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*)L_18)->___compiledGraph), (void*)NULL);
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_20 = V_4;
		uint64_t L_21 = V_6;
		uint64_t L_22 = V_5;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_23;
		L_23 = il2cpp_unsafe_add_byte_offset<HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5,uint64_t>(L_20, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_21, ((int64_t)1))), (int64_t)L_22)));
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5 L_24 = ___0_value;
		*(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*)L_23 = L_24;
		Il2CppCodeGenWriteBarrier((void**)&(((HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*)L_23)->___compiledGraph), (void*)NULL);
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_25 = V_4;
		uint64_t L_26 = V_6;
		uint64_t L_27 = V_5;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_28;
		L_28 = il2cpp_unsafe_add_byte_offset<HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5,uint64_t>(L_25, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_26, ((int64_t)2))), (int64_t)L_27)));
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5 L_29 = ___0_value;
		*(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*)L_28 = L_29;
		Il2CppCodeGenWriteBarrier((void**)&(((HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*)L_28)->___compiledGraph), (void*)NULL);
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_30 = V_4;
		uint64_t L_31 = V_6;
		uint64_t L_32 = V_5;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_33;
		L_33 = il2cpp_unsafe_add_byte_offset<HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5,uint64_t>(L_30, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_31, ((int64_t)3))), (int64_t)L_32)));
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5 L_34 = ___0_value;
		*(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*)L_33 = L_34;
		Il2CppCodeGenWriteBarrier((void**)&(((HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*)L_33)->___compiledGraph), (void*)NULL);
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_35 = V_4;
		uint64_t L_36 = V_6;
		uint64_t L_37 = V_5;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_38;
		L_38 = il2cpp_unsafe_add_byte_offset<HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5,uint64_t>(L_35, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_36, ((int64_t)4))), (int64_t)L_37)));
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5 L_39 = ___0_value;
		*(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*)L_38 = L_39;
		Il2CppCodeGenWriteBarrier((void**)&(((HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*)L_38)->___compiledGraph), (void*)NULL);
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_40 = V_4;
		uint64_t L_41 = V_6;
		uint64_t L_42 = V_5;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_43;
		L_43 = il2cpp_unsafe_add_byte_offset<HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5,uint64_t>(L_40, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_41, ((int64_t)5))), (int64_t)L_42)));
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5 L_44 = ___0_value;
		*(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*)L_43 = L_44;
		Il2CppCodeGenWriteBarrier((void**)&(((HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*)L_43)->___compiledGraph), (void*)NULL);
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_45 = V_4;
		uint64_t L_46 = V_6;
		uint64_t L_47 = V_5;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_48;
		L_48 = il2cpp_unsafe_add_byte_offset<HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5,uint64_t>(L_45, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_46, ((int64_t)6))), (int64_t)L_47)));
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5 L_49 = ___0_value;
		*(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*)L_48 = L_49;
		Il2CppCodeGenWriteBarrier((void**)&(((HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*)L_48)->___compiledGraph), (void*)NULL);
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_50 = V_4;
		uint64_t L_51 = V_6;
		uint64_t L_52 = V_5;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_53;
		L_53 = il2cpp_unsafe_add_byte_offset<HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5,uint64_t>(L_50, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_51, ((int64_t)7))), (int64_t)L_52)));
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5 L_54 = ___0_value;
		*(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*)L_53 = L_54;
		Il2CppCodeGenWriteBarrier((void**)&(((HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*)L_53)->___compiledGraph), (void*)NULL);
		uint64_t L_55 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_55, ((int64_t)8)));
	}

IL_0110:
	{
		uint64_t L_56 = V_6;
		uint64_t L_57 = V_3;
		if ((!(((uint64_t)L_56) >= ((uint64_t)((int64_t)((int64_t)L_57&((int64_t)((int32_t)-8))))))))
		{
			goto IL_0064;
		}
	}
	{
		uint64_t L_58 = V_6;
		uint64_t L_59 = V_3;
		if ((!(((uint64_t)L_58) < ((uint64_t)((int64_t)((int64_t)L_59&((int64_t)((int32_t)-4))))))))
		{
			goto IL_0198;
		}
	}
	{
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_60 = V_4;
		uint64_t L_61 = V_6;
		uint64_t L_62 = V_5;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_63;
		L_63 = il2cpp_unsafe_add_byte_offset<HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5,uint64_t>(L_60, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_61, (int64_t)L_62)));
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5 L_64 = ___0_value;
		*(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*)L_63 = L_64;
		Il2CppCodeGenWriteBarrier((void**)&(((HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*)L_63)->___compiledGraph), (void*)NULL);
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_65 = V_4;
		uint64_t L_66 = V_6;
		uint64_t L_67 = V_5;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_68;
		L_68 = il2cpp_unsafe_add_byte_offset<HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5,uint64_t>(L_65, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_66, ((int64_t)1))), (int64_t)L_67)));
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5 L_69 = ___0_value;
		*(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*)L_68 = L_69;
		Il2CppCodeGenWriteBarrier((void**)&(((HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*)L_68)->___compiledGraph), (void*)NULL);
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_70 = V_4;
		uint64_t L_71 = V_6;
		uint64_t L_72 = V_5;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_73;
		L_73 = il2cpp_unsafe_add_byte_offset<HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5,uint64_t>(L_70, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_71, ((int64_t)2))), (int64_t)L_72)));
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5 L_74 = ___0_value;
		*(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*)L_73 = L_74;
		Il2CppCodeGenWriteBarrier((void**)&(((HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*)L_73)->___compiledGraph), (void*)NULL);
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_75 = V_4;
		uint64_t L_76 = V_6;
		uint64_t L_77 = V_5;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_78;
		L_78 = il2cpp_unsafe_add_byte_offset<HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5,uint64_t>(L_75, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_76, ((int64_t)3))), (int64_t)L_77)));
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5 L_79 = ___0_value;
		*(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*)L_78 = L_79;
		Il2CppCodeGenWriteBarrier((void**)&(((HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*)L_78)->___compiledGraph), (void*)NULL);
		uint64_t L_80 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_80, ((int64_t)4)));
		goto IL_0198;
	}

IL_017f:
	{
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_81 = V_4;
		uint64_t L_82 = V_6;
		uint64_t L_83 = V_5;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_84;
		L_84 = il2cpp_unsafe_add_byte_offset<HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5,uint64_t>(L_81, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_82, (int64_t)L_83)));
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5 L_85 = ___0_value;
		*(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*)L_84 = L_85;
		Il2CppCodeGenWriteBarrier((void**)&(((HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5*)L_84)->___compiledGraph), (void*)NULL);
		uint64_t L_86 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_86, ((int64_t)1)));
	}

IL_0198:
	{
		uint64_t L_87 = V_6;
		uint64_t L_88 = V_3;
		if ((!(((uint64_t)L_87) >= ((uint64_t)L_88))))
		{
			goto IL_017f;
		}
	}
	{
		return;
	}
}
// Method Definition Index: 13825
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_CopyTo_mBE12777B45A199CF7F753E58352D0179314F3DBC_gshared (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B* __this, Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B ___0_destination, const RuntimeMethod* method) 
{
	ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_m1D64B90D223BD596E1C2DA8966AC4ED57C8E4589_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0038;
		}
	}
	{
		Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B L_2 = ___0_destination;
		ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 L_3 = L_2.____pointer;
		V_0 = L_3;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5, (Il2CppByReference*)(&V_0));
		ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 L_5 = __this->____pointer;
		V_0 = L_5;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5, (Il2CppByReference*)(&V_0));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisHashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5_mBF1724579F1B56AE7C6BDF919C304D2369837AA9(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return;
	}

IL_0038:
	{
		ThrowHelper_ThrowArgumentException_DestinationTooShort_m6468934A3BBB67DBC5BAEF7A64D91BD5BBBB3D4D(NULL);
		return;
	}
}
// Method Definition Index: 13826
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_TryCopyTo_m1E9A4EB1DDC26E21E24DAD6DC9490AEAF4BEBB25_gshared (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B* __this, Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B ___0_destination, const RuntimeMethod* method) 
{
	bool V_0 = false;
	ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		V_0 = (bool)0;
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_m1D64B90D223BD596E1C2DA8966AC4ED57C8E4589_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_003b;
		}
	}
	{
		Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B L_2 = ___0_destination;
		ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 L_3 = L_2.____pointer;
		V_1 = L_3;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5, (Il2CppByReference*)(&V_1));
		ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 L_5 = __this->____pointer;
		V_1 = L_5;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5, (Il2CppByReference*)(&V_1));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisHashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5_mBF1724579F1B56AE7C6BDF919C304D2369837AA9(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		V_0 = (bool)1;
	}

IL_003b:
	{
		bool L_8 = V_0;
		return L_8;
	}
}
// Method Definition Index: 13827
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_op_Equality_m9A785FEAD95315A9207385F2EB6B2AE4C2A91FFF_gshared (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B ___0_left, Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B ___1_right, const RuntimeMethod* method) 
{
	ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B L_0 = ___0_left;
		int32_t L_1 = L_0.____length;
		Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B L_2 = ___1_right;
		int32_t L_3 = L_2.____length;
		if ((!(((uint32_t)L_1) == ((uint32_t)L_3))))
		{
			goto IL_0030;
		}
	}
	{
		Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B L_4 = ___0_left;
		ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 L_5 = L_4.____pointer;
		V_0 = L_5;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5, (Il2CppByReference*)(&V_0));
		Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B L_7 = ___1_right;
		ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 L_8 = L_7.____pointer;
		V_0 = L_8;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_9;
		L_9 = IL2CPP_BY_REFERENCE_GET_VALUE(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5, (Il2CppByReference*)(&V_0));
		bool L_10;
		L_10 = il2cpp_unsafe_are_same(L_6, L_9);
		return L_10;
	}

IL_0030:
	{
		return (bool)0;
	}
}
// Method Definition Index: 13828
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ReadOnlySpan_1_t4990792F01A3EE12CEEC65526A813F1C0A67283E Span_1_op_Implicit_mC4C9E2068A09087BC4102329BD67F32B56B75F68_gshared (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B ___0_span, const RuntimeMethod* method) 
{
	ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B L_0 = ___0_span;
		ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 L_1 = L_0.____pointer;
		V_0 = L_1;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_2;
		L_2 = IL2CPP_BY_REFERENCE_GET_VALUE(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5, (Il2CppByReference*)(&V_0));
		Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B L_3 = ___0_span;
		int32_t L_4 = L_3.____length;
		ReadOnlySpan_1_t4990792F01A3EE12CEEC65526A813F1C0A67283E L_5;
		memset((&L_5), 0, sizeof(L_5));
		ReadOnlySpan_1__ctor_m15AA8CF0C1EB395F786CC37F5D7AF29EA7C10709_inline((&L_5), L_2, L_4, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 18));
		return L_5;
	}
}
// Method Definition Index: 13829
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Span_1_ToString_mAB10F193C69C489F0CDB76E8D063AE6045DF9642_gshared (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral0DB46164953228904843938099AF66650313FEE5);
		s_Il2CppMethodInitialized = true;
	}
	Il2CppChar* V_0 = NULL;
	ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_0 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_1;
		L_1 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_0, NULL);
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_2 = { reinterpret_cast<intptr_t> (&il2cpp_defaults.char_class->byval_arg) };
		Type_t* L_3;
		L_3 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_2, NULL);
		bool L_4;
		L_4 = Type_op_Equality_m99930A0E44E420A685FABA60E60BA1CC5FA0EBDC(L_1, L_3, NULL);
		if (!L_4)
		{
			goto IL_003e;
		}
	}
	{
		ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 L_5 = __this->____pointer;
		V_1 = L_5;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5, (Il2CppByReference*)(&V_1));
		Il2CppChar* L_7;
		L_7 = il2cpp_unsafe_as_ref<Il2CppChar>(L_6);
		V_0 = L_7;
		Il2CppChar* L_8 = V_0;
		int32_t L_9 = __this->____length;
		String_t* L_10;
		L_10 = String_CreateString_m3F8794FEB452558B8A68C65E1F0B603B3D94E0E2(NULL, (Il2CppChar*)((uintptr_t)L_8), 0, L_9, NULL);
		return L_10;
	}

IL_003e:
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_11 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_12;
		L_12 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_11, NULL);
		NullCheck((MemberInfo_t*)L_12);
		String_t* L_13;
		L_13 = VirtualFuncInvoker0< String_t* >::Invoke(7, (MemberInfo_t*)L_12);
		int32_t L_14 = __this->____length;
		int32_t L_15 = L_14;
		RuntimeObject* L_16 = Box(il2cpp_defaults.int32_class, &L_15);
		String_t* L_17;
		L_17 = String_Format_mFB7DA489BD99F4670881FF50EC017BFB0A5C0987(_stringLiteral0DB46164953228904843938099AF66650313FEE5, (RuntimeObject*)L_13, L_16, NULL);
		return L_17;
	}
}
// Method Definition Index: 13830
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B Span_1_Slice_m91C91A707653AE41969D64FA34083BEBA0935899_gshared (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B* __this, int32_t ___0_start, const RuntimeMethod* method) 
{
	ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) > ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 L_2 = __this->____pointer;
		V_0 = L_2;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_start;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_5;
		L_5 = il2cpp_unsafe_add<HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5,int32_t>(L_3, L_4);
		int32_t L_6 = __this->____length;
		int32_t L_7 = ___0_start;
		Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B L_8;
		memset((&L_8), 0, sizeof(L_8));
		Span_1__ctor_mA74DDDE530919E8BC3D60C7CBD981ED0F11DD14F_inline((&L_8), L_5, ((int32_t)il2cpp_codegen_subtract(L_6, L_7)), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_8;
	}
}
// Method Definition Index: 13831
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B Span_1_Slice_m60EDCA3A76E67C5E53C3B21D4D7ED20F3E5AFAE1_gshared (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B* __this, int32_t ___0_start, int32_t ___1_length, const RuntimeMethod* method) 
{
	ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0014;
		}
	}
	{
		int32_t L_2 = ___1_length;
		int32_t L_3 = __this->____length;
		int32_t L_4 = ___0_start;
		if ((!(((uint32_t)L_2) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(L_3, L_4))))))
		{
			goto IL_0019;
		}
	}

IL_0014:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0019:
	{
		ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 L_5 = __this->____pointer;
		V_0 = L_5;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5, (Il2CppByReference*)(&V_0));
		int32_t L_7 = ___0_start;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_8;
		L_8 = il2cpp_unsafe_add<HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5,int32_t>(L_6, L_7);
		int32_t L_9 = ___1_length;
		Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B L_10;
		memset((&L_10), 0, sizeof(L_10));
		Span_1__ctor_mA74DDDE530919E8BC3D60C7CBD981ED0F11DD14F_inline((&L_10), L_8, L_9, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_10;
	}
}
// Method Definition Index: 13832
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C* Span_1_ToArray_mBE77B4A1211C7EE4D8EE4475CD5F48AA7E51BB4C_gshared (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B* __this, const RuntimeMethod* method) 
{
	ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_000e;
		}
	}
	{
		HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C* L_1;
		L_1 = Array_Empty_TisHashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5_mA6305FE1F89FEF2783F9DE478AFB982C8BA1EDC2_inline(il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 20));
		return L_1;
	}

IL_000e:
	{
		int32_t L_2 = __this->____length;
		HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C* L_3 = (HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C*)(HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C*)SZArrayNew(il2cpp_rgctx_data(InitializedTypeInfo(method->klass)->rgctx_data, 21), (uint32_t)L_2);
		HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C* L_4 = L_3;
		NullCheck((RuntimeArray*)L_4);
		uint8_t* L_5;
		L_5 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_4, NULL);
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_6;
		L_6 = il2cpp_unsafe_as_ref<HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5>(L_5);
		ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 L_7 = __this->____pointer;
		V_0 = L_7;
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_8;
		L_8 = IL2CPP_BY_REFERENCE_GET_VALUE(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5, (Il2CppByReference*)(&V_0));
		int32_t L_9 = __this->____length;
		Buffer_Memmove_TisHashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5_mBF1724579F1B56AE7C6BDF919C304D2369837AA9(L_6, L_8, (uint64_t)((int64_t)L_9), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return L_4;
	}
}
// Method Definition Index: 13833
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m1D64B90D223BD596E1C2DA8966AC4ED57C8E4589_gshared (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13834
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_Equals_m81B7DEA1F9BDBA7B9741DC9437E2C0D715350CDD_gshared (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteral69508A540AFD085A745316DD7D6345B1C8CC662D)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13835
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_GetHashCode_m9C72656E7E6C5D2934F4FB5E080D5A0251206F80_gshared (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B* __this, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteralECE618215BAC99C6FD12D8A273CC2118945EDCC8)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13836
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B Span_1_op_Implicit_m1DAB2F2F239816405EA1C40C77E94532A121AE80_gshared (HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C* ___0_array, const RuntimeMethod* method) 
{
	{
		HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C* L_0 = ___0_array;
		Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B L_1;
		memset((&L_1), 0, sizeof(L_1));
		Span_1__ctor_m2646A3BCF55B82973A1E7845EE19BC641CD4D35E_inline((&L_1), L_0, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 22));
		return L_1;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// Method Definition Index: 13818
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m809249502A9C502A89D571353424C3BAC9610EFA_gshared (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3* __this, Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* ___0_array, const RuntimeMethod* method) 
{
	Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28));
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 L_1 = V_0;
		if (L_1.___hasValue)
		{
			goto IL_0037;
		}
	}
	{
		Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* L_2 = ___0_array;
		NullCheck((RuntimeObject*)L_2);
		Type_t* L_3;
		L_3 = Object_GetType_mE10A8FC1E57F3DF29972CCBC026C2DC3942263B3((RuntimeObject*)L_2, NULL);
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_4 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 3)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_5;
		L_5 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_4, NULL);
		bool L_6;
		L_6 = Type_op_Inequality_m83209C7BB3C05DFBEA3B6199B0BEFE8037301172(L_3, L_5, NULL);
		if (!L_6)
		{
			goto IL_0037;
		}
	}
	{
		ThrowHelper_ThrowArrayTypeMismatchException_m781AD7A903FEA43FAE3137977E6BC5F9BAEBC590(NULL);
	}

IL_0037:
	{
		Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* L_7 = ___0_array;
		NullCheck((RuntimeArray*)L_7);
		uint8_t* L_8;
		L_8 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_7, NULL);
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_9;
		L_9 = il2cpp_unsafe_as_ref<Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28>(L_8);
		ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C L_10;
		memset((&L_10), 0, sizeof(L_10));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_10), L_9);
		__this->____pointer = L_10;
		Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* L_11 = ___0_array;
		NullCheck(L_11);
		__this->____length = ((int32_t)(((RuntimeArray*)L_11)->max_length));
		return;
	}
}
// Method Definition Index: 13819
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m86B77173AFE17E5C448B2A46B2D4FA7C86D55239_gshared (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3* __this, Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* ___0_array, int32_t ___1_start, int32_t ___2_length, const RuntimeMethod* method) 
{
	Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_0016;
		}
	}
	{
		int32_t L_1 = ___1_start;
		if (L_1)
		{
			goto IL_0009;
		}
	}
	{
		int32_t L_2 = ___2_length;
		if (!L_2)
		{
			goto IL_000e;
		}
	}

IL_0009:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3));
		return;
	}

IL_0016:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28));
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 L_3 = V_0;
		if (L_3.___hasValue)
		{
			goto IL_0042;
		}
	}
	{
		Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* L_4 = ___0_array;
		NullCheck((RuntimeObject*)L_4);
		Type_t* L_5;
		L_5 = Object_GetType_mE10A8FC1E57F3DF29972CCBC026C2DC3942263B3((RuntimeObject*)L_4, NULL);
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_6 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 3)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_7;
		L_7 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_6, NULL);
		bool L_8;
		L_8 = Type_op_Inequality_m83209C7BB3C05DFBEA3B6199B0BEFE8037301172(L_5, L_7, NULL);
		if (!L_8)
		{
			goto IL_0042;
		}
	}
	{
		ThrowHelper_ThrowArrayTypeMismatchException_m781AD7A903FEA43FAE3137977E6BC5F9BAEBC590(NULL);
	}

IL_0042:
	{
		int32_t L_9 = ___1_start;
		Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* L_10 = ___0_array;
		NullCheck(L_10);
		if ((!(((uint32_t)L_9) <= ((uint32_t)((int32_t)(((RuntimeArray*)L_10)->max_length))))))
		{
			goto IL_0050;
		}
	}
	{
		int32_t L_11 = ___2_length;
		Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* L_12 = ___0_array;
		NullCheck(L_12);
		int32_t L_13 = ___1_start;
		if ((!(((uint32_t)L_11) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(((int32_t)(((RuntimeArray*)L_12)->max_length)), L_13))))))
		{
			goto IL_0055;
		}
	}

IL_0050:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0055:
	{
		Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* L_14 = ___0_array;
		NullCheck((RuntimeArray*)L_14);
		uint8_t* L_15;
		L_15 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_14, NULL);
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_16;
		L_16 = il2cpp_unsafe_as_ref<Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28>(L_15);
		int32_t L_17 = ___1_start;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_18;
		L_18 = il2cpp_unsafe_add<Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28,int32_t>(L_16, L_17);
		ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C L_19;
		memset((&L_19), 0, sizeof(L_19));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_19), L_18);
		__this->____pointer = L_19;
		int32_t L_20 = ___2_length;
		__this->____length = L_20;
		return;
	}
}
// Method Definition Index: 13820
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m39B630629631F5F3797AFE9EDDA475EE404BC100_gshared (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3* __this, void* ___0_pointer, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		goto IL_0016;
	}

IL_0016:
	{
		int32_t L_0 = ___1_length;
		if ((((int32_t)L_0) >= ((int32_t)0)))
		{
			goto IL_001f;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_001f:
	{
		void* L_1 = ___0_pointer;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_2;
		L_2 = il2cpp_unsafe_as_ref<Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28>((uint8_t*)L_1);
		ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C L_3;
		memset((&L_3), 0, sizeof(L_3));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_3), L_2);
		__this->____pointer = L_3;
		int32_t L_4 = ___1_length;
		__this->____length = L_4;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mDA8E6598A3E9F174C4024C9915ACA9463BA35F22_gshared (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3* __this, Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_0 = ___0_ptr;
		ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13822
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* Span_1_get_Item_m7A00D3468D32D9E828F5147FEBDC4B1601DCF647_gshared (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3* __this, int32_t ___0_index, const RuntimeMethod* method) 
{
	ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_index;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) >= ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowIndexOutOfRangeException_m86F753A24E2765A35546BA6352A7E4F0BB8A66B5(NULL);
	}

IL_000e:
	{
		ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C L_2 = __this->____pointer;
		V_0 = L_2;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_index;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_5;
		L_5 = il2cpp_unsafe_add<Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28,int32_t>(L_3, L_4);
		return L_5;
	}
}
// Method Definition Index: 13823
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* Span_1_GetPinnableReference_m4D8CBE3528B5E06DF38B65E0E8F8F2E95CFC2C33_gshared (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3* __this, const RuntimeMethod* method) 
{
	ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_0010;
		}
	}
	{
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_1;
		L_1 = il2cpp_unsafe_as_ref<Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28>((void*)((uintptr_t)0));
		return L_1;
	}

IL_0010:
	{
		ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C L_2 = __this->____pointer;
		V_0 = L_2;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28, (Il2CppByReference*)(&V_0));
		return L_3;
	}
}
// Method Definition Index: 13824
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_Fill_mE28027CAC681ADB1E428BF8BCF2EDEC974CF684A_gshared (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3* __this, Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 ___0_value, const RuntimeMethod* method) 
{
	uint32_t V_0 = 0;
	Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 V_1;
	memset((&V_1), 0, sizeof(V_1));
	ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C V_2;
	memset((&V_2), 0, sizeof(V_2));
	uint64_t V_3 = 0;
	Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* V_4 = NULL;
	uint64_t V_5 = 0;
	uint64_t V_6 = 0;
	{
		int32_t L_0;
		L_0 = il2cpp_unsafe_sizeof<Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28>();
		if ((!(((uint32_t)L_0) == ((uint32_t)1))))
		{
			goto IL_0037;
		}
	}
	{
		int32_t L_1 = __this->____length;
		V_0 = (uint32_t)L_1;
		uint32_t L_2 = V_0;
		if (L_2)
		{
			goto IL_0013;
		}
	}
	{
		return;
	}

IL_0013:
	{
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 L_3 = ___0_value;
		V_1 = L_3;
		ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C L_4 = __this->____pointer;
		V_2 = L_4;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_5;
		L_5 = IL2CPP_BY_REFERENCE_GET_VALUE(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28, (Il2CppByReference*)(&V_2));
		uint8_t* L_6;
		L_6 = il2cpp_unsafe_as_ref<uint8_t>(L_5);
		uint8_t* L_7;
		L_7 = il2cpp_unsafe_as_ref<uint8_t>((&V_1));
		int32_t L_8 = *((uint8_t*)L_7);
		uint32_t L_9 = V_0;
		Unsafe_InitBlockUnaligned_m6F2353EB9ABC9320E61629FAEE23948C80BFF03A(L_6, (uint8_t)L_8, L_9, NULL);
		return;
	}

IL_0037:
	{
		int32_t L_10 = __this->____length;
		V_3 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_10));
		uint64_t L_11 = V_3;
		if (L_11)
		{
			goto IL_0043;
		}
	}
	{
		return;
	}

IL_0043:
	{
		ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C L_12 = __this->____pointer;
		V_2 = L_12;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_13;
		L_13 = IL2CPP_BY_REFERENCE_GET_VALUE(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28, (Il2CppByReference*)(&V_2));
		V_4 = L_13;
		int32_t L_14;
		L_14 = il2cpp_unsafe_sizeof<Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28>();
		V_5 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_14));
		V_6 = (uint64_t)((int64_t)0);
		goto IL_0110;
	}

IL_0064:
	{
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_15 = V_4;
		uint64_t L_16 = V_6;
		uint64_t L_17 = V_5;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_18;
		L_18 = il2cpp_unsafe_add_byte_offset<Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28,uint64_t>(L_15, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_16, (int64_t)L_17)));
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 L_19 = ___0_value;
		*(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28*)L_18 = L_19;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_20 = V_4;
		uint64_t L_21 = V_6;
		uint64_t L_22 = V_5;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_23;
		L_23 = il2cpp_unsafe_add_byte_offset<Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28,uint64_t>(L_20, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_21, ((int64_t)1))), (int64_t)L_22)));
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 L_24 = ___0_value;
		*(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28*)L_23 = L_24;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_25 = V_4;
		uint64_t L_26 = V_6;
		uint64_t L_27 = V_5;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_28;
		L_28 = il2cpp_unsafe_add_byte_offset<Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28,uint64_t>(L_25, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_26, ((int64_t)2))), (int64_t)L_27)));
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 L_29 = ___0_value;
		*(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28*)L_28 = L_29;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_30 = V_4;
		uint64_t L_31 = V_6;
		uint64_t L_32 = V_5;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_33;
		L_33 = il2cpp_unsafe_add_byte_offset<Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28,uint64_t>(L_30, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_31, ((int64_t)3))), (int64_t)L_32)));
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 L_34 = ___0_value;
		*(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28*)L_33 = L_34;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_35 = V_4;
		uint64_t L_36 = V_6;
		uint64_t L_37 = V_5;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_38;
		L_38 = il2cpp_unsafe_add_byte_offset<Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28,uint64_t>(L_35, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_36, ((int64_t)4))), (int64_t)L_37)));
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 L_39 = ___0_value;
		*(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28*)L_38 = L_39;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_40 = V_4;
		uint64_t L_41 = V_6;
		uint64_t L_42 = V_5;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_43;
		L_43 = il2cpp_unsafe_add_byte_offset<Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28,uint64_t>(L_40, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_41, ((int64_t)5))), (int64_t)L_42)));
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 L_44 = ___0_value;
		*(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28*)L_43 = L_44;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_45 = V_4;
		uint64_t L_46 = V_6;
		uint64_t L_47 = V_5;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_48;
		L_48 = il2cpp_unsafe_add_byte_offset<Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28,uint64_t>(L_45, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_46, ((int64_t)6))), (int64_t)L_47)));
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 L_49 = ___0_value;
		*(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28*)L_48 = L_49;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_50 = V_4;
		uint64_t L_51 = V_6;
		uint64_t L_52 = V_5;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_53;
		L_53 = il2cpp_unsafe_add_byte_offset<Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28,uint64_t>(L_50, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_51, ((int64_t)7))), (int64_t)L_52)));
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 L_54 = ___0_value;
		*(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28*)L_53 = L_54;
		uint64_t L_55 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_55, ((int64_t)8)));
	}

IL_0110:
	{
		uint64_t L_56 = V_6;
		uint64_t L_57 = V_3;
		if ((!(((uint64_t)L_56) >= ((uint64_t)((int64_t)((int64_t)L_57&((int64_t)((int32_t)-8))))))))
		{
			goto IL_0064;
		}
	}
	{
		uint64_t L_58 = V_6;
		uint64_t L_59 = V_3;
		if ((!(((uint64_t)L_58) < ((uint64_t)((int64_t)((int64_t)L_59&((int64_t)((int32_t)-4))))))))
		{
			goto IL_0198;
		}
	}
	{
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_60 = V_4;
		uint64_t L_61 = V_6;
		uint64_t L_62 = V_5;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_63;
		L_63 = il2cpp_unsafe_add_byte_offset<Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28,uint64_t>(L_60, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_61, (int64_t)L_62)));
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 L_64 = ___0_value;
		*(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28*)L_63 = L_64;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_65 = V_4;
		uint64_t L_66 = V_6;
		uint64_t L_67 = V_5;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_68;
		L_68 = il2cpp_unsafe_add_byte_offset<Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28,uint64_t>(L_65, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_66, ((int64_t)1))), (int64_t)L_67)));
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 L_69 = ___0_value;
		*(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28*)L_68 = L_69;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_70 = V_4;
		uint64_t L_71 = V_6;
		uint64_t L_72 = V_5;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_73;
		L_73 = il2cpp_unsafe_add_byte_offset<Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28,uint64_t>(L_70, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_71, ((int64_t)2))), (int64_t)L_72)));
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 L_74 = ___0_value;
		*(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28*)L_73 = L_74;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_75 = V_4;
		uint64_t L_76 = V_6;
		uint64_t L_77 = V_5;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_78;
		L_78 = il2cpp_unsafe_add_byte_offset<Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28,uint64_t>(L_75, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_76, ((int64_t)3))), (int64_t)L_77)));
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 L_79 = ___0_value;
		*(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28*)L_78 = L_79;
		uint64_t L_80 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_80, ((int64_t)4)));
		goto IL_0198;
	}

IL_017f:
	{
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_81 = V_4;
		uint64_t L_82 = V_6;
		uint64_t L_83 = V_5;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_84;
		L_84 = il2cpp_unsafe_add_byte_offset<Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28,uint64_t>(L_81, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_82, (int64_t)L_83)));
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 L_85 = ___0_value;
		*(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28*)L_84 = L_85;
		uint64_t L_86 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_86, ((int64_t)1)));
	}

IL_0198:
	{
		uint64_t L_87 = V_6;
		uint64_t L_88 = V_3;
		if ((!(((uint64_t)L_87) >= ((uint64_t)L_88))))
		{
			goto IL_017f;
		}
	}
	{
		return;
	}
}
// Method Definition Index: 13825
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_CopyTo_m0364BD7751FBC14F5DA2CF8BA474307F6E815129_gshared (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3* __this, Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3 ___0_destination, const RuntimeMethod* method) 
{
	ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_m92912F015428BEA4C4E33A78B846E6B3CDB11751_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0038;
		}
	}
	{
		Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3 L_2 = ___0_destination;
		ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C L_3 = L_2.____pointer;
		V_0 = L_3;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28, (Il2CppByReference*)(&V_0));
		ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C L_5 = __this->____pointer;
		V_0 = L_5;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28, (Il2CppByReference*)(&V_0));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisNullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28_mBCF551834A5B6D371531B7F25078FA3DFB1B5E97(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return;
	}

IL_0038:
	{
		ThrowHelper_ThrowArgumentException_DestinationTooShort_m6468934A3BBB67DBC5BAEF7A64D91BD5BBBB3D4D(NULL);
		return;
	}
}
// Method Definition Index: 13826
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_TryCopyTo_m26C08A30C59BF1CF7196C28B23414AAFAA8EE83B_gshared (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3* __this, Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3 ___0_destination, const RuntimeMethod* method) 
{
	bool V_0 = false;
	ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		V_0 = (bool)0;
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_m92912F015428BEA4C4E33A78B846E6B3CDB11751_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_003b;
		}
	}
	{
		Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3 L_2 = ___0_destination;
		ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C L_3 = L_2.____pointer;
		V_1 = L_3;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28, (Il2CppByReference*)(&V_1));
		ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C L_5 = __this->____pointer;
		V_1 = L_5;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28, (Il2CppByReference*)(&V_1));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisNullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28_mBCF551834A5B6D371531B7F25078FA3DFB1B5E97(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		V_0 = (bool)1;
	}

IL_003b:
	{
		bool L_8 = V_0;
		return L_8;
	}
}
// Method Definition Index: 13827
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_op_Equality_mC3633C2B3E764D4E4DEBE743EC64C5B9701D9D06_gshared (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3 ___0_left, Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3 ___1_right, const RuntimeMethod* method) 
{
	ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3 L_0 = ___0_left;
		int32_t L_1 = L_0.____length;
		Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3 L_2 = ___1_right;
		int32_t L_3 = L_2.____length;
		if ((!(((uint32_t)L_1) == ((uint32_t)L_3))))
		{
			goto IL_0030;
		}
	}
	{
		Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3 L_4 = ___0_left;
		ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C L_5 = L_4.____pointer;
		V_0 = L_5;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28, (Il2CppByReference*)(&V_0));
		Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3 L_7 = ___1_right;
		ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C L_8 = L_7.____pointer;
		V_0 = L_8;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_9;
		L_9 = IL2CPP_BY_REFERENCE_GET_VALUE(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28, (Il2CppByReference*)(&V_0));
		bool L_10;
		L_10 = il2cpp_unsafe_are_same(L_6, L_9);
		return L_10;
	}

IL_0030:
	{
		return (bool)0;
	}
}
// Method Definition Index: 13828
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ReadOnlySpan_1_t38A9F2C8804739CCDFBD7062CA1C47C44BAE5AC2 Span_1_op_Implicit_m761C3454CAE69FEFBCF116FB76C960A81F8F1A72_gshared (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3 ___0_span, const RuntimeMethod* method) 
{
	ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3 L_0 = ___0_span;
		ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C L_1 = L_0.____pointer;
		V_0 = L_1;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_2;
		L_2 = IL2CPP_BY_REFERENCE_GET_VALUE(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28, (Il2CppByReference*)(&V_0));
		Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3 L_3 = ___0_span;
		int32_t L_4 = L_3.____length;
		ReadOnlySpan_1_t38A9F2C8804739CCDFBD7062CA1C47C44BAE5AC2 L_5;
		memset((&L_5), 0, sizeof(L_5));
		ReadOnlySpan_1__ctor_m4868E2A05AB294488A5AF17BC94F000C5A0F68F7_inline((&L_5), L_2, L_4, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 18));
		return L_5;
	}
}
// Method Definition Index: 13829
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Span_1_ToString_mAF2F48674F21E265E6A1E2841DA4F93B099BCC41_gshared (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral0DB46164953228904843938099AF66650313FEE5);
		s_Il2CppMethodInitialized = true;
	}
	Il2CppChar* V_0 = NULL;
	ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_0 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_1;
		L_1 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_0, NULL);
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_2 = { reinterpret_cast<intptr_t> (&il2cpp_defaults.char_class->byval_arg) };
		Type_t* L_3;
		L_3 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_2, NULL);
		bool L_4;
		L_4 = Type_op_Equality_m99930A0E44E420A685FABA60E60BA1CC5FA0EBDC(L_1, L_3, NULL);
		if (!L_4)
		{
			goto IL_003e;
		}
	}
	{
		ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C L_5 = __this->____pointer;
		V_1 = L_5;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28, (Il2CppByReference*)(&V_1));
		Il2CppChar* L_7;
		L_7 = il2cpp_unsafe_as_ref<Il2CppChar>(L_6);
		V_0 = L_7;
		Il2CppChar* L_8 = V_0;
		int32_t L_9 = __this->____length;
		String_t* L_10;
		L_10 = String_CreateString_m3F8794FEB452558B8A68C65E1F0B603B3D94E0E2(NULL, (Il2CppChar*)((uintptr_t)L_8), 0, L_9, NULL);
		return L_10;
	}

IL_003e:
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_11 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_12;
		L_12 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_11, NULL);
		NullCheck((MemberInfo_t*)L_12);
		String_t* L_13;
		L_13 = VirtualFuncInvoker0< String_t* >::Invoke(7, (MemberInfo_t*)L_12);
		int32_t L_14 = __this->____length;
		int32_t L_15 = L_14;
		RuntimeObject* L_16 = Box(il2cpp_defaults.int32_class, &L_15);
		String_t* L_17;
		L_17 = String_Format_mFB7DA489BD99F4670881FF50EC017BFB0A5C0987(_stringLiteral0DB46164953228904843938099AF66650313FEE5, (RuntimeObject*)L_13, L_16, NULL);
		return L_17;
	}
}
// Method Definition Index: 13830
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3 Span_1_Slice_m56B7FFB5404EF5699C0AAD0698A2110806CB7403_gshared (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3* __this, int32_t ___0_start, const RuntimeMethod* method) 
{
	ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) > ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C L_2 = __this->____pointer;
		V_0 = L_2;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_start;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_5;
		L_5 = il2cpp_unsafe_add<Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28,int32_t>(L_3, L_4);
		int32_t L_6 = __this->____length;
		int32_t L_7 = ___0_start;
		Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3 L_8;
		memset((&L_8), 0, sizeof(L_8));
		Span_1__ctor_mDA8E6598A3E9F174C4024C9915ACA9463BA35F22_inline((&L_8), L_5, ((int32_t)il2cpp_codegen_subtract(L_6, L_7)), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_8;
	}
}
// Method Definition Index: 13831
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3 Span_1_Slice_m1DA91F93C25B329E4DF9C8BF96C38AC3255CCD5A_gshared (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3* __this, int32_t ___0_start, int32_t ___1_length, const RuntimeMethod* method) 
{
	ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0014;
		}
	}
	{
		int32_t L_2 = ___1_length;
		int32_t L_3 = __this->____length;
		int32_t L_4 = ___0_start;
		if ((!(((uint32_t)L_2) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(L_3, L_4))))))
		{
			goto IL_0019;
		}
	}

IL_0014:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0019:
	{
		ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C L_5 = __this->____pointer;
		V_0 = L_5;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28, (Il2CppByReference*)(&V_0));
		int32_t L_7 = ___0_start;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_8;
		L_8 = il2cpp_unsafe_add<Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28,int32_t>(L_6, L_7);
		int32_t L_9 = ___1_length;
		Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3 L_10;
		memset((&L_10), 0, sizeof(L_10));
		Span_1__ctor_mDA8E6598A3E9F174C4024C9915ACA9463BA35F22_inline((&L_10), L_8, L_9, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_10;
	}
}
// Method Definition Index: 13832
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* Span_1_ToArray_m4F7B49E4DEFFF7590D3FEE459AFD8F93825E62C7_gshared (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3* __this, const RuntimeMethod* method) 
{
	ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_000e;
		}
	}
	{
		Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* L_1;
		L_1 = Array_Empty_TisNullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28_mFB61E6C1D57945549B403C13B1652F8CE5324A3C_inline(il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 20));
		return L_1;
	}

IL_000e:
	{
		int32_t L_2 = __this->____length;
		Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* L_3 = (Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43*)(Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43*)SZArrayNew(il2cpp_rgctx_data(InitializedTypeInfo(method->klass)->rgctx_data, 21), (uint32_t)L_2);
		Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* L_4 = L_3;
		NullCheck((RuntimeArray*)L_4);
		uint8_t* L_5;
		L_5 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_4, NULL);
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_6;
		L_6 = il2cpp_unsafe_as_ref<Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28>(L_5);
		ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C L_7 = __this->____pointer;
		V_0 = L_7;
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_8;
		L_8 = IL2CPP_BY_REFERENCE_GET_VALUE(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28, (Il2CppByReference*)(&V_0));
		int32_t L_9 = __this->____length;
		Buffer_Memmove_TisNullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28_mBCF551834A5B6D371531B7F25078FA3DFB1B5E97(L_6, L_8, (uint64_t)((int64_t)L_9), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return L_4;
	}
}
// Method Definition Index: 13833
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m92912F015428BEA4C4E33A78B846E6B3CDB11751_gshared (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13834
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_Equals_m7D50961771F0951CFC3634638723DF9EEF1A824F_gshared (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteral69508A540AFD085A745316DD7D6345B1C8CC662D)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13835
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_GetHashCode_m35D53A63F76BDF113E43492876A788F40434C748_gshared (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3* __this, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteralECE618215BAC99C6FD12D8A273CC2118945EDCC8)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13836
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3 Span_1_op_Implicit_m8E5FF3761AA50C08946266E63BD40F877F22245A_gshared (Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* ___0_array, const RuntimeMethod* method) 
{
	{
		Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* L_0 = ___0_array;
		Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3 L_1;
		memset((&L_1), 0, sizeof(L_1));
		Span_1__ctor_m809249502A9C502A89D571353424C3BAC9610EFA_inline((&L_1), L_0, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 22));
		return L_1;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// Method Definition Index: 13818
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mF27289AA0FF72C4ED5AEE3D24C8A1052C9522A5F_gshared (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019* __this, ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3* ___0_array, const RuntimeMethod* method) 
{
	ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA));
		goto IL_0037;
	}

IL_0037:
	{
		ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_4;
		L_4 = il2cpp_unsafe_as_ref<ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA>(L_3);
		ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13819
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m32AAD72579E779337B0727AACD14DD6B35DE2CC0_gshared (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019* __this, ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3* ___0_array, int32_t ___1_start, int32_t ___2_length, const RuntimeMethod* method) 
{
	ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_0016;
		}
	}
	{
		int32_t L_1 = ___1_start;
		if (L_1)
		{
			goto IL_0009;
		}
	}
	{
		int32_t L_2 = ___2_length;
		if (!L_2)
		{
			goto IL_000e;
		}
	}

IL_0009:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019));
		return;
	}

IL_0016:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA));
		goto IL_0042;
	}

IL_0042:
	{
		int32_t L_4 = ___1_start;
		ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3* L_5 = ___0_array;
		NullCheck(L_5);
		if ((!(((uint32_t)L_4) <= ((uint32_t)((int32_t)(((RuntimeArray*)L_5)->max_length))))))
		{
			goto IL_0050;
		}
	}
	{
		int32_t L_6 = ___2_length;
		ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3* L_7 = ___0_array;
		NullCheck(L_7);
		int32_t L_8 = ___1_start;
		if ((!(((uint32_t)L_6) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(((int32_t)(((RuntimeArray*)L_7)->max_length)), L_8))))))
		{
			goto IL_0055;
		}
	}

IL_0050:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0055:
	{
		ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3* L_9 = ___0_array;
		NullCheck((RuntimeArray*)L_9);
		uint8_t* L_10;
		L_10 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_9, NULL);
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_11;
		L_11 = il2cpp_unsafe_as_ref<ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA>(L_10);
		int32_t L_12 = ___1_start;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_13;
		L_13 = il2cpp_unsafe_add<ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA,int32_t>(L_11, L_12);
		ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E L_14;
		memset((&L_14), 0, sizeof(L_14));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_14), L_13);
		__this->____pointer = L_14;
		int32_t L_15 = ___2_length;
		__this->____length = L_15;
		return;
	}
}
// Method Definition Index: 13820
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m6EEC47D4B78C37C7CB1B82CBF667DBF42B8FBB6E_gshared (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019* __this, void* ___0_pointer, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		goto IL_0016;
	}

IL_0016:
	{
		int32_t L_0 = ___1_length;
		if ((((int32_t)L_0) >= ((int32_t)0)))
		{
			goto IL_001f;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_001f:
	{
		void* L_1 = ___0_pointer;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_2;
		L_2 = il2cpp_unsafe_as_ref<ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA>((uint8_t*)L_1);
		ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E L_3;
		memset((&L_3), 0, sizeof(L_3));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_3), L_2);
		__this->____pointer = L_3;
		int32_t L_4 = ___1_length;
		__this->____length = L_4;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m12E72689FB9ED51748E944C2B3CF2E15EF0EB168_gshared (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019* __this, ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_0 = ___0_ptr;
		ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13822
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* Span_1_get_Item_m4DC546863A59DD2304162A8104F71409DAF617CB_gshared (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019* __this, int32_t ___0_index, const RuntimeMethod* method) 
{
	ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_index;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) >= ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowIndexOutOfRangeException_m86F753A24E2765A35546BA6352A7E4F0BB8A66B5(NULL);
	}

IL_000e:
	{
		ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E L_2 = __this->____pointer;
		V_0 = L_2;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_index;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_5;
		L_5 = il2cpp_unsafe_add<ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA,int32_t>(L_3, L_4);
		return L_5;
	}
}
// Method Definition Index: 13823
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* Span_1_GetPinnableReference_mF7409B838DF67AA058BCBEF9AE940FDE406FF07B_gshared (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019* __this, const RuntimeMethod* method) 
{
	ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_0010;
		}
	}
	{
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_1;
		L_1 = il2cpp_unsafe_as_ref<ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA>((void*)((uintptr_t)0));
		return L_1;
	}

IL_0010:
	{
		ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E L_2 = __this->____pointer;
		V_0 = L_2;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA, (Il2CppByReference*)(&V_0));
		return L_3;
	}
}
// Method Definition Index: 13824
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_Fill_m18DBF6368735E5BFFFE6CEBDE8772E304D453E5D_gshared (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019* __this, ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA ___0_value, const RuntimeMethod* method) 
{
	uint32_t V_0 = 0;
	ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA V_1;
	memset((&V_1), 0, sizeof(V_1));
	ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E V_2;
	memset((&V_2), 0, sizeof(V_2));
	uint64_t V_3 = 0;
	ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* V_4 = NULL;
	uint64_t V_5 = 0;
	uint64_t V_6 = 0;
	{
		int32_t L_0;
		L_0 = il2cpp_unsafe_sizeof<ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA>();
		if ((!(((uint32_t)L_0) == ((uint32_t)1))))
		{
			goto IL_0037;
		}
	}
	{
		int32_t L_1 = __this->____length;
		V_0 = (uint32_t)L_1;
		uint32_t L_2 = V_0;
		if (L_2)
		{
			goto IL_0013;
		}
	}
	{
		return;
	}

IL_0013:
	{
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA L_3 = ___0_value;
		V_1 = L_3;
		ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E L_4 = __this->____pointer;
		V_2 = L_4;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_5;
		L_5 = IL2CPP_BY_REFERENCE_GET_VALUE(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA, (Il2CppByReference*)(&V_2));
		uint8_t* L_6;
		L_6 = il2cpp_unsafe_as_ref<uint8_t>(L_5);
		uint8_t* L_7;
		L_7 = il2cpp_unsafe_as_ref<uint8_t>((&V_1));
		int32_t L_8 = *((uint8_t*)L_7);
		uint32_t L_9 = V_0;
		Unsafe_InitBlockUnaligned_m6F2353EB9ABC9320E61629FAEE23948C80BFF03A(L_6, (uint8_t)L_8, L_9, NULL);
		return;
	}

IL_0037:
	{
		int32_t L_10 = __this->____length;
		V_3 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_10));
		uint64_t L_11 = V_3;
		if (L_11)
		{
			goto IL_0043;
		}
	}
	{
		return;
	}

IL_0043:
	{
		ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E L_12 = __this->____pointer;
		V_2 = L_12;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_13;
		L_13 = IL2CPP_BY_REFERENCE_GET_VALUE(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA, (Il2CppByReference*)(&V_2));
		V_4 = L_13;
		int32_t L_14;
		L_14 = il2cpp_unsafe_sizeof<ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA>();
		V_5 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_14));
		V_6 = (uint64_t)((int64_t)0);
		goto IL_0110;
	}

IL_0064:
	{
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_15 = V_4;
		uint64_t L_16 = V_6;
		uint64_t L_17 = V_5;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_18;
		L_18 = il2cpp_unsafe_add_byte_offset<ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA,uint64_t>(L_15, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_16, (int64_t)L_17)));
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA L_19 = ___0_value;
		*(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA*)L_18 = L_19;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_20 = V_4;
		uint64_t L_21 = V_6;
		uint64_t L_22 = V_5;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_23;
		L_23 = il2cpp_unsafe_add_byte_offset<ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA,uint64_t>(L_20, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_21, ((int64_t)1))), (int64_t)L_22)));
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA L_24 = ___0_value;
		*(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA*)L_23 = L_24;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_25 = V_4;
		uint64_t L_26 = V_6;
		uint64_t L_27 = V_5;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_28;
		L_28 = il2cpp_unsafe_add_byte_offset<ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA,uint64_t>(L_25, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_26, ((int64_t)2))), (int64_t)L_27)));
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA L_29 = ___0_value;
		*(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA*)L_28 = L_29;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_30 = V_4;
		uint64_t L_31 = V_6;
		uint64_t L_32 = V_5;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_33;
		L_33 = il2cpp_unsafe_add_byte_offset<ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA,uint64_t>(L_30, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_31, ((int64_t)3))), (int64_t)L_32)));
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA L_34 = ___0_value;
		*(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA*)L_33 = L_34;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_35 = V_4;
		uint64_t L_36 = V_6;
		uint64_t L_37 = V_5;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_38;
		L_38 = il2cpp_unsafe_add_byte_offset<ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA,uint64_t>(L_35, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_36, ((int64_t)4))), (int64_t)L_37)));
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA L_39 = ___0_value;
		*(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA*)L_38 = L_39;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_40 = V_4;
		uint64_t L_41 = V_6;
		uint64_t L_42 = V_5;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_43;
		L_43 = il2cpp_unsafe_add_byte_offset<ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA,uint64_t>(L_40, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_41, ((int64_t)5))), (int64_t)L_42)));
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA L_44 = ___0_value;
		*(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA*)L_43 = L_44;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_45 = V_4;
		uint64_t L_46 = V_6;
		uint64_t L_47 = V_5;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_48;
		L_48 = il2cpp_unsafe_add_byte_offset<ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA,uint64_t>(L_45, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_46, ((int64_t)6))), (int64_t)L_47)));
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA L_49 = ___0_value;
		*(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA*)L_48 = L_49;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_50 = V_4;
		uint64_t L_51 = V_6;
		uint64_t L_52 = V_5;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_53;
		L_53 = il2cpp_unsafe_add_byte_offset<ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA,uint64_t>(L_50, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_51, ((int64_t)7))), (int64_t)L_52)));
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA L_54 = ___0_value;
		*(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA*)L_53 = L_54;
		uint64_t L_55 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_55, ((int64_t)8)));
	}

IL_0110:
	{
		uint64_t L_56 = V_6;
		uint64_t L_57 = V_3;
		if ((!(((uint64_t)L_56) >= ((uint64_t)((int64_t)((int64_t)L_57&((int64_t)((int32_t)-8))))))))
		{
			goto IL_0064;
		}
	}
	{
		uint64_t L_58 = V_6;
		uint64_t L_59 = V_3;
		if ((!(((uint64_t)L_58) < ((uint64_t)((int64_t)((int64_t)L_59&((int64_t)((int32_t)-4))))))))
		{
			goto IL_0198;
		}
	}
	{
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_60 = V_4;
		uint64_t L_61 = V_6;
		uint64_t L_62 = V_5;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_63;
		L_63 = il2cpp_unsafe_add_byte_offset<ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA,uint64_t>(L_60, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_61, (int64_t)L_62)));
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA L_64 = ___0_value;
		*(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA*)L_63 = L_64;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_65 = V_4;
		uint64_t L_66 = V_6;
		uint64_t L_67 = V_5;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_68;
		L_68 = il2cpp_unsafe_add_byte_offset<ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA,uint64_t>(L_65, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_66, ((int64_t)1))), (int64_t)L_67)));
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA L_69 = ___0_value;
		*(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA*)L_68 = L_69;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_70 = V_4;
		uint64_t L_71 = V_6;
		uint64_t L_72 = V_5;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_73;
		L_73 = il2cpp_unsafe_add_byte_offset<ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA,uint64_t>(L_70, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_71, ((int64_t)2))), (int64_t)L_72)));
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA L_74 = ___0_value;
		*(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA*)L_73 = L_74;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_75 = V_4;
		uint64_t L_76 = V_6;
		uint64_t L_77 = V_5;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_78;
		L_78 = il2cpp_unsafe_add_byte_offset<ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA,uint64_t>(L_75, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_76, ((int64_t)3))), (int64_t)L_77)));
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA L_79 = ___0_value;
		*(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA*)L_78 = L_79;
		uint64_t L_80 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_80, ((int64_t)4)));
		goto IL_0198;
	}

IL_017f:
	{
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_81 = V_4;
		uint64_t L_82 = V_6;
		uint64_t L_83 = V_5;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_84;
		L_84 = il2cpp_unsafe_add_byte_offset<ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA,uint64_t>(L_81, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_82, (int64_t)L_83)));
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA L_85 = ___0_value;
		*(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA*)L_84 = L_85;
		uint64_t L_86 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_86, ((int64_t)1)));
	}

IL_0198:
	{
		uint64_t L_87 = V_6;
		uint64_t L_88 = V_3;
		if ((!(((uint64_t)L_87) >= ((uint64_t)L_88))))
		{
			goto IL_017f;
		}
	}
	{
		return;
	}
}
// Method Definition Index: 13825
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_CopyTo_mF703E57C020C19A0B60D3133B088CF73B220F4EF_gshared (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019* __this, Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019 ___0_destination, const RuntimeMethod* method) 
{
	ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_mE6A401C96B3DFE2B3B68A13B3AE43400879A9981_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0038;
		}
	}
	{
		Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019 L_2 = ___0_destination;
		ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E L_3 = L_2.____pointer;
		V_0 = L_3;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA, (Il2CppByReference*)(&V_0));
		ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E L_5 = __this->____pointer;
		V_0 = L_5;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA, (Il2CppByReference*)(&V_0));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA_mCB14434333DCB75A5FD671D9A992747A378043D9(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return;
	}

IL_0038:
	{
		ThrowHelper_ThrowArgumentException_DestinationTooShort_m6468934A3BBB67DBC5BAEF7A64D91BD5BBBB3D4D(NULL);
		return;
	}
}
// Method Definition Index: 13826
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_TryCopyTo_m30157FF8456EFCE08CBF8DB82B9B7994BF382872_gshared (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019* __this, Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019 ___0_destination, const RuntimeMethod* method) 
{
	bool V_0 = false;
	ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		V_0 = (bool)0;
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_mE6A401C96B3DFE2B3B68A13B3AE43400879A9981_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_003b;
		}
	}
	{
		Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019 L_2 = ___0_destination;
		ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E L_3 = L_2.____pointer;
		V_1 = L_3;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA, (Il2CppByReference*)(&V_1));
		ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E L_5 = __this->____pointer;
		V_1 = L_5;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA, (Il2CppByReference*)(&V_1));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA_mCB14434333DCB75A5FD671D9A992747A378043D9(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		V_0 = (bool)1;
	}

IL_003b:
	{
		bool L_8 = V_0;
		return L_8;
	}
}
// Method Definition Index: 13827
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_op_Equality_m8CF6784FED5E9A0C7FB65307608FA3B116B8908A_gshared (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019 ___0_left, Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019 ___1_right, const RuntimeMethod* method) 
{
	ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019 L_0 = ___0_left;
		int32_t L_1 = L_0.____length;
		Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019 L_2 = ___1_right;
		int32_t L_3 = L_2.____length;
		if ((!(((uint32_t)L_1) == ((uint32_t)L_3))))
		{
			goto IL_0030;
		}
	}
	{
		Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019 L_4 = ___0_left;
		ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E L_5 = L_4.____pointer;
		V_0 = L_5;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA, (Il2CppByReference*)(&V_0));
		Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019 L_7 = ___1_right;
		ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E L_8 = L_7.____pointer;
		V_0 = L_8;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_9;
		L_9 = IL2CPP_BY_REFERENCE_GET_VALUE(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA, (Il2CppByReference*)(&V_0));
		bool L_10;
		L_10 = il2cpp_unsafe_are_same(L_6, L_9);
		return L_10;
	}

IL_0030:
	{
		return (bool)0;
	}
}
// Method Definition Index: 13828
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ReadOnlySpan_1_t4757BCCBBFA890A355792A3EB923FE9C101A9C76 Span_1_op_Implicit_m93F7D2F6ECE56D3AEDABB6973B2AB8D570FCFCC1_gshared (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019 ___0_span, const RuntimeMethod* method) 
{
	ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019 L_0 = ___0_span;
		ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E L_1 = L_0.____pointer;
		V_0 = L_1;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_2;
		L_2 = IL2CPP_BY_REFERENCE_GET_VALUE(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA, (Il2CppByReference*)(&V_0));
		Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019 L_3 = ___0_span;
		int32_t L_4 = L_3.____length;
		ReadOnlySpan_1_t4757BCCBBFA890A355792A3EB923FE9C101A9C76 L_5;
		memset((&L_5), 0, sizeof(L_5));
		ReadOnlySpan_1__ctor_mFB782BAD5E6881410F56403A2B56A0ED54E3A1D8_inline((&L_5), L_2, L_4, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 18));
		return L_5;
	}
}
// Method Definition Index: 13829
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Span_1_ToString_mDCF5D85D1A8FBCD0DCB7490AB55A61CE284B0253_gshared (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral0DB46164953228904843938099AF66650313FEE5);
		s_Il2CppMethodInitialized = true;
	}
	Il2CppChar* V_0 = NULL;
	ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_0 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_1;
		L_1 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_0, NULL);
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_2 = { reinterpret_cast<intptr_t> (&il2cpp_defaults.char_class->byval_arg) };
		Type_t* L_3;
		L_3 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_2, NULL);
		bool L_4;
		L_4 = Type_op_Equality_m99930A0E44E420A685FABA60E60BA1CC5FA0EBDC(L_1, L_3, NULL);
		if (!L_4)
		{
			goto IL_003e;
		}
	}
	{
		ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E L_5 = __this->____pointer;
		V_1 = L_5;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA, (Il2CppByReference*)(&V_1));
		Il2CppChar* L_7;
		L_7 = il2cpp_unsafe_as_ref<Il2CppChar>(L_6);
		V_0 = L_7;
		Il2CppChar* L_8 = V_0;
		int32_t L_9 = __this->____length;
		String_t* L_10;
		L_10 = String_CreateString_m3F8794FEB452558B8A68C65E1F0B603B3D94E0E2(NULL, (Il2CppChar*)((uintptr_t)L_8), 0, L_9, NULL);
		return L_10;
	}

IL_003e:
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_11 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_12;
		L_12 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_11, NULL);
		NullCheck((MemberInfo_t*)L_12);
		String_t* L_13;
		L_13 = VirtualFuncInvoker0< String_t* >::Invoke(7, (MemberInfo_t*)L_12);
		int32_t L_14 = __this->____length;
		int32_t L_15 = L_14;
		RuntimeObject* L_16 = Box(il2cpp_defaults.int32_class, &L_15);
		String_t* L_17;
		L_17 = String_Format_mFB7DA489BD99F4670881FF50EC017BFB0A5C0987(_stringLiteral0DB46164953228904843938099AF66650313FEE5, (RuntimeObject*)L_13, L_16, NULL);
		return L_17;
	}
}
// Method Definition Index: 13830
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019 Span_1_Slice_m19D8FB9AB16860FC0CE7C08B9F0A7CEDA3C83609_gshared (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019* __this, int32_t ___0_start, const RuntimeMethod* method) 
{
	ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) > ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E L_2 = __this->____pointer;
		V_0 = L_2;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_start;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_5;
		L_5 = il2cpp_unsafe_add<ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA,int32_t>(L_3, L_4);
		int32_t L_6 = __this->____length;
		int32_t L_7 = ___0_start;
		Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019 L_8;
		memset((&L_8), 0, sizeof(L_8));
		Span_1__ctor_m12E72689FB9ED51748E944C2B3CF2E15EF0EB168_inline((&L_8), L_5, ((int32_t)il2cpp_codegen_subtract(L_6, L_7)), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_8;
	}
}
// Method Definition Index: 13831
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019 Span_1_Slice_m8BF5C601E380B4C7FB048648DF38628C120ABC49_gshared (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019* __this, int32_t ___0_start, int32_t ___1_length, const RuntimeMethod* method) 
{
	ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0014;
		}
	}
	{
		int32_t L_2 = ___1_length;
		int32_t L_3 = __this->____length;
		int32_t L_4 = ___0_start;
		if ((!(((uint32_t)L_2) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(L_3, L_4))))))
		{
			goto IL_0019;
		}
	}

IL_0014:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0019:
	{
		ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E L_5 = __this->____pointer;
		V_0 = L_5;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA, (Il2CppByReference*)(&V_0));
		int32_t L_7 = ___0_start;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_8;
		L_8 = il2cpp_unsafe_add<ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA,int32_t>(L_6, L_7);
		int32_t L_9 = ___1_length;
		Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019 L_10;
		memset((&L_10), 0, sizeof(L_10));
		Span_1__ctor_m12E72689FB9ED51748E944C2B3CF2E15EF0EB168_inline((&L_10), L_8, L_9, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_10;
	}
}
// Method Definition Index: 13832
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3* Span_1_ToArray_mEEEB7E7D0E96EF242704469E2D73B2031F7812DC_gshared (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019* __this, const RuntimeMethod* method) 
{
	ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_000e;
		}
	}
	{
		ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3* L_1;
		L_1 = Array_Empty_TisValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA_mB7B36B66111D0BD07DEA806DA065B3F1EB37F96C_inline(il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 20));
		return L_1;
	}

IL_000e:
	{
		int32_t L_2 = __this->____length;
		ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3* L_3 = (ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3*)(ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3*)SZArrayNew(il2cpp_rgctx_data(InitializedTypeInfo(method->klass)->rgctx_data, 21), (uint32_t)L_2);
		ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3* L_4 = L_3;
		NullCheck((RuntimeArray*)L_4);
		uint8_t* L_5;
		L_5 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_4, NULL);
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_6;
		L_6 = il2cpp_unsafe_as_ref<ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA>(L_5);
		ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E L_7 = __this->____pointer;
		V_0 = L_7;
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_8;
		L_8 = IL2CPP_BY_REFERENCE_GET_VALUE(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA, (Il2CppByReference*)(&V_0));
		int32_t L_9 = __this->____length;
		Buffer_Memmove_TisValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA_mCB14434333DCB75A5FD671D9A992747A378043D9(L_6, L_8, (uint64_t)((int64_t)L_9), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return L_4;
	}
}
// Method Definition Index: 13833
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mE6A401C96B3DFE2B3B68A13B3AE43400879A9981_gshared (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13834
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_Equals_mF882427F0C32366CF350B27F62502C3919AE5FE8_gshared (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteral69508A540AFD085A745316DD7D6345B1C8CC662D)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13835
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_GetHashCode_mF0F3E1276A5F35BCDC7781CB03A3F6272181008D_gshared (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019* __this, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteralECE618215BAC99C6FD12D8A273CC2118945EDCC8)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13836
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019 Span_1_op_Implicit_mA5441D25C571806A90BA258086C56167E01E0BBC_gshared (ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3* ___0_array, const RuntimeMethod* method) 
{
	{
		ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3* L_0 = ___0_array;
		Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019 L_1;
		memset((&L_1), 0, sizeof(L_1));
		Span_1__ctor_mF27289AA0FF72C4ED5AEE3D24C8A1052C9522A5F_inline((&L_1), L_0, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 22));
		return L_1;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// Method Definition Index: 13818
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m9F49684FE52CEB46E463E4333810DF0E97299A75_gshared (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352* __this, AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61* ___0_array, const RuntimeMethod* method) 
{
	AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A));
		goto IL_0037;
	}

IL_0037:
	{
		AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_4;
		L_4 = il2cpp_unsafe_as_ref<AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A>(L_3);
		ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13819
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mE4EC07983146A52D13499D2A872B94FE901A16CA_gshared (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352* __this, AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61* ___0_array, int32_t ___1_start, int32_t ___2_length, const RuntimeMethod* method) 
{
	AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_0016;
		}
	}
	{
		int32_t L_1 = ___1_start;
		if (L_1)
		{
			goto IL_0009;
		}
	}
	{
		int32_t L_2 = ___2_length;
		if (!L_2)
		{
			goto IL_000e;
		}
	}

IL_0009:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352));
		return;
	}

IL_0016:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A));
		goto IL_0042;
	}

IL_0042:
	{
		int32_t L_4 = ___1_start;
		AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61* L_5 = ___0_array;
		NullCheck(L_5);
		if ((!(((uint32_t)L_4) <= ((uint32_t)((int32_t)(((RuntimeArray*)L_5)->max_length))))))
		{
			goto IL_0050;
		}
	}
	{
		int32_t L_6 = ___2_length;
		AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61* L_7 = ___0_array;
		NullCheck(L_7);
		int32_t L_8 = ___1_start;
		if ((!(((uint32_t)L_6) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(((int32_t)(((RuntimeArray*)L_7)->max_length)), L_8))))))
		{
			goto IL_0055;
		}
	}

IL_0050:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0055:
	{
		AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61* L_9 = ___0_array;
		NullCheck((RuntimeArray*)L_9);
		uint8_t* L_10;
		L_10 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_9, NULL);
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_11;
		L_11 = il2cpp_unsafe_as_ref<AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A>(L_10);
		int32_t L_12 = ___1_start;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_13;
		L_13 = il2cpp_unsafe_add<AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A,int32_t>(L_11, L_12);
		ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 L_14;
		memset((&L_14), 0, sizeof(L_14));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_14), L_13);
		__this->____pointer = L_14;
		int32_t L_15 = ___2_length;
		__this->____length = L_15;
		return;
	}
}
// Method Definition Index: 13820
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m4BB31BA344A603F2133AA6588F2C2222BD45D533_gshared (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352* __this, void* ___0_pointer, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		goto IL_0016;
	}

IL_0016:
	{
		int32_t L_0 = ___1_length;
		if ((((int32_t)L_0) >= ((int32_t)0)))
		{
			goto IL_001f;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_001f:
	{
		void* L_1 = ___0_pointer;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_2;
		L_2 = il2cpp_unsafe_as_ref<AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A>((uint8_t*)L_1);
		ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 L_3;
		memset((&L_3), 0, sizeof(L_3));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_3), L_2);
		__this->____pointer = L_3;
		int32_t L_4 = ___1_length;
		__this->____length = L_4;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m62F7DD7486AA2DC1886916B354DF7CB30B2D12CC_gshared (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352* __this, AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_0 = ___0_ptr;
		ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13822
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* Span_1_get_Item_m52F08FC39C3E7FBDF1BD3BF14F9957A48904F137_gshared (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352* __this, int32_t ___0_index, const RuntimeMethod* method) 
{
	ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_index;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) >= ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowIndexOutOfRangeException_m86F753A24E2765A35546BA6352A7E4F0BB8A66B5(NULL);
	}

IL_000e:
	{
		ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 L_2 = __this->____pointer;
		V_0 = L_2;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_index;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_5;
		L_5 = il2cpp_unsafe_add<AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A,int32_t>(L_3, L_4);
		return L_5;
	}
}
// Method Definition Index: 13823
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* Span_1_GetPinnableReference_m50327375DE04F931E6975EF50F65CD91A414D45A_gshared (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352* __this, const RuntimeMethod* method) 
{
	ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_0010;
		}
	}
	{
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_1;
		L_1 = il2cpp_unsafe_as_ref<AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A>((void*)((uintptr_t)0));
		return L_1;
	}

IL_0010:
	{
		ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 L_2 = __this->____pointer;
		V_0 = L_2;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A, (Il2CppByReference*)(&V_0));
		return L_3;
	}
}
// Method Definition Index: 13824
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_Fill_m1CEB92DA59E53F278167DF34C188C960C3559D75_gshared (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352* __this, AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A ___0_value, const RuntimeMethod* method) 
{
	uint32_t V_0 = 0;
	AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A V_1;
	memset((&V_1), 0, sizeof(V_1));
	ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 V_2;
	memset((&V_2), 0, sizeof(V_2));
	uint64_t V_3 = 0;
	AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* V_4 = NULL;
	uint64_t V_5 = 0;
	uint64_t V_6 = 0;
	{
		int32_t L_0;
		L_0 = il2cpp_unsafe_sizeof<AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A>();
		if ((!(((uint32_t)L_0) == ((uint32_t)1))))
		{
			goto IL_0037;
		}
	}
	{
		int32_t L_1 = __this->____length;
		V_0 = (uint32_t)L_1;
		uint32_t L_2 = V_0;
		if (L_2)
		{
			goto IL_0013;
		}
	}
	{
		return;
	}

IL_0013:
	{
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A L_3 = ___0_value;
		V_1 = L_3;
		ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 L_4 = __this->____pointer;
		V_2 = L_4;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_5;
		L_5 = IL2CPP_BY_REFERENCE_GET_VALUE(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A, (Il2CppByReference*)(&V_2));
		uint8_t* L_6;
		L_6 = il2cpp_unsafe_as_ref<uint8_t>(L_5);
		uint8_t* L_7;
		L_7 = il2cpp_unsafe_as_ref<uint8_t>((&V_1));
		int32_t L_8 = *((uint8_t*)L_7);
		uint32_t L_9 = V_0;
		Unsafe_InitBlockUnaligned_m6F2353EB9ABC9320E61629FAEE23948C80BFF03A(L_6, (uint8_t)L_8, L_9, NULL);
		return;
	}

IL_0037:
	{
		int32_t L_10 = __this->____length;
		V_3 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_10));
		uint64_t L_11 = V_3;
		if (L_11)
		{
			goto IL_0043;
		}
	}
	{
		return;
	}

IL_0043:
	{
		ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 L_12 = __this->____pointer;
		V_2 = L_12;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_13;
		L_13 = IL2CPP_BY_REFERENCE_GET_VALUE(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A, (Il2CppByReference*)(&V_2));
		V_4 = L_13;
		int32_t L_14;
		L_14 = il2cpp_unsafe_sizeof<AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A>();
		V_5 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_14));
		V_6 = (uint64_t)((int64_t)0);
		goto IL_0110;
	}

IL_0064:
	{
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_15 = V_4;
		uint64_t L_16 = V_6;
		uint64_t L_17 = V_5;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_18;
		L_18 = il2cpp_unsafe_add_byte_offset<AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A,uint64_t>(L_15, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_16, (int64_t)L_17)));
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A L_19 = ___0_value;
		*(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A*)L_18 = L_19;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_20 = V_4;
		uint64_t L_21 = V_6;
		uint64_t L_22 = V_5;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_23;
		L_23 = il2cpp_unsafe_add_byte_offset<AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A,uint64_t>(L_20, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_21, ((int64_t)1))), (int64_t)L_22)));
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A L_24 = ___0_value;
		*(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A*)L_23 = L_24;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_25 = V_4;
		uint64_t L_26 = V_6;
		uint64_t L_27 = V_5;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_28;
		L_28 = il2cpp_unsafe_add_byte_offset<AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A,uint64_t>(L_25, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_26, ((int64_t)2))), (int64_t)L_27)));
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A L_29 = ___0_value;
		*(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A*)L_28 = L_29;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_30 = V_4;
		uint64_t L_31 = V_6;
		uint64_t L_32 = V_5;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_33;
		L_33 = il2cpp_unsafe_add_byte_offset<AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A,uint64_t>(L_30, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_31, ((int64_t)3))), (int64_t)L_32)));
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A L_34 = ___0_value;
		*(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A*)L_33 = L_34;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_35 = V_4;
		uint64_t L_36 = V_6;
		uint64_t L_37 = V_5;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_38;
		L_38 = il2cpp_unsafe_add_byte_offset<AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A,uint64_t>(L_35, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_36, ((int64_t)4))), (int64_t)L_37)));
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A L_39 = ___0_value;
		*(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A*)L_38 = L_39;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_40 = V_4;
		uint64_t L_41 = V_6;
		uint64_t L_42 = V_5;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_43;
		L_43 = il2cpp_unsafe_add_byte_offset<AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A,uint64_t>(L_40, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_41, ((int64_t)5))), (int64_t)L_42)));
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A L_44 = ___0_value;
		*(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A*)L_43 = L_44;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_45 = V_4;
		uint64_t L_46 = V_6;
		uint64_t L_47 = V_5;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_48;
		L_48 = il2cpp_unsafe_add_byte_offset<AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A,uint64_t>(L_45, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_46, ((int64_t)6))), (int64_t)L_47)));
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A L_49 = ___0_value;
		*(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A*)L_48 = L_49;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_50 = V_4;
		uint64_t L_51 = V_6;
		uint64_t L_52 = V_5;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_53;
		L_53 = il2cpp_unsafe_add_byte_offset<AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A,uint64_t>(L_50, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_51, ((int64_t)7))), (int64_t)L_52)));
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A L_54 = ___0_value;
		*(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A*)L_53 = L_54;
		uint64_t L_55 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_55, ((int64_t)8)));
	}

IL_0110:
	{
		uint64_t L_56 = V_6;
		uint64_t L_57 = V_3;
		if ((!(((uint64_t)L_56) >= ((uint64_t)((int64_t)((int64_t)L_57&((int64_t)((int32_t)-8))))))))
		{
			goto IL_0064;
		}
	}
	{
		uint64_t L_58 = V_6;
		uint64_t L_59 = V_3;
		if ((!(((uint64_t)L_58) < ((uint64_t)((int64_t)((int64_t)L_59&((int64_t)((int32_t)-4))))))))
		{
			goto IL_0198;
		}
	}
	{
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_60 = V_4;
		uint64_t L_61 = V_6;
		uint64_t L_62 = V_5;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_63;
		L_63 = il2cpp_unsafe_add_byte_offset<AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A,uint64_t>(L_60, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_61, (int64_t)L_62)));
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A L_64 = ___0_value;
		*(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A*)L_63 = L_64;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_65 = V_4;
		uint64_t L_66 = V_6;
		uint64_t L_67 = V_5;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_68;
		L_68 = il2cpp_unsafe_add_byte_offset<AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A,uint64_t>(L_65, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_66, ((int64_t)1))), (int64_t)L_67)));
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A L_69 = ___0_value;
		*(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A*)L_68 = L_69;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_70 = V_4;
		uint64_t L_71 = V_6;
		uint64_t L_72 = V_5;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_73;
		L_73 = il2cpp_unsafe_add_byte_offset<AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A,uint64_t>(L_70, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_71, ((int64_t)2))), (int64_t)L_72)));
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A L_74 = ___0_value;
		*(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A*)L_73 = L_74;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_75 = V_4;
		uint64_t L_76 = V_6;
		uint64_t L_77 = V_5;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_78;
		L_78 = il2cpp_unsafe_add_byte_offset<AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A,uint64_t>(L_75, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_76, ((int64_t)3))), (int64_t)L_77)));
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A L_79 = ___0_value;
		*(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A*)L_78 = L_79;
		uint64_t L_80 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_80, ((int64_t)4)));
		goto IL_0198;
	}

IL_017f:
	{
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_81 = V_4;
		uint64_t L_82 = V_6;
		uint64_t L_83 = V_5;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_84;
		L_84 = il2cpp_unsafe_add_byte_offset<AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A,uint64_t>(L_81, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_82, (int64_t)L_83)));
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A L_85 = ___0_value;
		*(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A*)L_84 = L_85;
		uint64_t L_86 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_86, ((int64_t)1)));
	}

IL_0198:
	{
		uint64_t L_87 = V_6;
		uint64_t L_88 = V_3;
		if ((!(((uint64_t)L_87) >= ((uint64_t)L_88))))
		{
			goto IL_017f;
		}
	}
	{
		return;
	}
}
// Method Definition Index: 13825
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_CopyTo_m7D4BDB61953C94803B245B5D9FDB4B324AF19441_gshared (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352* __this, Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352 ___0_destination, const RuntimeMethod* method) 
{
	ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_mFBFB62752766092200E6EFBA8017B4DD3A3CF901_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0038;
		}
	}
	{
		Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352 L_2 = ___0_destination;
		ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 L_3 = L_2.____pointer;
		V_0 = L_3;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A, (Il2CppByReference*)(&V_0));
		ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 L_5 = __this->____pointer;
		V_0 = L_5;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A, (Il2CppByReference*)(&V_0));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisAABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A_m47E56668B77DFD297A10C77037500A873C750328(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return;
	}

IL_0038:
	{
		ThrowHelper_ThrowArgumentException_DestinationTooShort_m6468934A3BBB67DBC5BAEF7A64D91BD5BBBB3D4D(NULL);
		return;
	}
}
// Method Definition Index: 13826
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_TryCopyTo_m41758333D76A840E4B66D273BD4194BF1EDD9C46_gshared (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352* __this, Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352 ___0_destination, const RuntimeMethod* method) 
{
	bool V_0 = false;
	ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		V_0 = (bool)0;
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_mFBFB62752766092200E6EFBA8017B4DD3A3CF901_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_003b;
		}
	}
	{
		Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352 L_2 = ___0_destination;
		ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 L_3 = L_2.____pointer;
		V_1 = L_3;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A, (Il2CppByReference*)(&V_1));
		ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 L_5 = __this->____pointer;
		V_1 = L_5;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A, (Il2CppByReference*)(&V_1));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisAABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A_m47E56668B77DFD297A10C77037500A873C750328(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		V_0 = (bool)1;
	}

IL_003b:
	{
		bool L_8 = V_0;
		return L_8;
	}
}
// Method Definition Index: 13827
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_op_Equality_m8A248B0BF84678642DF2F7E4531FB868A42A7924_gshared (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352 ___0_left, Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352 ___1_right, const RuntimeMethod* method) 
{
	ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352 L_0 = ___0_left;
		int32_t L_1 = L_0.____length;
		Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352 L_2 = ___1_right;
		int32_t L_3 = L_2.____length;
		if ((!(((uint32_t)L_1) == ((uint32_t)L_3))))
		{
			goto IL_0030;
		}
	}
	{
		Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352 L_4 = ___0_left;
		ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 L_5 = L_4.____pointer;
		V_0 = L_5;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A, (Il2CppByReference*)(&V_0));
		Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352 L_7 = ___1_right;
		ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 L_8 = L_7.____pointer;
		V_0 = L_8;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_9;
		L_9 = IL2CPP_BY_REFERENCE_GET_VALUE(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A, (Il2CppByReference*)(&V_0));
		bool L_10;
		L_10 = il2cpp_unsafe_are_same(L_6, L_9);
		return L_10;
	}

IL_0030:
	{
		return (bool)0;
	}
}
// Method Definition Index: 13828
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ReadOnlySpan_1_t32C8835D73D5F2ADCC5ED93FA71F63C5E9763632 Span_1_op_Implicit_m19354898DDD3EC6B34779174EA617152F9FC843B_gshared (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352 ___0_span, const RuntimeMethod* method) 
{
	ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352 L_0 = ___0_span;
		ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 L_1 = L_0.____pointer;
		V_0 = L_1;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_2;
		L_2 = IL2CPP_BY_REFERENCE_GET_VALUE(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A, (Il2CppByReference*)(&V_0));
		Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352 L_3 = ___0_span;
		int32_t L_4 = L_3.____length;
		ReadOnlySpan_1_t32C8835D73D5F2ADCC5ED93FA71F63C5E9763632 L_5;
		memset((&L_5), 0, sizeof(L_5));
		ReadOnlySpan_1__ctor_m00664DD373A36D0E1E71A6B15C893AFB29E09686_inline((&L_5), L_2, L_4, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 18));
		return L_5;
	}
}
// Method Definition Index: 13829
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Span_1_ToString_m4899E9443F2D5E6F0058BB58B58D5AA7FB31E036_gshared (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral0DB46164953228904843938099AF66650313FEE5);
		s_Il2CppMethodInitialized = true;
	}
	Il2CppChar* V_0 = NULL;
	ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_0 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_1;
		L_1 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_0, NULL);
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_2 = { reinterpret_cast<intptr_t> (&il2cpp_defaults.char_class->byval_arg) };
		Type_t* L_3;
		L_3 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_2, NULL);
		bool L_4;
		L_4 = Type_op_Equality_m99930A0E44E420A685FABA60E60BA1CC5FA0EBDC(L_1, L_3, NULL);
		if (!L_4)
		{
			goto IL_003e;
		}
	}
	{
		ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 L_5 = __this->____pointer;
		V_1 = L_5;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A, (Il2CppByReference*)(&V_1));
		Il2CppChar* L_7;
		L_7 = il2cpp_unsafe_as_ref<Il2CppChar>(L_6);
		V_0 = L_7;
		Il2CppChar* L_8 = V_0;
		int32_t L_9 = __this->____length;
		String_t* L_10;
		L_10 = String_CreateString_m3F8794FEB452558B8A68C65E1F0B603B3D94E0E2(NULL, (Il2CppChar*)((uintptr_t)L_8), 0, L_9, NULL);
		return L_10;
	}

IL_003e:
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_11 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_12;
		L_12 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_11, NULL);
		NullCheck((MemberInfo_t*)L_12);
		String_t* L_13;
		L_13 = VirtualFuncInvoker0< String_t* >::Invoke(7, (MemberInfo_t*)L_12);
		int32_t L_14 = __this->____length;
		int32_t L_15 = L_14;
		RuntimeObject* L_16 = Box(il2cpp_defaults.int32_class, &L_15);
		String_t* L_17;
		L_17 = String_Format_mFB7DA489BD99F4670881FF50EC017BFB0A5C0987(_stringLiteral0DB46164953228904843938099AF66650313FEE5, (RuntimeObject*)L_13, L_16, NULL);
		return L_17;
	}
}
// Method Definition Index: 13830
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352 Span_1_Slice_mC06CC7416FE1A9E79F1F06DEED1E3D81D3D3B71A_gshared (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352* __this, int32_t ___0_start, const RuntimeMethod* method) 
{
	ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) > ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 L_2 = __this->____pointer;
		V_0 = L_2;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_start;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_5;
		L_5 = il2cpp_unsafe_add<AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A,int32_t>(L_3, L_4);
		int32_t L_6 = __this->____length;
		int32_t L_7 = ___0_start;
		Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352 L_8;
		memset((&L_8), 0, sizeof(L_8));
		Span_1__ctor_m62F7DD7486AA2DC1886916B354DF7CB30B2D12CC_inline((&L_8), L_5, ((int32_t)il2cpp_codegen_subtract(L_6, L_7)), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_8;
	}
}
// Method Definition Index: 13831
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352 Span_1_Slice_m5700C13BF0EFC09AB50317A1305B4CEDDA537210_gshared (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352* __this, int32_t ___0_start, int32_t ___1_length, const RuntimeMethod* method) 
{
	ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0014;
		}
	}
	{
		int32_t L_2 = ___1_length;
		int32_t L_3 = __this->____length;
		int32_t L_4 = ___0_start;
		if ((!(((uint32_t)L_2) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(L_3, L_4))))))
		{
			goto IL_0019;
		}
	}

IL_0014:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0019:
	{
		ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 L_5 = __this->____pointer;
		V_0 = L_5;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A, (Il2CppByReference*)(&V_0));
		int32_t L_7 = ___0_start;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_8;
		L_8 = il2cpp_unsafe_add<AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A,int32_t>(L_6, L_7);
		int32_t L_9 = ___1_length;
		Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352 L_10;
		memset((&L_10), 0, sizeof(L_10));
		Span_1__ctor_m62F7DD7486AA2DC1886916B354DF7CB30B2D12CC_inline((&L_10), L_8, L_9, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_10;
	}
}
// Method Definition Index: 13832
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61* Span_1_ToArray_m96D2092418264AB8DF669A023E3D671447507EFD_gshared (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352* __this, const RuntimeMethod* method) 
{
	ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_000e;
		}
	}
	{
		AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61* L_1;
		L_1 = Array_Empty_TisAABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A_mE73376DEDDDFB934E9003663820D201F6C9B9C41_inline(il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 20));
		return L_1;
	}

IL_000e:
	{
		int32_t L_2 = __this->____length;
		AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61* L_3 = (AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61*)(AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61*)SZArrayNew(il2cpp_rgctx_data(InitializedTypeInfo(method->klass)->rgctx_data, 21), (uint32_t)L_2);
		AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61* L_4 = L_3;
		NullCheck((RuntimeArray*)L_4);
		uint8_t* L_5;
		L_5 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_4, NULL);
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_6;
		L_6 = il2cpp_unsafe_as_ref<AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A>(L_5);
		ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 L_7 = __this->____pointer;
		V_0 = L_7;
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_8;
		L_8 = IL2CPP_BY_REFERENCE_GET_VALUE(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A, (Il2CppByReference*)(&V_0));
		int32_t L_9 = __this->____length;
		Buffer_Memmove_TisAABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A_m47E56668B77DFD297A10C77037500A873C750328(L_6, L_8, (uint64_t)((int64_t)L_9), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return L_4;
	}
}
// Method Definition Index: 13833
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mFBFB62752766092200E6EFBA8017B4DD3A3CF901_gshared (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13834
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_Equals_mCE3DE3D8A365AF9878B318A63FBC09E9CAC8F423_gshared (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteral69508A540AFD085A745316DD7D6345B1C8CC662D)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13835
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_GetHashCode_m428926D7174AA0F904B382F942AB13898EC00D78_gshared (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352* __this, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteralECE618215BAC99C6FD12D8A273CC2118945EDCC8)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13836
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352 Span_1_op_Implicit_m7484AD0E7DC33EF961623C95DB4E5E945C43133D_gshared (AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61* ___0_array, const RuntimeMethod* method) 
{
	{
		AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61* L_0 = ___0_array;
		Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352 L_1;
		memset((&L_1), 0, sizeof(L_1));
		Span_1__ctor_m9F49684FE52CEB46E463E4333810DF0E97299A75_inline((&L_1), L_0, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 22));
		return L_1;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// Method Definition Index: 13818
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m41429CDEFEBC1254E9655F1E8E1020C0609E0919_gshared (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948* __this, ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F* ___0_array, const RuntimeMethod* method) 
{
	ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E));
		goto IL_0037;
	}

IL_0037:
	{
		ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_4;
		L_4 = il2cpp_unsafe_as_ref<ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E>(L_3);
		ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13819
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mFB1DF83BC23670EC8E9E5549A662686C2BF5A209_gshared (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948* __this, ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F* ___0_array, int32_t ___1_start, int32_t ___2_length, const RuntimeMethod* method) 
{
	ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_0016;
		}
	}
	{
		int32_t L_1 = ___1_start;
		if (L_1)
		{
			goto IL_0009;
		}
	}
	{
		int32_t L_2 = ___2_length;
		if (!L_2)
		{
			goto IL_000e;
		}
	}

IL_0009:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948));
		return;
	}

IL_0016:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E));
		goto IL_0042;
	}

IL_0042:
	{
		int32_t L_4 = ___1_start;
		ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F* L_5 = ___0_array;
		NullCheck(L_5);
		if ((!(((uint32_t)L_4) <= ((uint32_t)((int32_t)(((RuntimeArray*)L_5)->max_length))))))
		{
			goto IL_0050;
		}
	}
	{
		int32_t L_6 = ___2_length;
		ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F* L_7 = ___0_array;
		NullCheck(L_7);
		int32_t L_8 = ___1_start;
		if ((!(((uint32_t)L_6) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(((int32_t)(((RuntimeArray*)L_7)->max_length)), L_8))))))
		{
			goto IL_0055;
		}
	}

IL_0050:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0055:
	{
		ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F* L_9 = ___0_array;
		NullCheck((RuntimeArray*)L_9);
		uint8_t* L_10;
		L_10 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_9, NULL);
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_11;
		L_11 = il2cpp_unsafe_as_ref<ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E>(L_10);
		int32_t L_12 = ___1_start;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_13;
		L_13 = il2cpp_unsafe_add<ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E,int32_t>(L_11, L_12);
		ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 L_14;
		memset((&L_14), 0, sizeof(L_14));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_14), L_13);
		__this->____pointer = L_14;
		int32_t L_15 = ___2_length;
		__this->____length = L_15;
		return;
	}
}
// Method Definition Index: 13820
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m4A57D2C127E05581118403FF297339A88CBF04D7_gshared (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948* __this, void* ___0_pointer, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		goto IL_0016;
	}

IL_0016:
	{
		int32_t L_0 = ___1_length;
		if ((((int32_t)L_0) >= ((int32_t)0)))
		{
			goto IL_001f;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_001f:
	{
		void* L_1 = ___0_pointer;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_2;
		L_2 = il2cpp_unsafe_as_ref<ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E>((uint8_t*)L_1);
		ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 L_3;
		memset((&L_3), 0, sizeof(L_3));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_3), L_2);
		__this->____pointer = L_3;
		int32_t L_4 = ___1_length;
		__this->____length = L_4;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m51AEBDDF4FA3F8681B44273910D1645414D5B4AB_gshared (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948* __this, ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_0 = ___0_ptr;
		ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13822
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* Span_1_get_Item_mB31CA271AD62255832652CCBA3B6144E386155D7_gshared (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948* __this, int32_t ___0_index, const RuntimeMethod* method) 
{
	ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_index;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) >= ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowIndexOutOfRangeException_m86F753A24E2765A35546BA6352A7E4F0BB8A66B5(NULL);
	}

IL_000e:
	{
		ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 L_2 = __this->____pointer;
		V_0 = L_2;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_index;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_5;
		L_5 = il2cpp_unsafe_add<ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E,int32_t>(L_3, L_4);
		return L_5;
	}
}
// Method Definition Index: 13823
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* Span_1_GetPinnableReference_m7C8E33E87C25BE64667C578289D3DEE26FCCA0C4_gshared (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948* __this, const RuntimeMethod* method) 
{
	ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_0010;
		}
	}
	{
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_1;
		L_1 = il2cpp_unsafe_as_ref<ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E>((void*)((uintptr_t)0));
		return L_1;
	}

IL_0010:
	{
		ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 L_2 = __this->____pointer;
		V_0 = L_2;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E, (Il2CppByReference*)(&V_0));
		return L_3;
	}
}
// Method Definition Index: 13824
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_Fill_mC8DC7F6EDCE047652100F251B4D18E254E9C4B67_gshared (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948* __this, ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E ___0_value, const RuntimeMethod* method) 
{
	uint32_t V_0 = 0;
	ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E V_1;
	memset((&V_1), 0, sizeof(V_1));
	ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 V_2;
	memset((&V_2), 0, sizeof(V_2));
	uint64_t V_3 = 0;
	ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* V_4 = NULL;
	uint64_t V_5 = 0;
	uint64_t V_6 = 0;
	{
		int32_t L_0;
		L_0 = il2cpp_unsafe_sizeof<ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E>();
		if ((!(((uint32_t)L_0) == ((uint32_t)1))))
		{
			goto IL_0037;
		}
	}
	{
		int32_t L_1 = __this->____length;
		V_0 = (uint32_t)L_1;
		uint32_t L_2 = V_0;
		if (L_2)
		{
			goto IL_0013;
		}
	}
	{
		return;
	}

IL_0013:
	{
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E L_3 = ___0_value;
		V_1 = L_3;
		ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 L_4 = __this->____pointer;
		V_2 = L_4;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_5;
		L_5 = IL2CPP_BY_REFERENCE_GET_VALUE(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E, (Il2CppByReference*)(&V_2));
		uint8_t* L_6;
		L_6 = il2cpp_unsafe_as_ref<uint8_t>(L_5);
		uint8_t* L_7;
		L_7 = il2cpp_unsafe_as_ref<uint8_t>((&V_1));
		int32_t L_8 = *((uint8_t*)L_7);
		uint32_t L_9 = V_0;
		Unsafe_InitBlockUnaligned_m6F2353EB9ABC9320E61629FAEE23948C80BFF03A(L_6, (uint8_t)L_8, L_9, NULL);
		return;
	}

IL_0037:
	{
		int32_t L_10 = __this->____length;
		V_3 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_10));
		uint64_t L_11 = V_3;
		if (L_11)
		{
			goto IL_0043;
		}
	}
	{
		return;
	}

IL_0043:
	{
		ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 L_12 = __this->____pointer;
		V_2 = L_12;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_13;
		L_13 = IL2CPP_BY_REFERENCE_GET_VALUE(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E, (Il2CppByReference*)(&V_2));
		V_4 = L_13;
		int32_t L_14;
		L_14 = il2cpp_unsafe_sizeof<ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E>();
		V_5 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_14));
		V_6 = (uint64_t)((int64_t)0);
		goto IL_0110;
	}

IL_0064:
	{
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_15 = V_4;
		uint64_t L_16 = V_6;
		uint64_t L_17 = V_5;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_18;
		L_18 = il2cpp_unsafe_add_byte_offset<ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E,uint64_t>(L_15, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_16, (int64_t)L_17)));
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E L_19 = ___0_value;
		*(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E*)L_18 = L_19;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_20 = V_4;
		uint64_t L_21 = V_6;
		uint64_t L_22 = V_5;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_23;
		L_23 = il2cpp_unsafe_add_byte_offset<ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E,uint64_t>(L_20, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_21, ((int64_t)1))), (int64_t)L_22)));
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E L_24 = ___0_value;
		*(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E*)L_23 = L_24;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_25 = V_4;
		uint64_t L_26 = V_6;
		uint64_t L_27 = V_5;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_28;
		L_28 = il2cpp_unsafe_add_byte_offset<ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E,uint64_t>(L_25, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_26, ((int64_t)2))), (int64_t)L_27)));
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E L_29 = ___0_value;
		*(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E*)L_28 = L_29;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_30 = V_4;
		uint64_t L_31 = V_6;
		uint64_t L_32 = V_5;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_33;
		L_33 = il2cpp_unsafe_add_byte_offset<ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E,uint64_t>(L_30, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_31, ((int64_t)3))), (int64_t)L_32)));
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E L_34 = ___0_value;
		*(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E*)L_33 = L_34;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_35 = V_4;
		uint64_t L_36 = V_6;
		uint64_t L_37 = V_5;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_38;
		L_38 = il2cpp_unsafe_add_byte_offset<ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E,uint64_t>(L_35, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_36, ((int64_t)4))), (int64_t)L_37)));
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E L_39 = ___0_value;
		*(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E*)L_38 = L_39;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_40 = V_4;
		uint64_t L_41 = V_6;
		uint64_t L_42 = V_5;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_43;
		L_43 = il2cpp_unsafe_add_byte_offset<ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E,uint64_t>(L_40, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_41, ((int64_t)5))), (int64_t)L_42)));
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E L_44 = ___0_value;
		*(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E*)L_43 = L_44;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_45 = V_4;
		uint64_t L_46 = V_6;
		uint64_t L_47 = V_5;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_48;
		L_48 = il2cpp_unsafe_add_byte_offset<ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E,uint64_t>(L_45, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_46, ((int64_t)6))), (int64_t)L_47)));
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E L_49 = ___0_value;
		*(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E*)L_48 = L_49;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_50 = V_4;
		uint64_t L_51 = V_6;
		uint64_t L_52 = V_5;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_53;
		L_53 = il2cpp_unsafe_add_byte_offset<ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E,uint64_t>(L_50, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_51, ((int64_t)7))), (int64_t)L_52)));
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E L_54 = ___0_value;
		*(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E*)L_53 = L_54;
		uint64_t L_55 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_55, ((int64_t)8)));
	}

IL_0110:
	{
		uint64_t L_56 = V_6;
		uint64_t L_57 = V_3;
		if ((!(((uint64_t)L_56) >= ((uint64_t)((int64_t)((int64_t)L_57&((int64_t)((int32_t)-8))))))))
		{
			goto IL_0064;
		}
	}
	{
		uint64_t L_58 = V_6;
		uint64_t L_59 = V_3;
		if ((!(((uint64_t)L_58) < ((uint64_t)((int64_t)((int64_t)L_59&((int64_t)((int32_t)-4))))))))
		{
			goto IL_0198;
		}
	}
	{
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_60 = V_4;
		uint64_t L_61 = V_6;
		uint64_t L_62 = V_5;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_63;
		L_63 = il2cpp_unsafe_add_byte_offset<ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E,uint64_t>(L_60, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_61, (int64_t)L_62)));
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E L_64 = ___0_value;
		*(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E*)L_63 = L_64;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_65 = V_4;
		uint64_t L_66 = V_6;
		uint64_t L_67 = V_5;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_68;
		L_68 = il2cpp_unsafe_add_byte_offset<ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E,uint64_t>(L_65, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_66, ((int64_t)1))), (int64_t)L_67)));
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E L_69 = ___0_value;
		*(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E*)L_68 = L_69;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_70 = V_4;
		uint64_t L_71 = V_6;
		uint64_t L_72 = V_5;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_73;
		L_73 = il2cpp_unsafe_add_byte_offset<ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E,uint64_t>(L_70, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_71, ((int64_t)2))), (int64_t)L_72)));
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E L_74 = ___0_value;
		*(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E*)L_73 = L_74;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_75 = V_4;
		uint64_t L_76 = V_6;
		uint64_t L_77 = V_5;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_78;
		L_78 = il2cpp_unsafe_add_byte_offset<ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E,uint64_t>(L_75, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_76, ((int64_t)3))), (int64_t)L_77)));
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E L_79 = ___0_value;
		*(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E*)L_78 = L_79;
		uint64_t L_80 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_80, ((int64_t)4)));
		goto IL_0198;
	}

IL_017f:
	{
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_81 = V_4;
		uint64_t L_82 = V_6;
		uint64_t L_83 = V_5;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_84;
		L_84 = il2cpp_unsafe_add_byte_offset<ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E,uint64_t>(L_81, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_82, (int64_t)L_83)));
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E L_85 = ___0_value;
		*(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E*)L_84 = L_85;
		uint64_t L_86 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_86, ((int64_t)1)));
	}

IL_0198:
	{
		uint64_t L_87 = V_6;
		uint64_t L_88 = V_3;
		if ((!(((uint64_t)L_87) >= ((uint64_t)L_88))))
		{
			goto IL_017f;
		}
	}
	{
		return;
	}
}
// Method Definition Index: 13825
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_CopyTo_m69E286C36213A3F0A2865C947B1402909A27D13D_gshared (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948* __this, Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948 ___0_destination, const RuntimeMethod* method) 
{
	ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_mAB8B8F731C3753B681689724D8C10D0F6706324A_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0038;
		}
	}
	{
		Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948 L_2 = ___0_destination;
		ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 L_3 = L_2.____pointer;
		V_0 = L_3;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E, (Il2CppByReference*)(&V_0));
		ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 L_5 = __this->____pointer;
		V_0 = L_5;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E, (Il2CppByReference*)(&V_0));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E_mF7B64A7A3AED5F75779174F1164B6D60E51A6215(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return;
	}

IL_0038:
	{
		ThrowHelper_ThrowArgumentException_DestinationTooShort_m6468934A3BBB67DBC5BAEF7A64D91BD5BBBB3D4D(NULL);
		return;
	}
}
// Method Definition Index: 13826
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_TryCopyTo_m3F2672DBD1CBB222A9062D13B53EE549E236DEE9_gshared (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948* __this, Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948 ___0_destination, const RuntimeMethod* method) 
{
	bool V_0 = false;
	ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		V_0 = (bool)0;
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_mAB8B8F731C3753B681689724D8C10D0F6706324A_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_003b;
		}
	}
	{
		Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948 L_2 = ___0_destination;
		ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 L_3 = L_2.____pointer;
		V_1 = L_3;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E, (Il2CppByReference*)(&V_1));
		ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 L_5 = __this->____pointer;
		V_1 = L_5;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E, (Il2CppByReference*)(&V_1));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E_mF7B64A7A3AED5F75779174F1164B6D60E51A6215(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		V_0 = (bool)1;
	}

IL_003b:
	{
		bool L_8 = V_0;
		return L_8;
	}
}
// Method Definition Index: 13827
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_op_Equality_mF4C4A8902355CEED2915C9B09E31FD54FA1E4ABA_gshared (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948 ___0_left, Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948 ___1_right, const RuntimeMethod* method) 
{
	ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948 L_0 = ___0_left;
		int32_t L_1 = L_0.____length;
		Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948 L_2 = ___1_right;
		int32_t L_3 = L_2.____length;
		if ((!(((uint32_t)L_1) == ((uint32_t)L_3))))
		{
			goto IL_0030;
		}
	}
	{
		Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948 L_4 = ___0_left;
		ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 L_5 = L_4.____pointer;
		V_0 = L_5;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E, (Il2CppByReference*)(&V_0));
		Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948 L_7 = ___1_right;
		ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 L_8 = L_7.____pointer;
		V_0 = L_8;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_9;
		L_9 = IL2CPP_BY_REFERENCE_GET_VALUE(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E, (Il2CppByReference*)(&V_0));
		bool L_10;
		L_10 = il2cpp_unsafe_are_same(L_6, L_9);
		return L_10;
	}

IL_0030:
	{
		return (bool)0;
	}
}
// Method Definition Index: 13828
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ReadOnlySpan_1_tAE84767975672BD032424E74DD54F304DED0E2A7 Span_1_op_Implicit_m86584B024E037F330D6D8BAD3654D009976F3186_gshared (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948 ___0_span, const RuntimeMethod* method) 
{
	ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948 L_0 = ___0_span;
		ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 L_1 = L_0.____pointer;
		V_0 = L_1;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_2;
		L_2 = IL2CPP_BY_REFERENCE_GET_VALUE(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E, (Il2CppByReference*)(&V_0));
		Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948 L_3 = ___0_span;
		int32_t L_4 = L_3.____length;
		ReadOnlySpan_1_tAE84767975672BD032424E74DD54F304DED0E2A7 L_5;
		memset((&L_5), 0, sizeof(L_5));
		ReadOnlySpan_1__ctor_m61974A48F0249F9BF95E2B30196D22127E7AD9CB_inline((&L_5), L_2, L_4, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 18));
		return L_5;
	}
}
// Method Definition Index: 13829
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Span_1_ToString_m095D5F8A52B67EDB057CF850BB375D945B975194_gshared (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral0DB46164953228904843938099AF66650313FEE5);
		s_Il2CppMethodInitialized = true;
	}
	Il2CppChar* V_0 = NULL;
	ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_0 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_1;
		L_1 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_0, NULL);
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_2 = { reinterpret_cast<intptr_t> (&il2cpp_defaults.char_class->byval_arg) };
		Type_t* L_3;
		L_3 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_2, NULL);
		bool L_4;
		L_4 = Type_op_Equality_m99930A0E44E420A685FABA60E60BA1CC5FA0EBDC(L_1, L_3, NULL);
		if (!L_4)
		{
			goto IL_003e;
		}
	}
	{
		ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 L_5 = __this->____pointer;
		V_1 = L_5;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E, (Il2CppByReference*)(&V_1));
		Il2CppChar* L_7;
		L_7 = il2cpp_unsafe_as_ref<Il2CppChar>(L_6);
		V_0 = L_7;
		Il2CppChar* L_8 = V_0;
		int32_t L_9 = __this->____length;
		String_t* L_10;
		L_10 = String_CreateString_m3F8794FEB452558B8A68C65E1F0B603B3D94E0E2(NULL, (Il2CppChar*)((uintptr_t)L_8), 0, L_9, NULL);
		return L_10;
	}

IL_003e:
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_11 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_12;
		L_12 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_11, NULL);
		NullCheck((MemberInfo_t*)L_12);
		String_t* L_13;
		L_13 = VirtualFuncInvoker0< String_t* >::Invoke(7, (MemberInfo_t*)L_12);
		int32_t L_14 = __this->____length;
		int32_t L_15 = L_14;
		RuntimeObject* L_16 = Box(il2cpp_defaults.int32_class, &L_15);
		String_t* L_17;
		L_17 = String_Format_mFB7DA489BD99F4670881FF50EC017BFB0A5C0987(_stringLiteral0DB46164953228904843938099AF66650313FEE5, (RuntimeObject*)L_13, L_16, NULL);
		return L_17;
	}
}
// Method Definition Index: 13830
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948 Span_1_Slice_m57905D80A43A7487820D76CE38E84805236492B2_gshared (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948* __this, int32_t ___0_start, const RuntimeMethod* method) 
{
	ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) > ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 L_2 = __this->____pointer;
		V_0 = L_2;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_start;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_5;
		L_5 = il2cpp_unsafe_add<ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E,int32_t>(L_3, L_4);
		int32_t L_6 = __this->____length;
		int32_t L_7 = ___0_start;
		Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948 L_8;
		memset((&L_8), 0, sizeof(L_8));
		Span_1__ctor_m51AEBDDF4FA3F8681B44273910D1645414D5B4AB_inline((&L_8), L_5, ((int32_t)il2cpp_codegen_subtract(L_6, L_7)), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_8;
	}
}
// Method Definition Index: 13831
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948 Span_1_Slice_m085D783FD988C5CE9726A19B347DB3791B1BB7CF_gshared (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948* __this, int32_t ___0_start, int32_t ___1_length, const RuntimeMethod* method) 
{
	ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0014;
		}
	}
	{
		int32_t L_2 = ___1_length;
		int32_t L_3 = __this->____length;
		int32_t L_4 = ___0_start;
		if ((!(((uint32_t)L_2) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(L_3, L_4))))))
		{
			goto IL_0019;
		}
	}

IL_0014:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0019:
	{
		ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 L_5 = __this->____pointer;
		V_0 = L_5;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E, (Il2CppByReference*)(&V_0));
		int32_t L_7 = ___0_start;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_8;
		L_8 = il2cpp_unsafe_add<ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E,int32_t>(L_6, L_7);
		int32_t L_9 = ___1_length;
		Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948 L_10;
		memset((&L_10), 0, sizeof(L_10));
		Span_1__ctor_m51AEBDDF4FA3F8681B44273910D1645414D5B4AB_inline((&L_10), L_8, L_9, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_10;
	}
}
// Method Definition Index: 13832
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F* Span_1_ToArray_mB0B732DF6D1AAF2D2FD4818EF9091E54136DE58F_gshared (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948* __this, const RuntimeMethod* method) 
{
	ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_000e;
		}
	}
	{
		ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F* L_1;
		L_1 = Array_Empty_TisATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E_m5293579F263F9A3750FDFA1AA45718B28873BFBB_inline(il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 20));
		return L_1;
	}

IL_000e:
	{
		int32_t L_2 = __this->____length;
		ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F* L_3 = (ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F*)(ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F*)SZArrayNew(il2cpp_rgctx_data(InitializedTypeInfo(method->klass)->rgctx_data, 21), (uint32_t)L_2);
		ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F* L_4 = L_3;
		NullCheck((RuntimeArray*)L_4);
		uint8_t* L_5;
		L_5 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_4, NULL);
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_6;
		L_6 = il2cpp_unsafe_as_ref<ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E>(L_5);
		ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 L_7 = __this->____pointer;
		V_0 = L_7;
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_8;
		L_8 = IL2CPP_BY_REFERENCE_GET_VALUE(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E, (Il2CppByReference*)(&V_0));
		int32_t L_9 = __this->____length;
		Buffer_Memmove_TisATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E_mF7B64A7A3AED5F75779174F1164B6D60E51A6215(L_6, L_8, (uint64_t)((int64_t)L_9), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return L_4;
	}
}
// Method Definition Index: 13833
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mAB8B8F731C3753B681689724D8C10D0F6706324A_gshared (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13834
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_Equals_mEC9CE7A49026C798E76AD11A730B0199AEC6BD10_gshared (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteral69508A540AFD085A745316DD7D6345B1C8CC662D)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13835
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_GetHashCode_m313C66D04AC3CA5C139861E0B60F6A2C4ED47AFC_gshared (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948* __this, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteralECE618215BAC99C6FD12D8A273CC2118945EDCC8)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13836
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948 Span_1_op_Implicit_mD167D5E84BC89F06CF65BED50126A856D72DB6AD_gshared (ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F* ___0_array, const RuntimeMethod* method) 
{
	{
		ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F* L_0 = ___0_array;
		Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948 L_1;
		memset((&L_1), 0, sizeof(L_1));
		Span_1__ctor_m41429CDEFEBC1254E9655F1E8E1020C0609E0919_inline((&L_1), L_0, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 22));
		return L_1;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// Method Definition Index: 13818
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m9923F08D09F1AF1763CE22C041DC55410B5A05B1_gshared (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F* __this, AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1* ___0_array, const RuntimeMethod* method) 
{
	AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E));
		goto IL_0037;
	}

IL_0037:
	{
		AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_4;
		L_4 = il2cpp_unsafe_as_ref<AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E>(L_3);
		ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13819
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mED5F59A6F6DA4ECF6B65C2D8F1D3FB60473AC0CB_gshared (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F* __this, AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1* ___0_array, int32_t ___1_start, int32_t ___2_length, const RuntimeMethod* method) 
{
	AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_0016;
		}
	}
	{
		int32_t L_1 = ___1_start;
		if (L_1)
		{
			goto IL_0009;
		}
	}
	{
		int32_t L_2 = ___2_length;
		if (!L_2)
		{
			goto IL_000e;
		}
	}

IL_0009:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F));
		return;
	}

IL_0016:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E));
		goto IL_0042;
	}

IL_0042:
	{
		int32_t L_4 = ___1_start;
		AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1* L_5 = ___0_array;
		NullCheck(L_5);
		if ((!(((uint32_t)L_4) <= ((uint32_t)((int32_t)(((RuntimeArray*)L_5)->max_length))))))
		{
			goto IL_0050;
		}
	}
	{
		int32_t L_6 = ___2_length;
		AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1* L_7 = ___0_array;
		NullCheck(L_7);
		int32_t L_8 = ___1_start;
		if ((!(((uint32_t)L_6) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(((int32_t)(((RuntimeArray*)L_7)->max_length)), L_8))))))
		{
			goto IL_0055;
		}
	}

IL_0050:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0055:
	{
		AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1* L_9 = ___0_array;
		NullCheck((RuntimeArray*)L_9);
		uint8_t* L_10;
		L_10 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_9, NULL);
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_11;
		L_11 = il2cpp_unsafe_as_ref<AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E>(L_10);
		int32_t L_12 = ___1_start;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_13;
		L_13 = il2cpp_unsafe_add<AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E,int32_t>(L_11, L_12);
		ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 L_14;
		memset((&L_14), 0, sizeof(L_14));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_14), L_13);
		__this->____pointer = L_14;
		int32_t L_15 = ___2_length;
		__this->____length = L_15;
		return;
	}
}
// Method Definition Index: 13820
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m01AAD111BB5AB6F2C7D299CF56F2F99AA0139BF6_gshared (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F* __this, void* ___0_pointer, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		goto IL_0016;
	}

IL_0016:
	{
		int32_t L_0 = ___1_length;
		if ((((int32_t)L_0) >= ((int32_t)0)))
		{
			goto IL_001f;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_001f:
	{
		void* L_1 = ___0_pointer;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_2;
		L_2 = il2cpp_unsafe_as_ref<AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E>((uint8_t*)L_1);
		ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 L_3;
		memset((&L_3), 0, sizeof(L_3));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_3), L_2);
		__this->____pointer = L_3;
		int32_t L_4 = ___1_length;
		__this->____length = L_4;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mFFA3823A31B573A005E307DD54A3C8EA4704B552_gshared (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F* __this, AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_0 = ___0_ptr;
		ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13822
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* Span_1_get_Item_m47F122CF302AE7739862206F7380FFB92CFDAEB8_gshared (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F* __this, int32_t ___0_index, const RuntimeMethod* method) 
{
	ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_index;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) >= ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowIndexOutOfRangeException_m86F753A24E2765A35546BA6352A7E4F0BB8A66B5(NULL);
	}

IL_000e:
	{
		ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 L_2 = __this->____pointer;
		V_0 = L_2;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_index;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_5;
		L_5 = il2cpp_unsafe_add<AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E,int32_t>(L_3, L_4);
		return L_5;
	}
}
// Method Definition Index: 13823
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* Span_1_GetPinnableReference_m4DF0B0C0B0AC1FA128FE2C6EFB8BABEBC2A7DFF1_gshared (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F* __this, const RuntimeMethod* method) 
{
	ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_0010;
		}
	}
	{
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_1;
		L_1 = il2cpp_unsafe_as_ref<AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E>((void*)((uintptr_t)0));
		return L_1;
	}

IL_0010:
	{
		ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 L_2 = __this->____pointer;
		V_0 = L_2;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E, (Il2CppByReference*)(&V_0));
		return L_3;
	}
}
// Method Definition Index: 13824
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_Fill_mEA871B972BC5BEB5DA426D051142EFDCB445FBC2_gshared (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F* __this, AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E ___0_value, const RuntimeMethod* method) 
{
	uint32_t V_0 = 0;
	AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E V_1;
	memset((&V_1), 0, sizeof(V_1));
	ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 V_2;
	memset((&V_2), 0, sizeof(V_2));
	uint64_t V_3 = 0;
	AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* V_4 = NULL;
	uint64_t V_5 = 0;
	uint64_t V_6 = 0;
	{
		int32_t L_0;
		L_0 = il2cpp_unsafe_sizeof<AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E>();
		if ((!(((uint32_t)L_0) == ((uint32_t)1))))
		{
			goto IL_0037;
		}
	}
	{
		int32_t L_1 = __this->____length;
		V_0 = (uint32_t)L_1;
		uint32_t L_2 = V_0;
		if (L_2)
		{
			goto IL_0013;
		}
	}
	{
		return;
	}

IL_0013:
	{
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E L_3 = ___0_value;
		V_1 = L_3;
		ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 L_4 = __this->____pointer;
		V_2 = L_4;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_5;
		L_5 = IL2CPP_BY_REFERENCE_GET_VALUE(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E, (Il2CppByReference*)(&V_2));
		uint8_t* L_6;
		L_6 = il2cpp_unsafe_as_ref<uint8_t>(L_5);
		uint8_t* L_7;
		L_7 = il2cpp_unsafe_as_ref<uint8_t>((&V_1));
		int32_t L_8 = *((uint8_t*)L_7);
		uint32_t L_9 = V_0;
		Unsafe_InitBlockUnaligned_m6F2353EB9ABC9320E61629FAEE23948C80BFF03A(L_6, (uint8_t)L_8, L_9, NULL);
		return;
	}

IL_0037:
	{
		int32_t L_10 = __this->____length;
		V_3 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_10));
		uint64_t L_11 = V_3;
		if (L_11)
		{
			goto IL_0043;
		}
	}
	{
		return;
	}

IL_0043:
	{
		ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 L_12 = __this->____pointer;
		V_2 = L_12;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_13;
		L_13 = IL2CPP_BY_REFERENCE_GET_VALUE(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E, (Il2CppByReference*)(&V_2));
		V_4 = L_13;
		int32_t L_14;
		L_14 = il2cpp_unsafe_sizeof<AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E>();
		V_5 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_14));
		V_6 = (uint64_t)((int64_t)0);
		goto IL_0110;
	}

IL_0064:
	{
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_15 = V_4;
		uint64_t L_16 = V_6;
		uint64_t L_17 = V_5;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_18;
		L_18 = il2cpp_unsafe_add_byte_offset<AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E,uint64_t>(L_15, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_16, (int64_t)L_17)));
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E L_19 = ___0_value;
		*(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E*)L_18 = L_19;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_20 = V_4;
		uint64_t L_21 = V_6;
		uint64_t L_22 = V_5;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_23;
		L_23 = il2cpp_unsafe_add_byte_offset<AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E,uint64_t>(L_20, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_21, ((int64_t)1))), (int64_t)L_22)));
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E L_24 = ___0_value;
		*(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E*)L_23 = L_24;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_25 = V_4;
		uint64_t L_26 = V_6;
		uint64_t L_27 = V_5;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_28;
		L_28 = il2cpp_unsafe_add_byte_offset<AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E,uint64_t>(L_25, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_26, ((int64_t)2))), (int64_t)L_27)));
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E L_29 = ___0_value;
		*(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E*)L_28 = L_29;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_30 = V_4;
		uint64_t L_31 = V_6;
		uint64_t L_32 = V_5;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_33;
		L_33 = il2cpp_unsafe_add_byte_offset<AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E,uint64_t>(L_30, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_31, ((int64_t)3))), (int64_t)L_32)));
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E L_34 = ___0_value;
		*(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E*)L_33 = L_34;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_35 = V_4;
		uint64_t L_36 = V_6;
		uint64_t L_37 = V_5;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_38;
		L_38 = il2cpp_unsafe_add_byte_offset<AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E,uint64_t>(L_35, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_36, ((int64_t)4))), (int64_t)L_37)));
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E L_39 = ___0_value;
		*(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E*)L_38 = L_39;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_40 = V_4;
		uint64_t L_41 = V_6;
		uint64_t L_42 = V_5;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_43;
		L_43 = il2cpp_unsafe_add_byte_offset<AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E,uint64_t>(L_40, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_41, ((int64_t)5))), (int64_t)L_42)));
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E L_44 = ___0_value;
		*(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E*)L_43 = L_44;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_45 = V_4;
		uint64_t L_46 = V_6;
		uint64_t L_47 = V_5;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_48;
		L_48 = il2cpp_unsafe_add_byte_offset<AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E,uint64_t>(L_45, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_46, ((int64_t)6))), (int64_t)L_47)));
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E L_49 = ___0_value;
		*(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E*)L_48 = L_49;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_50 = V_4;
		uint64_t L_51 = V_6;
		uint64_t L_52 = V_5;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_53;
		L_53 = il2cpp_unsafe_add_byte_offset<AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E,uint64_t>(L_50, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_51, ((int64_t)7))), (int64_t)L_52)));
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E L_54 = ___0_value;
		*(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E*)L_53 = L_54;
		uint64_t L_55 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_55, ((int64_t)8)));
	}

IL_0110:
	{
		uint64_t L_56 = V_6;
		uint64_t L_57 = V_3;
		if ((!(((uint64_t)L_56) >= ((uint64_t)((int64_t)((int64_t)L_57&((int64_t)((int32_t)-8))))))))
		{
			goto IL_0064;
		}
	}
	{
		uint64_t L_58 = V_6;
		uint64_t L_59 = V_3;
		if ((!(((uint64_t)L_58) < ((uint64_t)((int64_t)((int64_t)L_59&((int64_t)((int32_t)-4))))))))
		{
			goto IL_0198;
		}
	}
	{
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_60 = V_4;
		uint64_t L_61 = V_6;
		uint64_t L_62 = V_5;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_63;
		L_63 = il2cpp_unsafe_add_byte_offset<AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E,uint64_t>(L_60, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_61, (int64_t)L_62)));
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E L_64 = ___0_value;
		*(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E*)L_63 = L_64;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_65 = V_4;
		uint64_t L_66 = V_6;
		uint64_t L_67 = V_5;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_68;
		L_68 = il2cpp_unsafe_add_byte_offset<AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E,uint64_t>(L_65, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_66, ((int64_t)1))), (int64_t)L_67)));
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E L_69 = ___0_value;
		*(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E*)L_68 = L_69;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_70 = V_4;
		uint64_t L_71 = V_6;
		uint64_t L_72 = V_5;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_73;
		L_73 = il2cpp_unsafe_add_byte_offset<AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E,uint64_t>(L_70, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_71, ((int64_t)2))), (int64_t)L_72)));
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E L_74 = ___0_value;
		*(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E*)L_73 = L_74;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_75 = V_4;
		uint64_t L_76 = V_6;
		uint64_t L_77 = V_5;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_78;
		L_78 = il2cpp_unsafe_add_byte_offset<AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E,uint64_t>(L_75, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_76, ((int64_t)3))), (int64_t)L_77)));
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E L_79 = ___0_value;
		*(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E*)L_78 = L_79;
		uint64_t L_80 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_80, ((int64_t)4)));
		goto IL_0198;
	}

IL_017f:
	{
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_81 = V_4;
		uint64_t L_82 = V_6;
		uint64_t L_83 = V_5;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_84;
		L_84 = il2cpp_unsafe_add_byte_offset<AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E,uint64_t>(L_81, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_82, (int64_t)L_83)));
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E L_85 = ___0_value;
		*(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E*)L_84 = L_85;
		uint64_t L_86 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_86, ((int64_t)1)));
	}

IL_0198:
	{
		uint64_t L_87 = V_6;
		uint64_t L_88 = V_3;
		if ((!(((uint64_t)L_87) >= ((uint64_t)L_88))))
		{
			goto IL_017f;
		}
	}
	{
		return;
	}
}
// Method Definition Index: 13825
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_CopyTo_m1DE3E48127AEF27F6E29B9E0D37CE69AABCF3877_gshared (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F* __this, Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F ___0_destination, const RuntimeMethod* method) 
{
	ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_m2275D4270FBF5A9F745DB11CE6CCE148D8C4672E_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0038;
		}
	}
	{
		Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F L_2 = ___0_destination;
		ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 L_3 = L_2.____pointer;
		V_0 = L_3;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E, (Il2CppByReference*)(&V_0));
		ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 L_5 = __this->____pointer;
		V_0 = L_5;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E, (Il2CppByReference*)(&V_0));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisAttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E_mE722009CF3369F14C5504F191D0EA30479F00E86(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return;
	}

IL_0038:
	{
		ThrowHelper_ThrowArgumentException_DestinationTooShort_m6468934A3BBB67DBC5BAEF7A64D91BD5BBBB3D4D(NULL);
		return;
	}
}
// Method Definition Index: 13826
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_TryCopyTo_mC058C8E77888F41553B363FEE01CF9A657E2215D_gshared (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F* __this, Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F ___0_destination, const RuntimeMethod* method) 
{
	bool V_0 = false;
	ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		V_0 = (bool)0;
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_m2275D4270FBF5A9F745DB11CE6CCE148D8C4672E_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_003b;
		}
	}
	{
		Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F L_2 = ___0_destination;
		ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 L_3 = L_2.____pointer;
		V_1 = L_3;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E, (Il2CppByReference*)(&V_1));
		ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 L_5 = __this->____pointer;
		V_1 = L_5;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E, (Il2CppByReference*)(&V_1));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisAttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E_mE722009CF3369F14C5504F191D0EA30479F00E86(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		V_0 = (bool)1;
	}

IL_003b:
	{
		bool L_8 = V_0;
		return L_8;
	}
}
// Method Definition Index: 13827
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_op_Equality_mCE280E09C31C923C5FB162407ABFF22D97D0BF97_gshared (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F ___0_left, Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F ___1_right, const RuntimeMethod* method) 
{
	ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F L_0 = ___0_left;
		int32_t L_1 = L_0.____length;
		Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F L_2 = ___1_right;
		int32_t L_3 = L_2.____length;
		if ((!(((uint32_t)L_1) == ((uint32_t)L_3))))
		{
			goto IL_0030;
		}
	}
	{
		Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F L_4 = ___0_left;
		ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 L_5 = L_4.____pointer;
		V_0 = L_5;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E, (Il2CppByReference*)(&V_0));
		Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F L_7 = ___1_right;
		ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 L_8 = L_7.____pointer;
		V_0 = L_8;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_9;
		L_9 = IL2CPP_BY_REFERENCE_GET_VALUE(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E, (Il2CppByReference*)(&V_0));
		bool L_10;
		L_10 = il2cpp_unsafe_are_same(L_6, L_9);
		return L_10;
	}

IL_0030:
	{
		return (bool)0;
	}
}
// Method Definition Index: 13828
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ReadOnlySpan_1_tBA07559B531DF8D3C488A21E738503C11F05E17A Span_1_op_Implicit_m09020CD84878F657D32140C1B0E5957742B074F5_gshared (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F ___0_span, const RuntimeMethod* method) 
{
	ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F L_0 = ___0_span;
		ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 L_1 = L_0.____pointer;
		V_0 = L_1;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_2;
		L_2 = IL2CPP_BY_REFERENCE_GET_VALUE(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E, (Il2CppByReference*)(&V_0));
		Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F L_3 = ___0_span;
		int32_t L_4 = L_3.____length;
		ReadOnlySpan_1_tBA07559B531DF8D3C488A21E738503C11F05E17A L_5;
		memset((&L_5), 0, sizeof(L_5));
		ReadOnlySpan_1__ctor_mB2FA851A491C5709D4BAB63C5DA5958FFFEEAA07_inline((&L_5), L_2, L_4, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 18));
		return L_5;
	}
}
// Method Definition Index: 13829
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Span_1_ToString_mC4B5A6EEA5EA252A4D5C818F68A45FBDBBD0D0F8_gshared (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral0DB46164953228904843938099AF66650313FEE5);
		s_Il2CppMethodInitialized = true;
	}
	Il2CppChar* V_0 = NULL;
	ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_0 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_1;
		L_1 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_0, NULL);
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_2 = { reinterpret_cast<intptr_t> (&il2cpp_defaults.char_class->byval_arg) };
		Type_t* L_3;
		L_3 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_2, NULL);
		bool L_4;
		L_4 = Type_op_Equality_m99930A0E44E420A685FABA60E60BA1CC5FA0EBDC(L_1, L_3, NULL);
		if (!L_4)
		{
			goto IL_003e;
		}
	}
	{
		ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 L_5 = __this->____pointer;
		V_1 = L_5;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E, (Il2CppByReference*)(&V_1));
		Il2CppChar* L_7;
		L_7 = il2cpp_unsafe_as_ref<Il2CppChar>(L_6);
		V_0 = L_7;
		Il2CppChar* L_8 = V_0;
		int32_t L_9 = __this->____length;
		String_t* L_10;
		L_10 = String_CreateString_m3F8794FEB452558B8A68C65E1F0B603B3D94E0E2(NULL, (Il2CppChar*)((uintptr_t)L_8), 0, L_9, NULL);
		return L_10;
	}

IL_003e:
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_11 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_12;
		L_12 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_11, NULL);
		NullCheck((MemberInfo_t*)L_12);
		String_t* L_13;
		L_13 = VirtualFuncInvoker0< String_t* >::Invoke(7, (MemberInfo_t*)L_12);
		int32_t L_14 = __this->____length;
		int32_t L_15 = L_14;
		RuntimeObject* L_16 = Box(il2cpp_defaults.int32_class, &L_15);
		String_t* L_17;
		L_17 = String_Format_mFB7DA489BD99F4670881FF50EC017BFB0A5C0987(_stringLiteral0DB46164953228904843938099AF66650313FEE5, (RuntimeObject*)L_13, L_16, NULL);
		return L_17;
	}
}
// Method Definition Index: 13830
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F Span_1_Slice_m373D0A01BEDFED2F380F42EFC08F521614B7BB45_gshared (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F* __this, int32_t ___0_start, const RuntimeMethod* method) 
{
	ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) > ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 L_2 = __this->____pointer;
		V_0 = L_2;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_start;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_5;
		L_5 = il2cpp_unsafe_add<AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E,int32_t>(L_3, L_4);
		int32_t L_6 = __this->____length;
		int32_t L_7 = ___0_start;
		Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F L_8;
		memset((&L_8), 0, sizeof(L_8));
		Span_1__ctor_mFFA3823A31B573A005E307DD54A3C8EA4704B552_inline((&L_8), L_5, ((int32_t)il2cpp_codegen_subtract(L_6, L_7)), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_8;
	}
}
// Method Definition Index: 13831
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F Span_1_Slice_mC20543F620A78C426E52A441D0850905328AE879_gshared (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F* __this, int32_t ___0_start, int32_t ___1_length, const RuntimeMethod* method) 
{
	ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0014;
		}
	}
	{
		int32_t L_2 = ___1_length;
		int32_t L_3 = __this->____length;
		int32_t L_4 = ___0_start;
		if ((!(((uint32_t)L_2) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(L_3, L_4))))))
		{
			goto IL_0019;
		}
	}

IL_0014:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0019:
	{
		ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 L_5 = __this->____pointer;
		V_0 = L_5;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E, (Il2CppByReference*)(&V_0));
		int32_t L_7 = ___0_start;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_8;
		L_8 = il2cpp_unsafe_add<AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E,int32_t>(L_6, L_7);
		int32_t L_9 = ___1_length;
		Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F L_10;
		memset((&L_10), 0, sizeof(L_10));
		Span_1__ctor_mFFA3823A31B573A005E307DD54A3C8EA4704B552_inline((&L_10), L_8, L_9, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_10;
	}
}
// Method Definition Index: 13832
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1* Span_1_ToArray_m06C73FA0B2FE24B8A6190246D8E336C1F5B60D75_gshared (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F* __this, const RuntimeMethod* method) 
{
	ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_000e;
		}
	}
	{
		AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1* L_1;
		L_1 = Array_Empty_TisAttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E_m0F7B255CF739B8C78F6D0663FFFD44753100FCAE_inline(il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 20));
		return L_1;
	}

IL_000e:
	{
		int32_t L_2 = __this->____length;
		AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1* L_3 = (AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1*)(AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1*)SZArrayNew(il2cpp_rgctx_data(InitializedTypeInfo(method->klass)->rgctx_data, 21), (uint32_t)L_2);
		AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1* L_4 = L_3;
		NullCheck((RuntimeArray*)L_4);
		uint8_t* L_5;
		L_5 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_4, NULL);
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_6;
		L_6 = il2cpp_unsafe_as_ref<AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E>(L_5);
		ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 L_7 = __this->____pointer;
		V_0 = L_7;
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_8;
		L_8 = IL2CPP_BY_REFERENCE_GET_VALUE(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E, (Il2CppByReference*)(&V_0));
		int32_t L_9 = __this->____length;
		Buffer_Memmove_TisAttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E_mE722009CF3369F14C5504F191D0EA30479F00E86(L_6, L_8, (uint64_t)((int64_t)L_9), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return L_4;
	}
}
// Method Definition Index: 13833
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m2275D4270FBF5A9F745DB11CE6CCE148D8C4672E_gshared (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13834
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_Equals_m75F12AB8F2C8BABD810DAA38E134C6EBD2AFB1EA_gshared (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteral69508A540AFD085A745316DD7D6345B1C8CC662D)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13835
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_GetHashCode_mC64FEFBE1E1B0A864A8EA488DD1AE1862FC40ED1_gshared (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F* __this, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteralECE618215BAC99C6FD12D8A273CC2118945EDCC8)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13836
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F Span_1_op_Implicit_m7C4D878370E31E8BEA4AA1CD1FA351617400AC1B_gshared (AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1* ___0_array, const RuntimeMethod* method) 
{
	{
		AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1* L_0 = ___0_array;
		Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F L_1;
		memset((&L_1), 0, sizeof(L_1));
		Span_1__ctor_m9923F08D09F1AF1763CE22C041DC55410B5A05B1_inline((&L_1), L_0, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 22));
		return L_1;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// Method Definition Index: 13818
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m7126F92024E73EE43580A7A9D64C39CD973406CC_gshared (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428* __this, BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80* ___0_array, const RuntimeMethod* method) 
{
	BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D));
		goto IL_0037;
	}

IL_0037:
	{
		BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_4;
		L_4 = il2cpp_unsafe_as_ref<BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D>(L_3);
		ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13819
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m6AD4D32958406E99771BD80D95955B6C1C1D8C7E_gshared (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428* __this, BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80* ___0_array, int32_t ___1_start, int32_t ___2_length, const RuntimeMethod* method) 
{
	BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_0016;
		}
	}
	{
		int32_t L_1 = ___1_start;
		if (L_1)
		{
			goto IL_0009;
		}
	}
	{
		int32_t L_2 = ___2_length;
		if (!L_2)
		{
			goto IL_000e;
		}
	}

IL_0009:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428));
		return;
	}

IL_0016:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D));
		goto IL_0042;
	}

IL_0042:
	{
		int32_t L_4 = ___1_start;
		BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80* L_5 = ___0_array;
		NullCheck(L_5);
		if ((!(((uint32_t)L_4) <= ((uint32_t)((int32_t)(((RuntimeArray*)L_5)->max_length))))))
		{
			goto IL_0050;
		}
	}
	{
		int32_t L_6 = ___2_length;
		BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80* L_7 = ___0_array;
		NullCheck(L_7);
		int32_t L_8 = ___1_start;
		if ((!(((uint32_t)L_6) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(((int32_t)(((RuntimeArray*)L_7)->max_length)), L_8))))))
		{
			goto IL_0055;
		}
	}

IL_0050:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0055:
	{
		BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80* L_9 = ___0_array;
		NullCheck((RuntimeArray*)L_9);
		uint8_t* L_10;
		L_10 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_9, NULL);
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_11;
		L_11 = il2cpp_unsafe_as_ref<BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D>(L_10);
		int32_t L_12 = ___1_start;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_13;
		L_13 = il2cpp_unsafe_add<BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D,int32_t>(L_11, L_12);
		ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 L_14;
		memset((&L_14), 0, sizeof(L_14));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_14), L_13);
		__this->____pointer = L_14;
		int32_t L_15 = ___2_length;
		__this->____length = L_15;
		return;
	}
}
// Method Definition Index: 13820
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mF21BAD08783861F66A5DC64B2008B831D4E70ECD_gshared (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428* __this, void* ___0_pointer, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		goto IL_0016;
	}

IL_0016:
	{
		int32_t L_0 = ___1_length;
		if ((((int32_t)L_0) >= ((int32_t)0)))
		{
			goto IL_001f;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_001f:
	{
		void* L_1 = ___0_pointer;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_2;
		L_2 = il2cpp_unsafe_as_ref<BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D>((uint8_t*)L_1);
		ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 L_3;
		memset((&L_3), 0, sizeof(L_3));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_3), L_2);
		__this->____pointer = L_3;
		int32_t L_4 = ___1_length;
		__this->____length = L_4;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mB2713B2424259A6D18038A2E29BD96DA081378D6_gshared (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428* __this, BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_0 = ___0_ptr;
		ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13822
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* Span_1_get_Item_m8F05BEF265BF6D467B7F3EC7E0FC6144A17514B9_gshared (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428* __this, int32_t ___0_index, const RuntimeMethod* method) 
{
	ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_index;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) >= ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowIndexOutOfRangeException_m86F753A24E2765A35546BA6352A7E4F0BB8A66B5(NULL);
	}

IL_000e:
	{
		ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 L_2 = __this->____pointer;
		V_0 = L_2;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_index;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_5;
		L_5 = il2cpp_unsafe_add<BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D,int32_t>(L_3, L_4);
		return L_5;
	}
}
// Method Definition Index: 13823
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* Span_1_GetPinnableReference_m1651F4B2CE8A49E7C5F7AFA47B2C760465CD6E77_gshared (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428* __this, const RuntimeMethod* method) 
{
	ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_0010;
		}
	}
	{
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_1;
		L_1 = il2cpp_unsafe_as_ref<BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D>((void*)((uintptr_t)0));
		return L_1;
	}

IL_0010:
	{
		ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 L_2 = __this->____pointer;
		V_0 = L_2;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D, (Il2CppByReference*)(&V_0));
		return L_3;
	}
}
// Method Definition Index: 13824
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_Fill_mCFE2C408BFC0620F17CED47CB3A5E4E9097A2A2C_gshared (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428* __this, BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D ___0_value, const RuntimeMethod* method) 
{
	uint32_t V_0 = 0;
	BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D V_1;
	memset((&V_1), 0, sizeof(V_1));
	ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 V_2;
	memset((&V_2), 0, sizeof(V_2));
	uint64_t V_3 = 0;
	BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* V_4 = NULL;
	uint64_t V_5 = 0;
	uint64_t V_6 = 0;
	{
		int32_t L_0;
		L_0 = il2cpp_unsafe_sizeof<BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D>();
		if ((!(((uint32_t)L_0) == ((uint32_t)1))))
		{
			goto IL_0037;
		}
	}
	{
		int32_t L_1 = __this->____length;
		V_0 = (uint32_t)L_1;
		uint32_t L_2 = V_0;
		if (L_2)
		{
			goto IL_0013;
		}
	}
	{
		return;
	}

IL_0013:
	{
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D L_3 = ___0_value;
		V_1 = L_3;
		ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 L_4 = __this->____pointer;
		V_2 = L_4;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_5;
		L_5 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D, (Il2CppByReference*)(&V_2));
		uint8_t* L_6;
		L_6 = il2cpp_unsafe_as_ref<uint8_t>(L_5);
		uint8_t* L_7;
		L_7 = il2cpp_unsafe_as_ref<uint8_t>((&V_1));
		int32_t L_8 = *((uint8_t*)L_7);
		uint32_t L_9 = V_0;
		Unsafe_InitBlockUnaligned_m6F2353EB9ABC9320E61629FAEE23948C80BFF03A(L_6, (uint8_t)L_8, L_9, NULL);
		return;
	}

IL_0037:
	{
		int32_t L_10 = __this->____length;
		V_3 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_10));
		uint64_t L_11 = V_3;
		if (L_11)
		{
			goto IL_0043;
		}
	}
	{
		return;
	}

IL_0043:
	{
		ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 L_12 = __this->____pointer;
		V_2 = L_12;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_13;
		L_13 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D, (Il2CppByReference*)(&V_2));
		V_4 = L_13;
		int32_t L_14;
		L_14 = il2cpp_unsafe_sizeof<BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D>();
		V_5 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_14));
		V_6 = (uint64_t)((int64_t)0);
		goto IL_0110;
	}

IL_0064:
	{
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_15 = V_4;
		uint64_t L_16 = V_6;
		uint64_t L_17 = V_5;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_18;
		L_18 = il2cpp_unsafe_add_byte_offset<BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D,uint64_t>(L_15, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_16, (int64_t)L_17)));
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D L_19 = ___0_value;
		*(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D*)L_18 = L_19;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_20 = V_4;
		uint64_t L_21 = V_6;
		uint64_t L_22 = V_5;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_23;
		L_23 = il2cpp_unsafe_add_byte_offset<BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D,uint64_t>(L_20, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_21, ((int64_t)1))), (int64_t)L_22)));
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D L_24 = ___0_value;
		*(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D*)L_23 = L_24;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_25 = V_4;
		uint64_t L_26 = V_6;
		uint64_t L_27 = V_5;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_28;
		L_28 = il2cpp_unsafe_add_byte_offset<BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D,uint64_t>(L_25, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_26, ((int64_t)2))), (int64_t)L_27)));
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D L_29 = ___0_value;
		*(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D*)L_28 = L_29;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_30 = V_4;
		uint64_t L_31 = V_6;
		uint64_t L_32 = V_5;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_33;
		L_33 = il2cpp_unsafe_add_byte_offset<BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D,uint64_t>(L_30, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_31, ((int64_t)3))), (int64_t)L_32)));
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D L_34 = ___0_value;
		*(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D*)L_33 = L_34;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_35 = V_4;
		uint64_t L_36 = V_6;
		uint64_t L_37 = V_5;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_38;
		L_38 = il2cpp_unsafe_add_byte_offset<BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D,uint64_t>(L_35, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_36, ((int64_t)4))), (int64_t)L_37)));
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D L_39 = ___0_value;
		*(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D*)L_38 = L_39;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_40 = V_4;
		uint64_t L_41 = V_6;
		uint64_t L_42 = V_5;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_43;
		L_43 = il2cpp_unsafe_add_byte_offset<BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D,uint64_t>(L_40, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_41, ((int64_t)5))), (int64_t)L_42)));
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D L_44 = ___0_value;
		*(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D*)L_43 = L_44;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_45 = V_4;
		uint64_t L_46 = V_6;
		uint64_t L_47 = V_5;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_48;
		L_48 = il2cpp_unsafe_add_byte_offset<BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D,uint64_t>(L_45, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_46, ((int64_t)6))), (int64_t)L_47)));
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D L_49 = ___0_value;
		*(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D*)L_48 = L_49;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_50 = V_4;
		uint64_t L_51 = V_6;
		uint64_t L_52 = V_5;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_53;
		L_53 = il2cpp_unsafe_add_byte_offset<BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D,uint64_t>(L_50, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_51, ((int64_t)7))), (int64_t)L_52)));
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D L_54 = ___0_value;
		*(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D*)L_53 = L_54;
		uint64_t L_55 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_55, ((int64_t)8)));
	}

IL_0110:
	{
		uint64_t L_56 = V_6;
		uint64_t L_57 = V_3;
		if ((!(((uint64_t)L_56) >= ((uint64_t)((int64_t)((int64_t)L_57&((int64_t)((int32_t)-8))))))))
		{
			goto IL_0064;
		}
	}
	{
		uint64_t L_58 = V_6;
		uint64_t L_59 = V_3;
		if ((!(((uint64_t)L_58) < ((uint64_t)((int64_t)((int64_t)L_59&((int64_t)((int32_t)-4))))))))
		{
			goto IL_0198;
		}
	}
	{
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_60 = V_4;
		uint64_t L_61 = V_6;
		uint64_t L_62 = V_5;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_63;
		L_63 = il2cpp_unsafe_add_byte_offset<BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D,uint64_t>(L_60, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_61, (int64_t)L_62)));
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D L_64 = ___0_value;
		*(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D*)L_63 = L_64;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_65 = V_4;
		uint64_t L_66 = V_6;
		uint64_t L_67 = V_5;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_68;
		L_68 = il2cpp_unsafe_add_byte_offset<BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D,uint64_t>(L_65, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_66, ((int64_t)1))), (int64_t)L_67)));
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D L_69 = ___0_value;
		*(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D*)L_68 = L_69;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_70 = V_4;
		uint64_t L_71 = V_6;
		uint64_t L_72 = V_5;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_73;
		L_73 = il2cpp_unsafe_add_byte_offset<BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D,uint64_t>(L_70, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_71, ((int64_t)2))), (int64_t)L_72)));
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D L_74 = ___0_value;
		*(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D*)L_73 = L_74;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_75 = V_4;
		uint64_t L_76 = V_6;
		uint64_t L_77 = V_5;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_78;
		L_78 = il2cpp_unsafe_add_byte_offset<BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D,uint64_t>(L_75, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_76, ((int64_t)3))), (int64_t)L_77)));
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D L_79 = ___0_value;
		*(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D*)L_78 = L_79;
		uint64_t L_80 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_80, ((int64_t)4)));
		goto IL_0198;
	}

IL_017f:
	{
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_81 = V_4;
		uint64_t L_82 = V_6;
		uint64_t L_83 = V_5;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_84;
		L_84 = il2cpp_unsafe_add_byte_offset<BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D,uint64_t>(L_81, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_82, (int64_t)L_83)));
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D L_85 = ___0_value;
		*(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D*)L_84 = L_85;
		uint64_t L_86 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_86, ((int64_t)1)));
	}

IL_0198:
	{
		uint64_t L_87 = V_6;
		uint64_t L_88 = V_3;
		if ((!(((uint64_t)L_87) >= ((uint64_t)L_88))))
		{
			goto IL_017f;
		}
	}
	{
		return;
	}
}
// Method Definition Index: 13825
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_CopyTo_mDCB97095A5B438EEDD243FF0924E8AFE4D0F040F_gshared (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428* __this, Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428 ___0_destination, const RuntimeMethod* method) 
{
	ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_m739A39BB426B19B7583557F308B2BDA52E2C3DD9_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0038;
		}
	}
	{
		Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428 L_2 = ___0_destination;
		ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 L_3 = L_2.____pointer;
		V_0 = L_3;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D, (Il2CppByReference*)(&V_0));
		ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 L_5 = __this->____pointer;
		V_0 = L_5;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D, (Il2CppByReference*)(&V_0));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D_m9074142CF2F0828232AFEBB926F490D33E285B9B(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return;
	}

IL_0038:
	{
		ThrowHelper_ThrowArgumentException_DestinationTooShort_m6468934A3BBB67DBC5BAEF7A64D91BD5BBBB3D4D(NULL);
		return;
	}
}
// Method Definition Index: 13826
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_TryCopyTo_mE4C5E1C41D72D994C9A4EAA1714F82F57ACB3D1D_gshared (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428* __this, Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428 ___0_destination, const RuntimeMethod* method) 
{
	bool V_0 = false;
	ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		V_0 = (bool)0;
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_m739A39BB426B19B7583557F308B2BDA52E2C3DD9_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_003b;
		}
	}
	{
		Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428 L_2 = ___0_destination;
		ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 L_3 = L_2.____pointer;
		V_1 = L_3;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D, (Il2CppByReference*)(&V_1));
		ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 L_5 = __this->____pointer;
		V_1 = L_5;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D, (Il2CppByReference*)(&V_1));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D_m9074142CF2F0828232AFEBB926F490D33E285B9B(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		V_0 = (bool)1;
	}

IL_003b:
	{
		bool L_8 = V_0;
		return L_8;
	}
}
// Method Definition Index: 13827
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_op_Equality_m49ACB9C0BC088C107A830ACBE5BD3DEAA66F52E3_gshared (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428 ___0_left, Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428 ___1_right, const RuntimeMethod* method) 
{
	ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428 L_0 = ___0_left;
		int32_t L_1 = L_0.____length;
		Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428 L_2 = ___1_right;
		int32_t L_3 = L_2.____length;
		if ((!(((uint32_t)L_1) == ((uint32_t)L_3))))
		{
			goto IL_0030;
		}
	}
	{
		Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428 L_4 = ___0_left;
		ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 L_5 = L_4.____pointer;
		V_0 = L_5;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D, (Il2CppByReference*)(&V_0));
		Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428 L_7 = ___1_right;
		ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 L_8 = L_7.____pointer;
		V_0 = L_8;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_9;
		L_9 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D, (Il2CppByReference*)(&V_0));
		bool L_10;
		L_10 = il2cpp_unsafe_are_same(L_6, L_9);
		return L_10;
	}

IL_0030:
	{
		return (bool)0;
	}
}
// Method Definition Index: 13828
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ReadOnlySpan_1_tE73282FE39C46EDD2EE2F61A9E3E69BE943784B7 Span_1_op_Implicit_m55793F89576C91F51F84FEE419581F8B4B5F38C5_gshared (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428 ___0_span, const RuntimeMethod* method) 
{
	ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428 L_0 = ___0_span;
		ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 L_1 = L_0.____pointer;
		V_0 = L_1;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_2;
		L_2 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D, (Il2CppByReference*)(&V_0));
		Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428 L_3 = ___0_span;
		int32_t L_4 = L_3.____length;
		ReadOnlySpan_1_tE73282FE39C46EDD2EE2F61A9E3E69BE943784B7 L_5;
		memset((&L_5), 0, sizeof(L_5));
		ReadOnlySpan_1__ctor_m729E93C65964D6955F3A0794056B0A8FA3E1FB44_inline((&L_5), L_2, L_4, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 18));
		return L_5;
	}
}
// Method Definition Index: 13829
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Span_1_ToString_m6103F745B1C7B9A9BD3A3116AF5F988065C25AF4_gshared (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral0DB46164953228904843938099AF66650313FEE5);
		s_Il2CppMethodInitialized = true;
	}
	Il2CppChar* V_0 = NULL;
	ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_0 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_1;
		L_1 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_0, NULL);
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_2 = { reinterpret_cast<intptr_t> (&il2cpp_defaults.char_class->byval_arg) };
		Type_t* L_3;
		L_3 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_2, NULL);
		bool L_4;
		L_4 = Type_op_Equality_m99930A0E44E420A685FABA60E60BA1CC5FA0EBDC(L_1, L_3, NULL);
		if (!L_4)
		{
			goto IL_003e;
		}
	}
	{
		ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 L_5 = __this->____pointer;
		V_1 = L_5;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D, (Il2CppByReference*)(&V_1));
		Il2CppChar* L_7;
		L_7 = il2cpp_unsafe_as_ref<Il2CppChar>(L_6);
		V_0 = L_7;
		Il2CppChar* L_8 = V_0;
		int32_t L_9 = __this->____length;
		String_t* L_10;
		L_10 = String_CreateString_m3F8794FEB452558B8A68C65E1F0B603B3D94E0E2(NULL, (Il2CppChar*)((uintptr_t)L_8), 0, L_9, NULL);
		return L_10;
	}

IL_003e:
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_11 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_12;
		L_12 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_11, NULL);
		NullCheck((MemberInfo_t*)L_12);
		String_t* L_13;
		L_13 = VirtualFuncInvoker0< String_t* >::Invoke(7, (MemberInfo_t*)L_12);
		int32_t L_14 = __this->____length;
		int32_t L_15 = L_14;
		RuntimeObject* L_16 = Box(il2cpp_defaults.int32_class, &L_15);
		String_t* L_17;
		L_17 = String_Format_mFB7DA489BD99F4670881FF50EC017BFB0A5C0987(_stringLiteral0DB46164953228904843938099AF66650313FEE5, (RuntimeObject*)L_13, L_16, NULL);
		return L_17;
	}
}
// Method Definition Index: 13830
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428 Span_1_Slice_m755E9ED71968348BB9C4E8463E30DE839E3FEFD0_gshared (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428* __this, int32_t ___0_start, const RuntimeMethod* method) 
{
	ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) > ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 L_2 = __this->____pointer;
		V_0 = L_2;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_start;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_5;
		L_5 = il2cpp_unsafe_add<BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D,int32_t>(L_3, L_4);
		int32_t L_6 = __this->____length;
		int32_t L_7 = ___0_start;
		Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428 L_8;
		memset((&L_8), 0, sizeof(L_8));
		Span_1__ctor_mB2713B2424259A6D18038A2E29BD96DA081378D6_inline((&L_8), L_5, ((int32_t)il2cpp_codegen_subtract(L_6, L_7)), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_8;
	}
}
// Method Definition Index: 13831
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428 Span_1_Slice_mA98F6A471377545511DC5E22FD4DF75F4E10F707_gshared (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428* __this, int32_t ___0_start, int32_t ___1_length, const RuntimeMethod* method) 
{
	ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0014;
		}
	}
	{
		int32_t L_2 = ___1_length;
		int32_t L_3 = __this->____length;
		int32_t L_4 = ___0_start;
		if ((!(((uint32_t)L_2) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(L_3, L_4))))))
		{
			goto IL_0019;
		}
	}

IL_0014:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0019:
	{
		ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 L_5 = __this->____pointer;
		V_0 = L_5;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D, (Il2CppByReference*)(&V_0));
		int32_t L_7 = ___0_start;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_8;
		L_8 = il2cpp_unsafe_add<BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D,int32_t>(L_6, L_7);
		int32_t L_9 = ___1_length;
		Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428 L_10;
		memset((&L_10), 0, sizeof(L_10));
		Span_1__ctor_mB2713B2424259A6D18038A2E29BD96DA081378D6_inline((&L_10), L_8, L_9, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_10;
	}
}
// Method Definition Index: 13832
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80* Span_1_ToArray_m33F9F235649E5BECFA4795C77789731BF922CFC7_gshared (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428* __this, const RuntimeMethod* method) 
{
	ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_000e;
		}
	}
	{
		BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80* L_1;
		L_1 = Array_Empty_TisBatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D_m652D1F78F0DEF31A60A1D09589A89DE06F12C3E4_inline(il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 20));
		return L_1;
	}

IL_000e:
	{
		int32_t L_2 = __this->____length;
		BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80* L_3 = (BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80*)(BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80*)SZArrayNew(il2cpp_rgctx_data(InitializedTypeInfo(method->klass)->rgctx_data, 21), (uint32_t)L_2);
		BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80* L_4 = L_3;
		NullCheck((RuntimeArray*)L_4);
		uint8_t* L_5;
		L_5 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_4, NULL);
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_6;
		L_6 = il2cpp_unsafe_as_ref<BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D>(L_5);
		ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 L_7 = __this->____pointer;
		V_0 = L_7;
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_8;
		L_8 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D, (Il2CppByReference*)(&V_0));
		int32_t L_9 = __this->____length;
		Buffer_Memmove_TisBatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D_m9074142CF2F0828232AFEBB926F490D33E285B9B(L_6, L_8, (uint64_t)((int64_t)L_9), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return L_4;
	}
}
// Method Definition Index: 13833
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m739A39BB426B19B7583557F308B2BDA52E2C3DD9_gshared (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13834
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_Equals_m31F00188DB58D0A6AC422C1110FE121512B83A8B_gshared (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteral69508A540AFD085A745316DD7D6345B1C8CC662D)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13835
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_GetHashCode_mB00CEDEC6497D53AB78D6B46111F2695915CF97A_gshared (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428* __this, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteralECE618215BAC99C6FD12D8A273CC2118945EDCC8)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13836
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428 Span_1_op_Implicit_m94CCC4AEF130B0783BEB1BFB5ACB39512FB9EF03_gshared (BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80* ___0_array, const RuntimeMethod* method) 
{
	{
		BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80* L_0 = ___0_array;
		Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428 L_1;
		memset((&L_1), 0, sizeof(L_1));
		Span_1__ctor_m7126F92024E73EE43580A7A9D64C39CD973406CC_inline((&L_1), L_0, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 22));
		return L_1;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// Method Definition Index: 13818
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mF34E7D8B1563ED8C47501C1BF37B17509D397F30_gshared (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4* __this, BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963* ___0_array, const RuntimeMethod* method) 
{
	BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770));
		goto IL_0037;
	}

IL_0037:
	{
		BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_4;
		L_4 = il2cpp_unsafe_as_ref<BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770>(L_3);
		ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13819
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m643DEDF7C1850BD7B7709CD86FDCA047AF0EB8EC_gshared (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4* __this, BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963* ___0_array, int32_t ___1_start, int32_t ___2_length, const RuntimeMethod* method) 
{
	BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_0016;
		}
	}
	{
		int32_t L_1 = ___1_start;
		if (L_1)
		{
			goto IL_0009;
		}
	}
	{
		int32_t L_2 = ___2_length;
		if (!L_2)
		{
			goto IL_000e;
		}
	}

IL_0009:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4));
		return;
	}

IL_0016:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770));
		goto IL_0042;
	}

IL_0042:
	{
		int32_t L_4 = ___1_start;
		BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963* L_5 = ___0_array;
		NullCheck(L_5);
		if ((!(((uint32_t)L_4) <= ((uint32_t)((int32_t)(((RuntimeArray*)L_5)->max_length))))))
		{
			goto IL_0050;
		}
	}
	{
		int32_t L_6 = ___2_length;
		BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963* L_7 = ___0_array;
		NullCheck(L_7);
		int32_t L_8 = ___1_start;
		if ((!(((uint32_t)L_6) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(((int32_t)(((RuntimeArray*)L_7)->max_length)), L_8))))))
		{
			goto IL_0055;
		}
	}

IL_0050:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0055:
	{
		BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963* L_9 = ___0_array;
		NullCheck((RuntimeArray*)L_9);
		uint8_t* L_10;
		L_10 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_9, NULL);
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_11;
		L_11 = il2cpp_unsafe_as_ref<BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770>(L_10);
		int32_t L_12 = ___1_start;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_13;
		L_13 = il2cpp_unsafe_add<BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770,int32_t>(L_11, L_12);
		ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E L_14;
		memset((&L_14), 0, sizeof(L_14));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_14), L_13);
		__this->____pointer = L_14;
		int32_t L_15 = ___2_length;
		__this->____length = L_15;
		return;
	}
}
// Method Definition Index: 13820
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m9FE72D8E9D60D129E0A6FE327E9982DE56C0EB26_gshared (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4* __this, void* ___0_pointer, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		goto IL_0016;
	}

IL_0016:
	{
		int32_t L_0 = ___1_length;
		if ((((int32_t)L_0) >= ((int32_t)0)))
		{
			goto IL_001f;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_001f:
	{
		void* L_1 = ___0_pointer;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_2;
		L_2 = il2cpp_unsafe_as_ref<BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770>((uint8_t*)L_1);
		ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E L_3;
		memset((&L_3), 0, sizeof(L_3));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_3), L_2);
		__this->____pointer = L_3;
		int32_t L_4 = ___1_length;
		__this->____length = L_4;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m38FB4416138CB97C429799865EA7C7156E442FCD_gshared (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4* __this, BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_0 = ___0_ptr;
		ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13822
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* Span_1_get_Item_m50103BBDD928B3AA67ED7CCC72A83249EAF08B5D_gshared (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4* __this, int32_t ___0_index, const RuntimeMethod* method) 
{
	ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_index;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) >= ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowIndexOutOfRangeException_m86F753A24E2765A35546BA6352A7E4F0BB8A66B5(NULL);
	}

IL_000e:
	{
		ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E L_2 = __this->____pointer;
		V_0 = L_2;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_index;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_5;
		L_5 = il2cpp_unsafe_add<BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770,int32_t>(L_3, L_4);
		return L_5;
	}
}
// Method Definition Index: 13823
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* Span_1_GetPinnableReference_m618BD5667BC972A35A1E636A8C09814F0E5B11EA_gshared (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4* __this, const RuntimeMethod* method) 
{
	ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_0010;
		}
	}
	{
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_1;
		L_1 = il2cpp_unsafe_as_ref<BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770>((void*)((uintptr_t)0));
		return L_1;
	}

IL_0010:
	{
		ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E L_2 = __this->____pointer;
		V_0 = L_2;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770, (Il2CppByReference*)(&V_0));
		return L_3;
	}
}
// Method Definition Index: 13824
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_Fill_m18B377868FBADF198A021DBC5F4E7077A7DC9EFE_gshared (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4* __this, BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770 ___0_value, const RuntimeMethod* method) 
{
	uint32_t V_0 = 0;
	BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770 V_1;
	memset((&V_1), 0, sizeof(V_1));
	ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E V_2;
	memset((&V_2), 0, sizeof(V_2));
	uint64_t V_3 = 0;
	BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* V_4 = NULL;
	uint64_t V_5 = 0;
	uint64_t V_6 = 0;
	{
		int32_t L_0;
		L_0 = il2cpp_unsafe_sizeof<BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770>();
		if ((!(((uint32_t)L_0) == ((uint32_t)1))))
		{
			goto IL_0037;
		}
	}
	{
		int32_t L_1 = __this->____length;
		V_0 = (uint32_t)L_1;
		uint32_t L_2 = V_0;
		if (L_2)
		{
			goto IL_0013;
		}
	}
	{
		return;
	}

IL_0013:
	{
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770 L_3 = ___0_value;
		V_1 = L_3;
		ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E L_4 = __this->____pointer;
		V_2 = L_4;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_5;
		L_5 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770, (Il2CppByReference*)(&V_2));
		uint8_t* L_6;
		L_6 = il2cpp_unsafe_as_ref<uint8_t>(L_5);
		uint8_t* L_7;
		L_7 = il2cpp_unsafe_as_ref<uint8_t>((&V_1));
		int32_t L_8 = *((uint8_t*)L_7);
		uint32_t L_9 = V_0;
		Unsafe_InitBlockUnaligned_m6F2353EB9ABC9320E61629FAEE23948C80BFF03A(L_6, (uint8_t)L_8, L_9, NULL);
		return;
	}

IL_0037:
	{
		int32_t L_10 = __this->____length;
		V_3 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_10));
		uint64_t L_11 = V_3;
		if (L_11)
		{
			goto IL_0043;
		}
	}
	{
		return;
	}

IL_0043:
	{
		ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E L_12 = __this->____pointer;
		V_2 = L_12;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_13;
		L_13 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770, (Il2CppByReference*)(&V_2));
		V_4 = L_13;
		int32_t L_14;
		L_14 = il2cpp_unsafe_sizeof<BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770>();
		V_5 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_14));
		V_6 = (uint64_t)((int64_t)0);
		goto IL_0110;
	}

IL_0064:
	{
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_15 = V_4;
		uint64_t L_16 = V_6;
		uint64_t L_17 = V_5;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_18;
		L_18 = il2cpp_unsafe_add_byte_offset<BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770,uint64_t>(L_15, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_16, (int64_t)L_17)));
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770 L_19 = ___0_value;
		*(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770*)L_18 = L_19;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_20 = V_4;
		uint64_t L_21 = V_6;
		uint64_t L_22 = V_5;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_23;
		L_23 = il2cpp_unsafe_add_byte_offset<BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770,uint64_t>(L_20, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_21, ((int64_t)1))), (int64_t)L_22)));
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770 L_24 = ___0_value;
		*(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770*)L_23 = L_24;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_25 = V_4;
		uint64_t L_26 = V_6;
		uint64_t L_27 = V_5;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_28;
		L_28 = il2cpp_unsafe_add_byte_offset<BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770,uint64_t>(L_25, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_26, ((int64_t)2))), (int64_t)L_27)));
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770 L_29 = ___0_value;
		*(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770*)L_28 = L_29;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_30 = V_4;
		uint64_t L_31 = V_6;
		uint64_t L_32 = V_5;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_33;
		L_33 = il2cpp_unsafe_add_byte_offset<BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770,uint64_t>(L_30, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_31, ((int64_t)3))), (int64_t)L_32)));
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770 L_34 = ___0_value;
		*(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770*)L_33 = L_34;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_35 = V_4;
		uint64_t L_36 = V_6;
		uint64_t L_37 = V_5;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_38;
		L_38 = il2cpp_unsafe_add_byte_offset<BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770,uint64_t>(L_35, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_36, ((int64_t)4))), (int64_t)L_37)));
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770 L_39 = ___0_value;
		*(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770*)L_38 = L_39;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_40 = V_4;
		uint64_t L_41 = V_6;
		uint64_t L_42 = V_5;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_43;
		L_43 = il2cpp_unsafe_add_byte_offset<BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770,uint64_t>(L_40, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_41, ((int64_t)5))), (int64_t)L_42)));
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770 L_44 = ___0_value;
		*(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770*)L_43 = L_44;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_45 = V_4;
		uint64_t L_46 = V_6;
		uint64_t L_47 = V_5;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_48;
		L_48 = il2cpp_unsafe_add_byte_offset<BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770,uint64_t>(L_45, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_46, ((int64_t)6))), (int64_t)L_47)));
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770 L_49 = ___0_value;
		*(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770*)L_48 = L_49;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_50 = V_4;
		uint64_t L_51 = V_6;
		uint64_t L_52 = V_5;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_53;
		L_53 = il2cpp_unsafe_add_byte_offset<BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770,uint64_t>(L_50, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_51, ((int64_t)7))), (int64_t)L_52)));
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770 L_54 = ___0_value;
		*(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770*)L_53 = L_54;
		uint64_t L_55 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_55, ((int64_t)8)));
	}

IL_0110:
	{
		uint64_t L_56 = V_6;
		uint64_t L_57 = V_3;
		if ((!(((uint64_t)L_56) >= ((uint64_t)((int64_t)((int64_t)L_57&((int64_t)((int32_t)-8))))))))
		{
			goto IL_0064;
		}
	}
	{
		uint64_t L_58 = V_6;
		uint64_t L_59 = V_3;
		if ((!(((uint64_t)L_58) < ((uint64_t)((int64_t)((int64_t)L_59&((int64_t)((int32_t)-4))))))))
		{
			goto IL_0198;
		}
	}
	{
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_60 = V_4;
		uint64_t L_61 = V_6;
		uint64_t L_62 = V_5;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_63;
		L_63 = il2cpp_unsafe_add_byte_offset<BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770,uint64_t>(L_60, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_61, (int64_t)L_62)));
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770 L_64 = ___0_value;
		*(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770*)L_63 = L_64;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_65 = V_4;
		uint64_t L_66 = V_6;
		uint64_t L_67 = V_5;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_68;
		L_68 = il2cpp_unsafe_add_byte_offset<BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770,uint64_t>(L_65, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_66, ((int64_t)1))), (int64_t)L_67)));
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770 L_69 = ___0_value;
		*(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770*)L_68 = L_69;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_70 = V_4;
		uint64_t L_71 = V_6;
		uint64_t L_72 = V_5;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_73;
		L_73 = il2cpp_unsafe_add_byte_offset<BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770,uint64_t>(L_70, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_71, ((int64_t)2))), (int64_t)L_72)));
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770 L_74 = ___0_value;
		*(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770*)L_73 = L_74;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_75 = V_4;
		uint64_t L_76 = V_6;
		uint64_t L_77 = V_5;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_78;
		L_78 = il2cpp_unsafe_add_byte_offset<BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770,uint64_t>(L_75, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_76, ((int64_t)3))), (int64_t)L_77)));
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770 L_79 = ___0_value;
		*(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770*)L_78 = L_79;
		uint64_t L_80 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_80, ((int64_t)4)));
		goto IL_0198;
	}

IL_017f:
	{
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_81 = V_4;
		uint64_t L_82 = V_6;
		uint64_t L_83 = V_5;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_84;
		L_84 = il2cpp_unsafe_add_byte_offset<BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770,uint64_t>(L_81, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_82, (int64_t)L_83)));
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770 L_85 = ___0_value;
		*(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770*)L_84 = L_85;
		uint64_t L_86 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_86, ((int64_t)1)));
	}

IL_0198:
	{
		uint64_t L_87 = V_6;
		uint64_t L_88 = V_3;
		if ((!(((uint64_t)L_87) >= ((uint64_t)L_88))))
		{
			goto IL_017f;
		}
	}
	{
		return;
	}
}
// Method Definition Index: 13825
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_CopyTo_m974958F6A925A0B58E89026DD2B7401C0D61E705_gshared (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4* __this, Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4 ___0_destination, const RuntimeMethod* method) 
{
	ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_m9535293C1FDA720739072391EDE6F574C67C905D_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0038;
		}
	}
	{
		Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4 L_2 = ___0_destination;
		ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E L_3 = L_2.____pointer;
		V_0 = L_3;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770, (Il2CppByReference*)(&V_0));
		ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E L_5 = __this->____pointer;
		V_0 = L_5;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770, (Il2CppByReference*)(&V_0));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770_m30599D9EEC5CBA92A026E19AFB3510603F2595EB(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return;
	}

IL_0038:
	{
		ThrowHelper_ThrowArgumentException_DestinationTooShort_m6468934A3BBB67DBC5BAEF7A64D91BD5BBBB3D4D(NULL);
		return;
	}
}
// Method Definition Index: 13826
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_TryCopyTo_m90F7C7B5CBD5024941C66EAAE021B1EBAE6EC3F5_gshared (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4* __this, Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4 ___0_destination, const RuntimeMethod* method) 
{
	bool V_0 = false;
	ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		V_0 = (bool)0;
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_m9535293C1FDA720739072391EDE6F574C67C905D_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_003b;
		}
	}
	{
		Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4 L_2 = ___0_destination;
		ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E L_3 = L_2.____pointer;
		V_1 = L_3;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770, (Il2CppByReference*)(&V_1));
		ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E L_5 = __this->____pointer;
		V_1 = L_5;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770, (Il2CppByReference*)(&V_1));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770_m30599D9EEC5CBA92A026E19AFB3510603F2595EB(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		V_0 = (bool)1;
	}

IL_003b:
	{
		bool L_8 = V_0;
		return L_8;
	}
}
// Method Definition Index: 13827
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_op_Equality_m96A8A3FC1E4A3EECA71BCA63CC470A3973BC462C_gshared (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4 ___0_left, Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4 ___1_right, const RuntimeMethod* method) 
{
	ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4 L_0 = ___0_left;
		int32_t L_1 = L_0.____length;
		Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4 L_2 = ___1_right;
		int32_t L_3 = L_2.____length;
		if ((!(((uint32_t)L_1) == ((uint32_t)L_3))))
		{
			goto IL_0030;
		}
	}
	{
		Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4 L_4 = ___0_left;
		ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E L_5 = L_4.____pointer;
		V_0 = L_5;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770, (Il2CppByReference*)(&V_0));
		Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4 L_7 = ___1_right;
		ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E L_8 = L_7.____pointer;
		V_0 = L_8;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_9;
		L_9 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770, (Il2CppByReference*)(&V_0));
		bool L_10;
		L_10 = il2cpp_unsafe_are_same(L_6, L_9);
		return L_10;
	}

IL_0030:
	{
		return (bool)0;
	}
}
// Method Definition Index: 13828
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ReadOnlySpan_1_t3D56DFE8743E7EF0665F38A59DA5B99E0F9425B0 Span_1_op_Implicit_m998DA71FEF5D20CC628DFFAE89B9ACBBBFB76764_gshared (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4 ___0_span, const RuntimeMethod* method) 
{
	ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4 L_0 = ___0_span;
		ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E L_1 = L_0.____pointer;
		V_0 = L_1;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_2;
		L_2 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770, (Il2CppByReference*)(&V_0));
		Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4 L_3 = ___0_span;
		int32_t L_4 = L_3.____length;
		ReadOnlySpan_1_t3D56DFE8743E7EF0665F38A59DA5B99E0F9425B0 L_5;
		memset((&L_5), 0, sizeof(L_5));
		ReadOnlySpan_1__ctor_m0D6A1AA77CFA40D8A9849AA4338A5A43DC6CF0C0_inline((&L_5), L_2, L_4, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 18));
		return L_5;
	}
}
// Method Definition Index: 13829
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Span_1_ToString_mCB736D7E52FAE49C4FAB8E0FECABF824E901AEF3_gshared (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral0DB46164953228904843938099AF66650313FEE5);
		s_Il2CppMethodInitialized = true;
	}
	Il2CppChar* V_0 = NULL;
	ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_0 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_1;
		L_1 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_0, NULL);
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_2 = { reinterpret_cast<intptr_t> (&il2cpp_defaults.char_class->byval_arg) };
		Type_t* L_3;
		L_3 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_2, NULL);
		bool L_4;
		L_4 = Type_op_Equality_m99930A0E44E420A685FABA60E60BA1CC5FA0EBDC(L_1, L_3, NULL);
		if (!L_4)
		{
			goto IL_003e;
		}
	}
	{
		ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E L_5 = __this->____pointer;
		V_1 = L_5;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770, (Il2CppByReference*)(&V_1));
		Il2CppChar* L_7;
		L_7 = il2cpp_unsafe_as_ref<Il2CppChar>(L_6);
		V_0 = L_7;
		Il2CppChar* L_8 = V_0;
		int32_t L_9 = __this->____length;
		String_t* L_10;
		L_10 = String_CreateString_m3F8794FEB452558B8A68C65E1F0B603B3D94E0E2(NULL, (Il2CppChar*)((uintptr_t)L_8), 0, L_9, NULL);
		return L_10;
	}

IL_003e:
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_11 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_12;
		L_12 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_11, NULL);
		NullCheck((MemberInfo_t*)L_12);
		String_t* L_13;
		L_13 = VirtualFuncInvoker0< String_t* >::Invoke(7, (MemberInfo_t*)L_12);
		int32_t L_14 = __this->____length;
		int32_t L_15 = L_14;
		RuntimeObject* L_16 = Box(il2cpp_defaults.int32_class, &L_15);
		String_t* L_17;
		L_17 = String_Format_mFB7DA489BD99F4670881FF50EC017BFB0A5C0987(_stringLiteral0DB46164953228904843938099AF66650313FEE5, (RuntimeObject*)L_13, L_16, NULL);
		return L_17;
	}
}
// Method Definition Index: 13830
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4 Span_1_Slice_m0780DE7E4024140DAE2C0CD4A42C32A8222B7031_gshared (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4* __this, int32_t ___0_start, const RuntimeMethod* method) 
{
	ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) > ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E L_2 = __this->____pointer;
		V_0 = L_2;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_start;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_5;
		L_5 = il2cpp_unsafe_add<BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770,int32_t>(L_3, L_4);
		int32_t L_6 = __this->____length;
		int32_t L_7 = ___0_start;
		Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4 L_8;
		memset((&L_8), 0, sizeof(L_8));
		Span_1__ctor_m38FB4416138CB97C429799865EA7C7156E442FCD_inline((&L_8), L_5, ((int32_t)il2cpp_codegen_subtract(L_6, L_7)), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_8;
	}
}
// Method Definition Index: 13831
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4 Span_1_Slice_m87CA7394D393469402EDA1D111AC529837CE3903_gshared (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4* __this, int32_t ___0_start, int32_t ___1_length, const RuntimeMethod* method) 
{
	ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0014;
		}
	}
	{
		int32_t L_2 = ___1_length;
		int32_t L_3 = __this->____length;
		int32_t L_4 = ___0_start;
		if ((!(((uint32_t)L_2) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(L_3, L_4))))))
		{
			goto IL_0019;
		}
	}

IL_0014:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0019:
	{
		ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E L_5 = __this->____pointer;
		V_0 = L_5;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770, (Il2CppByReference*)(&V_0));
		int32_t L_7 = ___0_start;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_8;
		L_8 = il2cpp_unsafe_add<BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770,int32_t>(L_6, L_7);
		int32_t L_9 = ___1_length;
		Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4 L_10;
		memset((&L_10), 0, sizeof(L_10));
		Span_1__ctor_m38FB4416138CB97C429799865EA7C7156E442FCD_inline((&L_10), L_8, L_9, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_10;
	}
}
// Method Definition Index: 13832
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963* Span_1_ToArray_mF60FADA35286C6A1B9378D426FE8944FF128555C_gshared (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4* __this, const RuntimeMethod* method) 
{
	ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_000e;
		}
	}
	{
		BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963* L_1;
		L_1 = Array_Empty_TisBatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770_mEFC02ED7475540CF71CD66D177FEDA1EA4155C01_inline(il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 20));
		return L_1;
	}

IL_000e:
	{
		int32_t L_2 = __this->____length;
		BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963* L_3 = (BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963*)(BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963*)SZArrayNew(il2cpp_rgctx_data(InitializedTypeInfo(method->klass)->rgctx_data, 21), (uint32_t)L_2);
		BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963* L_4 = L_3;
		NullCheck((RuntimeArray*)L_4);
		uint8_t* L_5;
		L_5 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_4, NULL);
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_6;
		L_6 = il2cpp_unsafe_as_ref<BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770>(L_5);
		ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E L_7 = __this->____pointer;
		V_0 = L_7;
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_8;
		L_8 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770, (Il2CppByReference*)(&V_0));
		int32_t L_9 = __this->____length;
		Buffer_Memmove_TisBatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770_m30599D9EEC5CBA92A026E19AFB3510603F2595EB(L_6, L_8, (uint64_t)((int64_t)L_9), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return L_4;
	}
}
// Method Definition Index: 13833
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m9535293C1FDA720739072391EDE6F574C67C905D_gshared (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13834
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_Equals_m4708194CCFCD7287B5612326754AA38F0AA89752_gshared (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteral69508A540AFD085A745316DD7D6345B1C8CC662D)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13835
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_GetHashCode_m1908469001D8386BBD7BD3C0266340BC2CA378CE_gshared (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4* __this, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteralECE618215BAC99C6FD12D8A273CC2118945EDCC8)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13836
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4 Span_1_op_Implicit_mE10CE8830ECF726171D045F5677893AE3794580D_gshared (BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963* ___0_array, const RuntimeMethod* method) 
{
	{
		BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963* L_0 = ___0_array;
		Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4 L_1;
		memset((&L_1), 0, sizeof(L_1));
		Span_1__ctor_mF34E7D8B1563ED8C47501C1BF37B17509D397F30_inline((&L_1), L_0, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 22));
		return L_1;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// Method Definition Index: 13818
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mCDB221C7D73874741828DA34B70D3B0AADC00136_gshared (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF* __this, BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4* ___0_array, const RuntimeMethod* method) 
{
	BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C));
		goto IL_0037;
	}

IL_0037:
	{
		BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_4;
		L_4 = il2cpp_unsafe_as_ref<BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C>(L_3);
		ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13819
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mDBDA094CF74AD490E55B4111F15EB2C7B27ECCCA_gshared (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF* __this, BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4* ___0_array, int32_t ___1_start, int32_t ___2_length, const RuntimeMethod* method) 
{
	BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_0016;
		}
	}
	{
		int32_t L_1 = ___1_start;
		if (L_1)
		{
			goto IL_0009;
		}
	}
	{
		int32_t L_2 = ___2_length;
		if (!L_2)
		{
			goto IL_000e;
		}
	}

IL_0009:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF));
		return;
	}

IL_0016:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C));
		goto IL_0042;
	}

IL_0042:
	{
		int32_t L_4 = ___1_start;
		BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4* L_5 = ___0_array;
		NullCheck(L_5);
		if ((!(((uint32_t)L_4) <= ((uint32_t)((int32_t)(((RuntimeArray*)L_5)->max_length))))))
		{
			goto IL_0050;
		}
	}
	{
		int32_t L_6 = ___2_length;
		BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4* L_7 = ___0_array;
		NullCheck(L_7);
		int32_t L_8 = ___1_start;
		if ((!(((uint32_t)L_6) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(((int32_t)(((RuntimeArray*)L_7)->max_length)), L_8))))))
		{
			goto IL_0055;
		}
	}

IL_0050:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0055:
	{
		BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4* L_9 = ___0_array;
		NullCheck((RuntimeArray*)L_9);
		uint8_t* L_10;
		L_10 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_9, NULL);
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_11;
		L_11 = il2cpp_unsafe_as_ref<BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C>(L_10);
		int32_t L_12 = ___1_start;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_13;
		L_13 = il2cpp_unsafe_add<BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C,int32_t>(L_11, L_12);
		ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 L_14;
		memset((&L_14), 0, sizeof(L_14));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_14), L_13);
		__this->____pointer = L_14;
		int32_t L_15 = ___2_length;
		__this->____length = L_15;
		return;
	}
}
// Method Definition Index: 13820
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m5920942D03E19FCD04594BB9B2A9C7A2AE3F7072_gshared (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF* __this, void* ___0_pointer, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		goto IL_0016;
	}

IL_0016:
	{
		int32_t L_0 = ___1_length;
		if ((((int32_t)L_0) >= ((int32_t)0)))
		{
			goto IL_001f;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_001f:
	{
		void* L_1 = ___0_pointer;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_2;
		L_2 = il2cpp_unsafe_as_ref<BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C>((uint8_t*)L_1);
		ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 L_3;
		memset((&L_3), 0, sizeof(L_3));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_3), L_2);
		__this->____pointer = L_3;
		int32_t L_4 = ___1_length;
		__this->____length = L_4;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mA2CE8DBE18F6BB54702DAA56720F89DD968A5271_gshared (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF* __this, BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_0 = ___0_ptr;
		ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13822
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* Span_1_get_Item_mDED0D4E05EEFB2F43FB76DAE465EB7F1C3AC2F94_gshared (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF* __this, int32_t ___0_index, const RuntimeMethod* method) 
{
	ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_index;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) >= ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowIndexOutOfRangeException_m86F753A24E2765A35546BA6352A7E4F0BB8A66B5(NULL);
	}

IL_000e:
	{
		ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 L_2 = __this->____pointer;
		V_0 = L_2;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_index;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_5;
		L_5 = il2cpp_unsafe_add<BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C,int32_t>(L_3, L_4);
		return L_5;
	}
}
// Method Definition Index: 13823
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* Span_1_GetPinnableReference_m26C585C677BE79CB1E8719987DAF7703B8666A44_gshared (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF* __this, const RuntimeMethod* method) 
{
	ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_0010;
		}
	}
	{
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_1;
		L_1 = il2cpp_unsafe_as_ref<BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C>((void*)((uintptr_t)0));
		return L_1;
	}

IL_0010:
	{
		ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 L_2 = __this->____pointer;
		V_0 = L_2;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C, (Il2CppByReference*)(&V_0));
		return L_3;
	}
}
// Method Definition Index: 13824
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_Fill_m51B360C07AA8862419A80D4598F0BF42274AFE65_gshared (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF* __this, BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C ___0_value, const RuntimeMethod* method) 
{
	uint32_t V_0 = 0;
	BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C V_1;
	memset((&V_1), 0, sizeof(V_1));
	ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 V_2;
	memset((&V_2), 0, sizeof(V_2));
	uint64_t V_3 = 0;
	BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* V_4 = NULL;
	uint64_t V_5 = 0;
	uint64_t V_6 = 0;
	{
		int32_t L_0;
		L_0 = il2cpp_unsafe_sizeof<BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C>();
		if ((!(((uint32_t)L_0) == ((uint32_t)1))))
		{
			goto IL_0037;
		}
	}
	{
		int32_t L_1 = __this->____length;
		V_0 = (uint32_t)L_1;
		uint32_t L_2 = V_0;
		if (L_2)
		{
			goto IL_0013;
		}
	}
	{
		return;
	}

IL_0013:
	{
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C L_3 = ___0_value;
		V_1 = L_3;
		ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 L_4 = __this->____pointer;
		V_2 = L_4;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_5;
		L_5 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C, (Il2CppByReference*)(&V_2));
		uint8_t* L_6;
		L_6 = il2cpp_unsafe_as_ref<uint8_t>(L_5);
		uint8_t* L_7;
		L_7 = il2cpp_unsafe_as_ref<uint8_t>((&V_1));
		int32_t L_8 = *((uint8_t*)L_7);
		uint32_t L_9 = V_0;
		Unsafe_InitBlockUnaligned_m6F2353EB9ABC9320E61629FAEE23948C80BFF03A(L_6, (uint8_t)L_8, L_9, NULL);
		return;
	}

IL_0037:
	{
		int32_t L_10 = __this->____length;
		V_3 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_10));
		uint64_t L_11 = V_3;
		if (L_11)
		{
			goto IL_0043;
		}
	}
	{
		return;
	}

IL_0043:
	{
		ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 L_12 = __this->____pointer;
		V_2 = L_12;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_13;
		L_13 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C, (Il2CppByReference*)(&V_2));
		V_4 = L_13;
		int32_t L_14;
		L_14 = il2cpp_unsafe_sizeof<BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C>();
		V_5 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_14));
		V_6 = (uint64_t)((int64_t)0);
		goto IL_0110;
	}

IL_0064:
	{
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_15 = V_4;
		uint64_t L_16 = V_6;
		uint64_t L_17 = V_5;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_18;
		L_18 = il2cpp_unsafe_add_byte_offset<BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C,uint64_t>(L_15, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_16, (int64_t)L_17)));
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C L_19 = ___0_value;
		*(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C*)L_18 = L_19;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_20 = V_4;
		uint64_t L_21 = V_6;
		uint64_t L_22 = V_5;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_23;
		L_23 = il2cpp_unsafe_add_byte_offset<BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C,uint64_t>(L_20, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_21, ((int64_t)1))), (int64_t)L_22)));
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C L_24 = ___0_value;
		*(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C*)L_23 = L_24;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_25 = V_4;
		uint64_t L_26 = V_6;
		uint64_t L_27 = V_5;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_28;
		L_28 = il2cpp_unsafe_add_byte_offset<BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C,uint64_t>(L_25, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_26, ((int64_t)2))), (int64_t)L_27)));
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C L_29 = ___0_value;
		*(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C*)L_28 = L_29;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_30 = V_4;
		uint64_t L_31 = V_6;
		uint64_t L_32 = V_5;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_33;
		L_33 = il2cpp_unsafe_add_byte_offset<BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C,uint64_t>(L_30, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_31, ((int64_t)3))), (int64_t)L_32)));
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C L_34 = ___0_value;
		*(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C*)L_33 = L_34;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_35 = V_4;
		uint64_t L_36 = V_6;
		uint64_t L_37 = V_5;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_38;
		L_38 = il2cpp_unsafe_add_byte_offset<BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C,uint64_t>(L_35, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_36, ((int64_t)4))), (int64_t)L_37)));
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C L_39 = ___0_value;
		*(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C*)L_38 = L_39;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_40 = V_4;
		uint64_t L_41 = V_6;
		uint64_t L_42 = V_5;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_43;
		L_43 = il2cpp_unsafe_add_byte_offset<BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C,uint64_t>(L_40, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_41, ((int64_t)5))), (int64_t)L_42)));
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C L_44 = ___0_value;
		*(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C*)L_43 = L_44;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_45 = V_4;
		uint64_t L_46 = V_6;
		uint64_t L_47 = V_5;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_48;
		L_48 = il2cpp_unsafe_add_byte_offset<BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C,uint64_t>(L_45, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_46, ((int64_t)6))), (int64_t)L_47)));
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C L_49 = ___0_value;
		*(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C*)L_48 = L_49;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_50 = V_4;
		uint64_t L_51 = V_6;
		uint64_t L_52 = V_5;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_53;
		L_53 = il2cpp_unsafe_add_byte_offset<BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C,uint64_t>(L_50, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_51, ((int64_t)7))), (int64_t)L_52)));
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C L_54 = ___0_value;
		*(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C*)L_53 = L_54;
		uint64_t L_55 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_55, ((int64_t)8)));
	}

IL_0110:
	{
		uint64_t L_56 = V_6;
		uint64_t L_57 = V_3;
		if ((!(((uint64_t)L_56) >= ((uint64_t)((int64_t)((int64_t)L_57&((int64_t)((int32_t)-8))))))))
		{
			goto IL_0064;
		}
	}
	{
		uint64_t L_58 = V_6;
		uint64_t L_59 = V_3;
		if ((!(((uint64_t)L_58) < ((uint64_t)((int64_t)((int64_t)L_59&((int64_t)((int32_t)-4))))))))
		{
			goto IL_0198;
		}
	}
	{
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_60 = V_4;
		uint64_t L_61 = V_6;
		uint64_t L_62 = V_5;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_63;
		L_63 = il2cpp_unsafe_add_byte_offset<BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C,uint64_t>(L_60, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_61, (int64_t)L_62)));
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C L_64 = ___0_value;
		*(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C*)L_63 = L_64;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_65 = V_4;
		uint64_t L_66 = V_6;
		uint64_t L_67 = V_5;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_68;
		L_68 = il2cpp_unsafe_add_byte_offset<BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C,uint64_t>(L_65, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_66, ((int64_t)1))), (int64_t)L_67)));
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C L_69 = ___0_value;
		*(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C*)L_68 = L_69;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_70 = V_4;
		uint64_t L_71 = V_6;
		uint64_t L_72 = V_5;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_73;
		L_73 = il2cpp_unsafe_add_byte_offset<BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C,uint64_t>(L_70, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_71, ((int64_t)2))), (int64_t)L_72)));
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C L_74 = ___0_value;
		*(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C*)L_73 = L_74;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_75 = V_4;
		uint64_t L_76 = V_6;
		uint64_t L_77 = V_5;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_78;
		L_78 = il2cpp_unsafe_add_byte_offset<BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C,uint64_t>(L_75, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_76, ((int64_t)3))), (int64_t)L_77)));
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C L_79 = ___0_value;
		*(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C*)L_78 = L_79;
		uint64_t L_80 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_80, ((int64_t)4)));
		goto IL_0198;
	}

IL_017f:
	{
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_81 = V_4;
		uint64_t L_82 = V_6;
		uint64_t L_83 = V_5;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_84;
		L_84 = il2cpp_unsafe_add_byte_offset<BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C,uint64_t>(L_81, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_82, (int64_t)L_83)));
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C L_85 = ___0_value;
		*(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C*)L_84 = L_85;
		uint64_t L_86 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_86, ((int64_t)1)));
	}

IL_0198:
	{
		uint64_t L_87 = V_6;
		uint64_t L_88 = V_3;
		if ((!(((uint64_t)L_87) >= ((uint64_t)L_88))))
		{
			goto IL_017f;
		}
	}
	{
		return;
	}
}
// Method Definition Index: 13825
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_CopyTo_m1F40559B54C3AA33371836D2795D064AFD9DDF6D_gshared (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF* __this, Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF ___0_destination, const RuntimeMethod* method) 
{
	ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_m8E10CA1F785F77C56E8D665A3CE6AF1DC31200FA_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0038;
		}
	}
	{
		Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF L_2 = ___0_destination;
		ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 L_3 = L_2.____pointer;
		V_0 = L_3;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C, (Il2CppByReference*)(&V_0));
		ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 L_5 = __this->____pointer;
		V_0 = L_5;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C, (Il2CppByReference*)(&V_0));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C_m8557E6B1B48B7EB426E6CCFA83223FDCB8F16135(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return;
	}

IL_0038:
	{
		ThrowHelper_ThrowArgumentException_DestinationTooShort_m6468934A3BBB67DBC5BAEF7A64D91BD5BBBB3D4D(NULL);
		return;
	}
}
// Method Definition Index: 13826
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_TryCopyTo_mF28A90CD8C350EC91CC7A40EB8E205BB74E4B9E5_gshared (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF* __this, Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF ___0_destination, const RuntimeMethod* method) 
{
	bool V_0 = false;
	ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		V_0 = (bool)0;
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_m8E10CA1F785F77C56E8D665A3CE6AF1DC31200FA_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_003b;
		}
	}
	{
		Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF L_2 = ___0_destination;
		ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 L_3 = L_2.____pointer;
		V_1 = L_3;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C, (Il2CppByReference*)(&V_1));
		ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 L_5 = __this->____pointer;
		V_1 = L_5;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C, (Il2CppByReference*)(&V_1));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C_m8557E6B1B48B7EB426E6CCFA83223FDCB8F16135(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		V_0 = (bool)1;
	}

IL_003b:
	{
		bool L_8 = V_0;
		return L_8;
	}
}
// Method Definition Index: 13827
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_op_Equality_mF7EF5329CE09A3D51618FF9C4AF38817C0E71B4F_gshared (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF ___0_left, Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF ___1_right, const RuntimeMethod* method) 
{
	ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF L_0 = ___0_left;
		int32_t L_1 = L_0.____length;
		Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF L_2 = ___1_right;
		int32_t L_3 = L_2.____length;
		if ((!(((uint32_t)L_1) == ((uint32_t)L_3))))
		{
			goto IL_0030;
		}
	}
	{
		Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF L_4 = ___0_left;
		ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 L_5 = L_4.____pointer;
		V_0 = L_5;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C, (Il2CppByReference*)(&V_0));
		Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF L_7 = ___1_right;
		ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 L_8 = L_7.____pointer;
		V_0 = L_8;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_9;
		L_9 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C, (Il2CppByReference*)(&V_0));
		bool L_10;
		L_10 = il2cpp_unsafe_are_same(L_6, L_9);
		return L_10;
	}

IL_0030:
	{
		return (bool)0;
	}
}
// Method Definition Index: 13828
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ReadOnlySpan_1_t92B77B310CC53A58C3C04B20E0B7F18C2EEFB130 Span_1_op_Implicit_mFFDF16C5B7C3592BCD641745FB8C5B830A4342FD_gshared (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF ___0_span, const RuntimeMethod* method) 
{
	ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF L_0 = ___0_span;
		ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 L_1 = L_0.____pointer;
		V_0 = L_1;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_2;
		L_2 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C, (Il2CppByReference*)(&V_0));
		Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF L_3 = ___0_span;
		int32_t L_4 = L_3.____length;
		ReadOnlySpan_1_t92B77B310CC53A58C3C04B20E0B7F18C2EEFB130 L_5;
		memset((&L_5), 0, sizeof(L_5));
		ReadOnlySpan_1__ctor_m89203DEBD91443BD65C0CCC9EAFA52A1CDE4B184_inline((&L_5), L_2, L_4, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 18));
		return L_5;
	}
}
// Method Definition Index: 13829
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Span_1_ToString_m9AD9A8A54C91FDB05D71B8F601FBB1F8F7AB641C_gshared (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral0DB46164953228904843938099AF66650313FEE5);
		s_Il2CppMethodInitialized = true;
	}
	Il2CppChar* V_0 = NULL;
	ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_0 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_1;
		L_1 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_0, NULL);
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_2 = { reinterpret_cast<intptr_t> (&il2cpp_defaults.char_class->byval_arg) };
		Type_t* L_3;
		L_3 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_2, NULL);
		bool L_4;
		L_4 = Type_op_Equality_m99930A0E44E420A685FABA60E60BA1CC5FA0EBDC(L_1, L_3, NULL);
		if (!L_4)
		{
			goto IL_003e;
		}
	}
	{
		ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 L_5 = __this->____pointer;
		V_1 = L_5;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C, (Il2CppByReference*)(&V_1));
		Il2CppChar* L_7;
		L_7 = il2cpp_unsafe_as_ref<Il2CppChar>(L_6);
		V_0 = L_7;
		Il2CppChar* L_8 = V_0;
		int32_t L_9 = __this->____length;
		String_t* L_10;
		L_10 = String_CreateString_m3F8794FEB452558B8A68C65E1F0B603B3D94E0E2(NULL, (Il2CppChar*)((uintptr_t)L_8), 0, L_9, NULL);
		return L_10;
	}

IL_003e:
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_11 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_12;
		L_12 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_11, NULL);
		NullCheck((MemberInfo_t*)L_12);
		String_t* L_13;
		L_13 = VirtualFuncInvoker0< String_t* >::Invoke(7, (MemberInfo_t*)L_12);
		int32_t L_14 = __this->____length;
		int32_t L_15 = L_14;
		RuntimeObject* L_16 = Box(il2cpp_defaults.int32_class, &L_15);
		String_t* L_17;
		L_17 = String_Format_mFB7DA489BD99F4670881FF50EC017BFB0A5C0987(_stringLiteral0DB46164953228904843938099AF66650313FEE5, (RuntimeObject*)L_13, L_16, NULL);
		return L_17;
	}
}
// Method Definition Index: 13830
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF Span_1_Slice_m5954A309C2AD4EA094B7C87069F90590CB4A0E52_gshared (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF* __this, int32_t ___0_start, const RuntimeMethod* method) 
{
	ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) > ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 L_2 = __this->____pointer;
		V_0 = L_2;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_start;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_5;
		L_5 = il2cpp_unsafe_add<BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C,int32_t>(L_3, L_4);
		int32_t L_6 = __this->____length;
		int32_t L_7 = ___0_start;
		Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF L_8;
		memset((&L_8), 0, sizeof(L_8));
		Span_1__ctor_mA2CE8DBE18F6BB54702DAA56720F89DD968A5271_inline((&L_8), L_5, ((int32_t)il2cpp_codegen_subtract(L_6, L_7)), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_8;
	}
}
// Method Definition Index: 13831
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF Span_1_Slice_mDCAEA440FB951CD66FF7A9A3CA0B03EDAD5EC4A9_gshared (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF* __this, int32_t ___0_start, int32_t ___1_length, const RuntimeMethod* method) 
{
	ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0014;
		}
	}
	{
		int32_t L_2 = ___1_length;
		int32_t L_3 = __this->____length;
		int32_t L_4 = ___0_start;
		if ((!(((uint32_t)L_2) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(L_3, L_4))))))
		{
			goto IL_0019;
		}
	}

IL_0014:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0019:
	{
		ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 L_5 = __this->____pointer;
		V_0 = L_5;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C, (Il2CppByReference*)(&V_0));
		int32_t L_7 = ___0_start;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_8;
		L_8 = il2cpp_unsafe_add<BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C,int32_t>(L_6, L_7);
		int32_t L_9 = ___1_length;
		Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF L_10;
		memset((&L_10), 0, sizeof(L_10));
		Span_1__ctor_mA2CE8DBE18F6BB54702DAA56720F89DD968A5271_inline((&L_10), L_8, L_9, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_10;
	}
}
// Method Definition Index: 13832
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4* Span_1_ToArray_m8DCE4D7A1E561D68CAD01C72F0A535BDD5F2923E_gshared (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF* __this, const RuntimeMethod* method) 
{
	ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_000e;
		}
	}
	{
		BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4* L_1;
		L_1 = Array_Empty_TisBatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C_m0154337C8713B2FE58E471FD10F37CFF0E4D1537_inline(il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 20));
		return L_1;
	}

IL_000e:
	{
		int32_t L_2 = __this->____length;
		BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4* L_3 = (BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4*)(BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4*)SZArrayNew(il2cpp_rgctx_data(InitializedTypeInfo(method->klass)->rgctx_data, 21), (uint32_t)L_2);
		BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4* L_4 = L_3;
		NullCheck((RuntimeArray*)L_4);
		uint8_t* L_5;
		L_5 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_4, NULL);
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_6;
		L_6 = il2cpp_unsafe_as_ref<BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C>(L_5);
		ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 L_7 = __this->____pointer;
		V_0 = L_7;
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_8;
		L_8 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C, (Il2CppByReference*)(&V_0));
		int32_t L_9 = __this->____length;
		Buffer_Memmove_TisBatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C_m8557E6B1B48B7EB426E6CCFA83223FDCB8F16135(L_6, L_8, (uint64_t)((int64_t)L_9), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return L_4;
	}
}
// Method Definition Index: 13833
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m8E10CA1F785F77C56E8D665A3CE6AF1DC31200FA_gshared (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13834
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_Equals_m1E8F4B2205D9620E76D00FAF9F7E2BE28E89D582_gshared (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteral69508A540AFD085A745316DD7D6345B1C8CC662D)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13835
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_GetHashCode_m42BD47603DFBC43C93A19D9673C5AA05425D2309_gshared (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF* __this, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteralECE618215BAC99C6FD12D8A273CC2118945EDCC8)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13836
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF Span_1_op_Implicit_m0F85272E683399DB9FF7A0E744F2C00C6597FF67_gshared (BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4* ___0_array, const RuntimeMethod* method) 
{
	{
		BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4* L_0 = ___0_array;
		Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF L_1;
		memset((&L_1), 0, sizeof(L_1));
		Span_1__ctor_mCDB221C7D73874741828DA34B70D3B0AADC00136_inline((&L_1), L_0, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 22));
		return L_1;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// Method Definition Index: 13818
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m81637CB567960AF0B3F0471290B423B290FE1830_gshared (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1* __this, BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E* ___0_array, const RuntimeMethod* method) 
{
	BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t3A087F8D965C32C63C3853E38E112343685765A1));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0));
		goto IL_0037;
	}

IL_0037:
	{
		BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_4;
		L_4 = il2cpp_unsafe_as_ref<BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0>(L_3);
		ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13819
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m58886398A26F7E4FDA5121ADBB4D128C8AA78E73_gshared (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1* __this, BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E* ___0_array, int32_t ___1_start, int32_t ___2_length, const RuntimeMethod* method) 
{
	BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_0016;
		}
	}
	{
		int32_t L_1 = ___1_start;
		if (L_1)
		{
			goto IL_0009;
		}
	}
	{
		int32_t L_2 = ___2_length;
		if (!L_2)
		{
			goto IL_000e;
		}
	}

IL_0009:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t3A087F8D965C32C63C3853E38E112343685765A1));
		return;
	}

IL_0016:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0));
		goto IL_0042;
	}

IL_0042:
	{
		int32_t L_4 = ___1_start;
		BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E* L_5 = ___0_array;
		NullCheck(L_5);
		if ((!(((uint32_t)L_4) <= ((uint32_t)((int32_t)(((RuntimeArray*)L_5)->max_length))))))
		{
			goto IL_0050;
		}
	}
	{
		int32_t L_6 = ___2_length;
		BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E* L_7 = ___0_array;
		NullCheck(L_7);
		int32_t L_8 = ___1_start;
		if ((!(((uint32_t)L_6) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(((int32_t)(((RuntimeArray*)L_7)->max_length)), L_8))))))
		{
			goto IL_0055;
		}
	}

IL_0050:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0055:
	{
		BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E* L_9 = ___0_array;
		NullCheck((RuntimeArray*)L_9);
		uint8_t* L_10;
		L_10 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_9, NULL);
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_11;
		L_11 = il2cpp_unsafe_as_ref<BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0>(L_10);
		int32_t L_12 = ___1_start;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_13;
		L_13 = il2cpp_unsafe_add<BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0,int32_t>(L_11, L_12);
		ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B L_14;
		memset((&L_14), 0, sizeof(L_14));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_14), L_13);
		__this->____pointer = L_14;
		int32_t L_15 = ___2_length;
		__this->____length = L_15;
		return;
	}
}
// Method Definition Index: 13820
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m1CB5190FFEDCC8EB7DB0ED8E31C144DFC2914C8E_gshared (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1* __this, void* ___0_pointer, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		goto IL_0016;
	}

IL_0016:
	{
		int32_t L_0 = ___1_length;
		if ((((int32_t)L_0) >= ((int32_t)0)))
		{
			goto IL_001f;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_001f:
	{
		void* L_1 = ___0_pointer;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_2;
		L_2 = il2cpp_unsafe_as_ref<BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0>((uint8_t*)L_1);
		ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B L_3;
		memset((&L_3), 0, sizeof(L_3));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_3), L_2);
		__this->____pointer = L_3;
		int32_t L_4 = ___1_length;
		__this->____length = L_4;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m0B727F1A38BD125BA88E22B9518BBEA69CD9F192_gshared (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1* __this, BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_0 = ___0_ptr;
		ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13822
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* Span_1_get_Item_m386EC452524AFADB61FA999DB30DD13846F5BE2A_gshared (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1* __this, int32_t ___0_index, const RuntimeMethod* method) 
{
	ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_index;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) >= ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowIndexOutOfRangeException_m86F753A24E2765A35546BA6352A7E4F0BB8A66B5(NULL);
	}

IL_000e:
	{
		ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B L_2 = __this->____pointer;
		V_0 = L_2;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_index;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_5;
		L_5 = il2cpp_unsafe_add<BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0,int32_t>(L_3, L_4);
		return L_5;
	}
}
// Method Definition Index: 13823
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* Span_1_GetPinnableReference_mD5BC982F3A994A8DB8ACB158E1BBDF8EDAE0E60F_gshared (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1* __this, const RuntimeMethod* method) 
{
	ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_0010;
		}
	}
	{
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_1;
		L_1 = il2cpp_unsafe_as_ref<BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0>((void*)((uintptr_t)0));
		return L_1;
	}

IL_0010:
	{
		ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B L_2 = __this->____pointer;
		V_0 = L_2;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0, (Il2CppByReference*)(&V_0));
		return L_3;
	}
}
// Method Definition Index: 13824
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_Fill_m1F838FD8426BE3F460F1C6F0DBBF0794968A3C32_gshared (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1* __this, BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0 ___0_value, const RuntimeMethod* method) 
{
	uint32_t V_0 = 0;
	BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0 V_1;
	memset((&V_1), 0, sizeof(V_1));
	ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B V_2;
	memset((&V_2), 0, sizeof(V_2));
	uint64_t V_3 = 0;
	BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* V_4 = NULL;
	uint64_t V_5 = 0;
	uint64_t V_6 = 0;
	{
		int32_t L_0;
		L_0 = il2cpp_unsafe_sizeof<BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0>();
		if ((!(((uint32_t)L_0) == ((uint32_t)1))))
		{
			goto IL_0037;
		}
	}
	{
		int32_t L_1 = __this->____length;
		V_0 = (uint32_t)L_1;
		uint32_t L_2 = V_0;
		if (L_2)
		{
			goto IL_0013;
		}
	}
	{
		return;
	}

IL_0013:
	{
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0 L_3 = ___0_value;
		V_1 = L_3;
		ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B L_4 = __this->____pointer;
		V_2 = L_4;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_5;
		L_5 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0, (Il2CppByReference*)(&V_2));
		uint8_t* L_6;
		L_6 = il2cpp_unsafe_as_ref<uint8_t>(L_5);
		uint8_t* L_7;
		L_7 = il2cpp_unsafe_as_ref<uint8_t>((&V_1));
		int32_t L_8 = *((uint8_t*)L_7);
		uint32_t L_9 = V_0;
		Unsafe_InitBlockUnaligned_m6F2353EB9ABC9320E61629FAEE23948C80BFF03A(L_6, (uint8_t)L_8, L_9, NULL);
		return;
	}

IL_0037:
	{
		int32_t L_10 = __this->____length;
		V_3 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_10));
		uint64_t L_11 = V_3;
		if (L_11)
		{
			goto IL_0043;
		}
	}
	{
		return;
	}

IL_0043:
	{
		ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B L_12 = __this->____pointer;
		V_2 = L_12;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_13;
		L_13 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0, (Il2CppByReference*)(&V_2));
		V_4 = L_13;
		int32_t L_14;
		L_14 = il2cpp_unsafe_sizeof<BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0>();
		V_5 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_14));
		V_6 = (uint64_t)((int64_t)0);
		goto IL_0110;
	}

IL_0064:
	{
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_15 = V_4;
		uint64_t L_16 = V_6;
		uint64_t L_17 = V_5;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_18;
		L_18 = il2cpp_unsafe_add_byte_offset<BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0,uint64_t>(L_15, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_16, (int64_t)L_17)));
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0 L_19 = ___0_value;
		*(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0*)L_18 = L_19;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_20 = V_4;
		uint64_t L_21 = V_6;
		uint64_t L_22 = V_5;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_23;
		L_23 = il2cpp_unsafe_add_byte_offset<BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0,uint64_t>(L_20, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_21, ((int64_t)1))), (int64_t)L_22)));
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0 L_24 = ___0_value;
		*(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0*)L_23 = L_24;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_25 = V_4;
		uint64_t L_26 = V_6;
		uint64_t L_27 = V_5;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_28;
		L_28 = il2cpp_unsafe_add_byte_offset<BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0,uint64_t>(L_25, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_26, ((int64_t)2))), (int64_t)L_27)));
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0 L_29 = ___0_value;
		*(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0*)L_28 = L_29;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_30 = V_4;
		uint64_t L_31 = V_6;
		uint64_t L_32 = V_5;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_33;
		L_33 = il2cpp_unsafe_add_byte_offset<BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0,uint64_t>(L_30, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_31, ((int64_t)3))), (int64_t)L_32)));
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0 L_34 = ___0_value;
		*(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0*)L_33 = L_34;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_35 = V_4;
		uint64_t L_36 = V_6;
		uint64_t L_37 = V_5;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_38;
		L_38 = il2cpp_unsafe_add_byte_offset<BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0,uint64_t>(L_35, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_36, ((int64_t)4))), (int64_t)L_37)));
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0 L_39 = ___0_value;
		*(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0*)L_38 = L_39;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_40 = V_4;
		uint64_t L_41 = V_6;
		uint64_t L_42 = V_5;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_43;
		L_43 = il2cpp_unsafe_add_byte_offset<BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0,uint64_t>(L_40, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_41, ((int64_t)5))), (int64_t)L_42)));
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0 L_44 = ___0_value;
		*(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0*)L_43 = L_44;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_45 = V_4;
		uint64_t L_46 = V_6;
		uint64_t L_47 = V_5;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_48;
		L_48 = il2cpp_unsafe_add_byte_offset<BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0,uint64_t>(L_45, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_46, ((int64_t)6))), (int64_t)L_47)));
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0 L_49 = ___0_value;
		*(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0*)L_48 = L_49;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_50 = V_4;
		uint64_t L_51 = V_6;
		uint64_t L_52 = V_5;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_53;
		L_53 = il2cpp_unsafe_add_byte_offset<BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0,uint64_t>(L_50, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_51, ((int64_t)7))), (int64_t)L_52)));
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0 L_54 = ___0_value;
		*(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0*)L_53 = L_54;
		uint64_t L_55 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_55, ((int64_t)8)));
	}

IL_0110:
	{
		uint64_t L_56 = V_6;
		uint64_t L_57 = V_3;
		if ((!(((uint64_t)L_56) >= ((uint64_t)((int64_t)((int64_t)L_57&((int64_t)((int32_t)-8))))))))
		{
			goto IL_0064;
		}
	}
	{
		uint64_t L_58 = V_6;
		uint64_t L_59 = V_3;
		if ((!(((uint64_t)L_58) < ((uint64_t)((int64_t)((int64_t)L_59&((int64_t)((int32_t)-4))))))))
		{
			goto IL_0198;
		}
	}
	{
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_60 = V_4;
		uint64_t L_61 = V_6;
		uint64_t L_62 = V_5;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_63;
		L_63 = il2cpp_unsafe_add_byte_offset<BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0,uint64_t>(L_60, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_61, (int64_t)L_62)));
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0 L_64 = ___0_value;
		*(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0*)L_63 = L_64;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_65 = V_4;
		uint64_t L_66 = V_6;
		uint64_t L_67 = V_5;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_68;
		L_68 = il2cpp_unsafe_add_byte_offset<BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0,uint64_t>(L_65, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_66, ((int64_t)1))), (int64_t)L_67)));
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0 L_69 = ___0_value;
		*(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0*)L_68 = L_69;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_70 = V_4;
		uint64_t L_71 = V_6;
		uint64_t L_72 = V_5;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_73;
		L_73 = il2cpp_unsafe_add_byte_offset<BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0,uint64_t>(L_70, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_71, ((int64_t)2))), (int64_t)L_72)));
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0 L_74 = ___0_value;
		*(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0*)L_73 = L_74;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_75 = V_4;
		uint64_t L_76 = V_6;
		uint64_t L_77 = V_5;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_78;
		L_78 = il2cpp_unsafe_add_byte_offset<BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0,uint64_t>(L_75, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_76, ((int64_t)3))), (int64_t)L_77)));
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0 L_79 = ___0_value;
		*(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0*)L_78 = L_79;
		uint64_t L_80 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_80, ((int64_t)4)));
		goto IL_0198;
	}

IL_017f:
	{
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_81 = V_4;
		uint64_t L_82 = V_6;
		uint64_t L_83 = V_5;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_84;
		L_84 = il2cpp_unsafe_add_byte_offset<BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0,uint64_t>(L_81, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_82, (int64_t)L_83)));
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0 L_85 = ___0_value;
		*(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0*)L_84 = L_85;
		uint64_t L_86 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_86, ((int64_t)1)));
	}

IL_0198:
	{
		uint64_t L_87 = V_6;
		uint64_t L_88 = V_3;
		if ((!(((uint64_t)L_87) >= ((uint64_t)L_88))))
		{
			goto IL_017f;
		}
	}
	{
		return;
	}
}
// Method Definition Index: 13825
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_CopyTo_m7C8A08F317CD4531D3E7EAB7A0A8311DC897375E_gshared (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1* __this, Span_1_t3A087F8D965C32C63C3853E38E112343685765A1 ___0_destination, const RuntimeMethod* method) 
{
	ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_mDDD127BFC8F4A5DEEE8DBC6779C72A75229E2A08_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0038;
		}
	}
	{
		Span_1_t3A087F8D965C32C63C3853E38E112343685765A1 L_2 = ___0_destination;
		ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B L_3 = L_2.____pointer;
		V_0 = L_3;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0, (Il2CppByReference*)(&V_0));
		ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B L_5 = __this->____pointer;
		V_0 = L_5;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0, (Il2CppByReference*)(&V_0));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0_m9EE961D328A463A3B89EBC9E2B20D70A09D560A9(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return;
	}

IL_0038:
	{
		ThrowHelper_ThrowArgumentException_DestinationTooShort_m6468934A3BBB67DBC5BAEF7A64D91BD5BBBB3D4D(NULL);
		return;
	}
}
// Method Definition Index: 13826
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_TryCopyTo_mA1EFBD5B67AF625AD450F87DDA70C29C8FF3A694_gshared (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1* __this, Span_1_t3A087F8D965C32C63C3853E38E112343685765A1 ___0_destination, const RuntimeMethod* method) 
{
	bool V_0 = false;
	ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		V_0 = (bool)0;
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_mDDD127BFC8F4A5DEEE8DBC6779C72A75229E2A08_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_003b;
		}
	}
	{
		Span_1_t3A087F8D965C32C63C3853E38E112343685765A1 L_2 = ___0_destination;
		ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B L_3 = L_2.____pointer;
		V_1 = L_3;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0, (Il2CppByReference*)(&V_1));
		ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B L_5 = __this->____pointer;
		V_1 = L_5;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0, (Il2CppByReference*)(&V_1));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0_m9EE961D328A463A3B89EBC9E2B20D70A09D560A9(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		V_0 = (bool)1;
	}

IL_003b:
	{
		bool L_8 = V_0;
		return L_8;
	}
}
// Method Definition Index: 13827
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_op_Equality_mE04D5A049404E8684DDCBDB692E969BCE1F838AC_gshared (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1 ___0_left, Span_1_t3A087F8D965C32C63C3853E38E112343685765A1 ___1_right, const RuntimeMethod* method) 
{
	ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_t3A087F8D965C32C63C3853E38E112343685765A1 L_0 = ___0_left;
		int32_t L_1 = L_0.____length;
		Span_1_t3A087F8D965C32C63C3853E38E112343685765A1 L_2 = ___1_right;
		int32_t L_3 = L_2.____length;
		if ((!(((uint32_t)L_1) == ((uint32_t)L_3))))
		{
			goto IL_0030;
		}
	}
	{
		Span_1_t3A087F8D965C32C63C3853E38E112343685765A1 L_4 = ___0_left;
		ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B L_5 = L_4.____pointer;
		V_0 = L_5;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0, (Il2CppByReference*)(&V_0));
		Span_1_t3A087F8D965C32C63C3853E38E112343685765A1 L_7 = ___1_right;
		ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B L_8 = L_7.____pointer;
		V_0 = L_8;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_9;
		L_9 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0, (Il2CppByReference*)(&V_0));
		bool L_10;
		L_10 = il2cpp_unsafe_are_same(L_6, L_9);
		return L_10;
	}

IL_0030:
	{
		return (bool)0;
	}
}
// Method Definition Index: 13828
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ReadOnlySpan_1_tD9DB60FA9C52DCE56999EB3FEEA0CDE0FDFFFFE2 Span_1_op_Implicit_m521E6AE0FD56767C27B6E5EBC1CA04718E856C13_gshared (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1 ___0_span, const RuntimeMethod* method) 
{
	ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_t3A087F8D965C32C63C3853E38E112343685765A1 L_0 = ___0_span;
		ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B L_1 = L_0.____pointer;
		V_0 = L_1;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_2;
		L_2 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0, (Il2CppByReference*)(&V_0));
		Span_1_t3A087F8D965C32C63C3853E38E112343685765A1 L_3 = ___0_span;
		int32_t L_4 = L_3.____length;
		ReadOnlySpan_1_tD9DB60FA9C52DCE56999EB3FEEA0CDE0FDFFFFE2 L_5;
		memset((&L_5), 0, sizeof(L_5));
		ReadOnlySpan_1__ctor_m9A03AF77602C387B913C41E787C615B3D1C1610F_inline((&L_5), L_2, L_4, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 18));
		return L_5;
	}
}
// Method Definition Index: 13829
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Span_1_ToString_mFA6CBF9C2177DA5730E43FDA72496276148CDDA6_gshared (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral0DB46164953228904843938099AF66650313FEE5);
		s_Il2CppMethodInitialized = true;
	}
	Il2CppChar* V_0 = NULL;
	ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_0 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_1;
		L_1 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_0, NULL);
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_2 = { reinterpret_cast<intptr_t> (&il2cpp_defaults.char_class->byval_arg) };
		Type_t* L_3;
		L_3 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_2, NULL);
		bool L_4;
		L_4 = Type_op_Equality_m99930A0E44E420A685FABA60E60BA1CC5FA0EBDC(L_1, L_3, NULL);
		if (!L_4)
		{
			goto IL_003e;
		}
	}
	{
		ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B L_5 = __this->____pointer;
		V_1 = L_5;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0, (Il2CppByReference*)(&V_1));
		Il2CppChar* L_7;
		L_7 = il2cpp_unsafe_as_ref<Il2CppChar>(L_6);
		V_0 = L_7;
		Il2CppChar* L_8 = V_0;
		int32_t L_9 = __this->____length;
		String_t* L_10;
		L_10 = String_CreateString_m3F8794FEB452558B8A68C65E1F0B603B3D94E0E2(NULL, (Il2CppChar*)((uintptr_t)L_8), 0, L_9, NULL);
		return L_10;
	}

IL_003e:
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_11 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_12;
		L_12 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_11, NULL);
		NullCheck((MemberInfo_t*)L_12);
		String_t* L_13;
		L_13 = VirtualFuncInvoker0< String_t* >::Invoke(7, (MemberInfo_t*)L_12);
		int32_t L_14 = __this->____length;
		int32_t L_15 = L_14;
		RuntimeObject* L_16 = Box(il2cpp_defaults.int32_class, &L_15);
		String_t* L_17;
		L_17 = String_Format_mFB7DA489BD99F4670881FF50EC017BFB0A5C0987(_stringLiteral0DB46164953228904843938099AF66650313FEE5, (RuntimeObject*)L_13, L_16, NULL);
		return L_17;
	}
}
// Method Definition Index: 13830
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t3A087F8D965C32C63C3853E38E112343685765A1 Span_1_Slice_m201476D6798AE48DDD265B7BE7A96C596C9E7C80_gshared (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1* __this, int32_t ___0_start, const RuntimeMethod* method) 
{
	ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) > ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B L_2 = __this->____pointer;
		V_0 = L_2;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_start;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_5;
		L_5 = il2cpp_unsafe_add<BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0,int32_t>(L_3, L_4);
		int32_t L_6 = __this->____length;
		int32_t L_7 = ___0_start;
		Span_1_t3A087F8D965C32C63C3853E38E112343685765A1 L_8;
		memset((&L_8), 0, sizeof(L_8));
		Span_1__ctor_m0B727F1A38BD125BA88E22B9518BBEA69CD9F192_inline((&L_8), L_5, ((int32_t)il2cpp_codegen_subtract(L_6, L_7)), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_8;
	}
}
// Method Definition Index: 13831
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t3A087F8D965C32C63C3853E38E112343685765A1 Span_1_Slice_m25EDBDB4C7B217E6FEDD222F5A628404DB45F9C5_gshared (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1* __this, int32_t ___0_start, int32_t ___1_length, const RuntimeMethod* method) 
{
	ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0014;
		}
	}
	{
		int32_t L_2 = ___1_length;
		int32_t L_3 = __this->____length;
		int32_t L_4 = ___0_start;
		if ((!(((uint32_t)L_2) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(L_3, L_4))))))
		{
			goto IL_0019;
		}
	}

IL_0014:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0019:
	{
		ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B L_5 = __this->____pointer;
		V_0 = L_5;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0, (Il2CppByReference*)(&V_0));
		int32_t L_7 = ___0_start;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_8;
		L_8 = il2cpp_unsafe_add<BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0,int32_t>(L_6, L_7);
		int32_t L_9 = ___1_length;
		Span_1_t3A087F8D965C32C63C3853E38E112343685765A1 L_10;
		memset((&L_10), 0, sizeof(L_10));
		Span_1__ctor_m0B727F1A38BD125BA88E22B9518BBEA69CD9F192_inline((&L_10), L_8, L_9, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_10;
	}
}
// Method Definition Index: 13832
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E* Span_1_ToArray_m2C5B4033D6D824A89AA74E7E425746717E3F4496_gshared (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1* __this, const RuntimeMethod* method) 
{
	ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_000e;
		}
	}
	{
		BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E* L_1;
		L_1 = Array_Empty_TisBatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0_m30D7C90FCBAF09BB4E8C21ED5FE267CFF2F47BA4_inline(il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 20));
		return L_1;
	}

IL_000e:
	{
		int32_t L_2 = __this->____length;
		BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E* L_3 = (BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E*)(BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E*)SZArrayNew(il2cpp_rgctx_data(InitializedTypeInfo(method->klass)->rgctx_data, 21), (uint32_t)L_2);
		BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E* L_4 = L_3;
		NullCheck((RuntimeArray*)L_4);
		uint8_t* L_5;
		L_5 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_4, NULL);
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_6;
		L_6 = il2cpp_unsafe_as_ref<BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0>(L_5);
		ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B L_7 = __this->____pointer;
		V_0 = L_7;
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_8;
		L_8 = IL2CPP_BY_REFERENCE_GET_VALUE(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0, (Il2CppByReference*)(&V_0));
		int32_t L_9 = __this->____length;
		Buffer_Memmove_TisBatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0_m9EE961D328A463A3B89EBC9E2B20D70A09D560A9(L_6, L_8, (uint64_t)((int64_t)L_9), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return L_4;
	}
}
// Method Definition Index: 13833
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mDDD127BFC8F4A5DEEE8DBC6779C72A75229E2A08_gshared (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13834
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_Equals_mE07E82E70F9DFAC255191D0A4003AD9B2F9904C1_gshared (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteral69508A540AFD085A745316DD7D6345B1C8CC662D)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13835
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_GetHashCode_m113342649B329719E2CD12C1E646EEDE0E596B92_gshared (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1* __this, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteralECE618215BAC99C6FD12D8A273CC2118945EDCC8)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13836
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t3A087F8D965C32C63C3853E38E112343685765A1 Span_1_op_Implicit_m61F00C4BA0AD560F69E15EB19A35A72090BD09DD_gshared (BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E* ___0_array, const RuntimeMethod* method) 
{
	{
		BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E* L_0 = ___0_array;
		Span_1_t3A087F8D965C32C63C3853E38E112343685765A1 L_1;
		memset((&L_1), 0, sizeof(L_1));
		Span_1__ctor_m81637CB567960AF0B3F0471290B423B290FE1830_inline((&L_1), L_0, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 22));
		return L_1;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// Method Definition Index: 13818
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mCA3E1FB18A1006842617ABD22464535398F4655D_gshared (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671* __this, BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B* ___0_array, const RuntimeMethod* method) 
{
	BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF));
		goto IL_0037;
	}

IL_0037:
	{
		BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_4;
		L_4 = il2cpp_unsafe_as_ref<BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF>(L_3);
		ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13819
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mFD33CF6810B10785B067103EEB5A566765495456_gshared (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671* __this, BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B* ___0_array, int32_t ___1_start, int32_t ___2_length, const RuntimeMethod* method) 
{
	BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_0016;
		}
	}
	{
		int32_t L_1 = ___1_start;
		if (L_1)
		{
			goto IL_0009;
		}
	}
	{
		int32_t L_2 = ___2_length;
		if (!L_2)
		{
			goto IL_000e;
		}
	}

IL_0009:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671));
		return;
	}

IL_0016:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF));
		goto IL_0042;
	}

IL_0042:
	{
		int32_t L_4 = ___1_start;
		BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B* L_5 = ___0_array;
		NullCheck(L_5);
		if ((!(((uint32_t)L_4) <= ((uint32_t)((int32_t)(((RuntimeArray*)L_5)->max_length))))))
		{
			goto IL_0050;
		}
	}
	{
		int32_t L_6 = ___2_length;
		BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B* L_7 = ___0_array;
		NullCheck(L_7);
		int32_t L_8 = ___1_start;
		if ((!(((uint32_t)L_6) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(((int32_t)(((RuntimeArray*)L_7)->max_length)), L_8))))))
		{
			goto IL_0055;
		}
	}

IL_0050:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0055:
	{
		BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B* L_9 = ___0_array;
		NullCheck((RuntimeArray*)L_9);
		uint8_t* L_10;
		L_10 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_9, NULL);
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_11;
		L_11 = il2cpp_unsafe_as_ref<BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF>(L_10);
		int32_t L_12 = ___1_start;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_13;
		L_13 = il2cpp_unsafe_add<BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF,int32_t>(L_11, L_12);
		ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F L_14;
		memset((&L_14), 0, sizeof(L_14));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_14), L_13);
		__this->____pointer = L_14;
		int32_t L_15 = ___2_length;
		__this->____length = L_15;
		return;
	}
}
// Method Definition Index: 13820
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m5DFA597BC248F8415E6290DEE630D9DEE6910CE5_gshared (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671* __this, void* ___0_pointer, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		goto IL_0016;
	}

IL_0016:
	{
		int32_t L_0 = ___1_length;
		if ((((int32_t)L_0) >= ((int32_t)0)))
		{
			goto IL_001f;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_001f:
	{
		void* L_1 = ___0_pointer;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_2;
		L_2 = il2cpp_unsafe_as_ref<BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF>((uint8_t*)L_1);
		ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F L_3;
		memset((&L_3), 0, sizeof(L_3));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_3), L_2);
		__this->____pointer = L_3;
		int32_t L_4 = ___1_length;
		__this->____length = L_4;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m4F32D7D5653D612D237DDFD35C9850E16CA11C18_gshared (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671* __this, BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_0 = ___0_ptr;
		ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13822
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* Span_1_get_Item_mD533811FA5C4B53120E3936C9C8195AA9D4AFA96_gshared (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671* __this, int32_t ___0_index, const RuntimeMethod* method) 
{
	ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_index;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) >= ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowIndexOutOfRangeException_m86F753A24E2765A35546BA6352A7E4F0BB8A66B5(NULL);
	}

IL_000e:
	{
		ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F L_2 = __this->____pointer;
		V_0 = L_2;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_index;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_5;
		L_5 = il2cpp_unsafe_add<BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF,int32_t>(L_3, L_4);
		return L_5;
	}
}
// Method Definition Index: 13823
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* Span_1_GetPinnableReference_m0CC6C500AA1584A03047A9D1F22489602953AAEB_gshared (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671* __this, const RuntimeMethod* method) 
{
	ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_0010;
		}
	}
	{
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_1;
		L_1 = il2cpp_unsafe_as_ref<BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF>((void*)((uintptr_t)0));
		return L_1;
	}

IL_0010:
	{
		ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F L_2 = __this->____pointer;
		V_0 = L_2;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF, (Il2CppByReference*)(&V_0));
		return L_3;
	}
}
// Method Definition Index: 13824
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_Fill_m59389A1C14E13B1AC0A9863ADC8C86F469146CA3_gshared (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671* __this, BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF ___0_value, const RuntimeMethod* method) 
{
	uint32_t V_0 = 0;
	BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF V_1;
	memset((&V_1), 0, sizeof(V_1));
	ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F V_2;
	memset((&V_2), 0, sizeof(V_2));
	uint64_t V_3 = 0;
	BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* V_4 = NULL;
	uint64_t V_5 = 0;
	uint64_t V_6 = 0;
	{
		int32_t L_0;
		L_0 = il2cpp_unsafe_sizeof<BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF>();
		if ((!(((uint32_t)L_0) == ((uint32_t)1))))
		{
			goto IL_0037;
		}
	}
	{
		int32_t L_1 = __this->____length;
		V_0 = (uint32_t)L_1;
		uint32_t L_2 = V_0;
		if (L_2)
		{
			goto IL_0013;
		}
	}
	{
		return;
	}

IL_0013:
	{
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF L_3 = ___0_value;
		V_1 = L_3;
		ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F L_4 = __this->____pointer;
		V_2 = L_4;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_5;
		L_5 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF, (Il2CppByReference*)(&V_2));
		uint8_t* L_6;
		L_6 = il2cpp_unsafe_as_ref<uint8_t>(L_5);
		uint8_t* L_7;
		L_7 = il2cpp_unsafe_as_ref<uint8_t>((&V_1));
		int32_t L_8 = *((uint8_t*)L_7);
		uint32_t L_9 = V_0;
		Unsafe_InitBlockUnaligned_m6F2353EB9ABC9320E61629FAEE23948C80BFF03A(L_6, (uint8_t)L_8, L_9, NULL);
		return;
	}

IL_0037:
	{
		int32_t L_10 = __this->____length;
		V_3 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_10));
		uint64_t L_11 = V_3;
		if (L_11)
		{
			goto IL_0043;
		}
	}
	{
		return;
	}

IL_0043:
	{
		ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F L_12 = __this->____pointer;
		V_2 = L_12;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_13;
		L_13 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF, (Il2CppByReference*)(&V_2));
		V_4 = L_13;
		int32_t L_14;
		L_14 = il2cpp_unsafe_sizeof<BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF>();
		V_5 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_14));
		V_6 = (uint64_t)((int64_t)0);
		goto IL_0110;
	}

IL_0064:
	{
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_15 = V_4;
		uint64_t L_16 = V_6;
		uint64_t L_17 = V_5;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_18;
		L_18 = il2cpp_unsafe_add_byte_offset<BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF,uint64_t>(L_15, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_16, (int64_t)L_17)));
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF L_19 = ___0_value;
		*(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF*)L_18 = L_19;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_20 = V_4;
		uint64_t L_21 = V_6;
		uint64_t L_22 = V_5;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_23;
		L_23 = il2cpp_unsafe_add_byte_offset<BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF,uint64_t>(L_20, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_21, ((int64_t)1))), (int64_t)L_22)));
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF L_24 = ___0_value;
		*(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF*)L_23 = L_24;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_25 = V_4;
		uint64_t L_26 = V_6;
		uint64_t L_27 = V_5;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_28;
		L_28 = il2cpp_unsafe_add_byte_offset<BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF,uint64_t>(L_25, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_26, ((int64_t)2))), (int64_t)L_27)));
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF L_29 = ___0_value;
		*(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF*)L_28 = L_29;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_30 = V_4;
		uint64_t L_31 = V_6;
		uint64_t L_32 = V_5;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_33;
		L_33 = il2cpp_unsafe_add_byte_offset<BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF,uint64_t>(L_30, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_31, ((int64_t)3))), (int64_t)L_32)));
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF L_34 = ___0_value;
		*(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF*)L_33 = L_34;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_35 = V_4;
		uint64_t L_36 = V_6;
		uint64_t L_37 = V_5;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_38;
		L_38 = il2cpp_unsafe_add_byte_offset<BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF,uint64_t>(L_35, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_36, ((int64_t)4))), (int64_t)L_37)));
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF L_39 = ___0_value;
		*(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF*)L_38 = L_39;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_40 = V_4;
		uint64_t L_41 = V_6;
		uint64_t L_42 = V_5;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_43;
		L_43 = il2cpp_unsafe_add_byte_offset<BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF,uint64_t>(L_40, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_41, ((int64_t)5))), (int64_t)L_42)));
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF L_44 = ___0_value;
		*(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF*)L_43 = L_44;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_45 = V_4;
		uint64_t L_46 = V_6;
		uint64_t L_47 = V_5;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_48;
		L_48 = il2cpp_unsafe_add_byte_offset<BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF,uint64_t>(L_45, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_46, ((int64_t)6))), (int64_t)L_47)));
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF L_49 = ___0_value;
		*(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF*)L_48 = L_49;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_50 = V_4;
		uint64_t L_51 = V_6;
		uint64_t L_52 = V_5;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_53;
		L_53 = il2cpp_unsafe_add_byte_offset<BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF,uint64_t>(L_50, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_51, ((int64_t)7))), (int64_t)L_52)));
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF L_54 = ___0_value;
		*(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF*)L_53 = L_54;
		uint64_t L_55 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_55, ((int64_t)8)));
	}

IL_0110:
	{
		uint64_t L_56 = V_6;
		uint64_t L_57 = V_3;
		if ((!(((uint64_t)L_56) >= ((uint64_t)((int64_t)((int64_t)L_57&((int64_t)((int32_t)-8))))))))
		{
			goto IL_0064;
		}
	}
	{
		uint64_t L_58 = V_6;
		uint64_t L_59 = V_3;
		if ((!(((uint64_t)L_58) < ((uint64_t)((int64_t)((int64_t)L_59&((int64_t)((int32_t)-4))))))))
		{
			goto IL_0198;
		}
	}
	{
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_60 = V_4;
		uint64_t L_61 = V_6;
		uint64_t L_62 = V_5;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_63;
		L_63 = il2cpp_unsafe_add_byte_offset<BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF,uint64_t>(L_60, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_61, (int64_t)L_62)));
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF L_64 = ___0_value;
		*(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF*)L_63 = L_64;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_65 = V_4;
		uint64_t L_66 = V_6;
		uint64_t L_67 = V_5;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_68;
		L_68 = il2cpp_unsafe_add_byte_offset<BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF,uint64_t>(L_65, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_66, ((int64_t)1))), (int64_t)L_67)));
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF L_69 = ___0_value;
		*(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF*)L_68 = L_69;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_70 = V_4;
		uint64_t L_71 = V_6;
		uint64_t L_72 = V_5;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_73;
		L_73 = il2cpp_unsafe_add_byte_offset<BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF,uint64_t>(L_70, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_71, ((int64_t)2))), (int64_t)L_72)));
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF L_74 = ___0_value;
		*(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF*)L_73 = L_74;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_75 = V_4;
		uint64_t L_76 = V_6;
		uint64_t L_77 = V_5;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_78;
		L_78 = il2cpp_unsafe_add_byte_offset<BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF,uint64_t>(L_75, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_76, ((int64_t)3))), (int64_t)L_77)));
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF L_79 = ___0_value;
		*(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF*)L_78 = L_79;
		uint64_t L_80 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_80, ((int64_t)4)));
		goto IL_0198;
	}

IL_017f:
	{
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_81 = V_4;
		uint64_t L_82 = V_6;
		uint64_t L_83 = V_5;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_84;
		L_84 = il2cpp_unsafe_add_byte_offset<BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF,uint64_t>(L_81, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_82, (int64_t)L_83)));
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF L_85 = ___0_value;
		*(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF*)L_84 = L_85;
		uint64_t L_86 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_86, ((int64_t)1)));
	}

IL_0198:
	{
		uint64_t L_87 = V_6;
		uint64_t L_88 = V_3;
		if ((!(((uint64_t)L_87) >= ((uint64_t)L_88))))
		{
			goto IL_017f;
		}
	}
	{
		return;
	}
}
// Method Definition Index: 13825
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_CopyTo_mDB36175473DF442B57F349C488305879B0CB7255_gshared (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671* __this, Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671 ___0_destination, const RuntimeMethod* method) 
{
	ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_mF5DDBBF4783D0A55C88BFD79E1A1F8B5DDCF60C5_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0038;
		}
	}
	{
		Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671 L_2 = ___0_destination;
		ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F L_3 = L_2.____pointer;
		V_0 = L_3;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF, (Il2CppByReference*)(&V_0));
		ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F L_5 = __this->____pointer;
		V_0 = L_5;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF, (Il2CppByReference*)(&V_0));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF_m3ED952566857853E08E1F43BA48367A61065281A(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return;
	}

IL_0038:
	{
		ThrowHelper_ThrowArgumentException_DestinationTooShort_m6468934A3BBB67DBC5BAEF7A64D91BD5BBBB3D4D(NULL);
		return;
	}
}
// Method Definition Index: 13826
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_TryCopyTo_m6AD1D8620133D11250A9BA747F911F499F41A35B_gshared (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671* __this, Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671 ___0_destination, const RuntimeMethod* method) 
{
	bool V_0 = false;
	ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		V_0 = (bool)0;
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_mF5DDBBF4783D0A55C88BFD79E1A1F8B5DDCF60C5_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_003b;
		}
	}
	{
		Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671 L_2 = ___0_destination;
		ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F L_3 = L_2.____pointer;
		V_1 = L_3;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF, (Il2CppByReference*)(&V_1));
		ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F L_5 = __this->____pointer;
		V_1 = L_5;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF, (Il2CppByReference*)(&V_1));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF_m3ED952566857853E08E1F43BA48367A61065281A(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		V_0 = (bool)1;
	}

IL_003b:
	{
		bool L_8 = V_0;
		return L_8;
	}
}
// Method Definition Index: 13827
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_op_Equality_mC29DD5ED4E3120237F7D42B1B965D6F5A3E7647C_gshared (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671 ___0_left, Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671 ___1_right, const RuntimeMethod* method) 
{
	ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671 L_0 = ___0_left;
		int32_t L_1 = L_0.____length;
		Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671 L_2 = ___1_right;
		int32_t L_3 = L_2.____length;
		if ((!(((uint32_t)L_1) == ((uint32_t)L_3))))
		{
			goto IL_0030;
		}
	}
	{
		Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671 L_4 = ___0_left;
		ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F L_5 = L_4.____pointer;
		V_0 = L_5;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF, (Il2CppByReference*)(&V_0));
		Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671 L_7 = ___1_right;
		ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F L_8 = L_7.____pointer;
		V_0 = L_8;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_9;
		L_9 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF, (Il2CppByReference*)(&V_0));
		bool L_10;
		L_10 = il2cpp_unsafe_are_same(L_6, L_9);
		return L_10;
	}

IL_0030:
	{
		return (bool)0;
	}
}
// Method Definition Index: 13828
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ReadOnlySpan_1_t3D251E6627DBB106D7D34F8DC234D102B691FD49 Span_1_op_Implicit_m2220F921A4A87212DB830E9F53F707FDD56C1C96_gshared (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671 ___0_span, const RuntimeMethod* method) 
{
	ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671 L_0 = ___0_span;
		ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F L_1 = L_0.____pointer;
		V_0 = L_1;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_2;
		L_2 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF, (Il2CppByReference*)(&V_0));
		Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671 L_3 = ___0_span;
		int32_t L_4 = L_3.____length;
		ReadOnlySpan_1_t3D251E6627DBB106D7D34F8DC234D102B691FD49 L_5;
		memset((&L_5), 0, sizeof(L_5));
		ReadOnlySpan_1__ctor_m1C72072A1F9FFD48A21C4C5F2A81954708D29495_inline((&L_5), L_2, L_4, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 18));
		return L_5;
	}
}
// Method Definition Index: 13829
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Span_1_ToString_m61DA7965FCDF9AEAB712B342C74927827FE9DBE1_gshared (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral0DB46164953228904843938099AF66650313FEE5);
		s_Il2CppMethodInitialized = true;
	}
	Il2CppChar* V_0 = NULL;
	ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_0 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_1;
		L_1 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_0, NULL);
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_2 = { reinterpret_cast<intptr_t> (&il2cpp_defaults.char_class->byval_arg) };
		Type_t* L_3;
		L_3 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_2, NULL);
		bool L_4;
		L_4 = Type_op_Equality_m99930A0E44E420A685FABA60E60BA1CC5FA0EBDC(L_1, L_3, NULL);
		if (!L_4)
		{
			goto IL_003e;
		}
	}
	{
		ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F L_5 = __this->____pointer;
		V_1 = L_5;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF, (Il2CppByReference*)(&V_1));
		Il2CppChar* L_7;
		L_7 = il2cpp_unsafe_as_ref<Il2CppChar>(L_6);
		V_0 = L_7;
		Il2CppChar* L_8 = V_0;
		int32_t L_9 = __this->____length;
		String_t* L_10;
		L_10 = String_CreateString_m3F8794FEB452558B8A68C65E1F0B603B3D94E0E2(NULL, (Il2CppChar*)((uintptr_t)L_8), 0, L_9, NULL);
		return L_10;
	}

IL_003e:
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_11 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_12;
		L_12 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_11, NULL);
		NullCheck((MemberInfo_t*)L_12);
		String_t* L_13;
		L_13 = VirtualFuncInvoker0< String_t* >::Invoke(7, (MemberInfo_t*)L_12);
		int32_t L_14 = __this->____length;
		int32_t L_15 = L_14;
		RuntimeObject* L_16 = Box(il2cpp_defaults.int32_class, &L_15);
		String_t* L_17;
		L_17 = String_Format_mFB7DA489BD99F4670881FF50EC017BFB0A5C0987(_stringLiteral0DB46164953228904843938099AF66650313FEE5, (RuntimeObject*)L_13, L_16, NULL);
		return L_17;
	}
}
// Method Definition Index: 13830
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671 Span_1_Slice_m741EAC6D068EC4FEFDF5A59805E791BC7F5A4046_gshared (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671* __this, int32_t ___0_start, const RuntimeMethod* method) 
{
	ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) > ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F L_2 = __this->____pointer;
		V_0 = L_2;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_start;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_5;
		L_5 = il2cpp_unsafe_add<BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF,int32_t>(L_3, L_4);
		int32_t L_6 = __this->____length;
		int32_t L_7 = ___0_start;
		Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671 L_8;
		memset((&L_8), 0, sizeof(L_8));
		Span_1__ctor_m4F32D7D5653D612D237DDFD35C9850E16CA11C18_inline((&L_8), L_5, ((int32_t)il2cpp_codegen_subtract(L_6, L_7)), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_8;
	}
}
// Method Definition Index: 13831
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671 Span_1_Slice_mE29D916E1DACE071E402F8EB19D17EB3E109DCB4_gshared (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671* __this, int32_t ___0_start, int32_t ___1_length, const RuntimeMethod* method) 
{
	ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0014;
		}
	}
	{
		int32_t L_2 = ___1_length;
		int32_t L_3 = __this->____length;
		int32_t L_4 = ___0_start;
		if ((!(((uint32_t)L_2) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(L_3, L_4))))))
		{
			goto IL_0019;
		}
	}

IL_0014:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0019:
	{
		ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F L_5 = __this->____pointer;
		V_0 = L_5;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF, (Il2CppByReference*)(&V_0));
		int32_t L_7 = ___0_start;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_8;
		L_8 = il2cpp_unsafe_add<BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF,int32_t>(L_6, L_7);
		int32_t L_9 = ___1_length;
		Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671 L_10;
		memset((&L_10), 0, sizeof(L_10));
		Span_1__ctor_m4F32D7D5653D612D237DDFD35C9850E16CA11C18_inline((&L_10), L_8, L_9, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_10;
	}
}
// Method Definition Index: 13832
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B* Span_1_ToArray_m8F1E0A08EFD1747DD532F65A14686F0783BDF711_gshared (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671* __this, const RuntimeMethod* method) 
{
	ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_000e;
		}
	}
	{
		BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B* L_1;
		L_1 = Array_Empty_TisBlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF_mCD144DB8231D7EA3AF1DB4FEB94681C9E580CA9D_inline(il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 20));
		return L_1;
	}

IL_000e:
	{
		int32_t L_2 = __this->____length;
		BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B* L_3 = (BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B*)(BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B*)SZArrayNew(il2cpp_rgctx_data(InitializedTypeInfo(method->klass)->rgctx_data, 21), (uint32_t)L_2);
		BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B* L_4 = L_3;
		NullCheck((RuntimeArray*)L_4);
		uint8_t* L_5;
		L_5 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_4, NULL);
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_6;
		L_6 = il2cpp_unsafe_as_ref<BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF>(L_5);
		ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F L_7 = __this->____pointer;
		V_0 = L_7;
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_8;
		L_8 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF, (Il2CppByReference*)(&V_0));
		int32_t L_9 = __this->____length;
		Buffer_Memmove_TisBlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF_m3ED952566857853E08E1F43BA48367A61065281A(L_6, L_8, (uint64_t)((int64_t)L_9), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return L_4;
	}
}
// Method Definition Index: 13833
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mF5DDBBF4783D0A55C88BFD79E1A1F8B5DDCF60C5_gshared (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13834
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_Equals_m821BBBACF2E09DB186D7EF0FBC7056846FD3DFC2_gshared (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteral69508A540AFD085A745316DD7D6345B1C8CC662D)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13835
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_GetHashCode_m9FCC61383FE9C38A07042FAA217F7FB5712DD8AF_gshared (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671* __this, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteralECE618215BAC99C6FD12D8A273CC2118945EDCC8)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13836
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671 Span_1_op_Implicit_m6D770C0E8BB6F22DB2D3418BC64730EB2A4F116F_gshared (BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B* ___0_array, const RuntimeMethod* method) 
{
	{
		BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B* L_0 = ___0_array;
		Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671 L_1;
		memset((&L_1), 0, sizeof(L_1));
		Span_1__ctor_mCA3E1FB18A1006842617ABD22464535398F4655D_inline((&L_1), L_0, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 22));
		return L_1;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// Method Definition Index: 13818
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mF80BBD6B1E4103830CA7E538A9EE8ABA983D97EC_gshared (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C* __this, BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515* ___0_array, const RuntimeMethod* method) 
{
	BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6));
		goto IL_0037;
	}

IL_0037:
	{
		BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_4;
		L_4 = il2cpp_unsafe_as_ref<BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6>(L_3);
		ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13819
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m4773E314A1E2A0CD2AC8579DB2E03903AE598691_gshared (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C* __this, BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515* ___0_array, int32_t ___1_start, int32_t ___2_length, const RuntimeMethod* method) 
{
	BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_0016;
		}
	}
	{
		int32_t L_1 = ___1_start;
		if (L_1)
		{
			goto IL_0009;
		}
	}
	{
		int32_t L_2 = ___2_length;
		if (!L_2)
		{
			goto IL_000e;
		}
	}

IL_0009:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C));
		return;
	}

IL_0016:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6));
		goto IL_0042;
	}

IL_0042:
	{
		int32_t L_4 = ___1_start;
		BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515* L_5 = ___0_array;
		NullCheck(L_5);
		if ((!(((uint32_t)L_4) <= ((uint32_t)((int32_t)(((RuntimeArray*)L_5)->max_length))))))
		{
			goto IL_0050;
		}
	}
	{
		int32_t L_6 = ___2_length;
		BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515* L_7 = ___0_array;
		NullCheck(L_7);
		int32_t L_8 = ___1_start;
		if ((!(((uint32_t)L_6) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(((int32_t)(((RuntimeArray*)L_7)->max_length)), L_8))))))
		{
			goto IL_0055;
		}
	}

IL_0050:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0055:
	{
		BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515* L_9 = ___0_array;
		NullCheck((RuntimeArray*)L_9);
		uint8_t* L_10;
		L_10 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_9, NULL);
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_11;
		L_11 = il2cpp_unsafe_as_ref<BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6>(L_10);
		int32_t L_12 = ___1_start;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_13;
		L_13 = il2cpp_unsafe_add<BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6,int32_t>(L_11, L_12);
		ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 L_14;
		memset((&L_14), 0, sizeof(L_14));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_14), L_13);
		__this->____pointer = L_14;
		int32_t L_15 = ___2_length;
		__this->____length = L_15;
		return;
	}
}
// Method Definition Index: 13820
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m9D758D2C22722BA0C966A2EFEC231CF327AE27CA_gshared (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C* __this, void* ___0_pointer, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		goto IL_0016;
	}

IL_0016:
	{
		int32_t L_0 = ___1_length;
		if ((((int32_t)L_0) >= ((int32_t)0)))
		{
			goto IL_001f;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_001f:
	{
		void* L_1 = ___0_pointer;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_2;
		L_2 = il2cpp_unsafe_as_ref<BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6>((uint8_t*)L_1);
		ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 L_3;
		memset((&L_3), 0, sizeof(L_3));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_3), L_2);
		__this->____pointer = L_3;
		int32_t L_4 = ___1_length;
		__this->____length = L_4;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m6C9685057F182B8896BBE6B894974625E6DA4221_gshared (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C* __this, BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_0 = ___0_ptr;
		ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13822
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* Span_1_get_Item_m867435369EE8AA05643B069D12342448A6C0D8B8_gshared (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C* __this, int32_t ___0_index, const RuntimeMethod* method) 
{
	ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_index;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) >= ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowIndexOutOfRangeException_m86F753A24E2765A35546BA6352A7E4F0BB8A66B5(NULL);
	}

IL_000e:
	{
		ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 L_2 = __this->____pointer;
		V_0 = L_2;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_index;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_5;
		L_5 = il2cpp_unsafe_add<BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6,int32_t>(L_3, L_4);
		return L_5;
	}
}
// Method Definition Index: 13823
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* Span_1_GetPinnableReference_mFDC7B3C56A93A8334A1354CDDA56EC6652D716FF_gshared (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C* __this, const RuntimeMethod* method) 
{
	ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_0010;
		}
	}
	{
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_1;
		L_1 = il2cpp_unsafe_as_ref<BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6>((void*)((uintptr_t)0));
		return L_1;
	}

IL_0010:
	{
		ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 L_2 = __this->____pointer;
		V_0 = L_2;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6, (Il2CppByReference*)(&V_0));
		return L_3;
	}
}
// Method Definition Index: 13824
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_Fill_mE47EFB4483702E1A60F8602814B99229737F7808_gshared (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C* __this, BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6 ___0_value, const RuntimeMethod* method) 
{
	uint32_t V_0 = 0;
	BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6 V_1;
	memset((&V_1), 0, sizeof(V_1));
	ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 V_2;
	memset((&V_2), 0, sizeof(V_2));
	uint64_t V_3 = 0;
	BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* V_4 = NULL;
	uint64_t V_5 = 0;
	uint64_t V_6 = 0;
	{
		int32_t L_0;
		L_0 = il2cpp_unsafe_sizeof<BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6>();
		if ((!(((uint32_t)L_0) == ((uint32_t)1))))
		{
			goto IL_0037;
		}
	}
	{
		int32_t L_1 = __this->____length;
		V_0 = (uint32_t)L_1;
		uint32_t L_2 = V_0;
		if (L_2)
		{
			goto IL_0013;
		}
	}
	{
		return;
	}

IL_0013:
	{
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6 L_3 = ___0_value;
		V_1 = L_3;
		ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 L_4 = __this->____pointer;
		V_2 = L_4;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_5;
		L_5 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6, (Il2CppByReference*)(&V_2));
		uint8_t* L_6;
		L_6 = il2cpp_unsafe_as_ref<uint8_t>(L_5);
		uint8_t* L_7;
		L_7 = il2cpp_unsafe_as_ref<uint8_t>((&V_1));
		int32_t L_8 = *((uint8_t*)L_7);
		uint32_t L_9 = V_0;
		Unsafe_InitBlockUnaligned_m6F2353EB9ABC9320E61629FAEE23948C80BFF03A(L_6, (uint8_t)L_8, L_9, NULL);
		return;
	}

IL_0037:
	{
		int32_t L_10 = __this->____length;
		V_3 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_10));
		uint64_t L_11 = V_3;
		if (L_11)
		{
			goto IL_0043;
		}
	}
	{
		return;
	}

IL_0043:
	{
		ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 L_12 = __this->____pointer;
		V_2 = L_12;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_13;
		L_13 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6, (Il2CppByReference*)(&V_2));
		V_4 = L_13;
		int32_t L_14;
		L_14 = il2cpp_unsafe_sizeof<BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6>();
		V_5 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_14));
		V_6 = (uint64_t)((int64_t)0);
		goto IL_0110;
	}

IL_0064:
	{
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_15 = V_4;
		uint64_t L_16 = V_6;
		uint64_t L_17 = V_5;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_18;
		L_18 = il2cpp_unsafe_add_byte_offset<BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6,uint64_t>(L_15, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_16, (int64_t)L_17)));
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6 L_19 = ___0_value;
		*(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6*)L_18 = L_19;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_20 = V_4;
		uint64_t L_21 = V_6;
		uint64_t L_22 = V_5;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_23;
		L_23 = il2cpp_unsafe_add_byte_offset<BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6,uint64_t>(L_20, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_21, ((int64_t)1))), (int64_t)L_22)));
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6 L_24 = ___0_value;
		*(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6*)L_23 = L_24;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_25 = V_4;
		uint64_t L_26 = V_6;
		uint64_t L_27 = V_5;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_28;
		L_28 = il2cpp_unsafe_add_byte_offset<BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6,uint64_t>(L_25, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_26, ((int64_t)2))), (int64_t)L_27)));
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6 L_29 = ___0_value;
		*(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6*)L_28 = L_29;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_30 = V_4;
		uint64_t L_31 = V_6;
		uint64_t L_32 = V_5;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_33;
		L_33 = il2cpp_unsafe_add_byte_offset<BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6,uint64_t>(L_30, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_31, ((int64_t)3))), (int64_t)L_32)));
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6 L_34 = ___0_value;
		*(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6*)L_33 = L_34;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_35 = V_4;
		uint64_t L_36 = V_6;
		uint64_t L_37 = V_5;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_38;
		L_38 = il2cpp_unsafe_add_byte_offset<BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6,uint64_t>(L_35, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_36, ((int64_t)4))), (int64_t)L_37)));
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6 L_39 = ___0_value;
		*(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6*)L_38 = L_39;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_40 = V_4;
		uint64_t L_41 = V_6;
		uint64_t L_42 = V_5;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_43;
		L_43 = il2cpp_unsafe_add_byte_offset<BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6,uint64_t>(L_40, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_41, ((int64_t)5))), (int64_t)L_42)));
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6 L_44 = ___0_value;
		*(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6*)L_43 = L_44;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_45 = V_4;
		uint64_t L_46 = V_6;
		uint64_t L_47 = V_5;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_48;
		L_48 = il2cpp_unsafe_add_byte_offset<BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6,uint64_t>(L_45, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_46, ((int64_t)6))), (int64_t)L_47)));
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6 L_49 = ___0_value;
		*(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6*)L_48 = L_49;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_50 = V_4;
		uint64_t L_51 = V_6;
		uint64_t L_52 = V_5;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_53;
		L_53 = il2cpp_unsafe_add_byte_offset<BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6,uint64_t>(L_50, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_51, ((int64_t)7))), (int64_t)L_52)));
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6 L_54 = ___0_value;
		*(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6*)L_53 = L_54;
		uint64_t L_55 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_55, ((int64_t)8)));
	}

IL_0110:
	{
		uint64_t L_56 = V_6;
		uint64_t L_57 = V_3;
		if ((!(((uint64_t)L_56) >= ((uint64_t)((int64_t)((int64_t)L_57&((int64_t)((int32_t)-8))))))))
		{
			goto IL_0064;
		}
	}
	{
		uint64_t L_58 = V_6;
		uint64_t L_59 = V_3;
		if ((!(((uint64_t)L_58) < ((uint64_t)((int64_t)((int64_t)L_59&((int64_t)((int32_t)-4))))))))
		{
			goto IL_0198;
		}
	}
	{
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_60 = V_4;
		uint64_t L_61 = V_6;
		uint64_t L_62 = V_5;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_63;
		L_63 = il2cpp_unsafe_add_byte_offset<BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6,uint64_t>(L_60, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_61, (int64_t)L_62)));
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6 L_64 = ___0_value;
		*(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6*)L_63 = L_64;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_65 = V_4;
		uint64_t L_66 = V_6;
		uint64_t L_67 = V_5;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_68;
		L_68 = il2cpp_unsafe_add_byte_offset<BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6,uint64_t>(L_65, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_66, ((int64_t)1))), (int64_t)L_67)));
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6 L_69 = ___0_value;
		*(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6*)L_68 = L_69;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_70 = V_4;
		uint64_t L_71 = V_6;
		uint64_t L_72 = V_5;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_73;
		L_73 = il2cpp_unsafe_add_byte_offset<BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6,uint64_t>(L_70, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_71, ((int64_t)2))), (int64_t)L_72)));
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6 L_74 = ___0_value;
		*(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6*)L_73 = L_74;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_75 = V_4;
		uint64_t L_76 = V_6;
		uint64_t L_77 = V_5;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_78;
		L_78 = il2cpp_unsafe_add_byte_offset<BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6,uint64_t>(L_75, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_76, ((int64_t)3))), (int64_t)L_77)));
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6 L_79 = ___0_value;
		*(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6*)L_78 = L_79;
		uint64_t L_80 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_80, ((int64_t)4)));
		goto IL_0198;
	}

IL_017f:
	{
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_81 = V_4;
		uint64_t L_82 = V_6;
		uint64_t L_83 = V_5;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_84;
		L_84 = il2cpp_unsafe_add_byte_offset<BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6,uint64_t>(L_81, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_82, (int64_t)L_83)));
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6 L_85 = ___0_value;
		*(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6*)L_84 = L_85;
		uint64_t L_86 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_86, ((int64_t)1)));
	}

IL_0198:
	{
		uint64_t L_87 = V_6;
		uint64_t L_88 = V_3;
		if ((!(((uint64_t)L_87) >= ((uint64_t)L_88))))
		{
			goto IL_017f;
		}
	}
	{
		return;
	}
}
// Method Definition Index: 13825
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_CopyTo_mADE83344EFC15B4FE8899F6415FD1D573DDAAF14_gshared (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C* __this, Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C ___0_destination, const RuntimeMethod* method) 
{
	ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_m0A22C7814AA57E51DE4EDC241010E81DA05627F9_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0038;
		}
	}
	{
		Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C L_2 = ___0_destination;
		ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 L_3 = L_2.____pointer;
		V_0 = L_3;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6, (Il2CppByReference*)(&V_0));
		ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 L_5 = __this->____pointer;
		V_0 = L_5;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6, (Il2CppByReference*)(&V_0));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6_mE178FB3796A6A1A4F519A7BA46829D3A14F94CBD(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return;
	}

IL_0038:
	{
		ThrowHelper_ThrowArgumentException_DestinationTooShort_m6468934A3BBB67DBC5BAEF7A64D91BD5BBBB3D4D(NULL);
		return;
	}
}
// Method Definition Index: 13826
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_TryCopyTo_m8B960FE0CC0868A5B0A65937DAFCB2929F7E625A_gshared (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C* __this, Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C ___0_destination, const RuntimeMethod* method) 
{
	bool V_0 = false;
	ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		V_0 = (bool)0;
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_m0A22C7814AA57E51DE4EDC241010E81DA05627F9_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_003b;
		}
	}
	{
		Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C L_2 = ___0_destination;
		ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 L_3 = L_2.____pointer;
		V_1 = L_3;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6, (Il2CppByReference*)(&V_1));
		ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 L_5 = __this->____pointer;
		V_1 = L_5;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6, (Il2CppByReference*)(&V_1));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6_mE178FB3796A6A1A4F519A7BA46829D3A14F94CBD(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		V_0 = (bool)1;
	}

IL_003b:
	{
		bool L_8 = V_0;
		return L_8;
	}
}
// Method Definition Index: 13827
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_op_Equality_mAE94DECC01F3A4D5F0C5FBA2E7907BF40CF7C9D1_gshared (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C ___0_left, Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C ___1_right, const RuntimeMethod* method) 
{
	ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C L_0 = ___0_left;
		int32_t L_1 = L_0.____length;
		Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C L_2 = ___1_right;
		int32_t L_3 = L_2.____length;
		if ((!(((uint32_t)L_1) == ((uint32_t)L_3))))
		{
			goto IL_0030;
		}
	}
	{
		Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C L_4 = ___0_left;
		ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 L_5 = L_4.____pointer;
		V_0 = L_5;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6, (Il2CppByReference*)(&V_0));
		Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C L_7 = ___1_right;
		ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 L_8 = L_7.____pointer;
		V_0 = L_8;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_9;
		L_9 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6, (Il2CppByReference*)(&V_0));
		bool L_10;
		L_10 = il2cpp_unsafe_are_same(L_6, L_9);
		return L_10;
	}

IL_0030:
	{
		return (bool)0;
	}
}
// Method Definition Index: 13828
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ReadOnlySpan_1_tD81D75B0E0400301DC302920BCCBB9FBD97CA2A8 Span_1_op_Implicit_m695E0E4F838DCFF3945ED16D10D75517964045EF_gshared (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C ___0_span, const RuntimeMethod* method) 
{
	ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C L_0 = ___0_span;
		ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 L_1 = L_0.____pointer;
		V_0 = L_1;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_2;
		L_2 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6, (Il2CppByReference*)(&V_0));
		Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C L_3 = ___0_span;
		int32_t L_4 = L_3.____length;
		ReadOnlySpan_1_tD81D75B0E0400301DC302920BCCBB9FBD97CA2A8 L_5;
		memset((&L_5), 0, sizeof(L_5));
		ReadOnlySpan_1__ctor_m143F99ABFB2902F2BE3AB6725D5F8AEAECDEC5F4_inline((&L_5), L_2, L_4, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 18));
		return L_5;
	}
}
// Method Definition Index: 13829
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Span_1_ToString_m26793FC09A6CDDCD4538326D81253A0527CE98E3_gshared (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral0DB46164953228904843938099AF66650313FEE5);
		s_Il2CppMethodInitialized = true;
	}
	Il2CppChar* V_0 = NULL;
	ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_0 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_1;
		L_1 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_0, NULL);
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_2 = { reinterpret_cast<intptr_t> (&il2cpp_defaults.char_class->byval_arg) };
		Type_t* L_3;
		L_3 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_2, NULL);
		bool L_4;
		L_4 = Type_op_Equality_m99930A0E44E420A685FABA60E60BA1CC5FA0EBDC(L_1, L_3, NULL);
		if (!L_4)
		{
			goto IL_003e;
		}
	}
	{
		ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 L_5 = __this->____pointer;
		V_1 = L_5;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6, (Il2CppByReference*)(&V_1));
		Il2CppChar* L_7;
		L_7 = il2cpp_unsafe_as_ref<Il2CppChar>(L_6);
		V_0 = L_7;
		Il2CppChar* L_8 = V_0;
		int32_t L_9 = __this->____length;
		String_t* L_10;
		L_10 = String_CreateString_m3F8794FEB452558B8A68C65E1F0B603B3D94E0E2(NULL, (Il2CppChar*)((uintptr_t)L_8), 0, L_9, NULL);
		return L_10;
	}

IL_003e:
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_11 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_12;
		L_12 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_11, NULL);
		NullCheck((MemberInfo_t*)L_12);
		String_t* L_13;
		L_13 = VirtualFuncInvoker0< String_t* >::Invoke(7, (MemberInfo_t*)L_12);
		int32_t L_14 = __this->____length;
		int32_t L_15 = L_14;
		RuntimeObject* L_16 = Box(il2cpp_defaults.int32_class, &L_15);
		String_t* L_17;
		L_17 = String_Format_mFB7DA489BD99F4670881FF50EC017BFB0A5C0987(_stringLiteral0DB46164953228904843938099AF66650313FEE5, (RuntimeObject*)L_13, L_16, NULL);
		return L_17;
	}
}
// Method Definition Index: 13830
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C Span_1_Slice_m995A6B6EED381840AD58F75CE7DCCF6FADD18220_gshared (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C* __this, int32_t ___0_start, const RuntimeMethod* method) 
{
	ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) > ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 L_2 = __this->____pointer;
		V_0 = L_2;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_start;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_5;
		L_5 = il2cpp_unsafe_add<BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6,int32_t>(L_3, L_4);
		int32_t L_6 = __this->____length;
		int32_t L_7 = ___0_start;
		Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C L_8;
		memset((&L_8), 0, sizeof(L_8));
		Span_1__ctor_m6C9685057F182B8896BBE6B894974625E6DA4221_inline((&L_8), L_5, ((int32_t)il2cpp_codegen_subtract(L_6, L_7)), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_8;
	}
}
// Method Definition Index: 13831
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C Span_1_Slice_m24491E84690B3EDABF70EAD4DC1120D3E74439F7_gshared (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C* __this, int32_t ___0_start, int32_t ___1_length, const RuntimeMethod* method) 
{
	ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0014;
		}
	}
	{
		int32_t L_2 = ___1_length;
		int32_t L_3 = __this->____length;
		int32_t L_4 = ___0_start;
		if ((!(((uint32_t)L_2) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(L_3, L_4))))))
		{
			goto IL_0019;
		}
	}

IL_0014:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0019:
	{
		ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 L_5 = __this->____pointer;
		V_0 = L_5;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6, (Il2CppByReference*)(&V_0));
		int32_t L_7 = ___0_start;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_8;
		L_8 = il2cpp_unsafe_add<BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6,int32_t>(L_6, L_7);
		int32_t L_9 = ___1_length;
		Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C L_10;
		memset((&L_10), 0, sizeof(L_10));
		Span_1__ctor_m6C9685057F182B8896BBE6B894974625E6DA4221_inline((&L_10), L_8, L_9, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_10;
	}
}
// Method Definition Index: 13832
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515* Span_1_ToArray_m49DE62393086FAB33829C8DEDBDC26A04FAB9A18_gshared (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C* __this, const RuntimeMethod* method) 
{
	ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_000e;
		}
	}
	{
		BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515* L_1;
		L_1 = Array_Empty_TisBlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6_m7D78EA1928C7379C27FE7337353FB51BEFD7476E_inline(il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 20));
		return L_1;
	}

IL_000e:
	{
		int32_t L_2 = __this->____length;
		BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515* L_3 = (BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515*)(BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515*)SZArrayNew(il2cpp_rgctx_data(InitializedTypeInfo(method->klass)->rgctx_data, 21), (uint32_t)L_2);
		BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515* L_4 = L_3;
		NullCheck((RuntimeArray*)L_4);
		uint8_t* L_5;
		L_5 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_4, NULL);
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_6;
		L_6 = il2cpp_unsafe_as_ref<BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6>(L_5);
		ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 L_7 = __this->____pointer;
		V_0 = L_7;
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_8;
		L_8 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6, (Il2CppByReference*)(&V_0));
		int32_t L_9 = __this->____length;
		Buffer_Memmove_TisBlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6_mE178FB3796A6A1A4F519A7BA46829D3A14F94CBD(L_6, L_8, (uint64_t)((int64_t)L_9), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return L_4;
	}
}
// Method Definition Index: 13833
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m0A22C7814AA57E51DE4EDC241010E81DA05627F9_gshared (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13834
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_Equals_m7E559DAFF58ED501B6B868B7FEA6A39292BEDB6D_gshared (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteral69508A540AFD085A745316DD7D6345B1C8CC662D)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13835
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_GetHashCode_m62B31265609A664C77B9CE4C34AC241A0BE8CA50_gshared (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C* __this, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteralECE618215BAC99C6FD12D8A273CC2118945EDCC8)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13836
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C Span_1_op_Implicit_m8E913E79442066F7AA96DCD667A45B556E649EDF_gshared (BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515* ___0_array, const RuntimeMethod* method) 
{
	{
		BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515* L_0 = ___0_array;
		Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C L_1;
		memset((&L_1), 0, sizeof(L_1));
		Span_1__ctor_mF80BBD6B1E4103830CA7E538A9EE8ABA983D97EC_inline((&L_1), L_0, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 22));
		return L_1;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// Method Definition Index: 13818
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mB392265B6A34EA59542E156547D1143AC4B0EBD4_gshared (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B* __this, BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A* ___0_array, const RuntimeMethod* method) 
{
	BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C));
		goto IL_0037;
	}

IL_0037:
	{
		BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_4;
		L_4 = il2cpp_unsafe_as_ref<BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C>(L_3);
		ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13819
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m0006DE3E774E4B0884E9F594FA2D00D8C2831FE8_gshared (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B* __this, BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A* ___0_array, int32_t ___1_start, int32_t ___2_length, const RuntimeMethod* method) 
{
	BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_0016;
		}
	}
	{
		int32_t L_1 = ___1_start;
		if (L_1)
		{
			goto IL_0009;
		}
	}
	{
		int32_t L_2 = ___2_length;
		if (!L_2)
		{
			goto IL_000e;
		}
	}

IL_0009:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B));
		return;
	}

IL_0016:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C));
		goto IL_0042;
	}

IL_0042:
	{
		int32_t L_4 = ___1_start;
		BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A* L_5 = ___0_array;
		NullCheck(L_5);
		if ((!(((uint32_t)L_4) <= ((uint32_t)((int32_t)(((RuntimeArray*)L_5)->max_length))))))
		{
			goto IL_0050;
		}
	}
	{
		int32_t L_6 = ___2_length;
		BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A* L_7 = ___0_array;
		NullCheck(L_7);
		int32_t L_8 = ___1_start;
		if ((!(((uint32_t)L_6) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(((int32_t)(((RuntimeArray*)L_7)->max_length)), L_8))))))
		{
			goto IL_0055;
		}
	}

IL_0050:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0055:
	{
		BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A* L_9 = ___0_array;
		NullCheck((RuntimeArray*)L_9);
		uint8_t* L_10;
		L_10 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_9, NULL);
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_11;
		L_11 = il2cpp_unsafe_as_ref<BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C>(L_10);
		int32_t L_12 = ___1_start;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_13;
		L_13 = il2cpp_unsafe_add<BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C,int32_t>(L_11, L_12);
		ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF L_14;
		memset((&L_14), 0, sizeof(L_14));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_14), L_13);
		__this->____pointer = L_14;
		int32_t L_15 = ___2_length;
		__this->____length = L_15;
		return;
	}
}
// Method Definition Index: 13820
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mFAC1E53095EFC84F292BA7916C6D66DC5FCA690A_gshared (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B* __this, void* ___0_pointer, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		goto IL_0016;
	}

IL_0016:
	{
		int32_t L_0 = ___1_length;
		if ((((int32_t)L_0) >= ((int32_t)0)))
		{
			goto IL_001f;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_001f:
	{
		void* L_1 = ___0_pointer;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_2;
		L_2 = il2cpp_unsafe_as_ref<BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C>((uint8_t*)L_1);
		ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF L_3;
		memset((&L_3), 0, sizeof(L_3));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_3), L_2);
		__this->____pointer = L_3;
		int32_t L_4 = ___1_length;
		__this->____length = L_4;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m773D9E2695DC1A1EAF17FDC94204CB7B1DE21582_gshared (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B* __this, BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_0 = ___0_ptr;
		ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13822
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* Span_1_get_Item_m73DC27BE948BD550479732B2640E4B90268CFFC2_gshared (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B* __this, int32_t ___0_index, const RuntimeMethod* method) 
{
	ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_index;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) >= ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowIndexOutOfRangeException_m86F753A24E2765A35546BA6352A7E4F0BB8A66B5(NULL);
	}

IL_000e:
	{
		ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF L_2 = __this->____pointer;
		V_0 = L_2;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_index;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_5;
		L_5 = il2cpp_unsafe_add<BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C,int32_t>(L_3, L_4);
		return L_5;
	}
}
// Method Definition Index: 13823
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* Span_1_GetPinnableReference_m4620B459B437004750FD2FF550B7737CE63F805E_gshared (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B* __this, const RuntimeMethod* method) 
{
	ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_0010;
		}
	}
	{
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_1;
		L_1 = il2cpp_unsafe_as_ref<BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C>((void*)((uintptr_t)0));
		return L_1;
	}

IL_0010:
	{
		ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF L_2 = __this->____pointer;
		V_0 = L_2;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C, (Il2CppByReference*)(&V_0));
		return L_3;
	}
}
// Method Definition Index: 13824
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_Fill_mE0AAF30EB7691430DF93CE9A8E65AF914A3EE3E8_gshared (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B* __this, BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C ___0_value, const RuntimeMethod* method) 
{
	uint32_t V_0 = 0;
	BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C V_1;
	memset((&V_1), 0, sizeof(V_1));
	ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF V_2;
	memset((&V_2), 0, sizeof(V_2));
	uint64_t V_3 = 0;
	BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* V_4 = NULL;
	uint64_t V_5 = 0;
	uint64_t V_6 = 0;
	{
		int32_t L_0;
		L_0 = il2cpp_unsafe_sizeof<BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C>();
		if ((!(((uint32_t)L_0) == ((uint32_t)1))))
		{
			goto IL_0037;
		}
	}
	{
		int32_t L_1 = __this->____length;
		V_0 = (uint32_t)L_1;
		uint32_t L_2 = V_0;
		if (L_2)
		{
			goto IL_0013;
		}
	}
	{
		return;
	}

IL_0013:
	{
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C L_3 = ___0_value;
		V_1 = L_3;
		ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF L_4 = __this->____pointer;
		V_2 = L_4;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_5;
		L_5 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C, (Il2CppByReference*)(&V_2));
		uint8_t* L_6;
		L_6 = il2cpp_unsafe_as_ref<uint8_t>(L_5);
		uint8_t* L_7;
		L_7 = il2cpp_unsafe_as_ref<uint8_t>((&V_1));
		int32_t L_8 = *((uint8_t*)L_7);
		uint32_t L_9 = V_0;
		Unsafe_InitBlockUnaligned_m6F2353EB9ABC9320E61629FAEE23948C80BFF03A(L_6, (uint8_t)L_8, L_9, NULL);
		return;
	}

IL_0037:
	{
		int32_t L_10 = __this->____length;
		V_3 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_10));
		uint64_t L_11 = V_3;
		if (L_11)
		{
			goto IL_0043;
		}
	}
	{
		return;
	}

IL_0043:
	{
		ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF L_12 = __this->____pointer;
		V_2 = L_12;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_13;
		L_13 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C, (Il2CppByReference*)(&V_2));
		V_4 = L_13;
		int32_t L_14;
		L_14 = il2cpp_unsafe_sizeof<BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C>();
		V_5 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_14));
		V_6 = (uint64_t)((int64_t)0);
		goto IL_0110;
	}

IL_0064:
	{
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_15 = V_4;
		uint64_t L_16 = V_6;
		uint64_t L_17 = V_5;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_18;
		L_18 = il2cpp_unsafe_add_byte_offset<BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C,uint64_t>(L_15, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_16, (int64_t)L_17)));
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C L_19 = ___0_value;
		*(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C*)L_18 = L_19;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_20 = V_4;
		uint64_t L_21 = V_6;
		uint64_t L_22 = V_5;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_23;
		L_23 = il2cpp_unsafe_add_byte_offset<BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C,uint64_t>(L_20, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_21, ((int64_t)1))), (int64_t)L_22)));
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C L_24 = ___0_value;
		*(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C*)L_23 = L_24;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_25 = V_4;
		uint64_t L_26 = V_6;
		uint64_t L_27 = V_5;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_28;
		L_28 = il2cpp_unsafe_add_byte_offset<BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C,uint64_t>(L_25, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_26, ((int64_t)2))), (int64_t)L_27)));
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C L_29 = ___0_value;
		*(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C*)L_28 = L_29;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_30 = V_4;
		uint64_t L_31 = V_6;
		uint64_t L_32 = V_5;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_33;
		L_33 = il2cpp_unsafe_add_byte_offset<BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C,uint64_t>(L_30, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_31, ((int64_t)3))), (int64_t)L_32)));
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C L_34 = ___0_value;
		*(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C*)L_33 = L_34;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_35 = V_4;
		uint64_t L_36 = V_6;
		uint64_t L_37 = V_5;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_38;
		L_38 = il2cpp_unsafe_add_byte_offset<BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C,uint64_t>(L_35, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_36, ((int64_t)4))), (int64_t)L_37)));
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C L_39 = ___0_value;
		*(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C*)L_38 = L_39;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_40 = V_4;
		uint64_t L_41 = V_6;
		uint64_t L_42 = V_5;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_43;
		L_43 = il2cpp_unsafe_add_byte_offset<BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C,uint64_t>(L_40, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_41, ((int64_t)5))), (int64_t)L_42)));
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C L_44 = ___0_value;
		*(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C*)L_43 = L_44;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_45 = V_4;
		uint64_t L_46 = V_6;
		uint64_t L_47 = V_5;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_48;
		L_48 = il2cpp_unsafe_add_byte_offset<BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C,uint64_t>(L_45, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_46, ((int64_t)6))), (int64_t)L_47)));
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C L_49 = ___0_value;
		*(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C*)L_48 = L_49;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_50 = V_4;
		uint64_t L_51 = V_6;
		uint64_t L_52 = V_5;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_53;
		L_53 = il2cpp_unsafe_add_byte_offset<BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C,uint64_t>(L_50, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_51, ((int64_t)7))), (int64_t)L_52)));
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C L_54 = ___0_value;
		*(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C*)L_53 = L_54;
		uint64_t L_55 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_55, ((int64_t)8)));
	}

IL_0110:
	{
		uint64_t L_56 = V_6;
		uint64_t L_57 = V_3;
		if ((!(((uint64_t)L_56) >= ((uint64_t)((int64_t)((int64_t)L_57&((int64_t)((int32_t)-8))))))))
		{
			goto IL_0064;
		}
	}
	{
		uint64_t L_58 = V_6;
		uint64_t L_59 = V_3;
		if ((!(((uint64_t)L_58) < ((uint64_t)((int64_t)((int64_t)L_59&((int64_t)((int32_t)-4))))))))
		{
			goto IL_0198;
		}
	}
	{
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_60 = V_4;
		uint64_t L_61 = V_6;
		uint64_t L_62 = V_5;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_63;
		L_63 = il2cpp_unsafe_add_byte_offset<BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C,uint64_t>(L_60, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_61, (int64_t)L_62)));
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C L_64 = ___0_value;
		*(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C*)L_63 = L_64;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_65 = V_4;
		uint64_t L_66 = V_6;
		uint64_t L_67 = V_5;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_68;
		L_68 = il2cpp_unsafe_add_byte_offset<BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C,uint64_t>(L_65, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_66, ((int64_t)1))), (int64_t)L_67)));
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C L_69 = ___0_value;
		*(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C*)L_68 = L_69;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_70 = V_4;
		uint64_t L_71 = V_6;
		uint64_t L_72 = V_5;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_73;
		L_73 = il2cpp_unsafe_add_byte_offset<BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C,uint64_t>(L_70, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_71, ((int64_t)2))), (int64_t)L_72)));
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C L_74 = ___0_value;
		*(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C*)L_73 = L_74;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_75 = V_4;
		uint64_t L_76 = V_6;
		uint64_t L_77 = V_5;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_78;
		L_78 = il2cpp_unsafe_add_byte_offset<BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C,uint64_t>(L_75, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_76, ((int64_t)3))), (int64_t)L_77)));
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C L_79 = ___0_value;
		*(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C*)L_78 = L_79;
		uint64_t L_80 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_80, ((int64_t)4)));
		goto IL_0198;
	}

IL_017f:
	{
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_81 = V_4;
		uint64_t L_82 = V_6;
		uint64_t L_83 = V_5;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_84;
		L_84 = il2cpp_unsafe_add_byte_offset<BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C,uint64_t>(L_81, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_82, (int64_t)L_83)));
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C L_85 = ___0_value;
		*(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C*)L_84 = L_85;
		uint64_t L_86 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_86, ((int64_t)1)));
	}

IL_0198:
	{
		uint64_t L_87 = V_6;
		uint64_t L_88 = V_3;
		if ((!(((uint64_t)L_87) >= ((uint64_t)L_88))))
		{
			goto IL_017f;
		}
	}
	{
		return;
	}
}
// Method Definition Index: 13825
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_CopyTo_m8CA473CD54839470D79A0455ED2999F0FF929628_gshared (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B* __this, Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B ___0_destination, const RuntimeMethod* method) 
{
	ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_m3C5D2034819739D3DDB78A3D890CEE1C94529B01_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0038;
		}
	}
	{
		Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B L_2 = ___0_destination;
		ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF L_3 = L_2.____pointer;
		V_0 = L_3;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C, (Il2CppByReference*)(&V_0));
		ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF L_5 = __this->____pointer;
		V_0 = L_5;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C, (Il2CppByReference*)(&V_0));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C_m107660B4F4107562042A7A5BD0F5123732E1C1BF(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return;
	}

IL_0038:
	{
		ThrowHelper_ThrowArgumentException_DestinationTooShort_m6468934A3BBB67DBC5BAEF7A64D91BD5BBBB3D4D(NULL);
		return;
	}
}
// Method Definition Index: 13826
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_TryCopyTo_m46A2F70C0DD837E75E7C7652F56BC2B9B05645E5_gshared (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B* __this, Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B ___0_destination, const RuntimeMethod* method) 
{
	bool V_0 = false;
	ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		V_0 = (bool)0;
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_m3C5D2034819739D3DDB78A3D890CEE1C94529B01_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_003b;
		}
	}
	{
		Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B L_2 = ___0_destination;
		ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF L_3 = L_2.____pointer;
		V_1 = L_3;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C, (Il2CppByReference*)(&V_1));
		ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF L_5 = __this->____pointer;
		V_1 = L_5;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C, (Il2CppByReference*)(&V_1));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C_m107660B4F4107562042A7A5BD0F5123732E1C1BF(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		V_0 = (bool)1;
	}

IL_003b:
	{
		bool L_8 = V_0;
		return L_8;
	}
}
// Method Definition Index: 13827
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_op_Equality_mEFA20AED0214B7A221E9826B9673A124163653C6_gshared (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B ___0_left, Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B ___1_right, const RuntimeMethod* method) 
{
	ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B L_0 = ___0_left;
		int32_t L_1 = L_0.____length;
		Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B L_2 = ___1_right;
		int32_t L_3 = L_2.____length;
		if ((!(((uint32_t)L_1) == ((uint32_t)L_3))))
		{
			goto IL_0030;
		}
	}
	{
		Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B L_4 = ___0_left;
		ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF L_5 = L_4.____pointer;
		V_0 = L_5;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C, (Il2CppByReference*)(&V_0));
		Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B L_7 = ___1_right;
		ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF L_8 = L_7.____pointer;
		V_0 = L_8;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_9;
		L_9 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C, (Il2CppByReference*)(&V_0));
		bool L_10;
		L_10 = il2cpp_unsafe_are_same(L_6, L_9);
		return L_10;
	}

IL_0030:
	{
		return (bool)0;
	}
}
// Method Definition Index: 13828
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ReadOnlySpan_1_t70882B83461088DC23C3DB9AF59753CF91E1F3AA Span_1_op_Implicit_mC5272E30538A158484CA1730BDCA1538E7BD51AC_gshared (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B ___0_span, const RuntimeMethod* method) 
{
	ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B L_0 = ___0_span;
		ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF L_1 = L_0.____pointer;
		V_0 = L_1;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_2;
		L_2 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C, (Il2CppByReference*)(&V_0));
		Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B L_3 = ___0_span;
		int32_t L_4 = L_3.____length;
		ReadOnlySpan_1_t70882B83461088DC23C3DB9AF59753CF91E1F3AA L_5;
		memset((&L_5), 0, sizeof(L_5));
		ReadOnlySpan_1__ctor_mD794B63B35B4B58E5337728A24E1998CFDD74810_inline((&L_5), L_2, L_4, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 18));
		return L_5;
	}
}
// Method Definition Index: 13829
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Span_1_ToString_m5621A583EECF457226E66278C9423C8245424F1F_gshared (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral0DB46164953228904843938099AF66650313FEE5);
		s_Il2CppMethodInitialized = true;
	}
	Il2CppChar* V_0 = NULL;
	ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_0 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_1;
		L_1 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_0, NULL);
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_2 = { reinterpret_cast<intptr_t> (&il2cpp_defaults.char_class->byval_arg) };
		Type_t* L_3;
		L_3 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_2, NULL);
		bool L_4;
		L_4 = Type_op_Equality_m99930A0E44E420A685FABA60E60BA1CC5FA0EBDC(L_1, L_3, NULL);
		if (!L_4)
		{
			goto IL_003e;
		}
	}
	{
		ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF L_5 = __this->____pointer;
		V_1 = L_5;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C, (Il2CppByReference*)(&V_1));
		Il2CppChar* L_7;
		L_7 = il2cpp_unsafe_as_ref<Il2CppChar>(L_6);
		V_0 = L_7;
		Il2CppChar* L_8 = V_0;
		int32_t L_9 = __this->____length;
		String_t* L_10;
		L_10 = String_CreateString_m3F8794FEB452558B8A68C65E1F0B603B3D94E0E2(NULL, (Il2CppChar*)((uintptr_t)L_8), 0, L_9, NULL);
		return L_10;
	}

IL_003e:
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_11 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_12;
		L_12 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_11, NULL);
		NullCheck((MemberInfo_t*)L_12);
		String_t* L_13;
		L_13 = VirtualFuncInvoker0< String_t* >::Invoke(7, (MemberInfo_t*)L_12);
		int32_t L_14 = __this->____length;
		int32_t L_15 = L_14;
		RuntimeObject* L_16 = Box(il2cpp_defaults.int32_class, &L_15);
		String_t* L_17;
		L_17 = String_Format_mFB7DA489BD99F4670881FF50EC017BFB0A5C0987(_stringLiteral0DB46164953228904843938099AF66650313FEE5, (RuntimeObject*)L_13, L_16, NULL);
		return L_17;
	}
}
// Method Definition Index: 13830
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B Span_1_Slice_mD85CEAFA2B78E02E3C8B9BAF64AC60C4E3906A73_gshared (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B* __this, int32_t ___0_start, const RuntimeMethod* method) 
{
	ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) > ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF L_2 = __this->____pointer;
		V_0 = L_2;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_start;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_5;
		L_5 = il2cpp_unsafe_add<BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C,int32_t>(L_3, L_4);
		int32_t L_6 = __this->____length;
		int32_t L_7 = ___0_start;
		Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B L_8;
		memset((&L_8), 0, sizeof(L_8));
		Span_1__ctor_m773D9E2695DC1A1EAF17FDC94204CB7B1DE21582_inline((&L_8), L_5, ((int32_t)il2cpp_codegen_subtract(L_6, L_7)), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_8;
	}
}
// Method Definition Index: 13831
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B Span_1_Slice_mBD4E1573A35C00A8E62AAFA830F787B6BE280218_gshared (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B* __this, int32_t ___0_start, int32_t ___1_length, const RuntimeMethod* method) 
{
	ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0014;
		}
	}
	{
		int32_t L_2 = ___1_length;
		int32_t L_3 = __this->____length;
		int32_t L_4 = ___0_start;
		if ((!(((uint32_t)L_2) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(L_3, L_4))))))
		{
			goto IL_0019;
		}
	}

IL_0014:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0019:
	{
		ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF L_5 = __this->____pointer;
		V_0 = L_5;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C, (Il2CppByReference*)(&V_0));
		int32_t L_7 = ___0_start;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_8;
		L_8 = il2cpp_unsafe_add<BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C,int32_t>(L_6, L_7);
		int32_t L_9 = ___1_length;
		Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B L_10;
		memset((&L_10), 0, sizeof(L_10));
		Span_1__ctor_m773D9E2695DC1A1EAF17FDC94204CB7B1DE21582_inline((&L_10), L_8, L_9, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_10;
	}
}
// Method Definition Index: 13832
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A* Span_1_ToArray_mC1AAAD0C3E2277B241A4F22D46825855DBC3F604_gshared (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B* __this, const RuntimeMethod* method) 
{
	ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_000e;
		}
	}
	{
		BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A* L_1;
		L_1 = Array_Empty_TisBlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C_m48CDEF41C2F032666E4312F197041C0757708AE3_inline(il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 20));
		return L_1;
	}

IL_000e:
	{
		int32_t L_2 = __this->____length;
		BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A* L_3 = (BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A*)(BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A*)SZArrayNew(il2cpp_rgctx_data(InitializedTypeInfo(method->klass)->rgctx_data, 21), (uint32_t)L_2);
		BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A* L_4 = L_3;
		NullCheck((RuntimeArray*)L_4);
		uint8_t* L_5;
		L_5 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_4, NULL);
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_6;
		L_6 = il2cpp_unsafe_as_ref<BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C>(L_5);
		ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF L_7 = __this->____pointer;
		V_0 = L_7;
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_8;
		L_8 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C, (Il2CppByReference*)(&V_0));
		int32_t L_9 = __this->____length;
		Buffer_Memmove_TisBlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C_m107660B4F4107562042A7A5BD0F5123732E1C1BF(L_6, L_8, (uint64_t)((int64_t)L_9), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return L_4;
	}
}
// Method Definition Index: 13833
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m3C5D2034819739D3DDB78A3D890CEE1C94529B01_gshared (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13834
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_Equals_m7F55EE83B9319F0B5E39F494B4DA746792AC439C_gshared (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteral69508A540AFD085A745316DD7D6345B1C8CC662D)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13835
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_GetHashCode_m96F76176FF5D31065703741A33020AF9ECE32C02_gshared (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B* __this, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteralECE618215BAC99C6FD12D8A273CC2118945EDCC8)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13836
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B Span_1_op_Implicit_m2243A55C41D8CCFC201C16BDEBC0B1714C358D15_gshared (BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A* ___0_array, const RuntimeMethod* method) 
{
	{
		BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A* L_0 = ___0_array;
		Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B L_1;
		memset((&L_1), 0, sizeof(L_1));
		Span_1__ctor_mB392265B6A34EA59542E156547D1143AC4B0EBD4_inline((&L_1), L_0, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 22));
		return L_1;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// Method Definition Index: 13818
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mD521304CD3352077C22527BDFF5A3A6F5BBA2345_gshared (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF* __this, BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8* ___0_array, const RuntimeMethod* method) 
{
	BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54));
		goto IL_0037;
	}

IL_0037:
	{
		BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_4;
		L_4 = il2cpp_unsafe_as_ref<BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54>(L_3);
		ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13819
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m41CBBD9269174D34492096CD4FE4DA748D886A09_gshared (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF* __this, BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8* ___0_array, int32_t ___1_start, int32_t ___2_length, const RuntimeMethod* method) 
{
	BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_0016;
		}
	}
	{
		int32_t L_1 = ___1_start;
		if (L_1)
		{
			goto IL_0009;
		}
	}
	{
		int32_t L_2 = ___2_length;
		if (!L_2)
		{
			goto IL_000e;
		}
	}

IL_0009:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF));
		return;
	}

IL_0016:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54));
		goto IL_0042;
	}

IL_0042:
	{
		int32_t L_4 = ___1_start;
		BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8* L_5 = ___0_array;
		NullCheck(L_5);
		if ((!(((uint32_t)L_4) <= ((uint32_t)((int32_t)(((RuntimeArray*)L_5)->max_length))))))
		{
			goto IL_0050;
		}
	}
	{
		int32_t L_6 = ___2_length;
		BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8* L_7 = ___0_array;
		NullCheck(L_7);
		int32_t L_8 = ___1_start;
		if ((!(((uint32_t)L_6) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(((int32_t)(((RuntimeArray*)L_7)->max_length)), L_8))))))
		{
			goto IL_0055;
		}
	}

IL_0050:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0055:
	{
		BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8* L_9 = ___0_array;
		NullCheck((RuntimeArray*)L_9);
		uint8_t* L_10;
		L_10 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_9, NULL);
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_11;
		L_11 = il2cpp_unsafe_as_ref<BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54>(L_10);
		int32_t L_12 = ___1_start;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_13;
		L_13 = il2cpp_unsafe_add<BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54,int32_t>(L_11, L_12);
		ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 L_14;
		memset((&L_14), 0, sizeof(L_14));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_14), L_13);
		__this->____pointer = L_14;
		int32_t L_15 = ___2_length;
		__this->____length = L_15;
		return;
	}
}
// Method Definition Index: 13820
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m415AB8FC86987A725E297624EA832A8629B5B779_gshared (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF* __this, void* ___0_pointer, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		goto IL_0016;
	}

IL_0016:
	{
		int32_t L_0 = ___1_length;
		if ((((int32_t)L_0) >= ((int32_t)0)))
		{
			goto IL_001f;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_001f:
	{
		void* L_1 = ___0_pointer;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_2;
		L_2 = il2cpp_unsafe_as_ref<BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54>((uint8_t*)L_1);
		ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 L_3;
		memset((&L_3), 0, sizeof(L_3));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_3), L_2);
		__this->____pointer = L_3;
		int32_t L_4 = ___1_length;
		__this->____length = L_4;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mEA9008985414217BB660044157B6458716A8AB1D_gshared (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF* __this, BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_0 = ___0_ptr;
		ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13822
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* Span_1_get_Item_m23A57CB949BBEC9D8010B089E2E6B43494174E09_gshared (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF* __this, int32_t ___0_index, const RuntimeMethod* method) 
{
	ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_index;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) >= ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowIndexOutOfRangeException_m86F753A24E2765A35546BA6352A7E4F0BB8A66B5(NULL);
	}

IL_000e:
	{
		ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 L_2 = __this->____pointer;
		V_0 = L_2;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_index;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_5;
		L_5 = il2cpp_unsafe_add<BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54,int32_t>(L_3, L_4);
		return L_5;
	}
}
// Method Definition Index: 13823
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* Span_1_GetPinnableReference_mB7260B33117356613BB8F807636FED0B4D7F5C74_gshared (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF* __this, const RuntimeMethod* method) 
{
	ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_0010;
		}
	}
	{
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_1;
		L_1 = il2cpp_unsafe_as_ref<BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54>((void*)((uintptr_t)0));
		return L_1;
	}

IL_0010:
	{
		ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 L_2 = __this->____pointer;
		V_0 = L_2;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54, (Il2CppByReference*)(&V_0));
		return L_3;
	}
}
// Method Definition Index: 13824
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_Fill_m2A8A33FC58264C3934935F879D3969D9C2830D08_gshared (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF* __this, BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54 ___0_value, const RuntimeMethod* method) 
{
	uint32_t V_0 = 0;
	BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54 V_1;
	memset((&V_1), 0, sizeof(V_1));
	ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 V_2;
	memset((&V_2), 0, sizeof(V_2));
	uint64_t V_3 = 0;
	BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* V_4 = NULL;
	uint64_t V_5 = 0;
	uint64_t V_6 = 0;
	{
		int32_t L_0;
		L_0 = il2cpp_unsafe_sizeof<BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54>();
		if ((!(((uint32_t)L_0) == ((uint32_t)1))))
		{
			goto IL_0037;
		}
	}
	{
		int32_t L_1 = __this->____length;
		V_0 = (uint32_t)L_1;
		uint32_t L_2 = V_0;
		if (L_2)
		{
			goto IL_0013;
		}
	}
	{
		return;
	}

IL_0013:
	{
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54 L_3 = ___0_value;
		V_1 = L_3;
		ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 L_4 = __this->____pointer;
		V_2 = L_4;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_5;
		L_5 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54, (Il2CppByReference*)(&V_2));
		uint8_t* L_6;
		L_6 = il2cpp_unsafe_as_ref<uint8_t>(L_5);
		uint8_t* L_7;
		L_7 = il2cpp_unsafe_as_ref<uint8_t>((&V_1));
		int32_t L_8 = *((uint8_t*)L_7);
		uint32_t L_9 = V_0;
		Unsafe_InitBlockUnaligned_m6F2353EB9ABC9320E61629FAEE23948C80BFF03A(L_6, (uint8_t)L_8, L_9, NULL);
		return;
	}

IL_0037:
	{
		int32_t L_10 = __this->____length;
		V_3 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_10));
		uint64_t L_11 = V_3;
		if (L_11)
		{
			goto IL_0043;
		}
	}
	{
		return;
	}

IL_0043:
	{
		ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 L_12 = __this->____pointer;
		V_2 = L_12;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_13;
		L_13 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54, (Il2CppByReference*)(&V_2));
		V_4 = L_13;
		int32_t L_14;
		L_14 = il2cpp_unsafe_sizeof<BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54>();
		V_5 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_14));
		V_6 = (uint64_t)((int64_t)0);
		goto IL_0110;
	}

IL_0064:
	{
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_15 = V_4;
		uint64_t L_16 = V_6;
		uint64_t L_17 = V_5;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_18;
		L_18 = il2cpp_unsafe_add_byte_offset<BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54,uint64_t>(L_15, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_16, (int64_t)L_17)));
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54 L_19 = ___0_value;
		*(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54*)L_18 = L_19;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_20 = V_4;
		uint64_t L_21 = V_6;
		uint64_t L_22 = V_5;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_23;
		L_23 = il2cpp_unsafe_add_byte_offset<BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54,uint64_t>(L_20, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_21, ((int64_t)1))), (int64_t)L_22)));
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54 L_24 = ___0_value;
		*(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54*)L_23 = L_24;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_25 = V_4;
		uint64_t L_26 = V_6;
		uint64_t L_27 = V_5;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_28;
		L_28 = il2cpp_unsafe_add_byte_offset<BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54,uint64_t>(L_25, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_26, ((int64_t)2))), (int64_t)L_27)));
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54 L_29 = ___0_value;
		*(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54*)L_28 = L_29;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_30 = V_4;
		uint64_t L_31 = V_6;
		uint64_t L_32 = V_5;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_33;
		L_33 = il2cpp_unsafe_add_byte_offset<BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54,uint64_t>(L_30, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_31, ((int64_t)3))), (int64_t)L_32)));
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54 L_34 = ___0_value;
		*(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54*)L_33 = L_34;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_35 = V_4;
		uint64_t L_36 = V_6;
		uint64_t L_37 = V_5;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_38;
		L_38 = il2cpp_unsafe_add_byte_offset<BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54,uint64_t>(L_35, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_36, ((int64_t)4))), (int64_t)L_37)));
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54 L_39 = ___0_value;
		*(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54*)L_38 = L_39;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_40 = V_4;
		uint64_t L_41 = V_6;
		uint64_t L_42 = V_5;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_43;
		L_43 = il2cpp_unsafe_add_byte_offset<BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54,uint64_t>(L_40, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_41, ((int64_t)5))), (int64_t)L_42)));
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54 L_44 = ___0_value;
		*(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54*)L_43 = L_44;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_45 = V_4;
		uint64_t L_46 = V_6;
		uint64_t L_47 = V_5;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_48;
		L_48 = il2cpp_unsafe_add_byte_offset<BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54,uint64_t>(L_45, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_46, ((int64_t)6))), (int64_t)L_47)));
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54 L_49 = ___0_value;
		*(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54*)L_48 = L_49;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_50 = V_4;
		uint64_t L_51 = V_6;
		uint64_t L_52 = V_5;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_53;
		L_53 = il2cpp_unsafe_add_byte_offset<BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54,uint64_t>(L_50, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_51, ((int64_t)7))), (int64_t)L_52)));
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54 L_54 = ___0_value;
		*(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54*)L_53 = L_54;
		uint64_t L_55 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_55, ((int64_t)8)));
	}

IL_0110:
	{
		uint64_t L_56 = V_6;
		uint64_t L_57 = V_3;
		if ((!(((uint64_t)L_56) >= ((uint64_t)((int64_t)((int64_t)L_57&((int64_t)((int32_t)-8))))))))
		{
			goto IL_0064;
		}
	}
	{
		uint64_t L_58 = V_6;
		uint64_t L_59 = V_3;
		if ((!(((uint64_t)L_58) < ((uint64_t)((int64_t)((int64_t)L_59&((int64_t)((int32_t)-4))))))))
		{
			goto IL_0198;
		}
	}
	{
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_60 = V_4;
		uint64_t L_61 = V_6;
		uint64_t L_62 = V_5;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_63;
		L_63 = il2cpp_unsafe_add_byte_offset<BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54,uint64_t>(L_60, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_61, (int64_t)L_62)));
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54 L_64 = ___0_value;
		*(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54*)L_63 = L_64;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_65 = V_4;
		uint64_t L_66 = V_6;
		uint64_t L_67 = V_5;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_68;
		L_68 = il2cpp_unsafe_add_byte_offset<BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54,uint64_t>(L_65, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_66, ((int64_t)1))), (int64_t)L_67)));
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54 L_69 = ___0_value;
		*(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54*)L_68 = L_69;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_70 = V_4;
		uint64_t L_71 = V_6;
		uint64_t L_72 = V_5;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_73;
		L_73 = il2cpp_unsafe_add_byte_offset<BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54,uint64_t>(L_70, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_71, ((int64_t)2))), (int64_t)L_72)));
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54 L_74 = ___0_value;
		*(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54*)L_73 = L_74;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_75 = V_4;
		uint64_t L_76 = V_6;
		uint64_t L_77 = V_5;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_78;
		L_78 = il2cpp_unsafe_add_byte_offset<BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54,uint64_t>(L_75, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_76, ((int64_t)3))), (int64_t)L_77)));
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54 L_79 = ___0_value;
		*(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54*)L_78 = L_79;
		uint64_t L_80 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_80, ((int64_t)4)));
		goto IL_0198;
	}

IL_017f:
	{
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_81 = V_4;
		uint64_t L_82 = V_6;
		uint64_t L_83 = V_5;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_84;
		L_84 = il2cpp_unsafe_add_byte_offset<BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54,uint64_t>(L_81, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_82, (int64_t)L_83)));
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54 L_85 = ___0_value;
		*(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54*)L_84 = L_85;
		uint64_t L_86 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_86, ((int64_t)1)));
	}

IL_0198:
	{
		uint64_t L_87 = V_6;
		uint64_t L_88 = V_3;
		if ((!(((uint64_t)L_87) >= ((uint64_t)L_88))))
		{
			goto IL_017f;
		}
	}
	{
		return;
	}
}
// Method Definition Index: 13825
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_CopyTo_m1F71FDF9D53AA00318AEF9A12BD3F7E76BB919F2_gshared (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF* __this, Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF ___0_destination, const RuntimeMethod* method) 
{
	ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_m088CD07D38433F7795ED549A2139E5D41EA16706_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0038;
		}
	}
	{
		Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF L_2 = ___0_destination;
		ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 L_3 = L_2.____pointer;
		V_0 = L_3;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54, (Il2CppByReference*)(&V_0));
		ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 L_5 = __this->____pointer;
		V_0 = L_5;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54, (Il2CppByReference*)(&V_0));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54_m812A4F9C363B5C0D2AAE7D5E5D180DB7F7FEF824(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return;
	}

IL_0038:
	{
		ThrowHelper_ThrowArgumentException_DestinationTooShort_m6468934A3BBB67DBC5BAEF7A64D91BD5BBBB3D4D(NULL);
		return;
	}
}
// Method Definition Index: 13826
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_TryCopyTo_m6347DEEACEE3DBB225BB9E1092C91DC98F1607E2_gshared (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF* __this, Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF ___0_destination, const RuntimeMethod* method) 
{
	bool V_0 = false;
	ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		V_0 = (bool)0;
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_m088CD07D38433F7795ED549A2139E5D41EA16706_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_003b;
		}
	}
	{
		Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF L_2 = ___0_destination;
		ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 L_3 = L_2.____pointer;
		V_1 = L_3;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54, (Il2CppByReference*)(&V_1));
		ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 L_5 = __this->____pointer;
		V_1 = L_5;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54, (Il2CppByReference*)(&V_1));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54_m812A4F9C363B5C0D2AAE7D5E5D180DB7F7FEF824(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		V_0 = (bool)1;
	}

IL_003b:
	{
		bool L_8 = V_0;
		return L_8;
	}
}
// Method Definition Index: 13827
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_op_Equality_m83DE301317CCAD4031D0B6D6142B147D2A8B2579_gshared (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF ___0_left, Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF ___1_right, const RuntimeMethod* method) 
{
	ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF L_0 = ___0_left;
		int32_t L_1 = L_0.____length;
		Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF L_2 = ___1_right;
		int32_t L_3 = L_2.____length;
		if ((!(((uint32_t)L_1) == ((uint32_t)L_3))))
		{
			goto IL_0030;
		}
	}
	{
		Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF L_4 = ___0_left;
		ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 L_5 = L_4.____pointer;
		V_0 = L_5;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54, (Il2CppByReference*)(&V_0));
		Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF L_7 = ___1_right;
		ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 L_8 = L_7.____pointer;
		V_0 = L_8;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_9;
		L_9 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54, (Il2CppByReference*)(&V_0));
		bool L_10;
		L_10 = il2cpp_unsafe_are_same(L_6, L_9);
		return L_10;
	}

IL_0030:
	{
		return (bool)0;
	}
}
// Method Definition Index: 13828
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ReadOnlySpan_1_t2EFE9320FC985EB4C740E0FD04D094F25024C51D Span_1_op_Implicit_mCAAAC696B8FF40FBD4FB29F5211AEE52CA73CF23_gshared (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF ___0_span, const RuntimeMethod* method) 
{
	ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF L_0 = ___0_span;
		ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 L_1 = L_0.____pointer;
		V_0 = L_1;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_2;
		L_2 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54, (Il2CppByReference*)(&V_0));
		Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF L_3 = ___0_span;
		int32_t L_4 = L_3.____length;
		ReadOnlySpan_1_t2EFE9320FC985EB4C740E0FD04D094F25024C51D L_5;
		memset((&L_5), 0, sizeof(L_5));
		ReadOnlySpan_1__ctor_m1A260D918B5EB96D17E965BA7BA73DA0C3493361_inline((&L_5), L_2, L_4, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 18));
		return L_5;
	}
}
// Method Definition Index: 13829
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Span_1_ToString_mA2E385C3E0316C161BC28C926653EF778ECE79E1_gshared (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral0DB46164953228904843938099AF66650313FEE5);
		s_Il2CppMethodInitialized = true;
	}
	Il2CppChar* V_0 = NULL;
	ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_0 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_1;
		L_1 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_0, NULL);
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_2 = { reinterpret_cast<intptr_t> (&il2cpp_defaults.char_class->byval_arg) };
		Type_t* L_3;
		L_3 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_2, NULL);
		bool L_4;
		L_4 = Type_op_Equality_m99930A0E44E420A685FABA60E60BA1CC5FA0EBDC(L_1, L_3, NULL);
		if (!L_4)
		{
			goto IL_003e;
		}
	}
	{
		ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 L_5 = __this->____pointer;
		V_1 = L_5;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54, (Il2CppByReference*)(&V_1));
		Il2CppChar* L_7;
		L_7 = il2cpp_unsafe_as_ref<Il2CppChar>(L_6);
		V_0 = L_7;
		Il2CppChar* L_8 = V_0;
		int32_t L_9 = __this->____length;
		String_t* L_10;
		L_10 = String_CreateString_m3F8794FEB452558B8A68C65E1F0B603B3D94E0E2(NULL, (Il2CppChar*)((uintptr_t)L_8), 0, L_9, NULL);
		return L_10;
	}

IL_003e:
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_11 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_12;
		L_12 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_11, NULL);
		NullCheck((MemberInfo_t*)L_12);
		String_t* L_13;
		L_13 = VirtualFuncInvoker0< String_t* >::Invoke(7, (MemberInfo_t*)L_12);
		int32_t L_14 = __this->____length;
		int32_t L_15 = L_14;
		RuntimeObject* L_16 = Box(il2cpp_defaults.int32_class, &L_15);
		String_t* L_17;
		L_17 = String_Format_mFB7DA489BD99F4670881FF50EC017BFB0A5C0987(_stringLiteral0DB46164953228904843938099AF66650313FEE5, (RuntimeObject*)L_13, L_16, NULL);
		return L_17;
	}
}
// Method Definition Index: 13830
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF Span_1_Slice_m6962B0CB13D72D7E3E7AAD0BE78BAC704D9D1A65_gshared (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF* __this, int32_t ___0_start, const RuntimeMethod* method) 
{
	ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) > ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 L_2 = __this->____pointer;
		V_0 = L_2;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_start;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_5;
		L_5 = il2cpp_unsafe_add<BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54,int32_t>(L_3, L_4);
		int32_t L_6 = __this->____length;
		int32_t L_7 = ___0_start;
		Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF L_8;
		memset((&L_8), 0, sizeof(L_8));
		Span_1__ctor_mEA9008985414217BB660044157B6458716A8AB1D_inline((&L_8), L_5, ((int32_t)il2cpp_codegen_subtract(L_6, L_7)), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_8;
	}
}
// Method Definition Index: 13831
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF Span_1_Slice_mBF42D1CB4DA7CB00F31CE101028189D40DDB6D0E_gshared (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF* __this, int32_t ___0_start, int32_t ___1_length, const RuntimeMethod* method) 
{
	ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0014;
		}
	}
	{
		int32_t L_2 = ___1_length;
		int32_t L_3 = __this->____length;
		int32_t L_4 = ___0_start;
		if ((!(((uint32_t)L_2) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(L_3, L_4))))))
		{
			goto IL_0019;
		}
	}

IL_0014:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0019:
	{
		ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 L_5 = __this->____pointer;
		V_0 = L_5;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54, (Il2CppByReference*)(&V_0));
		int32_t L_7 = ___0_start;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_8;
		L_8 = il2cpp_unsafe_add<BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54,int32_t>(L_6, L_7);
		int32_t L_9 = ___1_length;
		Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF L_10;
		memset((&L_10), 0, sizeof(L_10));
		Span_1__ctor_mEA9008985414217BB660044157B6458716A8AB1D_inline((&L_10), L_8, L_9, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_10;
	}
}
// Method Definition Index: 13832
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8* Span_1_ToArray_m18CFC6E2E78D646FBC99E9618523907D6175B35F_gshared (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF* __this, const RuntimeMethod* method) 
{
	ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_000e;
		}
	}
	{
		BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8* L_1;
		L_1 = Array_Empty_TisBlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54_mDE388119C5F2CA6DED1F8895551BEDC81C379FF9_inline(il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 20));
		return L_1;
	}

IL_000e:
	{
		int32_t L_2 = __this->____length;
		BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8* L_3 = (BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8*)(BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8*)SZArrayNew(il2cpp_rgctx_data(InitializedTypeInfo(method->klass)->rgctx_data, 21), (uint32_t)L_2);
		BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8* L_4 = L_3;
		NullCheck((RuntimeArray*)L_4);
		uint8_t* L_5;
		L_5 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_4, NULL);
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_6;
		L_6 = il2cpp_unsafe_as_ref<BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54>(L_5);
		ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 L_7 = __this->____pointer;
		V_0 = L_7;
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_8;
		L_8 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54, (Il2CppByReference*)(&V_0));
		int32_t L_9 = __this->____length;
		Buffer_Memmove_TisBlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54_m812A4F9C363B5C0D2AAE7D5E5D180DB7F7FEF824(L_6, L_8, (uint64_t)((int64_t)L_9), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return L_4;
	}
}
// Method Definition Index: 13833
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m088CD07D38433F7795ED549A2139E5D41EA16706_gshared (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13834
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_Equals_mDC3980F146F1709A06CFD47462C731521041564E_gshared (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteral69508A540AFD085A745316DD7D6345B1C8CC662D)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13835
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_GetHashCode_mF9BE77DD75BEE69AED1F0174C1B2840AB086AC8F_gshared (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF* __this, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteralECE618215BAC99C6FD12D8A273CC2118945EDCC8)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13836
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF Span_1_op_Implicit_m51E3482670B614CF227480786743D43FA79A43CF_gshared (BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8* ___0_array, const RuntimeMethod* method) 
{
	{
		BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8* L_0 = ___0_array;
		Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF L_1;
		memset((&L_1), 0, sizeof(L_1));
		Span_1__ctor_mD521304CD3352077C22527BDFF5A3A6F5BBA2345_inline((&L_1), L_0, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 22));
		return L_1;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// Method Definition Index: 13818
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mBC0A2A6F732CAD76B8E0B559AAF16DDDC7DA95FB_gshared (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C* __this, BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F* ___0_array, const RuntimeMethod* method) 
{
	BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901));
		goto IL_0037;
	}

IL_0037:
	{
		BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_4;
		L_4 = il2cpp_unsafe_as_ref<BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901>(L_3);
		ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13819
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m9AC220B55F05A0DD7ED573ED8CCEF000E8F602D4_gshared (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C* __this, BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F* ___0_array, int32_t ___1_start, int32_t ___2_length, const RuntimeMethod* method) 
{
	BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_0016;
		}
	}
	{
		int32_t L_1 = ___1_start;
		if (L_1)
		{
			goto IL_0009;
		}
	}
	{
		int32_t L_2 = ___2_length;
		if (!L_2)
		{
			goto IL_000e;
		}
	}

IL_0009:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C));
		return;
	}

IL_0016:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901));
		goto IL_0042;
	}

IL_0042:
	{
		int32_t L_4 = ___1_start;
		BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F* L_5 = ___0_array;
		NullCheck(L_5);
		if ((!(((uint32_t)L_4) <= ((uint32_t)((int32_t)(((RuntimeArray*)L_5)->max_length))))))
		{
			goto IL_0050;
		}
	}
	{
		int32_t L_6 = ___2_length;
		BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F* L_7 = ___0_array;
		NullCheck(L_7);
		int32_t L_8 = ___1_start;
		if ((!(((uint32_t)L_6) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(((int32_t)(((RuntimeArray*)L_7)->max_length)), L_8))))))
		{
			goto IL_0055;
		}
	}

IL_0050:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0055:
	{
		BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F* L_9 = ___0_array;
		NullCheck((RuntimeArray*)L_9);
		uint8_t* L_10;
		L_10 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_9, NULL);
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_11;
		L_11 = il2cpp_unsafe_as_ref<BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901>(L_10);
		int32_t L_12 = ___1_start;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_13;
		L_13 = il2cpp_unsafe_add<BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901,int32_t>(L_11, L_12);
		ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 L_14;
		memset((&L_14), 0, sizeof(L_14));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_14), L_13);
		__this->____pointer = L_14;
		int32_t L_15 = ___2_length;
		__this->____length = L_15;
		return;
	}
}
// Method Definition Index: 13820
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mCB1BDC0140A269D70097CECEA774740C2E9ED9BB_gshared (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C* __this, void* ___0_pointer, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		goto IL_0016;
	}

IL_0016:
	{
		int32_t L_0 = ___1_length;
		if ((((int32_t)L_0) >= ((int32_t)0)))
		{
			goto IL_001f;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_001f:
	{
		void* L_1 = ___0_pointer;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_2;
		L_2 = il2cpp_unsafe_as_ref<BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901>((uint8_t*)L_1);
		ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 L_3;
		memset((&L_3), 0, sizeof(L_3));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_3), L_2);
		__this->____pointer = L_3;
		int32_t L_4 = ___1_length;
		__this->____length = L_4;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m18D55B2B52C0EE4D379FCBBC6EE16E72CE2928E1_gshared (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C* __this, BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_0 = ___0_ptr;
		ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13822
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* Span_1_get_Item_mED589783F666A14BC3384697EE8953A268DAC552_gshared (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C* __this, int32_t ___0_index, const RuntimeMethod* method) 
{
	ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_index;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) >= ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowIndexOutOfRangeException_m86F753A24E2765A35546BA6352A7E4F0BB8A66B5(NULL);
	}

IL_000e:
	{
		ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 L_2 = __this->____pointer;
		V_0 = L_2;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_index;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_5;
		L_5 = il2cpp_unsafe_add<BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901,int32_t>(L_3, L_4);
		return L_5;
	}
}
// Method Definition Index: 13823
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* Span_1_GetPinnableReference_mA5BE8E27EB48D689881E39F839BC4F0C72B246DB_gshared (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C* __this, const RuntimeMethod* method) 
{
	ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_0010;
		}
	}
	{
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_1;
		L_1 = il2cpp_unsafe_as_ref<BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901>((void*)((uintptr_t)0));
		return L_1;
	}

IL_0010:
	{
		ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 L_2 = __this->____pointer;
		V_0 = L_2;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901, (Il2CppByReference*)(&V_0));
		return L_3;
	}
}
// Method Definition Index: 13824
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_Fill_m851D68A2707CEF09B28EA66EC891E95FDF520B19_gshared (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C* __this, BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901 ___0_value, const RuntimeMethod* method) 
{
	uint32_t V_0 = 0;
	BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901 V_1;
	memset((&V_1), 0, sizeof(V_1));
	ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 V_2;
	memset((&V_2), 0, sizeof(V_2));
	uint64_t V_3 = 0;
	BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* V_4 = NULL;
	uint64_t V_5 = 0;
	uint64_t V_6 = 0;
	{
		int32_t L_0;
		L_0 = il2cpp_unsafe_sizeof<BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901>();
		if ((!(((uint32_t)L_0) == ((uint32_t)1))))
		{
			goto IL_0037;
		}
	}
	{
		int32_t L_1 = __this->____length;
		V_0 = (uint32_t)L_1;
		uint32_t L_2 = V_0;
		if (L_2)
		{
			goto IL_0013;
		}
	}
	{
		return;
	}

IL_0013:
	{
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901 L_3 = ___0_value;
		V_1 = L_3;
		ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 L_4 = __this->____pointer;
		V_2 = L_4;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_5;
		L_5 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901, (Il2CppByReference*)(&V_2));
		uint8_t* L_6;
		L_6 = il2cpp_unsafe_as_ref<uint8_t>(L_5);
		uint8_t* L_7;
		L_7 = il2cpp_unsafe_as_ref<uint8_t>((&V_1));
		int32_t L_8 = *((uint8_t*)L_7);
		uint32_t L_9 = V_0;
		Unsafe_InitBlockUnaligned_m6F2353EB9ABC9320E61629FAEE23948C80BFF03A(L_6, (uint8_t)L_8, L_9, NULL);
		return;
	}

IL_0037:
	{
		int32_t L_10 = __this->____length;
		V_3 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_10));
		uint64_t L_11 = V_3;
		if (L_11)
		{
			goto IL_0043;
		}
	}
	{
		return;
	}

IL_0043:
	{
		ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 L_12 = __this->____pointer;
		V_2 = L_12;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_13;
		L_13 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901, (Il2CppByReference*)(&V_2));
		V_4 = L_13;
		int32_t L_14;
		L_14 = il2cpp_unsafe_sizeof<BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901>();
		V_5 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_14));
		V_6 = (uint64_t)((int64_t)0);
		goto IL_0110;
	}

IL_0064:
	{
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_15 = V_4;
		uint64_t L_16 = V_6;
		uint64_t L_17 = V_5;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_18;
		L_18 = il2cpp_unsafe_add_byte_offset<BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901,uint64_t>(L_15, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_16, (int64_t)L_17)));
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901 L_19 = ___0_value;
		*(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901*)L_18 = L_19;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_20 = V_4;
		uint64_t L_21 = V_6;
		uint64_t L_22 = V_5;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_23;
		L_23 = il2cpp_unsafe_add_byte_offset<BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901,uint64_t>(L_20, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_21, ((int64_t)1))), (int64_t)L_22)));
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901 L_24 = ___0_value;
		*(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901*)L_23 = L_24;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_25 = V_4;
		uint64_t L_26 = V_6;
		uint64_t L_27 = V_5;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_28;
		L_28 = il2cpp_unsafe_add_byte_offset<BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901,uint64_t>(L_25, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_26, ((int64_t)2))), (int64_t)L_27)));
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901 L_29 = ___0_value;
		*(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901*)L_28 = L_29;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_30 = V_4;
		uint64_t L_31 = V_6;
		uint64_t L_32 = V_5;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_33;
		L_33 = il2cpp_unsafe_add_byte_offset<BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901,uint64_t>(L_30, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_31, ((int64_t)3))), (int64_t)L_32)));
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901 L_34 = ___0_value;
		*(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901*)L_33 = L_34;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_35 = V_4;
		uint64_t L_36 = V_6;
		uint64_t L_37 = V_5;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_38;
		L_38 = il2cpp_unsafe_add_byte_offset<BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901,uint64_t>(L_35, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_36, ((int64_t)4))), (int64_t)L_37)));
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901 L_39 = ___0_value;
		*(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901*)L_38 = L_39;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_40 = V_4;
		uint64_t L_41 = V_6;
		uint64_t L_42 = V_5;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_43;
		L_43 = il2cpp_unsafe_add_byte_offset<BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901,uint64_t>(L_40, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_41, ((int64_t)5))), (int64_t)L_42)));
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901 L_44 = ___0_value;
		*(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901*)L_43 = L_44;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_45 = V_4;
		uint64_t L_46 = V_6;
		uint64_t L_47 = V_5;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_48;
		L_48 = il2cpp_unsafe_add_byte_offset<BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901,uint64_t>(L_45, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_46, ((int64_t)6))), (int64_t)L_47)));
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901 L_49 = ___0_value;
		*(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901*)L_48 = L_49;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_50 = V_4;
		uint64_t L_51 = V_6;
		uint64_t L_52 = V_5;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_53;
		L_53 = il2cpp_unsafe_add_byte_offset<BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901,uint64_t>(L_50, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_51, ((int64_t)7))), (int64_t)L_52)));
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901 L_54 = ___0_value;
		*(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901*)L_53 = L_54;
		uint64_t L_55 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_55, ((int64_t)8)));
	}

IL_0110:
	{
		uint64_t L_56 = V_6;
		uint64_t L_57 = V_3;
		if ((!(((uint64_t)L_56) >= ((uint64_t)((int64_t)((int64_t)L_57&((int64_t)((int32_t)-8))))))))
		{
			goto IL_0064;
		}
	}
	{
		uint64_t L_58 = V_6;
		uint64_t L_59 = V_3;
		if ((!(((uint64_t)L_58) < ((uint64_t)((int64_t)((int64_t)L_59&((int64_t)((int32_t)-4))))))))
		{
			goto IL_0198;
		}
	}
	{
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_60 = V_4;
		uint64_t L_61 = V_6;
		uint64_t L_62 = V_5;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_63;
		L_63 = il2cpp_unsafe_add_byte_offset<BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901,uint64_t>(L_60, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_61, (int64_t)L_62)));
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901 L_64 = ___0_value;
		*(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901*)L_63 = L_64;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_65 = V_4;
		uint64_t L_66 = V_6;
		uint64_t L_67 = V_5;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_68;
		L_68 = il2cpp_unsafe_add_byte_offset<BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901,uint64_t>(L_65, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_66, ((int64_t)1))), (int64_t)L_67)));
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901 L_69 = ___0_value;
		*(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901*)L_68 = L_69;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_70 = V_4;
		uint64_t L_71 = V_6;
		uint64_t L_72 = V_5;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_73;
		L_73 = il2cpp_unsafe_add_byte_offset<BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901,uint64_t>(L_70, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_71, ((int64_t)2))), (int64_t)L_72)));
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901 L_74 = ___0_value;
		*(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901*)L_73 = L_74;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_75 = V_4;
		uint64_t L_76 = V_6;
		uint64_t L_77 = V_5;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_78;
		L_78 = il2cpp_unsafe_add_byte_offset<BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901,uint64_t>(L_75, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_76, ((int64_t)3))), (int64_t)L_77)));
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901 L_79 = ___0_value;
		*(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901*)L_78 = L_79;
		uint64_t L_80 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_80, ((int64_t)4)));
		goto IL_0198;
	}

IL_017f:
	{
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_81 = V_4;
		uint64_t L_82 = V_6;
		uint64_t L_83 = V_5;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_84;
		L_84 = il2cpp_unsafe_add_byte_offset<BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901,uint64_t>(L_81, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_82, (int64_t)L_83)));
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901 L_85 = ___0_value;
		*(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901*)L_84 = L_85;
		uint64_t L_86 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_86, ((int64_t)1)));
	}

IL_0198:
	{
		uint64_t L_87 = V_6;
		uint64_t L_88 = V_3;
		if ((!(((uint64_t)L_87) >= ((uint64_t)L_88))))
		{
			goto IL_017f;
		}
	}
	{
		return;
	}
}
// Method Definition Index: 13825
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_CopyTo_mA6197925F333B9CA7CD79D142B48B2AFFE3BA054_gshared (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C* __this, Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C ___0_destination, const RuntimeMethod* method) 
{
	ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_mE19749B8DE0A55279184BDD9C3F394605245F91A_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0038;
		}
	}
	{
		Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C L_2 = ___0_destination;
		ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 L_3 = L_2.____pointer;
		V_0 = L_3;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901, (Il2CppByReference*)(&V_0));
		ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 L_5 = __this->____pointer;
		V_0 = L_5;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901, (Il2CppByReference*)(&V_0));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901_mCB357A3CD61F6C424F89F1F9C63A4446F5B9C57E(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return;
	}

IL_0038:
	{
		ThrowHelper_ThrowArgumentException_DestinationTooShort_m6468934A3BBB67DBC5BAEF7A64D91BD5BBBB3D4D(NULL);
		return;
	}
}
// Method Definition Index: 13826
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_TryCopyTo_m3BD50024911118C0EA67643284B5E3F30A2E751E_gshared (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C* __this, Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C ___0_destination, const RuntimeMethod* method) 
{
	bool V_0 = false;
	ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		V_0 = (bool)0;
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_mE19749B8DE0A55279184BDD9C3F394605245F91A_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_003b;
		}
	}
	{
		Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C L_2 = ___0_destination;
		ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 L_3 = L_2.____pointer;
		V_1 = L_3;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901, (Il2CppByReference*)(&V_1));
		ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 L_5 = __this->____pointer;
		V_1 = L_5;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901, (Il2CppByReference*)(&V_1));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901_mCB357A3CD61F6C424F89F1F9C63A4446F5B9C57E(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		V_0 = (bool)1;
	}

IL_003b:
	{
		bool L_8 = V_0;
		return L_8;
	}
}
// Method Definition Index: 13827
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_op_Equality_mE86DBDDCE1B435E6626C9DE5C740AFC51D586EC2_gshared (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C ___0_left, Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C ___1_right, const RuntimeMethod* method) 
{
	ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C L_0 = ___0_left;
		int32_t L_1 = L_0.____length;
		Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C L_2 = ___1_right;
		int32_t L_3 = L_2.____length;
		if ((!(((uint32_t)L_1) == ((uint32_t)L_3))))
		{
			goto IL_0030;
		}
	}
	{
		Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C L_4 = ___0_left;
		ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 L_5 = L_4.____pointer;
		V_0 = L_5;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901, (Il2CppByReference*)(&V_0));
		Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C L_7 = ___1_right;
		ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 L_8 = L_7.____pointer;
		V_0 = L_8;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_9;
		L_9 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901, (Il2CppByReference*)(&V_0));
		bool L_10;
		L_10 = il2cpp_unsafe_are_same(L_6, L_9);
		return L_10;
	}

IL_0030:
	{
		return (bool)0;
	}
}
// Method Definition Index: 13828
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ReadOnlySpan_1_t5EE3C4B6EA25BB8F3F956DFD28820F5666342758 Span_1_op_Implicit_m662C4CEB116C78B10DD4EE858321A28889BE5D75_gshared (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C ___0_span, const RuntimeMethod* method) 
{
	ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C L_0 = ___0_span;
		ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 L_1 = L_0.____pointer;
		V_0 = L_1;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_2;
		L_2 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901, (Il2CppByReference*)(&V_0));
		Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C L_3 = ___0_span;
		int32_t L_4 = L_3.____length;
		ReadOnlySpan_1_t5EE3C4B6EA25BB8F3F956DFD28820F5666342758 L_5;
		memset((&L_5), 0, sizeof(L_5));
		ReadOnlySpan_1__ctor_mE88D51223E12D11DE487A5E2C7AF2013C6413887_inline((&L_5), L_2, L_4, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 18));
		return L_5;
	}
}
// Method Definition Index: 13829
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Span_1_ToString_mF7CDCB76F95F17951B41C8361D88A4C607623C9D_gshared (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral0DB46164953228904843938099AF66650313FEE5);
		s_Il2CppMethodInitialized = true;
	}
	Il2CppChar* V_0 = NULL;
	ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_0 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_1;
		L_1 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_0, NULL);
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_2 = { reinterpret_cast<intptr_t> (&il2cpp_defaults.char_class->byval_arg) };
		Type_t* L_3;
		L_3 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_2, NULL);
		bool L_4;
		L_4 = Type_op_Equality_m99930A0E44E420A685FABA60E60BA1CC5FA0EBDC(L_1, L_3, NULL);
		if (!L_4)
		{
			goto IL_003e;
		}
	}
	{
		ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 L_5 = __this->____pointer;
		V_1 = L_5;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901, (Il2CppByReference*)(&V_1));
		Il2CppChar* L_7;
		L_7 = il2cpp_unsafe_as_ref<Il2CppChar>(L_6);
		V_0 = L_7;
		Il2CppChar* L_8 = V_0;
		int32_t L_9 = __this->____length;
		String_t* L_10;
		L_10 = String_CreateString_m3F8794FEB452558B8A68C65E1F0B603B3D94E0E2(NULL, (Il2CppChar*)((uintptr_t)L_8), 0, L_9, NULL);
		return L_10;
	}

IL_003e:
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_11 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_12;
		L_12 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_11, NULL);
		NullCheck((MemberInfo_t*)L_12);
		String_t* L_13;
		L_13 = VirtualFuncInvoker0< String_t* >::Invoke(7, (MemberInfo_t*)L_12);
		int32_t L_14 = __this->____length;
		int32_t L_15 = L_14;
		RuntimeObject* L_16 = Box(il2cpp_defaults.int32_class, &L_15);
		String_t* L_17;
		L_17 = String_Format_mFB7DA489BD99F4670881FF50EC017BFB0A5C0987(_stringLiteral0DB46164953228904843938099AF66650313FEE5, (RuntimeObject*)L_13, L_16, NULL);
		return L_17;
	}
}
// Method Definition Index: 13830
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C Span_1_Slice_m7D030DFE43949C2F889BEC9EE19A956D17AD18D6_gshared (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C* __this, int32_t ___0_start, const RuntimeMethod* method) 
{
	ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) > ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 L_2 = __this->____pointer;
		V_0 = L_2;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_start;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_5;
		L_5 = il2cpp_unsafe_add<BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901,int32_t>(L_3, L_4);
		int32_t L_6 = __this->____length;
		int32_t L_7 = ___0_start;
		Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C L_8;
		memset((&L_8), 0, sizeof(L_8));
		Span_1__ctor_m18D55B2B52C0EE4D379FCBBC6EE16E72CE2928E1_inline((&L_8), L_5, ((int32_t)il2cpp_codegen_subtract(L_6, L_7)), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_8;
	}
}
// Method Definition Index: 13831
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C Span_1_Slice_mB0A6A6166F66C1F9D0E75DA7DE7E2BBF9C9DF407_gshared (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C* __this, int32_t ___0_start, int32_t ___1_length, const RuntimeMethod* method) 
{
	ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0014;
		}
	}
	{
		int32_t L_2 = ___1_length;
		int32_t L_3 = __this->____length;
		int32_t L_4 = ___0_start;
		if ((!(((uint32_t)L_2) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(L_3, L_4))))))
		{
			goto IL_0019;
		}
	}

IL_0014:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0019:
	{
		ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 L_5 = __this->____pointer;
		V_0 = L_5;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901, (Il2CppByReference*)(&V_0));
		int32_t L_7 = ___0_start;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_8;
		L_8 = il2cpp_unsafe_add<BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901,int32_t>(L_6, L_7);
		int32_t L_9 = ___1_length;
		Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C L_10;
		memset((&L_10), 0, sizeof(L_10));
		Span_1__ctor_m18D55B2B52C0EE4D379FCBBC6EE16E72CE2928E1_inline((&L_10), L_8, L_9, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_10;
	}
}
// Method Definition Index: 13832
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F* Span_1_ToArray_mCBC8716EE478F83E4ABB7D8938950301365A1CDB_gshared (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C* __this, const RuntimeMethod* method) 
{
	ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_000e;
		}
	}
	{
		BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F* L_1;
		L_1 = Array_Empty_TisBlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901_m75A4A4FD904386887E2E78BA168595529AF13939_inline(il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 20));
		return L_1;
	}

IL_000e:
	{
		int32_t L_2 = __this->____length;
		BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F* L_3 = (BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F*)(BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F*)SZArrayNew(il2cpp_rgctx_data(InitializedTypeInfo(method->klass)->rgctx_data, 21), (uint32_t)L_2);
		BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F* L_4 = L_3;
		NullCheck((RuntimeArray*)L_4);
		uint8_t* L_5;
		L_5 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_4, NULL);
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_6;
		L_6 = il2cpp_unsafe_as_ref<BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901>(L_5);
		ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 L_7 = __this->____pointer;
		V_0 = L_7;
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_8;
		L_8 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901, (Il2CppByReference*)(&V_0));
		int32_t L_9 = __this->____length;
		Buffer_Memmove_TisBlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901_mCB357A3CD61F6C424F89F1F9C63A4446F5B9C57E(L_6, L_8, (uint64_t)((int64_t)L_9), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return L_4;
	}
}
// Method Definition Index: 13833
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mE19749B8DE0A55279184BDD9C3F394605245F91A_gshared (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13834
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_Equals_m84D15037DB9F6AABA69DF8C1CB3272B04111DBA5_gshared (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteral69508A540AFD085A745316DD7D6345B1C8CC662D)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13835
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_GetHashCode_m523F849869E2B077A52585FA1285BAEDAEBEA16A_gshared (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C* __this, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteralECE618215BAC99C6FD12D8A273CC2118945EDCC8)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13836
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C Span_1_op_Implicit_mDAE46617FD1B6A54D23C0FA963B13ED070D78FCB_gshared (BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F* ___0_array, const RuntimeMethod* method) 
{
	{
		BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F* L_0 = ___0_array;
		Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C L_1;
		memset((&L_1), 0, sizeof(L_1));
		Span_1__ctor_mBC0A2A6F732CAD76B8E0B559AAF16DDDC7DA95FB_inline((&L_1), L_0, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 22));
		return L_1;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// Method Definition Index: 13818
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mDAF271BFB6432EB8FCE7E7E95BB3A4918F1BCC92_gshared (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1* __this, BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A* ___0_array, const RuntimeMethod* method) 
{
	BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472));
		goto IL_0037;
	}

IL_0037:
	{
		BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_4;
		L_4 = il2cpp_unsafe_as_ref<BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472>(L_3);
		ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13819
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m4E7CB32DEDC997853314441ED9792FE2F7456408_gshared (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1* __this, BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A* ___0_array, int32_t ___1_start, int32_t ___2_length, const RuntimeMethod* method) 
{
	BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_0016;
		}
	}
	{
		int32_t L_1 = ___1_start;
		if (L_1)
		{
			goto IL_0009;
		}
	}
	{
		int32_t L_2 = ___2_length;
		if (!L_2)
		{
			goto IL_000e;
		}
	}

IL_0009:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1));
		return;
	}

IL_0016:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472));
		goto IL_0042;
	}

IL_0042:
	{
		int32_t L_4 = ___1_start;
		BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A* L_5 = ___0_array;
		NullCheck(L_5);
		if ((!(((uint32_t)L_4) <= ((uint32_t)((int32_t)(((RuntimeArray*)L_5)->max_length))))))
		{
			goto IL_0050;
		}
	}
	{
		int32_t L_6 = ___2_length;
		BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A* L_7 = ___0_array;
		NullCheck(L_7);
		int32_t L_8 = ___1_start;
		if ((!(((uint32_t)L_6) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(((int32_t)(((RuntimeArray*)L_7)->max_length)), L_8))))))
		{
			goto IL_0055;
		}
	}

IL_0050:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0055:
	{
		BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A* L_9 = ___0_array;
		NullCheck((RuntimeArray*)L_9);
		uint8_t* L_10;
		L_10 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_9, NULL);
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_11;
		L_11 = il2cpp_unsafe_as_ref<BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472>(L_10);
		int32_t L_12 = ___1_start;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_13;
		L_13 = il2cpp_unsafe_add<BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472,int32_t>(L_11, L_12);
		ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 L_14;
		memset((&L_14), 0, sizeof(L_14));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_14), L_13);
		__this->____pointer = L_14;
		int32_t L_15 = ___2_length;
		__this->____length = L_15;
		return;
	}
}
// Method Definition Index: 13820
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mB96BFEADE40B6C5385D0CCDFF43B912A9626BD62_gshared (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1* __this, void* ___0_pointer, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		goto IL_0016;
	}

IL_0016:
	{
		int32_t L_0 = ___1_length;
		if ((((int32_t)L_0) >= ((int32_t)0)))
		{
			goto IL_001f;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_001f:
	{
		void* L_1 = ___0_pointer;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_2;
		L_2 = il2cpp_unsafe_as_ref<BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472>((uint8_t*)L_1);
		ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 L_3;
		memset((&L_3), 0, sizeof(L_3));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_3), L_2);
		__this->____pointer = L_3;
		int32_t L_4 = ___1_length;
		__this->____length = L_4;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m0635E38BE51343FEA108E4BAC304A66703B9E727_gshared (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1* __this, BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_0 = ___0_ptr;
		ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13822
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* Span_1_get_Item_m2DFFF9D32428AC30D43B49BC0582280578A318ED_gshared (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1* __this, int32_t ___0_index, const RuntimeMethod* method) 
{
	ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_index;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) >= ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowIndexOutOfRangeException_m86F753A24E2765A35546BA6352A7E4F0BB8A66B5(NULL);
	}

IL_000e:
	{
		ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 L_2 = __this->____pointer;
		V_0 = L_2;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_index;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_5;
		L_5 = il2cpp_unsafe_add<BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472,int32_t>(L_3, L_4);
		return L_5;
	}
}
// Method Definition Index: 13823
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* Span_1_GetPinnableReference_m3FA6D8CDDAF1D3095BC11713A869678A8D445370_gshared (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1* __this, const RuntimeMethod* method) 
{
	ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_0010;
		}
	}
	{
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_1;
		L_1 = il2cpp_unsafe_as_ref<BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472>((void*)((uintptr_t)0));
		return L_1;
	}

IL_0010:
	{
		ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 L_2 = __this->____pointer;
		V_0 = L_2;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472, (Il2CppByReference*)(&V_0));
		return L_3;
	}
}
// Method Definition Index: 13824
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_Fill_m00345EB8321F450EF4C70CF4C913BDADAFAAFC1C_gshared (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1* __this, BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472 ___0_value, const RuntimeMethod* method) 
{
	uint32_t V_0 = 0;
	BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472 V_1;
	memset((&V_1), 0, sizeof(V_1));
	ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 V_2;
	memset((&V_2), 0, sizeof(V_2));
	uint64_t V_3 = 0;
	BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* V_4 = NULL;
	uint64_t V_5 = 0;
	uint64_t V_6 = 0;
	{
		int32_t L_0;
		L_0 = il2cpp_unsafe_sizeof<BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472>();
		if ((!(((uint32_t)L_0) == ((uint32_t)1))))
		{
			goto IL_0037;
		}
	}
	{
		int32_t L_1 = __this->____length;
		V_0 = (uint32_t)L_1;
		uint32_t L_2 = V_0;
		if (L_2)
		{
			goto IL_0013;
		}
	}
	{
		return;
	}

IL_0013:
	{
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472 L_3 = ___0_value;
		V_1 = L_3;
		ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 L_4 = __this->____pointer;
		V_2 = L_4;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_5;
		L_5 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472, (Il2CppByReference*)(&V_2));
		uint8_t* L_6;
		L_6 = il2cpp_unsafe_as_ref<uint8_t>(L_5);
		uint8_t* L_7;
		L_7 = il2cpp_unsafe_as_ref<uint8_t>((&V_1));
		int32_t L_8 = *((uint8_t*)L_7);
		uint32_t L_9 = V_0;
		Unsafe_InitBlockUnaligned_m6F2353EB9ABC9320E61629FAEE23948C80BFF03A(L_6, (uint8_t)L_8, L_9, NULL);
		return;
	}

IL_0037:
	{
		int32_t L_10 = __this->____length;
		V_3 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_10));
		uint64_t L_11 = V_3;
		if (L_11)
		{
			goto IL_0043;
		}
	}
	{
		return;
	}

IL_0043:
	{
		ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 L_12 = __this->____pointer;
		V_2 = L_12;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_13;
		L_13 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472, (Il2CppByReference*)(&V_2));
		V_4 = L_13;
		int32_t L_14;
		L_14 = il2cpp_unsafe_sizeof<BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472>();
		V_5 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_14));
		V_6 = (uint64_t)((int64_t)0);
		goto IL_0110;
	}

IL_0064:
	{
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_15 = V_4;
		uint64_t L_16 = V_6;
		uint64_t L_17 = V_5;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_18;
		L_18 = il2cpp_unsafe_add_byte_offset<BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472,uint64_t>(L_15, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_16, (int64_t)L_17)));
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472 L_19 = ___0_value;
		*(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472*)L_18 = L_19;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_20 = V_4;
		uint64_t L_21 = V_6;
		uint64_t L_22 = V_5;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_23;
		L_23 = il2cpp_unsafe_add_byte_offset<BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472,uint64_t>(L_20, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_21, ((int64_t)1))), (int64_t)L_22)));
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472 L_24 = ___0_value;
		*(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472*)L_23 = L_24;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_25 = V_4;
		uint64_t L_26 = V_6;
		uint64_t L_27 = V_5;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_28;
		L_28 = il2cpp_unsafe_add_byte_offset<BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472,uint64_t>(L_25, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_26, ((int64_t)2))), (int64_t)L_27)));
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472 L_29 = ___0_value;
		*(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472*)L_28 = L_29;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_30 = V_4;
		uint64_t L_31 = V_6;
		uint64_t L_32 = V_5;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_33;
		L_33 = il2cpp_unsafe_add_byte_offset<BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472,uint64_t>(L_30, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_31, ((int64_t)3))), (int64_t)L_32)));
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472 L_34 = ___0_value;
		*(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472*)L_33 = L_34;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_35 = V_4;
		uint64_t L_36 = V_6;
		uint64_t L_37 = V_5;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_38;
		L_38 = il2cpp_unsafe_add_byte_offset<BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472,uint64_t>(L_35, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_36, ((int64_t)4))), (int64_t)L_37)));
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472 L_39 = ___0_value;
		*(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472*)L_38 = L_39;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_40 = V_4;
		uint64_t L_41 = V_6;
		uint64_t L_42 = V_5;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_43;
		L_43 = il2cpp_unsafe_add_byte_offset<BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472,uint64_t>(L_40, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_41, ((int64_t)5))), (int64_t)L_42)));
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472 L_44 = ___0_value;
		*(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472*)L_43 = L_44;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_45 = V_4;
		uint64_t L_46 = V_6;
		uint64_t L_47 = V_5;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_48;
		L_48 = il2cpp_unsafe_add_byte_offset<BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472,uint64_t>(L_45, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_46, ((int64_t)6))), (int64_t)L_47)));
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472 L_49 = ___0_value;
		*(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472*)L_48 = L_49;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_50 = V_4;
		uint64_t L_51 = V_6;
		uint64_t L_52 = V_5;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_53;
		L_53 = il2cpp_unsafe_add_byte_offset<BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472,uint64_t>(L_50, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_51, ((int64_t)7))), (int64_t)L_52)));
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472 L_54 = ___0_value;
		*(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472*)L_53 = L_54;
		uint64_t L_55 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_55, ((int64_t)8)));
	}

IL_0110:
	{
		uint64_t L_56 = V_6;
		uint64_t L_57 = V_3;
		if ((!(((uint64_t)L_56) >= ((uint64_t)((int64_t)((int64_t)L_57&((int64_t)((int32_t)-8))))))))
		{
			goto IL_0064;
		}
	}
	{
		uint64_t L_58 = V_6;
		uint64_t L_59 = V_3;
		if ((!(((uint64_t)L_58) < ((uint64_t)((int64_t)((int64_t)L_59&((int64_t)((int32_t)-4))))))))
		{
			goto IL_0198;
		}
	}
	{
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_60 = V_4;
		uint64_t L_61 = V_6;
		uint64_t L_62 = V_5;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_63;
		L_63 = il2cpp_unsafe_add_byte_offset<BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472,uint64_t>(L_60, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_61, (int64_t)L_62)));
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472 L_64 = ___0_value;
		*(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472*)L_63 = L_64;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_65 = V_4;
		uint64_t L_66 = V_6;
		uint64_t L_67 = V_5;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_68;
		L_68 = il2cpp_unsafe_add_byte_offset<BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472,uint64_t>(L_65, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_66, ((int64_t)1))), (int64_t)L_67)));
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472 L_69 = ___0_value;
		*(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472*)L_68 = L_69;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_70 = V_4;
		uint64_t L_71 = V_6;
		uint64_t L_72 = V_5;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_73;
		L_73 = il2cpp_unsafe_add_byte_offset<BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472,uint64_t>(L_70, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_71, ((int64_t)2))), (int64_t)L_72)));
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472 L_74 = ___0_value;
		*(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472*)L_73 = L_74;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_75 = V_4;
		uint64_t L_76 = V_6;
		uint64_t L_77 = V_5;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_78;
		L_78 = il2cpp_unsafe_add_byte_offset<BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472,uint64_t>(L_75, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_76, ((int64_t)3))), (int64_t)L_77)));
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472 L_79 = ___0_value;
		*(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472*)L_78 = L_79;
		uint64_t L_80 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_80, ((int64_t)4)));
		goto IL_0198;
	}

IL_017f:
	{
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_81 = V_4;
		uint64_t L_82 = V_6;
		uint64_t L_83 = V_5;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_84;
		L_84 = il2cpp_unsafe_add_byte_offset<BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472,uint64_t>(L_81, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_82, (int64_t)L_83)));
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472 L_85 = ___0_value;
		*(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472*)L_84 = L_85;
		uint64_t L_86 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_86, ((int64_t)1)));
	}

IL_0198:
	{
		uint64_t L_87 = V_6;
		uint64_t L_88 = V_3;
		if ((!(((uint64_t)L_87) >= ((uint64_t)L_88))))
		{
			goto IL_017f;
		}
	}
	{
		return;
	}
}
// Method Definition Index: 13825
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_CopyTo_m65B2B88743ED3F2CE0FFC99806BA5F564C5728F6_gshared (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1* __this, Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1 ___0_destination, const RuntimeMethod* method) 
{
	ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_m27BEF44B2CCD3948DC00D344142E534E36BA7524_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0038;
		}
	}
	{
		Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1 L_2 = ___0_destination;
		ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 L_3 = L_2.____pointer;
		V_0 = L_3;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472, (Il2CppByReference*)(&V_0));
		ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 L_5 = __this->____pointer;
		V_0 = L_5;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472, (Il2CppByReference*)(&V_0));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472_mDE86D51ADDC3C7F13B214DA755964EA5014FC510(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return;
	}

IL_0038:
	{
		ThrowHelper_ThrowArgumentException_DestinationTooShort_m6468934A3BBB67DBC5BAEF7A64D91BD5BBBB3D4D(NULL);
		return;
	}
}
// Method Definition Index: 13826
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_TryCopyTo_mCA31070D238448C938A75D7AEAF7DD5395E4311E_gshared (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1* __this, Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1 ___0_destination, const RuntimeMethod* method) 
{
	bool V_0 = false;
	ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		V_0 = (bool)0;
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_m27BEF44B2CCD3948DC00D344142E534E36BA7524_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_003b;
		}
	}
	{
		Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1 L_2 = ___0_destination;
		ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 L_3 = L_2.____pointer;
		V_1 = L_3;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472, (Il2CppByReference*)(&V_1));
		ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 L_5 = __this->____pointer;
		V_1 = L_5;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472, (Il2CppByReference*)(&V_1));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472_mDE86D51ADDC3C7F13B214DA755964EA5014FC510(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		V_0 = (bool)1;
	}

IL_003b:
	{
		bool L_8 = V_0;
		return L_8;
	}
}
// Method Definition Index: 13827
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_op_Equality_mB4C0130A3F3A42A700782383C4A3B6CE76F49970_gshared (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1 ___0_left, Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1 ___1_right, const RuntimeMethod* method) 
{
	ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1 L_0 = ___0_left;
		int32_t L_1 = L_0.____length;
		Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1 L_2 = ___1_right;
		int32_t L_3 = L_2.____length;
		if ((!(((uint32_t)L_1) == ((uint32_t)L_3))))
		{
			goto IL_0030;
		}
	}
	{
		Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1 L_4 = ___0_left;
		ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 L_5 = L_4.____pointer;
		V_0 = L_5;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472, (Il2CppByReference*)(&V_0));
		Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1 L_7 = ___1_right;
		ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 L_8 = L_7.____pointer;
		V_0 = L_8;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_9;
		L_9 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472, (Il2CppByReference*)(&V_0));
		bool L_10;
		L_10 = il2cpp_unsafe_are_same(L_6, L_9);
		return L_10;
	}

IL_0030:
	{
		return (bool)0;
	}
}
// Method Definition Index: 13828
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ReadOnlySpan_1_t9DCA2EC81EE91A02D7C140CF3376183B663C483A Span_1_op_Implicit_m4FF45CC64927A10C4A6D08D16601C0D064F4027B_gshared (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1 ___0_span, const RuntimeMethod* method) 
{
	ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1 L_0 = ___0_span;
		ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 L_1 = L_0.____pointer;
		V_0 = L_1;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_2;
		L_2 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472, (Il2CppByReference*)(&V_0));
		Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1 L_3 = ___0_span;
		int32_t L_4 = L_3.____length;
		ReadOnlySpan_1_t9DCA2EC81EE91A02D7C140CF3376183B663C483A L_5;
		memset((&L_5), 0, sizeof(L_5));
		ReadOnlySpan_1__ctor_m83EAE3C4FCB23391B66637D0580733B85E12B21A_inline((&L_5), L_2, L_4, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 18));
		return L_5;
	}
}
// Method Definition Index: 13829
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Span_1_ToString_mAB40E4EBF8AF405E3616017EB247A7D6FB8759FC_gshared (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral0DB46164953228904843938099AF66650313FEE5);
		s_Il2CppMethodInitialized = true;
	}
	Il2CppChar* V_0 = NULL;
	ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_0 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_1;
		L_1 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_0, NULL);
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_2 = { reinterpret_cast<intptr_t> (&il2cpp_defaults.char_class->byval_arg) };
		Type_t* L_3;
		L_3 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_2, NULL);
		bool L_4;
		L_4 = Type_op_Equality_m99930A0E44E420A685FABA60E60BA1CC5FA0EBDC(L_1, L_3, NULL);
		if (!L_4)
		{
			goto IL_003e;
		}
	}
	{
		ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 L_5 = __this->____pointer;
		V_1 = L_5;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472, (Il2CppByReference*)(&V_1));
		Il2CppChar* L_7;
		L_7 = il2cpp_unsafe_as_ref<Il2CppChar>(L_6);
		V_0 = L_7;
		Il2CppChar* L_8 = V_0;
		int32_t L_9 = __this->____length;
		String_t* L_10;
		L_10 = String_CreateString_m3F8794FEB452558B8A68C65E1F0B603B3D94E0E2(NULL, (Il2CppChar*)((uintptr_t)L_8), 0, L_9, NULL);
		return L_10;
	}

IL_003e:
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_11 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_12;
		L_12 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_11, NULL);
		NullCheck((MemberInfo_t*)L_12);
		String_t* L_13;
		L_13 = VirtualFuncInvoker0< String_t* >::Invoke(7, (MemberInfo_t*)L_12);
		int32_t L_14 = __this->____length;
		int32_t L_15 = L_14;
		RuntimeObject* L_16 = Box(il2cpp_defaults.int32_class, &L_15);
		String_t* L_17;
		L_17 = String_Format_mFB7DA489BD99F4670881FF50EC017BFB0A5C0987(_stringLiteral0DB46164953228904843938099AF66650313FEE5, (RuntimeObject*)L_13, L_16, NULL);
		return L_17;
	}
}
// Method Definition Index: 13830
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1 Span_1_Slice_mA539BFA2F5AE80D6E5A2D15ACE42AD96D5713B43_gshared (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1* __this, int32_t ___0_start, const RuntimeMethod* method) 
{
	ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) > ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 L_2 = __this->____pointer;
		V_0 = L_2;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_start;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_5;
		L_5 = il2cpp_unsafe_add<BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472,int32_t>(L_3, L_4);
		int32_t L_6 = __this->____length;
		int32_t L_7 = ___0_start;
		Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1 L_8;
		memset((&L_8), 0, sizeof(L_8));
		Span_1__ctor_m0635E38BE51343FEA108E4BAC304A66703B9E727_inline((&L_8), L_5, ((int32_t)il2cpp_codegen_subtract(L_6, L_7)), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_8;
	}
}
// Method Definition Index: 13831
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1 Span_1_Slice_m87BB31DB04EA06FD32B688FFEC55BA2DD7D22EC7_gshared (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1* __this, int32_t ___0_start, int32_t ___1_length, const RuntimeMethod* method) 
{
	ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0014;
		}
	}
	{
		int32_t L_2 = ___1_length;
		int32_t L_3 = __this->____length;
		int32_t L_4 = ___0_start;
		if ((!(((uint32_t)L_2) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(L_3, L_4))))))
		{
			goto IL_0019;
		}
	}

IL_0014:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0019:
	{
		ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 L_5 = __this->____pointer;
		V_0 = L_5;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472, (Il2CppByReference*)(&V_0));
		int32_t L_7 = ___0_start;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_8;
		L_8 = il2cpp_unsafe_add<BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472,int32_t>(L_6, L_7);
		int32_t L_9 = ___1_length;
		Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1 L_10;
		memset((&L_10), 0, sizeof(L_10));
		Span_1__ctor_m0635E38BE51343FEA108E4BAC304A66703B9E727_inline((&L_10), L_8, L_9, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_10;
	}
}
// Method Definition Index: 13832
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A* Span_1_ToArray_mA99E0B42161B83D5EEA26FF188911FAD68CF9319_gshared (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1* __this, const RuntimeMethod* method) 
{
	ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_000e;
		}
	}
	{
		BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A* L_1;
		L_1 = Array_Empty_TisBlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472_m6A8620E16588CCAE8CBA423DCB92B7E1D919A942_inline(il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 20));
		return L_1;
	}

IL_000e:
	{
		int32_t L_2 = __this->____length;
		BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A* L_3 = (BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A*)(BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A*)SZArrayNew(il2cpp_rgctx_data(InitializedTypeInfo(method->klass)->rgctx_data, 21), (uint32_t)L_2);
		BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A* L_4 = L_3;
		NullCheck((RuntimeArray*)L_4);
		uint8_t* L_5;
		L_5 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_4, NULL);
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_6;
		L_6 = il2cpp_unsafe_as_ref<BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472>(L_5);
		ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 L_7 = __this->____pointer;
		V_0 = L_7;
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_8;
		L_8 = IL2CPP_BY_REFERENCE_GET_VALUE(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472, (Il2CppByReference*)(&V_0));
		int32_t L_9 = __this->____length;
		Buffer_Memmove_TisBlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472_mDE86D51ADDC3C7F13B214DA755964EA5014FC510(L_6, L_8, (uint64_t)((int64_t)L_9), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return L_4;
	}
}
// Method Definition Index: 13833
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m27BEF44B2CCD3948DC00D344142E534E36BA7524_gshared (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13834
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_Equals_m7782E9755EFA7AD283C813BB5168DE8E182E2FA5_gshared (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteral69508A540AFD085A745316DD7D6345B1C8CC662D)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13835
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_GetHashCode_m53F7F5E368908672150DBC5DC8768E9C3058CE36_gshared (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1* __this, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteralECE618215BAC99C6FD12D8A273CC2118945EDCC8)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13836
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1 Span_1_op_Implicit_mC6B1ACEF0A6B0F9CEF18D5F0A25623C9760FDEA2_gshared (BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A* ___0_array, const RuntimeMethod* method) 
{
	{
		BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A* L_0 = ___0_array;
		Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1 L_1;
		memset((&L_1), 0, sizeof(L_1));
		Span_1__ctor_mDAF271BFB6432EB8FCE7E7E95BB3A4918F1BCC92_inline((&L_1), L_0, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 22));
		return L_1;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// Method Definition Index: 13818
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m1D909AF584BE28F3A4AD990A5D7BE41287E28B38_gshared (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F* __this, BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651* ___0_array, const RuntimeMethod* method) 
{
	BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F));
		goto IL_0037;
	}

IL_0037:
	{
		BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_4;
		L_4 = il2cpp_unsafe_as_ref<BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F>(L_3);
		ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13819
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mC004D19B5E92ED05206D4FEF61FCE04D32DEF58A_gshared (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F* __this, BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651* ___0_array, int32_t ___1_start, int32_t ___2_length, const RuntimeMethod* method) 
{
	BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_0016;
		}
	}
	{
		int32_t L_1 = ___1_start;
		if (L_1)
		{
			goto IL_0009;
		}
	}
	{
		int32_t L_2 = ___2_length;
		if (!L_2)
		{
			goto IL_000e;
		}
	}

IL_0009:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F));
		return;
	}

IL_0016:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F));
		goto IL_0042;
	}

IL_0042:
	{
		int32_t L_4 = ___1_start;
		BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651* L_5 = ___0_array;
		NullCheck(L_5);
		if ((!(((uint32_t)L_4) <= ((uint32_t)((int32_t)(((RuntimeArray*)L_5)->max_length))))))
		{
			goto IL_0050;
		}
	}
	{
		int32_t L_6 = ___2_length;
		BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651* L_7 = ___0_array;
		NullCheck(L_7);
		int32_t L_8 = ___1_start;
		if ((!(((uint32_t)L_6) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(((int32_t)(((RuntimeArray*)L_7)->max_length)), L_8))))))
		{
			goto IL_0055;
		}
	}

IL_0050:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0055:
	{
		BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651* L_9 = ___0_array;
		NullCheck((RuntimeArray*)L_9);
		uint8_t* L_10;
		L_10 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_9, NULL);
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_11;
		L_11 = il2cpp_unsafe_as_ref<BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F>(L_10);
		int32_t L_12 = ___1_start;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_13;
		L_13 = il2cpp_unsafe_add<BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F,int32_t>(L_11, L_12);
		ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 L_14;
		memset((&L_14), 0, sizeof(L_14));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_14), L_13);
		__this->____pointer = L_14;
		int32_t L_15 = ___2_length;
		__this->____length = L_15;
		return;
	}
}
// Method Definition Index: 13820
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m7567DF292516EAF388B2B23118310BEC461C4C06_gshared (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F* __this, void* ___0_pointer, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		goto IL_0016;
	}

IL_0016:
	{
		int32_t L_0 = ___1_length;
		if ((((int32_t)L_0) >= ((int32_t)0)))
		{
			goto IL_001f;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_001f:
	{
		void* L_1 = ___0_pointer;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_2;
		L_2 = il2cpp_unsafe_as_ref<BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F>((uint8_t*)L_1);
		ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 L_3;
		memset((&L_3), 0, sizeof(L_3));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_3), L_2);
		__this->____pointer = L_3;
		int32_t L_4 = ___1_length;
		__this->____length = L_4;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mB74D4D3C2AD6B802DDB07920C132F554AED832BB_gshared (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F* __this, BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_0 = ___0_ptr;
		ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13822
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* Span_1_get_Item_m191908546402CCAF4B926044F1CEBA030C762C90_gshared (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F* __this, int32_t ___0_index, const RuntimeMethod* method) 
{
	ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_index;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) >= ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowIndexOutOfRangeException_m86F753A24E2765A35546BA6352A7E4F0BB8A66B5(NULL);
	}

IL_000e:
	{
		ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 L_2 = __this->____pointer;
		V_0 = L_2;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_index;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_5;
		L_5 = il2cpp_unsafe_add<BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F,int32_t>(L_3, L_4);
		return L_5;
	}
}
// Method Definition Index: 13823
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* Span_1_GetPinnableReference_mD95038A506E3D6E298ED536D536AACDCFF429BC1_gshared (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F* __this, const RuntimeMethod* method) 
{
	ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_0010;
		}
	}
	{
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_1;
		L_1 = il2cpp_unsafe_as_ref<BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F>((void*)((uintptr_t)0));
		return L_1;
	}

IL_0010:
	{
		ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 L_2 = __this->____pointer;
		V_0 = L_2;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F, (Il2CppByReference*)(&V_0));
		return L_3;
	}
}
// Method Definition Index: 13824
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_Fill_mA6EDB5054EE5CECB71A59212C0AD73857B06D025_gshared (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F* __this, BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F ___0_value, const RuntimeMethod* method) 
{
	uint32_t V_0 = 0;
	BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F V_1;
	memset((&V_1), 0, sizeof(V_1));
	ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 V_2;
	memset((&V_2), 0, sizeof(V_2));
	uint64_t V_3 = 0;
	BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* V_4 = NULL;
	uint64_t V_5 = 0;
	uint64_t V_6 = 0;
	{
		int32_t L_0;
		L_0 = il2cpp_unsafe_sizeof<BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F>();
		if ((!(((uint32_t)L_0) == ((uint32_t)1))))
		{
			goto IL_0037;
		}
	}
	{
		int32_t L_1 = __this->____length;
		V_0 = (uint32_t)L_1;
		uint32_t L_2 = V_0;
		if (L_2)
		{
			goto IL_0013;
		}
	}
	{
		return;
	}

IL_0013:
	{
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F L_3 = ___0_value;
		V_1 = L_3;
		ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 L_4 = __this->____pointer;
		V_2 = L_4;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_5;
		L_5 = IL2CPP_BY_REFERENCE_GET_VALUE(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F, (Il2CppByReference*)(&V_2));
		uint8_t* L_6;
		L_6 = il2cpp_unsafe_as_ref<uint8_t>(L_5);
		uint8_t* L_7;
		L_7 = il2cpp_unsafe_as_ref<uint8_t>((&V_1));
		int32_t L_8 = *((uint8_t*)L_7);
		uint32_t L_9 = V_0;
		Unsafe_InitBlockUnaligned_m6F2353EB9ABC9320E61629FAEE23948C80BFF03A(L_6, (uint8_t)L_8, L_9, NULL);
		return;
	}

IL_0037:
	{
		int32_t L_10 = __this->____length;
		V_3 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_10));
		uint64_t L_11 = V_3;
		if (L_11)
		{
			goto IL_0043;
		}
	}
	{
		return;
	}

IL_0043:
	{
		ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 L_12 = __this->____pointer;
		V_2 = L_12;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_13;
		L_13 = IL2CPP_BY_REFERENCE_GET_VALUE(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F, (Il2CppByReference*)(&V_2));
		V_4 = L_13;
		int32_t L_14;
		L_14 = il2cpp_unsafe_sizeof<BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F>();
		V_5 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_14));
		V_6 = (uint64_t)((int64_t)0);
		goto IL_0110;
	}

IL_0064:
	{
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_15 = V_4;
		uint64_t L_16 = V_6;
		uint64_t L_17 = V_5;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_18;
		L_18 = il2cpp_unsafe_add_byte_offset<BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F,uint64_t>(L_15, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_16, (int64_t)L_17)));
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F L_19 = ___0_value;
		*(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F*)L_18 = L_19;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_20 = V_4;
		uint64_t L_21 = V_6;
		uint64_t L_22 = V_5;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_23;
		L_23 = il2cpp_unsafe_add_byte_offset<BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F,uint64_t>(L_20, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_21, ((int64_t)1))), (int64_t)L_22)));
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F L_24 = ___0_value;
		*(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F*)L_23 = L_24;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_25 = V_4;
		uint64_t L_26 = V_6;
		uint64_t L_27 = V_5;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_28;
		L_28 = il2cpp_unsafe_add_byte_offset<BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F,uint64_t>(L_25, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_26, ((int64_t)2))), (int64_t)L_27)));
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F L_29 = ___0_value;
		*(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F*)L_28 = L_29;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_30 = V_4;
		uint64_t L_31 = V_6;
		uint64_t L_32 = V_5;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_33;
		L_33 = il2cpp_unsafe_add_byte_offset<BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F,uint64_t>(L_30, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_31, ((int64_t)3))), (int64_t)L_32)));
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F L_34 = ___0_value;
		*(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F*)L_33 = L_34;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_35 = V_4;
		uint64_t L_36 = V_6;
		uint64_t L_37 = V_5;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_38;
		L_38 = il2cpp_unsafe_add_byte_offset<BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F,uint64_t>(L_35, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_36, ((int64_t)4))), (int64_t)L_37)));
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F L_39 = ___0_value;
		*(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F*)L_38 = L_39;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_40 = V_4;
		uint64_t L_41 = V_6;
		uint64_t L_42 = V_5;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_43;
		L_43 = il2cpp_unsafe_add_byte_offset<BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F,uint64_t>(L_40, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_41, ((int64_t)5))), (int64_t)L_42)));
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F L_44 = ___0_value;
		*(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F*)L_43 = L_44;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_45 = V_4;
		uint64_t L_46 = V_6;
		uint64_t L_47 = V_5;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_48;
		L_48 = il2cpp_unsafe_add_byte_offset<BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F,uint64_t>(L_45, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_46, ((int64_t)6))), (int64_t)L_47)));
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F L_49 = ___0_value;
		*(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F*)L_48 = L_49;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_50 = V_4;
		uint64_t L_51 = V_6;
		uint64_t L_52 = V_5;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_53;
		L_53 = il2cpp_unsafe_add_byte_offset<BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F,uint64_t>(L_50, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_51, ((int64_t)7))), (int64_t)L_52)));
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F L_54 = ___0_value;
		*(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F*)L_53 = L_54;
		uint64_t L_55 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_55, ((int64_t)8)));
	}

IL_0110:
	{
		uint64_t L_56 = V_6;
		uint64_t L_57 = V_3;
		if ((!(((uint64_t)L_56) >= ((uint64_t)((int64_t)((int64_t)L_57&((int64_t)((int32_t)-8))))))))
		{
			goto IL_0064;
		}
	}
	{
		uint64_t L_58 = V_6;
		uint64_t L_59 = V_3;
		if ((!(((uint64_t)L_58) < ((uint64_t)((int64_t)((int64_t)L_59&((int64_t)((int32_t)-4))))))))
		{
			goto IL_0198;
		}
	}
	{
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_60 = V_4;
		uint64_t L_61 = V_6;
		uint64_t L_62 = V_5;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_63;
		L_63 = il2cpp_unsafe_add_byte_offset<BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F,uint64_t>(L_60, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_61, (int64_t)L_62)));
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F L_64 = ___0_value;
		*(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F*)L_63 = L_64;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_65 = V_4;
		uint64_t L_66 = V_6;
		uint64_t L_67 = V_5;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_68;
		L_68 = il2cpp_unsafe_add_byte_offset<BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F,uint64_t>(L_65, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_66, ((int64_t)1))), (int64_t)L_67)));
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F L_69 = ___0_value;
		*(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F*)L_68 = L_69;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_70 = V_4;
		uint64_t L_71 = V_6;
		uint64_t L_72 = V_5;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_73;
		L_73 = il2cpp_unsafe_add_byte_offset<BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F,uint64_t>(L_70, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_71, ((int64_t)2))), (int64_t)L_72)));
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F L_74 = ___0_value;
		*(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F*)L_73 = L_74;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_75 = V_4;
		uint64_t L_76 = V_6;
		uint64_t L_77 = V_5;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_78;
		L_78 = il2cpp_unsafe_add_byte_offset<BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F,uint64_t>(L_75, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_76, ((int64_t)3))), (int64_t)L_77)));
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F L_79 = ___0_value;
		*(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F*)L_78 = L_79;
		uint64_t L_80 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_80, ((int64_t)4)));
		goto IL_0198;
	}

IL_017f:
	{
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_81 = V_4;
		uint64_t L_82 = V_6;
		uint64_t L_83 = V_5;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_84;
		L_84 = il2cpp_unsafe_add_byte_offset<BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F,uint64_t>(L_81, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_82, (int64_t)L_83)));
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F L_85 = ___0_value;
		*(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F*)L_84 = L_85;
		uint64_t L_86 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_86, ((int64_t)1)));
	}

IL_0198:
	{
		uint64_t L_87 = V_6;
		uint64_t L_88 = V_3;
		if ((!(((uint64_t)L_87) >= ((uint64_t)L_88))))
		{
			goto IL_017f;
		}
	}
	{
		return;
	}
}
// Method Definition Index: 13825
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_CopyTo_m492139B2BCFB349DFBC2BBF80E1D32B1153042B0_gshared (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F* __this, Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F ___0_destination, const RuntimeMethod* method) 
{
	ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_mCF6D6F9A859D0DB86629D46B25379F2D9C9B8CBB_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0038;
		}
	}
	{
		Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F L_2 = ___0_destination;
		ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 L_3 = L_2.____pointer;
		V_0 = L_3;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F, (Il2CppByReference*)(&V_0));
		ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 L_5 = __this->____pointer;
		V_0 = L_5;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F, (Il2CppByReference*)(&V_0));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F_m5ADA1F648D3D4D37DB595FA24A1207E2AFE0FDE5(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return;
	}

IL_0038:
	{
		ThrowHelper_ThrowArgumentException_DestinationTooShort_m6468934A3BBB67DBC5BAEF7A64D91BD5BBBB3D4D(NULL);
		return;
	}
}
// Method Definition Index: 13826
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_TryCopyTo_m6BED16B9366315638CB6193ECE3F7D2977ACD00C_gshared (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F* __this, Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F ___0_destination, const RuntimeMethod* method) 
{
	bool V_0 = false;
	ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		V_0 = (bool)0;
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_mCF6D6F9A859D0DB86629D46B25379F2D9C9B8CBB_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_003b;
		}
	}
	{
		Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F L_2 = ___0_destination;
		ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 L_3 = L_2.____pointer;
		V_1 = L_3;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F, (Il2CppByReference*)(&V_1));
		ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 L_5 = __this->____pointer;
		V_1 = L_5;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F, (Il2CppByReference*)(&V_1));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F_m5ADA1F648D3D4D37DB595FA24A1207E2AFE0FDE5(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		V_0 = (bool)1;
	}

IL_003b:
	{
		bool L_8 = V_0;
		return L_8;
	}
}
// Method Definition Index: 13827
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_op_Equality_m13DB42C6CFB869E67658E1DC900DB75760B2D864_gshared (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F ___0_left, Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F ___1_right, const RuntimeMethod* method) 
{
	ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F L_0 = ___0_left;
		int32_t L_1 = L_0.____length;
		Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F L_2 = ___1_right;
		int32_t L_3 = L_2.____length;
		if ((!(((uint32_t)L_1) == ((uint32_t)L_3))))
		{
			goto IL_0030;
		}
	}
	{
		Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F L_4 = ___0_left;
		ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 L_5 = L_4.____pointer;
		V_0 = L_5;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F, (Il2CppByReference*)(&V_0));
		Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F L_7 = ___1_right;
		ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 L_8 = L_7.____pointer;
		V_0 = L_8;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_9;
		L_9 = IL2CPP_BY_REFERENCE_GET_VALUE(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F, (Il2CppByReference*)(&V_0));
		bool L_10;
		L_10 = il2cpp_unsafe_are_same(L_6, L_9);
		return L_10;
	}

IL_0030:
	{
		return (bool)0;
	}
}
// Method Definition Index: 13828
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ReadOnlySpan_1_t29FEC1E8134CF98858AC01DEE01F4B20811F583F Span_1_op_Implicit_m995964325FCC3041D5E638CC453303233E10048A_gshared (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F ___0_span, const RuntimeMethod* method) 
{
	ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F L_0 = ___0_span;
		ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 L_1 = L_0.____pointer;
		V_0 = L_1;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_2;
		L_2 = IL2CPP_BY_REFERENCE_GET_VALUE(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F, (Il2CppByReference*)(&V_0));
		Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F L_3 = ___0_span;
		int32_t L_4 = L_3.____length;
		ReadOnlySpan_1_t29FEC1E8134CF98858AC01DEE01F4B20811F583F L_5;
		memset((&L_5), 0, sizeof(L_5));
		ReadOnlySpan_1__ctor_m0519BAD7DF271438138DD6AEC8CE28A60D0619CE_inline((&L_5), L_2, L_4, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 18));
		return L_5;
	}
}
// Method Definition Index: 13829
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Span_1_ToString_m4ABF153853B5D4D21DE15729F45554D25789AAA0_gshared (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral0DB46164953228904843938099AF66650313FEE5);
		s_Il2CppMethodInitialized = true;
	}
	Il2CppChar* V_0 = NULL;
	ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_0 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_1;
		L_1 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_0, NULL);
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_2 = { reinterpret_cast<intptr_t> (&il2cpp_defaults.char_class->byval_arg) };
		Type_t* L_3;
		L_3 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_2, NULL);
		bool L_4;
		L_4 = Type_op_Equality_m99930A0E44E420A685FABA60E60BA1CC5FA0EBDC(L_1, L_3, NULL);
		if (!L_4)
		{
			goto IL_003e;
		}
	}
	{
		ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 L_5 = __this->____pointer;
		V_1 = L_5;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F, (Il2CppByReference*)(&V_1));
		Il2CppChar* L_7;
		L_7 = il2cpp_unsafe_as_ref<Il2CppChar>(L_6);
		V_0 = L_7;
		Il2CppChar* L_8 = V_0;
		int32_t L_9 = __this->____length;
		String_t* L_10;
		L_10 = String_CreateString_m3F8794FEB452558B8A68C65E1F0B603B3D94E0E2(NULL, (Il2CppChar*)((uintptr_t)L_8), 0, L_9, NULL);
		return L_10;
	}

IL_003e:
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_11 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_12;
		L_12 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_11, NULL);
		NullCheck((MemberInfo_t*)L_12);
		String_t* L_13;
		L_13 = VirtualFuncInvoker0< String_t* >::Invoke(7, (MemberInfo_t*)L_12);
		int32_t L_14 = __this->____length;
		int32_t L_15 = L_14;
		RuntimeObject* L_16 = Box(il2cpp_defaults.int32_class, &L_15);
		String_t* L_17;
		L_17 = String_Format_mFB7DA489BD99F4670881FF50EC017BFB0A5C0987(_stringLiteral0DB46164953228904843938099AF66650313FEE5, (RuntimeObject*)L_13, L_16, NULL);
		return L_17;
	}
}
// Method Definition Index: 13830
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F Span_1_Slice_m0B9A41E3B8D2B2F8F9EA30FA547C89DC0CD024C9_gshared (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F* __this, int32_t ___0_start, const RuntimeMethod* method) 
{
	ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) > ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 L_2 = __this->____pointer;
		V_0 = L_2;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_start;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_5;
		L_5 = il2cpp_unsafe_add<BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F,int32_t>(L_3, L_4);
		int32_t L_6 = __this->____length;
		int32_t L_7 = ___0_start;
		Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F L_8;
		memset((&L_8), 0, sizeof(L_8));
		Span_1__ctor_mB74D4D3C2AD6B802DDB07920C132F554AED832BB_inline((&L_8), L_5, ((int32_t)il2cpp_codegen_subtract(L_6, L_7)), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_8;
	}
}
// Method Definition Index: 13831
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F Span_1_Slice_mB9CBAF3C6C76C26B4FA2599463FDD387909E11E0_gshared (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F* __this, int32_t ___0_start, int32_t ___1_length, const RuntimeMethod* method) 
{
	ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0014;
		}
	}
	{
		int32_t L_2 = ___1_length;
		int32_t L_3 = __this->____length;
		int32_t L_4 = ___0_start;
		if ((!(((uint32_t)L_2) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(L_3, L_4))))))
		{
			goto IL_0019;
		}
	}

IL_0014:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0019:
	{
		ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 L_5 = __this->____pointer;
		V_0 = L_5;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F, (Il2CppByReference*)(&V_0));
		int32_t L_7 = ___0_start;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_8;
		L_8 = il2cpp_unsafe_add<BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F,int32_t>(L_6, L_7);
		int32_t L_9 = ___1_length;
		Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F L_10;
		memset((&L_10), 0, sizeof(L_10));
		Span_1__ctor_mB74D4D3C2AD6B802DDB07920C132F554AED832BB_inline((&L_10), L_8, L_9, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_10;
	}
}
// Method Definition Index: 13832
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651* Span_1_ToArray_mA3B9B019C213D77BA565097E2E762C3A51031B64_gshared (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F* __this, const RuntimeMethod* method) 
{
	ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_000e;
		}
	}
	{
		BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651* L_1;
		L_1 = Array_Empty_TisBoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F_m327783287506F38234D68882E5BEE202618B5300_inline(il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 20));
		return L_1;
	}

IL_000e:
	{
		int32_t L_2 = __this->____length;
		BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651* L_3 = (BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651*)(BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651*)SZArrayNew(il2cpp_rgctx_data(InitializedTypeInfo(method->klass)->rgctx_data, 21), (uint32_t)L_2);
		BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651* L_4 = L_3;
		NullCheck((RuntimeArray*)L_4);
		uint8_t* L_5;
		L_5 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_4, NULL);
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_6;
		L_6 = il2cpp_unsafe_as_ref<BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F>(L_5);
		ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 L_7 = __this->____pointer;
		V_0 = L_7;
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_8;
		L_8 = IL2CPP_BY_REFERENCE_GET_VALUE(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F, (Il2CppByReference*)(&V_0));
		int32_t L_9 = __this->____length;
		Buffer_Memmove_TisBoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F_m5ADA1F648D3D4D37DB595FA24A1207E2AFE0FDE5(L_6, L_8, (uint64_t)((int64_t)L_9), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return L_4;
	}
}
// Method Definition Index: 13833
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mCF6D6F9A859D0DB86629D46B25379F2D9C9B8CBB_gshared (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13834
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_Equals_m62B20436A1C0BBAC6F87A7A21C964F61F7DF6EC8_gshared (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteral69508A540AFD085A745316DD7D6345B1C8CC662D)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13835
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_GetHashCode_m5113E93248A796211D38FB0089101CBA9968A245_gshared (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F* __this, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteralECE618215BAC99C6FD12D8A273CC2118945EDCC8)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13836
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F Span_1_op_Implicit_m93526F73308D3A2340F93EB81820FC5D0B9E5510_gshared (BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651* ___0_array, const RuntimeMethod* method) 
{
	{
		BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651* L_0 = ___0_array;
		Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F L_1;
		memset((&L_1), 0, sizeof(L_1));
		Span_1__ctor_m1D909AF584BE28F3A4AD990A5D7BE41287E28B38_inline((&L_1), L_0, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 22));
		return L_1;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// Method Definition Index: 13818
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m5AF7377F267C08DB0D59CB23F59DF954F7DEE62C_gshared (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51* __this, BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4* ___0_array, const RuntimeMethod* method) 
{
	bool V_0 = false;
	{
		BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(bool));
		goto IL_0037;
	}

IL_0037:
	{
		BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		bool* L_4;
		L_4 = il2cpp_unsafe_as_ref<bool>(L_3);
		ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13819
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mF010B57B13C6597DA14D7957BD2E07090F8336A6_gshared (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51* __this, BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4* ___0_array, int32_t ___1_start, int32_t ___2_length, const RuntimeMethod* method) 
{
	bool V_0 = false;
	{
		BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_0016;
		}
	}
	{
		int32_t L_1 = ___1_start;
		if (L_1)
		{
			goto IL_0009;
		}
	}
	{
		int32_t L_2 = ___2_length;
		if (!L_2)
		{
			goto IL_000e;
		}
	}

IL_0009:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51));
		return;
	}

IL_0016:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(bool));
		goto IL_0042;
	}

IL_0042:
	{
		int32_t L_4 = ___1_start;
		BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4* L_5 = ___0_array;
		NullCheck(L_5);
		if ((!(((uint32_t)L_4) <= ((uint32_t)((int32_t)(((RuntimeArray*)L_5)->max_length))))))
		{
			goto IL_0050;
		}
	}
	{
		int32_t L_6 = ___2_length;
		BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4* L_7 = ___0_array;
		NullCheck(L_7);
		int32_t L_8 = ___1_start;
		if ((!(((uint32_t)L_6) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(((int32_t)(((RuntimeArray*)L_7)->max_length)), L_8))))))
		{
			goto IL_0055;
		}
	}

IL_0050:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0055:
	{
		BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4* L_9 = ___0_array;
		NullCheck((RuntimeArray*)L_9);
		uint8_t* L_10;
		L_10 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_9, NULL);
		bool* L_11;
		L_11 = il2cpp_unsafe_as_ref<bool>(L_10);
		int32_t L_12 = ___1_start;
		bool* L_13;
		L_13 = il2cpp_unsafe_add<bool,int32_t>(L_11, L_12);
		ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 L_14;
		memset((&L_14), 0, sizeof(L_14));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_14), L_13);
		__this->____pointer = L_14;
		int32_t L_15 = ___2_length;
		__this->____length = L_15;
		return;
	}
}
// Method Definition Index: 13820
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m9863659F38934C7B3CF0E727F433498C43070D2F_gshared (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51* __this, void* ___0_pointer, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		goto IL_0016;
	}

IL_0016:
	{
		int32_t L_0 = ___1_length;
		if ((((int32_t)L_0) >= ((int32_t)0)))
		{
			goto IL_001f;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_001f:
	{
		void* L_1 = ___0_pointer;
		bool* L_2;
		L_2 = il2cpp_unsafe_as_ref<bool>((uint8_t*)L_1);
		ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 L_3;
		memset((&L_3), 0, sizeof(L_3));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_3), L_2);
		__this->____pointer = L_3;
		int32_t L_4 = ___1_length;
		__this->____length = L_4;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mC733F92ACB7C0D922180991442FE7A28DCB60772_gshared (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51* __this, bool* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		bool* L_0 = ___0_ptr;
		ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13822
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool* Span_1_get_Item_m27BAFEC8B3FFE7DEB25D60F10C9941C139B90E41_gshared (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51* __this, int32_t ___0_index, const RuntimeMethod* method) 
{
	ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_index;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) >= ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowIndexOutOfRangeException_m86F753A24E2765A35546BA6352A7E4F0BB8A66B5(NULL);
	}

IL_000e:
	{
		ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 L_2 = __this->____pointer;
		V_0 = L_2;
		bool* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(bool, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_index;
		bool* L_5;
		L_5 = il2cpp_unsafe_add<bool,int32_t>(L_3, L_4);
		return L_5;
	}
}
// Method Definition Index: 13823
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool* Span_1_GetPinnableReference_mC68E9B3CD4F8680E06367DD4736FA06D7D3CA4F2_gshared (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51* __this, const RuntimeMethod* method) 
{
	ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_0010;
		}
	}
	{
		bool* L_1;
		L_1 = il2cpp_unsafe_as_ref<bool>((void*)((uintptr_t)0));
		return L_1;
	}

IL_0010:
	{
		ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 L_2 = __this->____pointer;
		V_0 = L_2;
		bool* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(bool, (Il2CppByReference*)(&V_0));
		return L_3;
	}
}
// Method Definition Index: 13824
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_Fill_m82EADA29C24D18F714A43323334596A4FCB9FD82_gshared (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51* __this, bool ___0_value, const RuntimeMethod* method) 
{
	uint32_t V_0 = 0;
	bool V_1 = false;
	ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 V_2;
	memset((&V_2), 0, sizeof(V_2));
	uint64_t V_3 = 0;
	bool* V_4 = NULL;
	uint64_t V_5 = 0;
	uint64_t V_6 = 0;
	{
		int32_t L_0;
		L_0 = il2cpp_unsafe_sizeof<bool>();
		if ((!(((uint32_t)L_0) == ((uint32_t)1))))
		{
			goto IL_0037;
		}
	}
	{
		int32_t L_1 = __this->____length;
		V_0 = (uint32_t)L_1;
		uint32_t L_2 = V_0;
		if (L_2)
		{
			goto IL_0013;
		}
	}
	{
		return;
	}

IL_0013:
	{
		bool L_3 = ___0_value;
		V_1 = L_3;
		ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 L_4 = __this->____pointer;
		V_2 = L_4;
		bool* L_5;
		L_5 = IL2CPP_BY_REFERENCE_GET_VALUE(bool, (Il2CppByReference*)(&V_2));
		uint8_t* L_6;
		L_6 = il2cpp_unsafe_as_ref<uint8_t>(L_5);
		uint8_t* L_7;
		L_7 = il2cpp_unsafe_as_ref<uint8_t>((&V_1));
		int32_t L_8 = *((uint8_t*)L_7);
		uint32_t L_9 = V_0;
		Unsafe_InitBlockUnaligned_m6F2353EB9ABC9320E61629FAEE23948C80BFF03A(L_6, (uint8_t)L_8, L_9, NULL);
		return;
	}

IL_0037:
	{
		int32_t L_10 = __this->____length;
		V_3 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_10));
		uint64_t L_11 = V_3;
		if (L_11)
		{
			goto IL_0043;
		}
	}
	{
		return;
	}

IL_0043:
	{
		ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 L_12 = __this->____pointer;
		V_2 = L_12;
		bool* L_13;
		L_13 = IL2CPP_BY_REFERENCE_GET_VALUE(bool, (Il2CppByReference*)(&V_2));
		V_4 = L_13;
		int32_t L_14;
		L_14 = il2cpp_unsafe_sizeof<bool>();
		V_5 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_14));
		V_6 = (uint64_t)((int64_t)0);
		goto IL_0110;
	}

IL_0064:
	{
		bool* L_15 = V_4;
		uint64_t L_16 = V_6;
		uint64_t L_17 = V_5;
		bool* L_18;
		L_18 = il2cpp_unsafe_add_byte_offset<bool,uint64_t>(L_15, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_16, (int64_t)L_17)));
		bool L_19 = ___0_value;
		*(bool*)L_18 = L_19;
		bool* L_20 = V_4;
		uint64_t L_21 = V_6;
		uint64_t L_22 = V_5;
		bool* L_23;
		L_23 = il2cpp_unsafe_add_byte_offset<bool,uint64_t>(L_20, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_21, ((int64_t)1))), (int64_t)L_22)));
		bool L_24 = ___0_value;
		*(bool*)L_23 = L_24;
		bool* L_25 = V_4;
		uint64_t L_26 = V_6;
		uint64_t L_27 = V_5;
		bool* L_28;
		L_28 = il2cpp_unsafe_add_byte_offset<bool,uint64_t>(L_25, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_26, ((int64_t)2))), (int64_t)L_27)));
		bool L_29 = ___0_value;
		*(bool*)L_28 = L_29;
		bool* L_30 = V_4;
		uint64_t L_31 = V_6;
		uint64_t L_32 = V_5;
		bool* L_33;
		L_33 = il2cpp_unsafe_add_byte_offset<bool,uint64_t>(L_30, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_31, ((int64_t)3))), (int64_t)L_32)));
		bool L_34 = ___0_value;
		*(bool*)L_33 = L_34;
		bool* L_35 = V_4;
		uint64_t L_36 = V_6;
		uint64_t L_37 = V_5;
		bool* L_38;
		L_38 = il2cpp_unsafe_add_byte_offset<bool,uint64_t>(L_35, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_36, ((int64_t)4))), (int64_t)L_37)));
		bool L_39 = ___0_value;
		*(bool*)L_38 = L_39;
		bool* L_40 = V_4;
		uint64_t L_41 = V_6;
		uint64_t L_42 = V_5;
		bool* L_43;
		L_43 = il2cpp_unsafe_add_byte_offset<bool,uint64_t>(L_40, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_41, ((int64_t)5))), (int64_t)L_42)));
		bool L_44 = ___0_value;
		*(bool*)L_43 = L_44;
		bool* L_45 = V_4;
		uint64_t L_46 = V_6;
		uint64_t L_47 = V_5;
		bool* L_48;
		L_48 = il2cpp_unsafe_add_byte_offset<bool,uint64_t>(L_45, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_46, ((int64_t)6))), (int64_t)L_47)));
		bool L_49 = ___0_value;
		*(bool*)L_48 = L_49;
		bool* L_50 = V_4;
		uint64_t L_51 = V_6;
		uint64_t L_52 = V_5;
		bool* L_53;
		L_53 = il2cpp_unsafe_add_byte_offset<bool,uint64_t>(L_50, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_51, ((int64_t)7))), (int64_t)L_52)));
		bool L_54 = ___0_value;
		*(bool*)L_53 = L_54;
		uint64_t L_55 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_55, ((int64_t)8)));
	}

IL_0110:
	{
		uint64_t L_56 = V_6;
		uint64_t L_57 = V_3;
		if ((!(((uint64_t)L_56) >= ((uint64_t)((int64_t)((int64_t)L_57&((int64_t)((int32_t)-8))))))))
		{
			goto IL_0064;
		}
	}
	{
		uint64_t L_58 = V_6;
		uint64_t L_59 = V_3;
		if ((!(((uint64_t)L_58) < ((uint64_t)((int64_t)((int64_t)L_59&((int64_t)((int32_t)-4))))))))
		{
			goto IL_0198;
		}
	}
	{
		bool* L_60 = V_4;
		uint64_t L_61 = V_6;
		uint64_t L_62 = V_5;
		bool* L_63;
		L_63 = il2cpp_unsafe_add_byte_offset<bool,uint64_t>(L_60, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_61, (int64_t)L_62)));
		bool L_64 = ___0_value;
		*(bool*)L_63 = L_64;
		bool* L_65 = V_4;
		uint64_t L_66 = V_6;
		uint64_t L_67 = V_5;
		bool* L_68;
		L_68 = il2cpp_unsafe_add_byte_offset<bool,uint64_t>(L_65, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_66, ((int64_t)1))), (int64_t)L_67)));
		bool L_69 = ___0_value;
		*(bool*)L_68 = L_69;
		bool* L_70 = V_4;
		uint64_t L_71 = V_6;
		uint64_t L_72 = V_5;
		bool* L_73;
		L_73 = il2cpp_unsafe_add_byte_offset<bool,uint64_t>(L_70, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_71, ((int64_t)2))), (int64_t)L_72)));
		bool L_74 = ___0_value;
		*(bool*)L_73 = L_74;
		bool* L_75 = V_4;
		uint64_t L_76 = V_6;
		uint64_t L_77 = V_5;
		bool* L_78;
		L_78 = il2cpp_unsafe_add_byte_offset<bool,uint64_t>(L_75, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_76, ((int64_t)3))), (int64_t)L_77)));
		bool L_79 = ___0_value;
		*(bool*)L_78 = L_79;
		uint64_t L_80 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_80, ((int64_t)4)));
		goto IL_0198;
	}

IL_017f:
	{
		bool* L_81 = V_4;
		uint64_t L_82 = V_6;
		uint64_t L_83 = V_5;
		bool* L_84;
		L_84 = il2cpp_unsafe_add_byte_offset<bool,uint64_t>(L_81, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_82, (int64_t)L_83)));
		bool L_85 = ___0_value;
		*(bool*)L_84 = L_85;
		uint64_t L_86 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_86, ((int64_t)1)));
	}

IL_0198:
	{
		uint64_t L_87 = V_6;
		uint64_t L_88 = V_3;
		if ((!(((uint64_t)L_87) >= ((uint64_t)L_88))))
		{
			goto IL_017f;
		}
	}
	{
		return;
	}
}
// Method Definition Index: 13825
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_CopyTo_mD4A4E2B11E4B55D0E34818F92516CA8C73F66E52_gshared (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51* __this, Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51 ___0_destination, const RuntimeMethod* method) 
{
	ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_mED1253429B93CB6D2928015A22105A16FF64C86B_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0038;
		}
	}
	{
		Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51 L_2 = ___0_destination;
		ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 L_3 = L_2.____pointer;
		V_0 = L_3;
		bool* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(bool, (Il2CppByReference*)(&V_0));
		ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 L_5 = __this->____pointer;
		V_0 = L_5;
		bool* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(bool, (Il2CppByReference*)(&V_0));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBoolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_m6C1A997DCFEFC3BA96EEB5E75FB8B54DF2D21198(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return;
	}

IL_0038:
	{
		ThrowHelper_ThrowArgumentException_DestinationTooShort_m6468934A3BBB67DBC5BAEF7A64D91BD5BBBB3D4D(NULL);
		return;
	}
}
// Method Definition Index: 13826
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_TryCopyTo_m840B35C6072B9118C6B103D322BACAAE77E2F08B_gshared (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51* __this, Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51 ___0_destination, const RuntimeMethod* method) 
{
	bool V_0 = false;
	ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		V_0 = (bool)0;
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_mED1253429B93CB6D2928015A22105A16FF64C86B_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_003b;
		}
	}
	{
		Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51 L_2 = ___0_destination;
		ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 L_3 = L_2.____pointer;
		V_1 = L_3;
		bool* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(bool, (Il2CppByReference*)(&V_1));
		ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 L_5 = __this->____pointer;
		V_1 = L_5;
		bool* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(bool, (Il2CppByReference*)(&V_1));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBoolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_m6C1A997DCFEFC3BA96EEB5E75FB8B54DF2D21198(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		V_0 = (bool)1;
	}

IL_003b:
	{
		bool L_8 = V_0;
		return L_8;
	}
}
// Method Definition Index: 13827
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_op_Equality_mD294DFFE07221746B3FDFAE65325FFF7523C842A_gshared (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51 ___0_left, Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51 ___1_right, const RuntimeMethod* method) 
{
	ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51 L_0 = ___0_left;
		int32_t L_1 = L_0.____length;
		Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51 L_2 = ___1_right;
		int32_t L_3 = L_2.____length;
		if ((!(((uint32_t)L_1) == ((uint32_t)L_3))))
		{
			goto IL_0030;
		}
	}
	{
		Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51 L_4 = ___0_left;
		ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 L_5 = L_4.____pointer;
		V_0 = L_5;
		bool* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(bool, (Il2CppByReference*)(&V_0));
		Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51 L_7 = ___1_right;
		ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 L_8 = L_7.____pointer;
		V_0 = L_8;
		bool* L_9;
		L_9 = IL2CPP_BY_REFERENCE_GET_VALUE(bool, (Il2CppByReference*)(&V_0));
		bool L_10;
		L_10 = il2cpp_unsafe_are_same(L_6, L_9);
		return L_10;
	}

IL_0030:
	{
		return (bool)0;
	}
}
// Method Definition Index: 13828
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ReadOnlySpan_1_t1CABD9AC48FDD39B6D2C26BCA3D87FCA5F94F1C2 Span_1_op_Implicit_m532947D2F4A072CD15514EE6D890A44C2B4C465B_gshared (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51 ___0_span, const RuntimeMethod* method) 
{
	ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51 L_0 = ___0_span;
		ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 L_1 = L_0.____pointer;
		V_0 = L_1;
		bool* L_2;
		L_2 = IL2CPP_BY_REFERENCE_GET_VALUE(bool, (Il2CppByReference*)(&V_0));
		Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51 L_3 = ___0_span;
		int32_t L_4 = L_3.____length;
		ReadOnlySpan_1_t1CABD9AC48FDD39B6D2C26BCA3D87FCA5F94F1C2 L_5;
		memset((&L_5), 0, sizeof(L_5));
		ReadOnlySpan_1__ctor_m5818A0EC9B5A3628E69F90A3521BDE96E1FDC74F_inline((&L_5), L_2, L_4, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 18));
		return L_5;
	}
}
// Method Definition Index: 13829
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Span_1_ToString_mF5B8BAB40C1B80B9B1A0921B31B2968310CB65A3_gshared (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral0DB46164953228904843938099AF66650313FEE5);
		s_Il2CppMethodInitialized = true;
	}
	Il2CppChar* V_0 = NULL;
	ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_0 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_1;
		L_1 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_0, NULL);
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_2 = { reinterpret_cast<intptr_t> (&il2cpp_defaults.char_class->byval_arg) };
		Type_t* L_3;
		L_3 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_2, NULL);
		bool L_4;
		L_4 = Type_op_Equality_m99930A0E44E420A685FABA60E60BA1CC5FA0EBDC(L_1, L_3, NULL);
		if (!L_4)
		{
			goto IL_003e;
		}
	}
	{
		ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 L_5 = __this->____pointer;
		V_1 = L_5;
		bool* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(bool, (Il2CppByReference*)(&V_1));
		Il2CppChar* L_7;
		L_7 = il2cpp_unsafe_as_ref<Il2CppChar>(L_6);
		V_0 = L_7;
		Il2CppChar* L_8 = V_0;
		int32_t L_9 = __this->____length;
		String_t* L_10;
		L_10 = String_CreateString_m3F8794FEB452558B8A68C65E1F0B603B3D94E0E2(NULL, (Il2CppChar*)((uintptr_t)L_8), 0, L_9, NULL);
		return L_10;
	}

IL_003e:
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_11 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_12;
		L_12 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_11, NULL);
		NullCheck((MemberInfo_t*)L_12);
		String_t* L_13;
		L_13 = VirtualFuncInvoker0< String_t* >::Invoke(7, (MemberInfo_t*)L_12);
		int32_t L_14 = __this->____length;
		int32_t L_15 = L_14;
		RuntimeObject* L_16 = Box(il2cpp_defaults.int32_class, &L_15);
		String_t* L_17;
		L_17 = String_Format_mFB7DA489BD99F4670881FF50EC017BFB0A5C0987(_stringLiteral0DB46164953228904843938099AF66650313FEE5, (RuntimeObject*)L_13, L_16, NULL);
		return L_17;
	}
}
// Method Definition Index: 13830
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51 Span_1_Slice_mF6D7367FEA695E2CE8E186FC9798415839735753_gshared (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51* __this, int32_t ___0_start, const RuntimeMethod* method) 
{
	ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) > ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 L_2 = __this->____pointer;
		V_0 = L_2;
		bool* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(bool, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_start;
		bool* L_5;
		L_5 = il2cpp_unsafe_add<bool,int32_t>(L_3, L_4);
		int32_t L_6 = __this->____length;
		int32_t L_7 = ___0_start;
		Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51 L_8;
		memset((&L_8), 0, sizeof(L_8));
		Span_1__ctor_mC733F92ACB7C0D922180991442FE7A28DCB60772_inline((&L_8), L_5, ((int32_t)il2cpp_codegen_subtract(L_6, L_7)), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_8;
	}
}
// Method Definition Index: 13831
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51 Span_1_Slice_m673F7A3E7082015E7E5AA80ACF267623570ABB84_gshared (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51* __this, int32_t ___0_start, int32_t ___1_length, const RuntimeMethod* method) 
{
	ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0014;
		}
	}
	{
		int32_t L_2 = ___1_length;
		int32_t L_3 = __this->____length;
		int32_t L_4 = ___0_start;
		if ((!(((uint32_t)L_2) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(L_3, L_4))))))
		{
			goto IL_0019;
		}
	}

IL_0014:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0019:
	{
		ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 L_5 = __this->____pointer;
		V_0 = L_5;
		bool* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(bool, (Il2CppByReference*)(&V_0));
		int32_t L_7 = ___0_start;
		bool* L_8;
		L_8 = il2cpp_unsafe_add<bool,int32_t>(L_6, L_7);
		int32_t L_9 = ___1_length;
		Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51 L_10;
		memset((&L_10), 0, sizeof(L_10));
		Span_1__ctor_mC733F92ACB7C0D922180991442FE7A28DCB60772_inline((&L_10), L_8, L_9, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_10;
	}
}
// Method Definition Index: 13832
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4* Span_1_ToArray_mFC04BEB2B94DC9AB1599B278616C046F62EA99C2_gshared (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51* __this, const RuntimeMethod* method) 
{
	ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_000e;
		}
	}
	{
		BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4* L_1;
		L_1 = Array_Empty_TisBoolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_mAB215C445888719BD89809D99C3DBD3135C2B1E7_inline(il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 20));
		return L_1;
	}

IL_000e:
	{
		int32_t L_2 = __this->____length;
		BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4* L_3 = (BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4*)(BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4*)SZArrayNew(il2cpp_rgctx_data(InitializedTypeInfo(method->klass)->rgctx_data, 21), (uint32_t)L_2);
		BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4* L_4 = L_3;
		NullCheck((RuntimeArray*)L_4);
		uint8_t* L_5;
		L_5 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_4, NULL);
		bool* L_6;
		L_6 = il2cpp_unsafe_as_ref<bool>(L_5);
		ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 L_7 = __this->____pointer;
		V_0 = L_7;
		bool* L_8;
		L_8 = IL2CPP_BY_REFERENCE_GET_VALUE(bool, (Il2CppByReference*)(&V_0));
		int32_t L_9 = __this->____length;
		Buffer_Memmove_TisBoolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_m6C1A997DCFEFC3BA96EEB5E75FB8B54DF2D21198(L_6, L_8, (uint64_t)((int64_t)L_9), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return L_4;
	}
}
// Method Definition Index: 13833
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mED1253429B93CB6D2928015A22105A16FF64C86B_gshared (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13834
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_Equals_m55CEACD776C1B696BF7E0EEA57CA12538E5B6A93_gshared (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteral69508A540AFD085A745316DD7D6345B1C8CC662D)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13835
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_GetHashCode_m1F00FAF1BC9554C7D07229B28319B50B119EAF21_gshared (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51* __this, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteralECE618215BAC99C6FD12D8A273CC2118945EDCC8)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13836
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51 Span_1_op_Implicit_m1F838ECD24613509CFE3CA0A82DD6F0762357A99_gshared (BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4* ___0_array, const RuntimeMethod* method) 
{
	{
		BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4* L_0 = ___0_array;
		Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51 L_1;
		memset((&L_1), 0, sizeof(L_1));
		Span_1__ctor_m5AF7377F267C08DB0D59CB23F59DF954F7DEE62C_inline((&L_1), L_0, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 22));
		return L_1;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// Method Definition Index: 13818
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m81F10BC6C4033345B53A361FD5777CB15BA51592_gshared (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68* __this, BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47* ___0_array, const RuntimeMethod* method) 
{
	BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010));
		goto IL_0037;
	}

IL_0037:
	{
		BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_4;
		L_4 = il2cpp_unsafe_as_ref<BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010>(L_3);
		ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13819
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m503CBBB4C5325BC55B3590482583DA9E757A26E4_gshared (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68* __this, BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47* ___0_array, int32_t ___1_start, int32_t ___2_length, const RuntimeMethod* method) 
{
	BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_0016;
		}
	}
	{
		int32_t L_1 = ___1_start;
		if (L_1)
		{
			goto IL_0009;
		}
	}
	{
		int32_t L_2 = ___2_length;
		if (!L_2)
		{
			goto IL_000e;
		}
	}

IL_0009:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68));
		return;
	}

IL_0016:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010));
		goto IL_0042;
	}

IL_0042:
	{
		int32_t L_4 = ___1_start;
		BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47* L_5 = ___0_array;
		NullCheck(L_5);
		if ((!(((uint32_t)L_4) <= ((uint32_t)((int32_t)(((RuntimeArray*)L_5)->max_length))))))
		{
			goto IL_0050;
		}
	}
	{
		int32_t L_6 = ___2_length;
		BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47* L_7 = ___0_array;
		NullCheck(L_7);
		int32_t L_8 = ___1_start;
		if ((!(((uint32_t)L_6) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(((int32_t)(((RuntimeArray*)L_7)->max_length)), L_8))))))
		{
			goto IL_0055;
		}
	}

IL_0050:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0055:
	{
		BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47* L_9 = ___0_array;
		NullCheck((RuntimeArray*)L_9);
		uint8_t* L_10;
		L_10 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_9, NULL);
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_11;
		L_11 = il2cpp_unsafe_as_ref<BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010>(L_10);
		int32_t L_12 = ___1_start;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_13;
		L_13 = il2cpp_unsafe_add<BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010,int32_t>(L_11, L_12);
		ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A L_14;
		memset((&L_14), 0, sizeof(L_14));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_14), L_13);
		__this->____pointer = L_14;
		int32_t L_15 = ___2_length;
		__this->____length = L_15;
		return;
	}
}
// Method Definition Index: 13820
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m1BF351BF290637664C8DBA004A039AB5DC6C3037_gshared (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68* __this, void* ___0_pointer, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		goto IL_0016;
	}

IL_0016:
	{
		int32_t L_0 = ___1_length;
		if ((((int32_t)L_0) >= ((int32_t)0)))
		{
			goto IL_001f;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_001f:
	{
		void* L_1 = ___0_pointer;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_2;
		L_2 = il2cpp_unsafe_as_ref<BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010>((uint8_t*)L_1);
		ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A L_3;
		memset((&L_3), 0, sizeof(L_3));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_3), L_2);
		__this->____pointer = L_3;
		int32_t L_4 = ___1_length;
		__this->____length = L_4;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m58CD191F04E31F33A163B278675E0FA9DF59166D_gshared (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68* __this, BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_0 = ___0_ptr;
		ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13822
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* Span_1_get_Item_mB7B50EE7C8CC03D1E78C03DCA1377BF645A22ACE_gshared (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68* __this, int32_t ___0_index, const RuntimeMethod* method) 
{
	ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_index;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) >= ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowIndexOutOfRangeException_m86F753A24E2765A35546BA6352A7E4F0BB8A66B5(NULL);
	}

IL_000e:
	{
		ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A L_2 = __this->____pointer;
		V_0 = L_2;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_index;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_5;
		L_5 = il2cpp_unsafe_add<BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010,int32_t>(L_3, L_4);
		return L_5;
	}
}
// Method Definition Index: 13823
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* Span_1_GetPinnableReference_m27B4C08C307EA0A6A74EBB5E4FACE23383A8A273_gshared (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68* __this, const RuntimeMethod* method) 
{
	ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_0010;
		}
	}
	{
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_1;
		L_1 = il2cpp_unsafe_as_ref<BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010>((void*)((uintptr_t)0));
		return L_1;
	}

IL_0010:
	{
		ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A L_2 = __this->____pointer;
		V_0 = L_2;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010, (Il2CppByReference*)(&V_0));
		return L_3;
	}
}
// Method Definition Index: 13824
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_Fill_m92B04AC3EE51A8F01A24C405AFBA0342B22DFA9E_gshared (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68* __this, BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010 ___0_value, const RuntimeMethod* method) 
{
	uint32_t V_0 = 0;
	BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010 V_1;
	memset((&V_1), 0, sizeof(V_1));
	ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A V_2;
	memset((&V_2), 0, sizeof(V_2));
	uint64_t V_3 = 0;
	BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* V_4 = NULL;
	uint64_t V_5 = 0;
	uint64_t V_6 = 0;
	{
		int32_t L_0;
		L_0 = il2cpp_unsafe_sizeof<BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010>();
		if ((!(((uint32_t)L_0) == ((uint32_t)1))))
		{
			goto IL_0037;
		}
	}
	{
		int32_t L_1 = __this->____length;
		V_0 = (uint32_t)L_1;
		uint32_t L_2 = V_0;
		if (L_2)
		{
			goto IL_0013;
		}
	}
	{
		return;
	}

IL_0013:
	{
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010 L_3 = ___0_value;
		V_1 = L_3;
		ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A L_4 = __this->____pointer;
		V_2 = L_4;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_5;
		L_5 = IL2CPP_BY_REFERENCE_GET_VALUE(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010, (Il2CppByReference*)(&V_2));
		uint8_t* L_6;
		L_6 = il2cpp_unsafe_as_ref<uint8_t>(L_5);
		uint8_t* L_7;
		L_7 = il2cpp_unsafe_as_ref<uint8_t>((&V_1));
		int32_t L_8 = *((uint8_t*)L_7);
		uint32_t L_9 = V_0;
		Unsafe_InitBlockUnaligned_m6F2353EB9ABC9320E61629FAEE23948C80BFF03A(L_6, (uint8_t)L_8, L_9, NULL);
		return;
	}

IL_0037:
	{
		int32_t L_10 = __this->____length;
		V_3 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_10));
		uint64_t L_11 = V_3;
		if (L_11)
		{
			goto IL_0043;
		}
	}
	{
		return;
	}

IL_0043:
	{
		ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A L_12 = __this->____pointer;
		V_2 = L_12;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_13;
		L_13 = IL2CPP_BY_REFERENCE_GET_VALUE(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010, (Il2CppByReference*)(&V_2));
		V_4 = L_13;
		int32_t L_14;
		L_14 = il2cpp_unsafe_sizeof<BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010>();
		V_5 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_14));
		V_6 = (uint64_t)((int64_t)0);
		goto IL_0110;
	}

IL_0064:
	{
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_15 = V_4;
		uint64_t L_16 = V_6;
		uint64_t L_17 = V_5;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_18;
		L_18 = il2cpp_unsafe_add_byte_offset<BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010,uint64_t>(L_15, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_16, (int64_t)L_17)));
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010 L_19 = ___0_value;
		*(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010*)L_18 = L_19;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_20 = V_4;
		uint64_t L_21 = V_6;
		uint64_t L_22 = V_5;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_23;
		L_23 = il2cpp_unsafe_add_byte_offset<BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010,uint64_t>(L_20, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_21, ((int64_t)1))), (int64_t)L_22)));
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010 L_24 = ___0_value;
		*(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010*)L_23 = L_24;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_25 = V_4;
		uint64_t L_26 = V_6;
		uint64_t L_27 = V_5;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_28;
		L_28 = il2cpp_unsafe_add_byte_offset<BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010,uint64_t>(L_25, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_26, ((int64_t)2))), (int64_t)L_27)));
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010 L_29 = ___0_value;
		*(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010*)L_28 = L_29;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_30 = V_4;
		uint64_t L_31 = V_6;
		uint64_t L_32 = V_5;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_33;
		L_33 = il2cpp_unsafe_add_byte_offset<BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010,uint64_t>(L_30, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_31, ((int64_t)3))), (int64_t)L_32)));
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010 L_34 = ___0_value;
		*(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010*)L_33 = L_34;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_35 = V_4;
		uint64_t L_36 = V_6;
		uint64_t L_37 = V_5;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_38;
		L_38 = il2cpp_unsafe_add_byte_offset<BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010,uint64_t>(L_35, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_36, ((int64_t)4))), (int64_t)L_37)));
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010 L_39 = ___0_value;
		*(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010*)L_38 = L_39;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_40 = V_4;
		uint64_t L_41 = V_6;
		uint64_t L_42 = V_5;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_43;
		L_43 = il2cpp_unsafe_add_byte_offset<BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010,uint64_t>(L_40, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_41, ((int64_t)5))), (int64_t)L_42)));
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010 L_44 = ___0_value;
		*(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010*)L_43 = L_44;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_45 = V_4;
		uint64_t L_46 = V_6;
		uint64_t L_47 = V_5;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_48;
		L_48 = il2cpp_unsafe_add_byte_offset<BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010,uint64_t>(L_45, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_46, ((int64_t)6))), (int64_t)L_47)));
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010 L_49 = ___0_value;
		*(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010*)L_48 = L_49;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_50 = V_4;
		uint64_t L_51 = V_6;
		uint64_t L_52 = V_5;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_53;
		L_53 = il2cpp_unsafe_add_byte_offset<BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010,uint64_t>(L_50, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_51, ((int64_t)7))), (int64_t)L_52)));
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010 L_54 = ___0_value;
		*(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010*)L_53 = L_54;
		uint64_t L_55 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_55, ((int64_t)8)));
	}

IL_0110:
	{
		uint64_t L_56 = V_6;
		uint64_t L_57 = V_3;
		if ((!(((uint64_t)L_56) >= ((uint64_t)((int64_t)((int64_t)L_57&((int64_t)((int32_t)-8))))))))
		{
			goto IL_0064;
		}
	}
	{
		uint64_t L_58 = V_6;
		uint64_t L_59 = V_3;
		if ((!(((uint64_t)L_58) < ((uint64_t)((int64_t)((int64_t)L_59&((int64_t)((int32_t)-4))))))))
		{
			goto IL_0198;
		}
	}
	{
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_60 = V_4;
		uint64_t L_61 = V_6;
		uint64_t L_62 = V_5;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_63;
		L_63 = il2cpp_unsafe_add_byte_offset<BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010,uint64_t>(L_60, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_61, (int64_t)L_62)));
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010 L_64 = ___0_value;
		*(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010*)L_63 = L_64;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_65 = V_4;
		uint64_t L_66 = V_6;
		uint64_t L_67 = V_5;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_68;
		L_68 = il2cpp_unsafe_add_byte_offset<BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010,uint64_t>(L_65, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_66, ((int64_t)1))), (int64_t)L_67)));
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010 L_69 = ___0_value;
		*(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010*)L_68 = L_69;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_70 = V_4;
		uint64_t L_71 = V_6;
		uint64_t L_72 = V_5;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_73;
		L_73 = il2cpp_unsafe_add_byte_offset<BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010,uint64_t>(L_70, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_71, ((int64_t)2))), (int64_t)L_72)));
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010 L_74 = ___0_value;
		*(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010*)L_73 = L_74;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_75 = V_4;
		uint64_t L_76 = V_6;
		uint64_t L_77 = V_5;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_78;
		L_78 = il2cpp_unsafe_add_byte_offset<BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010,uint64_t>(L_75, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_76, ((int64_t)3))), (int64_t)L_77)));
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010 L_79 = ___0_value;
		*(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010*)L_78 = L_79;
		uint64_t L_80 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_80, ((int64_t)4)));
		goto IL_0198;
	}

IL_017f:
	{
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_81 = V_4;
		uint64_t L_82 = V_6;
		uint64_t L_83 = V_5;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_84;
		L_84 = il2cpp_unsafe_add_byte_offset<BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010,uint64_t>(L_81, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_82, (int64_t)L_83)));
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010 L_85 = ___0_value;
		*(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010*)L_84 = L_85;
		uint64_t L_86 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_86, ((int64_t)1)));
	}

IL_0198:
	{
		uint64_t L_87 = V_6;
		uint64_t L_88 = V_3;
		if ((!(((uint64_t)L_87) >= ((uint64_t)L_88))))
		{
			goto IL_017f;
		}
	}
	{
		return;
	}
}
// Method Definition Index: 13825
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_CopyTo_m60CE6C903BAFB277FE4B68F0DDD1DA3B02A8BC7A_gshared (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68* __this, Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68 ___0_destination, const RuntimeMethod* method) 
{
	ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_mA3FAE42313871FB8DAC80A4C14DDE4CCE75F1713_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0038;
		}
	}
	{
		Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68 L_2 = ___0_destination;
		ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A L_3 = L_2.____pointer;
		V_0 = L_3;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010, (Il2CppByReference*)(&V_0));
		ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A L_5 = __this->____pointer;
		V_0 = L_5;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010, (Il2CppByReference*)(&V_0));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010_m795273AEE7A248BA5B241E561023F0456EF5DED1(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return;
	}

IL_0038:
	{
		ThrowHelper_ThrowArgumentException_DestinationTooShort_m6468934A3BBB67DBC5BAEF7A64D91BD5BBBB3D4D(NULL);
		return;
	}
}
// Method Definition Index: 13826
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_TryCopyTo_mD547D226E91EF0A4D68C2DE6747B899099F7A987_gshared (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68* __this, Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68 ___0_destination, const RuntimeMethod* method) 
{
	bool V_0 = false;
	ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		V_0 = (bool)0;
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_mA3FAE42313871FB8DAC80A4C14DDE4CCE75F1713_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_003b;
		}
	}
	{
		Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68 L_2 = ___0_destination;
		ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A L_3 = L_2.____pointer;
		V_1 = L_3;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010, (Il2CppByReference*)(&V_1));
		ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A L_5 = __this->____pointer;
		V_1 = L_5;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010, (Il2CppByReference*)(&V_1));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010_m795273AEE7A248BA5B241E561023F0456EF5DED1(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		V_0 = (bool)1;
	}

IL_003b:
	{
		bool L_8 = V_0;
		return L_8;
	}
}
// Method Definition Index: 13827
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_op_Equality_mFA54654862DFB62C74FB5B4E74DEEEA7258A0106_gshared (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68 ___0_left, Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68 ___1_right, const RuntimeMethod* method) 
{
	ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68 L_0 = ___0_left;
		int32_t L_1 = L_0.____length;
		Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68 L_2 = ___1_right;
		int32_t L_3 = L_2.____length;
		if ((!(((uint32_t)L_1) == ((uint32_t)L_3))))
		{
			goto IL_0030;
		}
	}
	{
		Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68 L_4 = ___0_left;
		ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A L_5 = L_4.____pointer;
		V_0 = L_5;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010, (Il2CppByReference*)(&V_0));
		Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68 L_7 = ___1_right;
		ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A L_8 = L_7.____pointer;
		V_0 = L_8;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_9;
		L_9 = IL2CPP_BY_REFERENCE_GET_VALUE(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010, (Il2CppByReference*)(&V_0));
		bool L_10;
		L_10 = il2cpp_unsafe_are_same(L_6, L_9);
		return L_10;
	}

IL_0030:
	{
		return (bool)0;
	}
}
// Method Definition Index: 13828
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ReadOnlySpan_1_t5D1B06A3C861E766B5669E3742152A803D0F24AA Span_1_op_Implicit_mDEA4AE3796AE1CF2E217FAFB8AAAF4EF4CD3BE06_gshared (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68 ___0_span, const RuntimeMethod* method) 
{
	ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68 L_0 = ___0_span;
		ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A L_1 = L_0.____pointer;
		V_0 = L_1;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_2;
		L_2 = IL2CPP_BY_REFERENCE_GET_VALUE(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010, (Il2CppByReference*)(&V_0));
		Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68 L_3 = ___0_span;
		int32_t L_4 = L_3.____length;
		ReadOnlySpan_1_t5D1B06A3C861E766B5669E3742152A803D0F24AA L_5;
		memset((&L_5), 0, sizeof(L_5));
		ReadOnlySpan_1__ctor_m4C7DD11D4BCC31C1EC9F0EA7D34388DB0B14A07A_inline((&L_5), L_2, L_4, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 18));
		return L_5;
	}
}
// Method Definition Index: 13829
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Span_1_ToString_mE03C46915517B1C1DE92A8C3833F979D9C512147_gshared (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral0DB46164953228904843938099AF66650313FEE5);
		s_Il2CppMethodInitialized = true;
	}
	Il2CppChar* V_0 = NULL;
	ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_0 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_1;
		L_1 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_0, NULL);
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_2 = { reinterpret_cast<intptr_t> (&il2cpp_defaults.char_class->byval_arg) };
		Type_t* L_3;
		L_3 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_2, NULL);
		bool L_4;
		L_4 = Type_op_Equality_m99930A0E44E420A685FABA60E60BA1CC5FA0EBDC(L_1, L_3, NULL);
		if (!L_4)
		{
			goto IL_003e;
		}
	}
	{
		ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A L_5 = __this->____pointer;
		V_1 = L_5;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010, (Il2CppByReference*)(&V_1));
		Il2CppChar* L_7;
		L_7 = il2cpp_unsafe_as_ref<Il2CppChar>(L_6);
		V_0 = L_7;
		Il2CppChar* L_8 = V_0;
		int32_t L_9 = __this->____length;
		String_t* L_10;
		L_10 = String_CreateString_m3F8794FEB452558B8A68C65E1F0B603B3D94E0E2(NULL, (Il2CppChar*)((uintptr_t)L_8), 0, L_9, NULL);
		return L_10;
	}

IL_003e:
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_11 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_12;
		L_12 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_11, NULL);
		NullCheck((MemberInfo_t*)L_12);
		String_t* L_13;
		L_13 = VirtualFuncInvoker0< String_t* >::Invoke(7, (MemberInfo_t*)L_12);
		int32_t L_14 = __this->____length;
		int32_t L_15 = L_14;
		RuntimeObject* L_16 = Box(il2cpp_defaults.int32_class, &L_15);
		String_t* L_17;
		L_17 = String_Format_mFB7DA489BD99F4670881FF50EC017BFB0A5C0987(_stringLiteral0DB46164953228904843938099AF66650313FEE5, (RuntimeObject*)L_13, L_16, NULL);
		return L_17;
	}
}
// Method Definition Index: 13830
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68 Span_1_Slice_m71F66E82B497EDEFAA6C05B61E8D5269F6BD6768_gshared (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68* __this, int32_t ___0_start, const RuntimeMethod* method) 
{
	ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) > ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A L_2 = __this->____pointer;
		V_0 = L_2;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_start;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_5;
		L_5 = il2cpp_unsafe_add<BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010,int32_t>(L_3, L_4);
		int32_t L_6 = __this->____length;
		int32_t L_7 = ___0_start;
		Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68 L_8;
		memset((&L_8), 0, sizeof(L_8));
		Span_1__ctor_m58CD191F04E31F33A163B278675E0FA9DF59166D_inline((&L_8), L_5, ((int32_t)il2cpp_codegen_subtract(L_6, L_7)), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_8;
	}
}
// Method Definition Index: 13831
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68 Span_1_Slice_mCD16A186DE80BBD11D476920A3F59374FC82AA3A_gshared (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68* __this, int32_t ___0_start, int32_t ___1_length, const RuntimeMethod* method) 
{
	ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0014;
		}
	}
	{
		int32_t L_2 = ___1_length;
		int32_t L_3 = __this->____length;
		int32_t L_4 = ___0_start;
		if ((!(((uint32_t)L_2) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(L_3, L_4))))))
		{
			goto IL_0019;
		}
	}

IL_0014:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0019:
	{
		ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A L_5 = __this->____pointer;
		V_0 = L_5;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010, (Il2CppByReference*)(&V_0));
		int32_t L_7 = ___0_start;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_8;
		L_8 = il2cpp_unsafe_add<BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010,int32_t>(L_6, L_7);
		int32_t L_9 = ___1_length;
		Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68 L_10;
		memset((&L_10), 0, sizeof(L_10));
		Span_1__ctor_m58CD191F04E31F33A163B278675E0FA9DF59166D_inline((&L_10), L_8, L_9, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_10;
	}
}
// Method Definition Index: 13832
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47* Span_1_ToArray_mA06436883B577519721F7120E4E97B6A09AE9A34_gshared (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68* __this, const RuntimeMethod* method) 
{
	ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_000e;
		}
	}
	{
		BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47* L_1;
		L_1 = Array_Empty_TisBoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010_mD26FFE584F3D72C1C64FD47A5D59D19E615D8050_inline(il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 20));
		return L_1;
	}

IL_000e:
	{
		int32_t L_2 = __this->____length;
		BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47* L_3 = (BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47*)(BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47*)SZArrayNew(il2cpp_rgctx_data(InitializedTypeInfo(method->klass)->rgctx_data, 21), (uint32_t)L_2);
		BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47* L_4 = L_3;
		NullCheck((RuntimeArray*)L_4);
		uint8_t* L_5;
		L_5 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_4, NULL);
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_6;
		L_6 = il2cpp_unsafe_as_ref<BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010>(L_5);
		ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A L_7 = __this->____pointer;
		V_0 = L_7;
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_8;
		L_8 = IL2CPP_BY_REFERENCE_GET_VALUE(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010, (Il2CppByReference*)(&V_0));
		int32_t L_9 = __this->____length;
		Buffer_Memmove_TisBoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010_m795273AEE7A248BA5B241E561023F0456EF5DED1(L_6, L_8, (uint64_t)((int64_t)L_9), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return L_4;
	}
}
// Method Definition Index: 13833
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mA3FAE42313871FB8DAC80A4C14DDE4CCE75F1713_gshared (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13834
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_Equals_m1F06F70877B365CDC03424CA9244D7402DCF2E98_gshared (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteral69508A540AFD085A745316DD7D6345B1C8CC662D)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13835
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_GetHashCode_m6AFC41E16438921AE019797FEF2A63869EB2D3B5_gshared (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68* __this, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteralECE618215BAC99C6FD12D8A273CC2118945EDCC8)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13836
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68 Span_1_op_Implicit_m0960F106DDF39687ED3DF0D35F5B72D4D1B7B9FF_gshared (BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47* ___0_array, const RuntimeMethod* method) 
{
	{
		BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47* L_0 = ___0_array;
		Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68 L_1;
		memset((&L_1), 0, sizeof(L_1));
		Span_1__ctor_m81F10BC6C4033345B53A361FD5777CB15BA51592_inline((&L_1), L_0, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 22));
		return L_1;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// Method Definition Index: 13818
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m2F8451EBCA1900A8FF93D17DF8177C41EAA2E94B_gshared (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634* __this, BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5* ___0_array, const RuntimeMethod* method) 
{
	Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3));
		goto IL_0037;
	}

IL_0037:
	{
		BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_4;
		L_4 = il2cpp_unsafe_as_ref<Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3>(L_3);
		ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13819
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m1D68DA3C995B291B2A4E6045AE32252D77530ED1_gshared (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634* __this, BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5* ___0_array, int32_t ___1_start, int32_t ___2_length, const RuntimeMethod* method) 
{
	Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_0016;
		}
	}
	{
		int32_t L_1 = ___1_start;
		if (L_1)
		{
			goto IL_0009;
		}
	}
	{
		int32_t L_2 = ___2_length;
		if (!L_2)
		{
			goto IL_000e;
		}
	}

IL_0009:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634));
		return;
	}

IL_0016:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3));
		goto IL_0042;
	}

IL_0042:
	{
		int32_t L_4 = ___1_start;
		BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5* L_5 = ___0_array;
		NullCheck(L_5);
		if ((!(((uint32_t)L_4) <= ((uint32_t)((int32_t)(((RuntimeArray*)L_5)->max_length))))))
		{
			goto IL_0050;
		}
	}
	{
		int32_t L_6 = ___2_length;
		BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5* L_7 = ___0_array;
		NullCheck(L_7);
		int32_t L_8 = ___1_start;
		if ((!(((uint32_t)L_6) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(((int32_t)(((RuntimeArray*)L_7)->max_length)), L_8))))))
		{
			goto IL_0055;
		}
	}

IL_0050:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0055:
	{
		BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5* L_9 = ___0_array;
		NullCheck((RuntimeArray*)L_9);
		uint8_t* L_10;
		L_10 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_9, NULL);
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_11;
		L_11 = il2cpp_unsafe_as_ref<Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3>(L_10);
		int32_t L_12 = ___1_start;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_13;
		L_13 = il2cpp_unsafe_add<Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3,int32_t>(L_11, L_12);
		ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B L_14;
		memset((&L_14), 0, sizeof(L_14));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_14), L_13);
		__this->____pointer = L_14;
		int32_t L_15 = ___2_length;
		__this->____length = L_15;
		return;
	}
}
// Method Definition Index: 13820
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m7A5841E1E41FE87941B0108F82BB86231379DC86_gshared (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634* __this, void* ___0_pointer, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		goto IL_0016;
	}

IL_0016:
	{
		int32_t L_0 = ___1_length;
		if ((((int32_t)L_0) >= ((int32_t)0)))
		{
			goto IL_001f;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_001f:
	{
		void* L_1 = ___0_pointer;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_2;
		L_2 = il2cpp_unsafe_as_ref<Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3>((uint8_t*)L_1);
		ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B L_3;
		memset((&L_3), 0, sizeof(L_3));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_3), L_2);
		__this->____pointer = L_3;
		int32_t L_4 = ___1_length;
		__this->____length = L_4;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mC3D811BCA73C14AA673ADAB2E2CF39FE1385714B_gshared (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634* __this, Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_0 = ___0_ptr;
		ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13822
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* Span_1_get_Item_m23EB68F027999B3A731DF8D6525D0D34EA9362F4_gshared (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634* __this, int32_t ___0_index, const RuntimeMethod* method) 
{
	ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_index;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) >= ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowIndexOutOfRangeException_m86F753A24E2765A35546BA6352A7E4F0BB8A66B5(NULL);
	}

IL_000e:
	{
		ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B L_2 = __this->____pointer;
		V_0 = L_2;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_index;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_5;
		L_5 = il2cpp_unsafe_add<Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3,int32_t>(L_3, L_4);
		return L_5;
	}
}
// Method Definition Index: 13823
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* Span_1_GetPinnableReference_m14130DD0E4AB97B002DEA65B3F3AB37938EFDB80_gshared (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634* __this, const RuntimeMethod* method) 
{
	ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_0010;
		}
	}
	{
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_1;
		L_1 = il2cpp_unsafe_as_ref<Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3>((void*)((uintptr_t)0));
		return L_1;
	}

IL_0010:
	{
		ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B L_2 = __this->____pointer;
		V_0 = L_2;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3, (Il2CppByReference*)(&V_0));
		return L_3;
	}
}
// Method Definition Index: 13824
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_Fill_mCED8A4E9FEA8D8594A3867571BCF99B8C9298EE5_gshared (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634* __this, Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3 ___0_value, const RuntimeMethod* method) 
{
	uint32_t V_0 = 0;
	Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3 V_1;
	memset((&V_1), 0, sizeof(V_1));
	ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B V_2;
	memset((&V_2), 0, sizeof(V_2));
	uint64_t V_3 = 0;
	Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* V_4 = NULL;
	uint64_t V_5 = 0;
	uint64_t V_6 = 0;
	{
		int32_t L_0;
		L_0 = il2cpp_unsafe_sizeof<Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3>();
		if ((!(((uint32_t)L_0) == ((uint32_t)1))))
		{
			goto IL_0037;
		}
	}
	{
		int32_t L_1 = __this->____length;
		V_0 = (uint32_t)L_1;
		uint32_t L_2 = V_0;
		if (L_2)
		{
			goto IL_0013;
		}
	}
	{
		return;
	}

IL_0013:
	{
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3 L_3 = ___0_value;
		V_1 = L_3;
		ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B L_4 = __this->____pointer;
		V_2 = L_4;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_5;
		L_5 = IL2CPP_BY_REFERENCE_GET_VALUE(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3, (Il2CppByReference*)(&V_2));
		uint8_t* L_6;
		L_6 = il2cpp_unsafe_as_ref<uint8_t>(L_5);
		uint8_t* L_7;
		L_7 = il2cpp_unsafe_as_ref<uint8_t>((&V_1));
		int32_t L_8 = *((uint8_t*)L_7);
		uint32_t L_9 = V_0;
		Unsafe_InitBlockUnaligned_m6F2353EB9ABC9320E61629FAEE23948C80BFF03A(L_6, (uint8_t)L_8, L_9, NULL);
		return;
	}

IL_0037:
	{
		int32_t L_10 = __this->____length;
		V_3 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_10));
		uint64_t L_11 = V_3;
		if (L_11)
		{
			goto IL_0043;
		}
	}
	{
		return;
	}

IL_0043:
	{
		ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B L_12 = __this->____pointer;
		V_2 = L_12;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_13;
		L_13 = IL2CPP_BY_REFERENCE_GET_VALUE(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3, (Il2CppByReference*)(&V_2));
		V_4 = L_13;
		int32_t L_14;
		L_14 = il2cpp_unsafe_sizeof<Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3>();
		V_5 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_14));
		V_6 = (uint64_t)((int64_t)0);
		goto IL_0110;
	}

IL_0064:
	{
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_15 = V_4;
		uint64_t L_16 = V_6;
		uint64_t L_17 = V_5;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_18;
		L_18 = il2cpp_unsafe_add_byte_offset<Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3,uint64_t>(L_15, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_16, (int64_t)L_17)));
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3 L_19 = ___0_value;
		*(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3*)L_18 = L_19;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_20 = V_4;
		uint64_t L_21 = V_6;
		uint64_t L_22 = V_5;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_23;
		L_23 = il2cpp_unsafe_add_byte_offset<Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3,uint64_t>(L_20, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_21, ((int64_t)1))), (int64_t)L_22)));
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3 L_24 = ___0_value;
		*(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3*)L_23 = L_24;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_25 = V_4;
		uint64_t L_26 = V_6;
		uint64_t L_27 = V_5;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_28;
		L_28 = il2cpp_unsafe_add_byte_offset<Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3,uint64_t>(L_25, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_26, ((int64_t)2))), (int64_t)L_27)));
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3 L_29 = ___0_value;
		*(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3*)L_28 = L_29;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_30 = V_4;
		uint64_t L_31 = V_6;
		uint64_t L_32 = V_5;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_33;
		L_33 = il2cpp_unsafe_add_byte_offset<Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3,uint64_t>(L_30, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_31, ((int64_t)3))), (int64_t)L_32)));
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3 L_34 = ___0_value;
		*(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3*)L_33 = L_34;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_35 = V_4;
		uint64_t L_36 = V_6;
		uint64_t L_37 = V_5;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_38;
		L_38 = il2cpp_unsafe_add_byte_offset<Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3,uint64_t>(L_35, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_36, ((int64_t)4))), (int64_t)L_37)));
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3 L_39 = ___0_value;
		*(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3*)L_38 = L_39;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_40 = V_4;
		uint64_t L_41 = V_6;
		uint64_t L_42 = V_5;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_43;
		L_43 = il2cpp_unsafe_add_byte_offset<Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3,uint64_t>(L_40, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_41, ((int64_t)5))), (int64_t)L_42)));
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3 L_44 = ___0_value;
		*(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3*)L_43 = L_44;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_45 = V_4;
		uint64_t L_46 = V_6;
		uint64_t L_47 = V_5;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_48;
		L_48 = il2cpp_unsafe_add_byte_offset<Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3,uint64_t>(L_45, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_46, ((int64_t)6))), (int64_t)L_47)));
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3 L_49 = ___0_value;
		*(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3*)L_48 = L_49;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_50 = V_4;
		uint64_t L_51 = V_6;
		uint64_t L_52 = V_5;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_53;
		L_53 = il2cpp_unsafe_add_byte_offset<Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3,uint64_t>(L_50, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_51, ((int64_t)7))), (int64_t)L_52)));
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3 L_54 = ___0_value;
		*(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3*)L_53 = L_54;
		uint64_t L_55 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_55, ((int64_t)8)));
	}

IL_0110:
	{
		uint64_t L_56 = V_6;
		uint64_t L_57 = V_3;
		if ((!(((uint64_t)L_56) >= ((uint64_t)((int64_t)((int64_t)L_57&((int64_t)((int32_t)-8))))))))
		{
			goto IL_0064;
		}
	}
	{
		uint64_t L_58 = V_6;
		uint64_t L_59 = V_3;
		if ((!(((uint64_t)L_58) < ((uint64_t)((int64_t)((int64_t)L_59&((int64_t)((int32_t)-4))))))))
		{
			goto IL_0198;
		}
	}
	{
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_60 = V_4;
		uint64_t L_61 = V_6;
		uint64_t L_62 = V_5;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_63;
		L_63 = il2cpp_unsafe_add_byte_offset<Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3,uint64_t>(L_60, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_61, (int64_t)L_62)));
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3 L_64 = ___0_value;
		*(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3*)L_63 = L_64;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_65 = V_4;
		uint64_t L_66 = V_6;
		uint64_t L_67 = V_5;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_68;
		L_68 = il2cpp_unsafe_add_byte_offset<Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3,uint64_t>(L_65, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_66, ((int64_t)1))), (int64_t)L_67)));
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3 L_69 = ___0_value;
		*(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3*)L_68 = L_69;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_70 = V_4;
		uint64_t L_71 = V_6;
		uint64_t L_72 = V_5;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_73;
		L_73 = il2cpp_unsafe_add_byte_offset<Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3,uint64_t>(L_70, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_71, ((int64_t)2))), (int64_t)L_72)));
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3 L_74 = ___0_value;
		*(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3*)L_73 = L_74;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_75 = V_4;
		uint64_t L_76 = V_6;
		uint64_t L_77 = V_5;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_78;
		L_78 = il2cpp_unsafe_add_byte_offset<Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3,uint64_t>(L_75, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_76, ((int64_t)3))), (int64_t)L_77)));
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3 L_79 = ___0_value;
		*(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3*)L_78 = L_79;
		uint64_t L_80 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_80, ((int64_t)4)));
		goto IL_0198;
	}

IL_017f:
	{
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_81 = V_4;
		uint64_t L_82 = V_6;
		uint64_t L_83 = V_5;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_84;
		L_84 = il2cpp_unsafe_add_byte_offset<Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3,uint64_t>(L_81, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_82, (int64_t)L_83)));
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3 L_85 = ___0_value;
		*(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3*)L_84 = L_85;
		uint64_t L_86 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_86, ((int64_t)1)));
	}

IL_0198:
	{
		uint64_t L_87 = V_6;
		uint64_t L_88 = V_3;
		if ((!(((uint64_t)L_87) >= ((uint64_t)L_88))))
		{
			goto IL_017f;
		}
	}
	{
		return;
	}
}
// Method Definition Index: 13825
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_CopyTo_mB183FB8E4C4ACE66FA4B887B2C6C26A314B5E080_gshared (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634* __this, Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634 ___0_destination, const RuntimeMethod* method) 
{
	ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_mF487895F53310FF6F399B7BD07038C49D29A6EAB_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0038;
		}
	}
	{
		Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634 L_2 = ___0_destination;
		ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B L_3 = L_2.____pointer;
		V_0 = L_3;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3, (Il2CppByReference*)(&V_0));
		ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B L_5 = __this->____pointer;
		V_0 = L_5;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3, (Il2CppByReference*)(&V_0));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3_m4925E1D88272BE2E628F28AFFEC4FF09CC2EC647(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return;
	}

IL_0038:
	{
		ThrowHelper_ThrowArgumentException_DestinationTooShort_m6468934A3BBB67DBC5BAEF7A64D91BD5BBBB3D4D(NULL);
		return;
	}
}
// Method Definition Index: 13826
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_TryCopyTo_mACC4DE78CDB50F24DD23B186C7E8DC340220254A_gshared (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634* __this, Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634 ___0_destination, const RuntimeMethod* method) 
{
	bool V_0 = false;
	ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		V_0 = (bool)0;
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_mF487895F53310FF6F399B7BD07038C49D29A6EAB_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_003b;
		}
	}
	{
		Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634 L_2 = ___0_destination;
		ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B L_3 = L_2.____pointer;
		V_1 = L_3;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3, (Il2CppByReference*)(&V_1));
		ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B L_5 = __this->____pointer;
		V_1 = L_5;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3, (Il2CppByReference*)(&V_1));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisBounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3_m4925E1D88272BE2E628F28AFFEC4FF09CC2EC647(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		V_0 = (bool)1;
	}

IL_003b:
	{
		bool L_8 = V_0;
		return L_8;
	}
}
// Method Definition Index: 13827
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_op_Equality_m1E4FBF19B62F0C30ADDB5CEED260A2D15D4A81A2_gshared (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634 ___0_left, Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634 ___1_right, const RuntimeMethod* method) 
{
	ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634 L_0 = ___0_left;
		int32_t L_1 = L_0.____length;
		Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634 L_2 = ___1_right;
		int32_t L_3 = L_2.____length;
		if ((!(((uint32_t)L_1) == ((uint32_t)L_3))))
		{
			goto IL_0030;
		}
	}
	{
		Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634 L_4 = ___0_left;
		ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B L_5 = L_4.____pointer;
		V_0 = L_5;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3, (Il2CppByReference*)(&V_0));
		Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634 L_7 = ___1_right;
		ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B L_8 = L_7.____pointer;
		V_0 = L_8;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_9;
		L_9 = IL2CPP_BY_REFERENCE_GET_VALUE(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3, (Il2CppByReference*)(&V_0));
		bool L_10;
		L_10 = il2cpp_unsafe_are_same(L_6, L_9);
		return L_10;
	}

IL_0030:
	{
		return (bool)0;
	}
}
// Method Definition Index: 13828
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ReadOnlySpan_1_tCA4391229D70967C5F52C769440BB78CBF80BE99 Span_1_op_Implicit_m0AA6AC1C800F0A73B2D20034416FF2F5F4806F90_gshared (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634 ___0_span, const RuntimeMethod* method) 
{
	ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634 L_0 = ___0_span;
		ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B L_1 = L_0.____pointer;
		V_0 = L_1;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_2;
		L_2 = IL2CPP_BY_REFERENCE_GET_VALUE(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3, (Il2CppByReference*)(&V_0));
		Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634 L_3 = ___0_span;
		int32_t L_4 = L_3.____length;
		ReadOnlySpan_1_tCA4391229D70967C5F52C769440BB78CBF80BE99 L_5;
		memset((&L_5), 0, sizeof(L_5));
		ReadOnlySpan_1__ctor_mB60BC31800C0877424E21575E45AE6C8AA50ADA6_inline((&L_5), L_2, L_4, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 18));
		return L_5;
	}
}
// Method Definition Index: 13829
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Span_1_ToString_mD2D2EBB7EED4A99E548C43E4528340F8372063E7_gshared (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral0DB46164953228904843938099AF66650313FEE5);
		s_Il2CppMethodInitialized = true;
	}
	Il2CppChar* V_0 = NULL;
	ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_0 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_1;
		L_1 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_0, NULL);
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_2 = { reinterpret_cast<intptr_t> (&il2cpp_defaults.char_class->byval_arg) };
		Type_t* L_3;
		L_3 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_2, NULL);
		bool L_4;
		L_4 = Type_op_Equality_m99930A0E44E420A685FABA60E60BA1CC5FA0EBDC(L_1, L_3, NULL);
		if (!L_4)
		{
			goto IL_003e;
		}
	}
	{
		ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B L_5 = __this->____pointer;
		V_1 = L_5;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3, (Il2CppByReference*)(&V_1));
		Il2CppChar* L_7;
		L_7 = il2cpp_unsafe_as_ref<Il2CppChar>(L_6);
		V_0 = L_7;
		Il2CppChar* L_8 = V_0;
		int32_t L_9 = __this->____length;
		String_t* L_10;
		L_10 = String_CreateString_m3F8794FEB452558B8A68C65E1F0B603B3D94E0E2(NULL, (Il2CppChar*)((uintptr_t)L_8), 0, L_9, NULL);
		return L_10;
	}

IL_003e:
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_11 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_12;
		L_12 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_11, NULL);
		NullCheck((MemberInfo_t*)L_12);
		String_t* L_13;
		L_13 = VirtualFuncInvoker0< String_t* >::Invoke(7, (MemberInfo_t*)L_12);
		int32_t L_14 = __this->____length;
		int32_t L_15 = L_14;
		RuntimeObject* L_16 = Box(il2cpp_defaults.int32_class, &L_15);
		String_t* L_17;
		L_17 = String_Format_mFB7DA489BD99F4670881FF50EC017BFB0A5C0987(_stringLiteral0DB46164953228904843938099AF66650313FEE5, (RuntimeObject*)L_13, L_16, NULL);
		return L_17;
	}
}
// Method Definition Index: 13830
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634 Span_1_Slice_m5DF84A262FF00D41C01E47878D2B66EB18E9E7DF_gshared (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634* __this, int32_t ___0_start, const RuntimeMethod* method) 
{
	ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) > ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B L_2 = __this->____pointer;
		V_0 = L_2;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_start;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_5;
		L_5 = il2cpp_unsafe_add<Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3,int32_t>(L_3, L_4);
		int32_t L_6 = __this->____length;
		int32_t L_7 = ___0_start;
		Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634 L_8;
		memset((&L_8), 0, sizeof(L_8));
		Span_1__ctor_mC3D811BCA73C14AA673ADAB2E2CF39FE1385714B_inline((&L_8), L_5, ((int32_t)il2cpp_codegen_subtract(L_6, L_7)), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_8;
	}
}
// Method Definition Index: 13831
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634 Span_1_Slice_m1A08ECA0C3737BBD50983EF79197E8FF50E84E81_gshared (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634* __this, int32_t ___0_start, int32_t ___1_length, const RuntimeMethod* method) 
{
	ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0014;
		}
	}
	{
		int32_t L_2 = ___1_length;
		int32_t L_3 = __this->____length;
		int32_t L_4 = ___0_start;
		if ((!(((uint32_t)L_2) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(L_3, L_4))))))
		{
			goto IL_0019;
		}
	}

IL_0014:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0019:
	{
		ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B L_5 = __this->____pointer;
		V_0 = L_5;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3, (Il2CppByReference*)(&V_0));
		int32_t L_7 = ___0_start;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_8;
		L_8 = il2cpp_unsafe_add<Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3,int32_t>(L_6, L_7);
		int32_t L_9 = ___1_length;
		Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634 L_10;
		memset((&L_10), 0, sizeof(L_10));
		Span_1__ctor_mC3D811BCA73C14AA673ADAB2E2CF39FE1385714B_inline((&L_10), L_8, L_9, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_10;
	}
}
// Method Definition Index: 13832
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5* Span_1_ToArray_mBBD4D2D1C13110607114AF4817753190558B3689_gshared (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634* __this, const RuntimeMethod* method) 
{
	ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_000e;
		}
	}
	{
		BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5* L_1;
		L_1 = Array_Empty_TisBounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3_mB6CFBB5D8AF33F0BAE72154209AB29B8D52FBCDA_inline(il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 20));
		return L_1;
	}

IL_000e:
	{
		int32_t L_2 = __this->____length;
		BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5* L_3 = (BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5*)(BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5*)SZArrayNew(il2cpp_rgctx_data(InitializedTypeInfo(method->klass)->rgctx_data, 21), (uint32_t)L_2);
		BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5* L_4 = L_3;
		NullCheck((RuntimeArray*)L_4);
		uint8_t* L_5;
		L_5 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_4, NULL);
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_6;
		L_6 = il2cpp_unsafe_as_ref<Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3>(L_5);
		ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B L_7 = __this->____pointer;
		V_0 = L_7;
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_8;
		L_8 = IL2CPP_BY_REFERENCE_GET_VALUE(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3, (Il2CppByReference*)(&V_0));
		int32_t L_9 = __this->____length;
		Buffer_Memmove_TisBounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3_m4925E1D88272BE2E628F28AFFEC4FF09CC2EC647(L_6, L_8, (uint64_t)((int64_t)L_9), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return L_4;
	}
}
// Method Definition Index: 13833
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mF487895F53310FF6F399B7BD07038C49D29A6EAB_gshared (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13834
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_Equals_m7767F56E291A6E7D52299410A83F3650CBB5D46A_gshared (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteral69508A540AFD085A745316DD7D6345B1C8CC662D)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13835
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_GetHashCode_mA98E83BB9F2E7AFC80DB8C1D8EA7ACBEE665D609_gshared (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634* __this, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteralECE618215BAC99C6FD12D8A273CC2118945EDCC8)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13836
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634 Span_1_op_Implicit_m296328C71F84E227BAAC66BCD8D4B6CCC4D0EF8B_gshared (BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5* ___0_array, const RuntimeMethod* method) 
{
	{
		BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5* L_0 = ___0_array;
		Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634 L_1;
		memset((&L_1), 0, sizeof(L_1));
		Span_1__ctor_m2F8451EBCA1900A8FF93D17DF8177C41EAA2E94B_inline((&L_1), L_0, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 22));
		return L_1;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// Method Definition Index: 13818
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m513968BDBFF3CFCE89F3F77FE44CAB22CA474EF9_gshared (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305* __this, ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* ___0_array, const RuntimeMethod* method) 
{
	uint8_t V_0 = 0x0;
	{
		ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(uint8_t));
		goto IL_0037;
	}

IL_0037:
	{
		ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		uint8_t* L_4;
		L_4 = il2cpp_unsafe_as_ref<uint8_t>(L_3);
		ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13819
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m698EC79E2E44AFF16BA096D0861CFB129FBF8218_gshared (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305* __this, ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* ___0_array, int32_t ___1_start, int32_t ___2_length, const RuntimeMethod* method) 
{
	uint8_t V_0 = 0x0;
	{
		ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_0016;
		}
	}
	{
		int32_t L_1 = ___1_start;
		if (L_1)
		{
			goto IL_0009;
		}
	}
	{
		int32_t L_2 = ___2_length;
		if (!L_2)
		{
			goto IL_000e;
		}
	}

IL_0009:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305));
		return;
	}

IL_0016:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(uint8_t));
		goto IL_0042;
	}

IL_0042:
	{
		int32_t L_4 = ___1_start;
		ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* L_5 = ___0_array;
		NullCheck(L_5);
		if ((!(((uint32_t)L_4) <= ((uint32_t)((int32_t)(((RuntimeArray*)L_5)->max_length))))))
		{
			goto IL_0050;
		}
	}
	{
		int32_t L_6 = ___2_length;
		ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* L_7 = ___0_array;
		NullCheck(L_7);
		int32_t L_8 = ___1_start;
		if ((!(((uint32_t)L_6) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(((int32_t)(((RuntimeArray*)L_7)->max_length)), L_8))))))
		{
			goto IL_0055;
		}
	}

IL_0050:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0055:
	{
		ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* L_9 = ___0_array;
		NullCheck((RuntimeArray*)L_9);
		uint8_t* L_10;
		L_10 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_9, NULL);
		uint8_t* L_11;
		L_11 = il2cpp_unsafe_as_ref<uint8_t>(L_10);
		int32_t L_12 = ___1_start;
		uint8_t* L_13;
		L_13 = il2cpp_unsafe_add<uint8_t,int32_t>(L_11, L_12);
		ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC L_14;
		memset((&L_14), 0, sizeof(L_14));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_14), L_13);
		__this->____pointer = L_14;
		int32_t L_15 = ___2_length;
		__this->____length = L_15;
		return;
	}
}
// Method Definition Index: 13820
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_mE18EBB601FBFA01BA29FE353364700952A9091FE_gshared (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305* __this, void* ___0_pointer, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		goto IL_0016;
	}

IL_0016:
	{
		int32_t L_0 = ___1_length;
		if ((((int32_t)L_0) >= ((int32_t)0)))
		{
			goto IL_001f;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_001f:
	{
		void* L_1 = ___0_pointer;
		uint8_t* L_2;
		L_2 = il2cpp_unsafe_as_ref<uint8_t>((uint8_t*)L_1);
		ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC L_3;
		memset((&L_3), 0, sizeof(L_3));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_3), L_2);
		__this->____pointer = L_3;
		int32_t L_4 = ___1_length;
		__this->____length = L_4;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1__ctor_m947BF95D54571BF3897F96822B7A8FDA5853497B_gshared (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305* __this, uint8_t* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		uint8_t* L_0 = ___0_ptr;
		ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13822
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR uint8_t* Span_1_get_Item_mA500F3AC2BE20A39F8425A1CCA39B704F44DC0E1_gshared (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305* __this, int32_t ___0_index, const RuntimeMethod* method) 
{
	ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_index;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) >= ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowIndexOutOfRangeException_m86F753A24E2765A35546BA6352A7E4F0BB8A66B5(NULL);
	}

IL_000e:
	{
		ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC L_2 = __this->____pointer;
		V_0 = L_2;
		uint8_t* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(uint8_t, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_index;
		uint8_t* L_5;
		L_5 = il2cpp_unsafe_add<uint8_t,int32_t>(L_3, L_4);
		return L_5;
	}
}
// Method Definition Index: 13823
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR uint8_t* Span_1_GetPinnableReference_m55DA180AC02A047DAC0626C7B8CBC2E87626DD0C_gshared (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305* __this, const RuntimeMethod* method) 
{
	ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_0010;
		}
	}
	{
		uint8_t* L_1;
		L_1 = il2cpp_unsafe_as_ref<uint8_t>((void*)((uintptr_t)0));
		return L_1;
	}

IL_0010:
	{
		ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC L_2 = __this->____pointer;
		V_0 = L_2;
		uint8_t* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(uint8_t, (Il2CppByReference*)(&V_0));
		return L_3;
	}
}
// Method Definition Index: 13824
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_Fill_m5C8306E094A7E370D52DE264ED5D3743FC7A51A8_gshared (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305* __this, uint8_t ___0_value, const RuntimeMethod* method) 
{
	uint32_t V_0 = 0;
	uint8_t V_1 = 0x0;
	ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC V_2;
	memset((&V_2), 0, sizeof(V_2));
	uint64_t V_3 = 0;
	uint8_t* V_4 = NULL;
	uint64_t V_5 = 0;
	uint64_t V_6 = 0;
	{
		int32_t L_0;
		L_0 = il2cpp_unsafe_sizeof<uint8_t>();
		if ((!(((uint32_t)L_0) == ((uint32_t)1))))
		{
			goto IL_0037;
		}
	}
	{
		int32_t L_1 = __this->____length;
		V_0 = (uint32_t)L_1;
		uint32_t L_2 = V_0;
		if (L_2)
		{
			goto IL_0013;
		}
	}
	{
		return;
	}

IL_0013:
	{
		uint8_t L_3 = ___0_value;
		V_1 = L_3;
		ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC L_4 = __this->____pointer;
		V_2 = L_4;
		uint8_t* L_5;
		L_5 = IL2CPP_BY_REFERENCE_GET_VALUE(uint8_t, (Il2CppByReference*)(&V_2));
		uint8_t* L_6;
		L_6 = il2cpp_unsafe_as_ref<uint8_t>(L_5);
		uint8_t* L_7;
		L_7 = il2cpp_unsafe_as_ref<uint8_t>((&V_1));
		int32_t L_8 = *((uint8_t*)L_7);
		uint32_t L_9 = V_0;
		Unsafe_InitBlockUnaligned_m6F2353EB9ABC9320E61629FAEE23948C80BFF03A(L_6, (uint8_t)L_8, L_9, NULL);
		return;
	}

IL_0037:
	{
		int32_t L_10 = __this->____length;
		V_3 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_10));
		uint64_t L_11 = V_3;
		if (L_11)
		{
			goto IL_0043;
		}
	}
	{
		return;
	}

IL_0043:
	{
		ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC L_12 = __this->____pointer;
		V_2 = L_12;
		uint8_t* L_13;
		L_13 = IL2CPP_BY_REFERENCE_GET_VALUE(uint8_t, (Il2CppByReference*)(&V_2));
		V_4 = L_13;
		int32_t L_14;
		L_14 = il2cpp_unsafe_sizeof<uint8_t>();
		V_5 = (uint64_t)((int64_t)(uint64_t)((uint32_t)L_14));
		V_6 = (uint64_t)((int64_t)0);
		goto IL_0110;
	}

IL_0064:
	{
		uint8_t* L_15 = V_4;
		uint64_t L_16 = V_6;
		uint64_t L_17 = V_5;
		uint8_t* L_18;
		L_18 = il2cpp_unsafe_add_byte_offset<uint8_t,uint64_t>(L_15, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_16, (int64_t)L_17)));
		uint8_t L_19 = ___0_value;
		*(uint8_t*)L_18 = L_19;
		uint8_t* L_20 = V_4;
		uint64_t L_21 = V_6;
		uint64_t L_22 = V_5;
		uint8_t* L_23;
		L_23 = il2cpp_unsafe_add_byte_offset<uint8_t,uint64_t>(L_20, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_21, ((int64_t)1))), (int64_t)L_22)));
		uint8_t L_24 = ___0_value;
		*(uint8_t*)L_23 = L_24;
		uint8_t* L_25 = V_4;
		uint64_t L_26 = V_6;
		uint64_t L_27 = V_5;
		uint8_t* L_28;
		L_28 = il2cpp_unsafe_add_byte_offset<uint8_t,uint64_t>(L_25, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_26, ((int64_t)2))), (int64_t)L_27)));
		uint8_t L_29 = ___0_value;
		*(uint8_t*)L_28 = L_29;
		uint8_t* L_30 = V_4;
		uint64_t L_31 = V_6;
		uint64_t L_32 = V_5;
		uint8_t* L_33;
		L_33 = il2cpp_unsafe_add_byte_offset<uint8_t,uint64_t>(L_30, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_31, ((int64_t)3))), (int64_t)L_32)));
		uint8_t L_34 = ___0_value;
		*(uint8_t*)L_33 = L_34;
		uint8_t* L_35 = V_4;
		uint64_t L_36 = V_6;
		uint64_t L_37 = V_5;
		uint8_t* L_38;
		L_38 = il2cpp_unsafe_add_byte_offset<uint8_t,uint64_t>(L_35, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_36, ((int64_t)4))), (int64_t)L_37)));
		uint8_t L_39 = ___0_value;
		*(uint8_t*)L_38 = L_39;
		uint8_t* L_40 = V_4;
		uint64_t L_41 = V_6;
		uint64_t L_42 = V_5;
		uint8_t* L_43;
		L_43 = il2cpp_unsafe_add_byte_offset<uint8_t,uint64_t>(L_40, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_41, ((int64_t)5))), (int64_t)L_42)));
		uint8_t L_44 = ___0_value;
		*(uint8_t*)L_43 = L_44;
		uint8_t* L_45 = V_4;
		uint64_t L_46 = V_6;
		uint64_t L_47 = V_5;
		uint8_t* L_48;
		L_48 = il2cpp_unsafe_add_byte_offset<uint8_t,uint64_t>(L_45, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_46, ((int64_t)6))), (int64_t)L_47)));
		uint8_t L_49 = ___0_value;
		*(uint8_t*)L_48 = L_49;
		uint8_t* L_50 = V_4;
		uint64_t L_51 = V_6;
		uint64_t L_52 = V_5;
		uint8_t* L_53;
		L_53 = il2cpp_unsafe_add_byte_offset<uint8_t,uint64_t>(L_50, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_51, ((int64_t)7))), (int64_t)L_52)));
		uint8_t L_54 = ___0_value;
		*(uint8_t*)L_53 = L_54;
		uint64_t L_55 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_55, ((int64_t)8)));
	}

IL_0110:
	{
		uint64_t L_56 = V_6;
		uint64_t L_57 = V_3;
		if ((!(((uint64_t)L_56) >= ((uint64_t)((int64_t)((int64_t)L_57&((int64_t)((int32_t)-8))))))))
		{
			goto IL_0064;
		}
	}
	{
		uint64_t L_58 = V_6;
		uint64_t L_59 = V_3;
		if ((!(((uint64_t)L_58) < ((uint64_t)((int64_t)((int64_t)L_59&((int64_t)((int32_t)-4))))))))
		{
			goto IL_0198;
		}
	}
	{
		uint8_t* L_60 = V_4;
		uint64_t L_61 = V_6;
		uint64_t L_62 = V_5;
		uint8_t* L_63;
		L_63 = il2cpp_unsafe_add_byte_offset<uint8_t,uint64_t>(L_60, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_61, (int64_t)L_62)));
		uint8_t L_64 = ___0_value;
		*(uint8_t*)L_63 = L_64;
		uint8_t* L_65 = V_4;
		uint64_t L_66 = V_6;
		uint64_t L_67 = V_5;
		uint8_t* L_68;
		L_68 = il2cpp_unsafe_add_byte_offset<uint8_t,uint64_t>(L_65, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_66, ((int64_t)1))), (int64_t)L_67)));
		uint8_t L_69 = ___0_value;
		*(uint8_t*)L_68 = L_69;
		uint8_t* L_70 = V_4;
		uint64_t L_71 = V_6;
		uint64_t L_72 = V_5;
		uint8_t* L_73;
		L_73 = il2cpp_unsafe_add_byte_offset<uint8_t,uint64_t>(L_70, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_71, ((int64_t)2))), (int64_t)L_72)));
		uint8_t L_74 = ___0_value;
		*(uint8_t*)L_73 = L_74;
		uint8_t* L_75 = V_4;
		uint64_t L_76 = V_6;
		uint64_t L_77 = V_5;
		uint8_t* L_78;
		L_78 = il2cpp_unsafe_add_byte_offset<uint8_t,uint64_t>(L_75, (uint64_t)((int64_t)il2cpp_codegen_multiply(((int64_t)il2cpp_codegen_add((int64_t)L_76, ((int64_t)3))), (int64_t)L_77)));
		uint8_t L_79 = ___0_value;
		*(uint8_t*)L_78 = L_79;
		uint64_t L_80 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_80, ((int64_t)4)));
		goto IL_0198;
	}

IL_017f:
	{
		uint8_t* L_81 = V_4;
		uint64_t L_82 = V_6;
		uint64_t L_83 = V_5;
		uint8_t* L_84;
		L_84 = il2cpp_unsafe_add_byte_offset<uint8_t,uint64_t>(L_81, (uint64_t)((int64_t)il2cpp_codegen_multiply((int64_t)L_82, (int64_t)L_83)));
		uint8_t L_85 = ___0_value;
		*(uint8_t*)L_84 = L_85;
		uint64_t L_86 = V_6;
		V_6 = (uint64_t)((int64_t)il2cpp_codegen_add((int64_t)L_86, ((int64_t)1)));
	}

IL_0198:
	{
		uint64_t L_87 = V_6;
		uint64_t L_88 = V_3;
		if ((!(((uint64_t)L_87) >= ((uint64_t)L_88))))
		{
			goto IL_017f;
		}
	}
	{
		return;
	}
}
// Method Definition Index: 13825
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Span_1_CopyTo_m7A06ABD95EC3209F4FC307CAB38FD87202A88542_gshared (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305* __this, Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305 ___0_destination, const RuntimeMethod* method) 
{
	ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_m8E944E4954E037877A25B9FF6B901F1F901D4769_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0038;
		}
	}
	{
		Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305 L_2 = ___0_destination;
		ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC L_3 = L_2.____pointer;
		V_0 = L_3;
		uint8_t* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(uint8_t, (Il2CppByReference*)(&V_0));
		ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC L_5 = __this->____pointer;
		V_0 = L_5;
		uint8_t* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(uint8_t, (Il2CppByReference*)(&V_0));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisByte_t94D9231AC217BE4D2E004C4CD32DF6D099EA41A3_mB1465EEEBE0A608FA51B29BC3F145F287AD04190(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return;
	}

IL_0038:
	{
		ThrowHelper_ThrowArgumentException_DestinationTooShort_m6468934A3BBB67DBC5BAEF7A64D91BD5BBBB3D4D(NULL);
		return;
	}
}
// Method Definition Index: 13826
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_TryCopyTo_mFD99196C62E0CE029A2E64ED0B0F4FEC623B9F56_gshared (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305* __this, Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305 ___0_destination, const RuntimeMethod* method) 
{
	bool V_0 = false;
	ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		V_0 = (bool)0;
		int32_t L_0 = __this->____length;
		int32_t L_1;
		L_1 = Span_1_get_Length_m8E944E4954E037877A25B9FF6B901F1F901D4769_inline((&___0_destination), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 13));
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_003b;
		}
	}
	{
		Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305 L_2 = ___0_destination;
		ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC L_3 = L_2.____pointer;
		V_1 = L_3;
		uint8_t* L_4;
		L_4 = IL2CPP_BY_REFERENCE_GET_VALUE(uint8_t, (Il2CppByReference*)(&V_1));
		ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC L_5 = __this->____pointer;
		V_1 = L_5;
		uint8_t* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(uint8_t, (Il2CppByReference*)(&V_1));
		int32_t L_7 = __this->____length;
		Buffer_Memmove_TisByte_t94D9231AC217BE4D2E004C4CD32DF6D099EA41A3_mB1465EEEBE0A608FA51B29BC3F145F287AD04190(L_4, L_6, (uint64_t)((int64_t)L_7), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		V_0 = (bool)1;
	}

IL_003b:
	{
		bool L_8 = V_0;
		return L_8;
	}
}
// Method Definition Index: 13827
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_op_Equality_mBB7F4722E6098C4202A57F3782309BD5CBBF2BF7_gshared (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305 ___0_left, Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305 ___1_right, const RuntimeMethod* method) 
{
	ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305 L_0 = ___0_left;
		int32_t L_1 = L_0.____length;
		Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305 L_2 = ___1_right;
		int32_t L_3 = L_2.____length;
		if ((!(((uint32_t)L_1) == ((uint32_t)L_3))))
		{
			goto IL_0030;
		}
	}
	{
		Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305 L_4 = ___0_left;
		ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC L_5 = L_4.____pointer;
		V_0 = L_5;
		uint8_t* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(uint8_t, (Il2CppByReference*)(&V_0));
		Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305 L_7 = ___1_right;
		ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC L_8 = L_7.____pointer;
		V_0 = L_8;
		uint8_t* L_9;
		L_9 = IL2CPP_BY_REFERENCE_GET_VALUE(uint8_t, (Il2CppByReference*)(&V_0));
		bool L_10;
		L_10 = il2cpp_unsafe_are_same(L_6, L_9);
		return L_10;
	}

IL_0030:
	{
		return (bool)0;
	}
}
// Method Definition Index: 13828
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ReadOnlySpan_1_tA850A6C0E88ABBA37646A078ACBC24D6D5FD9B4D Span_1_op_Implicit_mD249188242C0C9D3192A31E9F7FA74C683F05B84_gshared (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305 ___0_span, const RuntimeMethod* method) 
{
	ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305 L_0 = ___0_span;
		ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC L_1 = L_0.____pointer;
		V_0 = L_1;
		uint8_t* L_2;
		L_2 = IL2CPP_BY_REFERENCE_GET_VALUE(uint8_t, (Il2CppByReference*)(&V_0));
		Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305 L_3 = ___0_span;
		int32_t L_4 = L_3.____length;
		ReadOnlySpan_1_tA850A6C0E88ABBA37646A078ACBC24D6D5FD9B4D L_5;
		memset((&L_5), 0, sizeof(L_5));
		ReadOnlySpan_1__ctor_m0FC0B92549C2968E80B5F75A85F28B96DBFCFD63_inline((&L_5), L_2, L_4, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 18));
		return L_5;
	}
}
// Method Definition Index: 13829
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Span_1_ToString_m94E8AC193D974B79432BD6D8CC8AE7E7832AC6A4_gshared (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral0DB46164953228904843938099AF66650313FEE5);
		s_Il2CppMethodInitialized = true;
	}
	Il2CppChar* V_0 = NULL;
	ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_0 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_1;
		L_1 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_0, NULL);
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_2 = { reinterpret_cast<intptr_t> (&il2cpp_defaults.char_class->byval_arg) };
		Type_t* L_3;
		L_3 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_2, NULL);
		bool L_4;
		L_4 = Type_op_Equality_m99930A0E44E420A685FABA60E60BA1CC5FA0EBDC(L_1, L_3, NULL);
		if (!L_4)
		{
			goto IL_003e;
		}
	}
	{
		ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC L_5 = __this->____pointer;
		V_1 = L_5;
		uint8_t* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(uint8_t, (Il2CppByReference*)(&V_1));
		Il2CppChar* L_7;
		L_7 = il2cpp_unsafe_as_ref<Il2CppChar>(L_6);
		V_0 = L_7;
		Il2CppChar* L_8 = V_0;
		int32_t L_9 = __this->____length;
		String_t* L_10;
		L_10 = String_CreateString_m3F8794FEB452558B8A68C65E1F0B603B3D94E0E2(NULL, (Il2CppChar*)((uintptr_t)L_8), 0, L_9, NULL);
		return L_10;
	}

IL_003e:
	{
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_11 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 9)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_12;
		L_12 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_11, NULL);
		NullCheck((MemberInfo_t*)L_12);
		String_t* L_13;
		L_13 = VirtualFuncInvoker0< String_t* >::Invoke(7, (MemberInfo_t*)L_12);
		int32_t L_14 = __this->____length;
		int32_t L_15 = L_14;
		RuntimeObject* L_16 = Box(il2cpp_defaults.int32_class, &L_15);
		String_t* L_17;
		L_17 = String_Format_mFB7DA489BD99F4670881FF50EC017BFB0A5C0987(_stringLiteral0DB46164953228904843938099AF66650313FEE5, (RuntimeObject*)L_13, L_16, NULL);
		return L_17;
	}
}
// Method Definition Index: 13830
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305 Span_1_Slice_m720734AA48ECB663CAA0594530927B9015A64341_gshared (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305* __this, int32_t ___0_start, const RuntimeMethod* method) 
{
	ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) > ((uint32_t)L_1))))
		{
			goto IL_000e;
		}
	}
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_000e:
	{
		ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC L_2 = __this->____pointer;
		V_0 = L_2;
		uint8_t* L_3;
		L_3 = IL2CPP_BY_REFERENCE_GET_VALUE(uint8_t, (Il2CppByReference*)(&V_0));
		int32_t L_4 = ___0_start;
		uint8_t* L_5;
		L_5 = il2cpp_unsafe_add<uint8_t,int32_t>(L_3, L_4);
		int32_t L_6 = __this->____length;
		int32_t L_7 = ___0_start;
		Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305 L_8;
		memset((&L_8), 0, sizeof(L_8));
		Span_1__ctor_m947BF95D54571BF3897F96822B7A8FDA5853497B_inline((&L_8), L_5, ((int32_t)il2cpp_codegen_subtract(L_6, L_7)), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_8;
	}
}
// Method Definition Index: 13831
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305 Span_1_Slice_m9D8BA8245B8DC9BFB4A4164759CBAAEAD1318CD6_gshared (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305* __this, int32_t ___0_start, int32_t ___1_length, const RuntimeMethod* method) 
{
	ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = ___0_start;
		int32_t L_1 = __this->____length;
		if ((!(((uint32_t)L_0) <= ((uint32_t)L_1))))
		{
			goto IL_0014;
		}
	}
	{
		int32_t L_2 = ___1_length;
		int32_t L_3 = __this->____length;
		int32_t L_4 = ___0_start;
		if ((!(((uint32_t)L_2) > ((uint32_t)((int32_t)il2cpp_codegen_subtract(L_3, L_4))))))
		{
			goto IL_0019;
		}
	}

IL_0014:
	{
		ThrowHelper_ThrowArgumentOutOfRangeException_mD7D90276EDCDF9394A8EA635923E3B48BB71BD56(NULL);
	}

IL_0019:
	{
		ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC L_5 = __this->____pointer;
		V_0 = L_5;
		uint8_t* L_6;
		L_6 = IL2CPP_BY_REFERENCE_GET_VALUE(uint8_t, (Il2CppByReference*)(&V_0));
		int32_t L_7 = ___0_start;
		uint8_t* L_8;
		L_8 = il2cpp_unsafe_add<uint8_t,int32_t>(L_6, L_7);
		int32_t L_9 = ___1_length;
		Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305 L_10;
		memset((&L_10), 0, sizeof(L_10));
		Span_1__ctor_m947BF95D54571BF3897F96822B7A8FDA5853497B_inline((&L_10), L_8, L_9, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 19));
		return L_10;
	}
}
// Method Definition Index: 13832
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* Span_1_ToArray_mF415F39478D842BDA5A27003F3B9D3903DCE24BF_gshared (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305* __this, const RuntimeMethod* method) 
{
	ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		int32_t L_0 = __this->____length;
		if (L_0)
		{
			goto IL_000e;
		}
	}
	{
		ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* L_1;
		L_1 = Array_Empty_TisByte_t94D9231AC217BE4D2E004C4CD32DF6D099EA41A3_m6080CA526758F4FA182A066B2780D1761CD36ED5_inline(il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 20));
		return L_1;
	}

IL_000e:
	{
		int32_t L_2 = __this->____length;
		ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* L_3 = (ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031*)(ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031*)SZArrayNew(il2cpp_rgctx_data(InitializedTypeInfo(method->klass)->rgctx_data, 21), (uint32_t)L_2);
		ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* L_4 = L_3;
		NullCheck((RuntimeArray*)L_4);
		uint8_t* L_5;
		L_5 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_4, NULL);
		uint8_t* L_6;
		L_6 = il2cpp_unsafe_as_ref<uint8_t>(L_5);
		ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC L_7 = __this->____pointer;
		V_0 = L_7;
		uint8_t* L_8;
		L_8 = IL2CPP_BY_REFERENCE_GET_VALUE(uint8_t, (Il2CppByReference*)(&V_0));
		int32_t L_9 = __this->____length;
		Buffer_Memmove_TisByte_t94D9231AC217BE4D2E004C4CD32DF6D099EA41A3_mB1465EEEBE0A608FA51B29BC3F145F287AD04190(L_6, L_8, (uint64_t)((int64_t)L_9), il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 15));
		return L_4;
	}
}
// Method Definition Index: 13833
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m8E944E4954E037877A25B9FF6B901F1F901D4769_gshared (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13834
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Span_1_Equals_m7F08055851C835FE3E76471A6015683E6CCBD980_gshared (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteral69508A540AFD085A745316DD7D6345B1C8CC662D)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13835
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Span_1_GetHashCode_mAB5C55282F13372D4B32AFA20E3E2618CE417F61_gshared (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305* __this, const RuntimeMethod* method) 
{
	{
		NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A* L_0 = (NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A*)il2cpp_codegen_object_new(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&NotSupportedException_t1429765983D409BD2986508963C98D214E4EBF4A_il2cpp_TypeInfo_var)));
		NotSupportedException__ctor_mE174750CF0247BBB47544FFD71D66BB89630945B(L_0, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteralECE618215BAC99C6FD12D8A273CC2118945EDCC8)), NULL);
		IL2CPP_RAISE_MANAGED_EXCEPTION(L_0, method);
	}
}
// Method Definition Index: 13836
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305 Span_1_op_Implicit_m8619157C8809464A173FF531960A75A0ACE2CE91_gshared (ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* ___0_array, const RuntimeMethod* method) 
{
	{
		ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* L_0 = ___0_array;
		Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305 L_1;
		memset((&L_1), 0, sizeof(L_1));
		Span_1__ctor_m513968BDBFF3CFCE89F3F77FE44CAB22CA474EF9_inline((&L_1), L_0, il2cpp_rgctx_method(InitializedTypeInfo(method->klass)->rgctx_data, 22));
		return L_1;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
// Method Definition Index: 14481
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR uint8_t* Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline (RuntimeArray* __this, const RuntimeMethod* method) 
{
	{
		RawData_t37CAF2D3F74B7723974ED7CEEE9B297D8FA64ED0* L_0;
		L_0 = il2cpp_unsafe_as<RawData_t37CAF2D3F74B7723974ED7CEEE9B297D8FA64ED0*>(__this);
		NullCheck(L_0);
		uint8_t* L_1 = (uint8_t*)(&L_0->___Data);
		return L_1;
	}
}
// Method Definition Index: 13833
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m1D64B90D223BD596E1C2DA8966AC4ED57C8E4589_gshared_inline (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13729
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m15AA8CF0C1EB395F786CC37F5D7AF29EA7C10709_gshared_inline (ReadOnlySpan_1_t4990792F01A3EE12CEEC65526A813F1C0A67283E* __this, HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_0 = ___0_ptr;
		ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mA74DDDE530919E8BC3D60C7CBD981ED0F11DD14F_gshared_inline (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B* __this, HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_0 = ___0_ptr;
		ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 14530
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C* Array_Empty_TisHashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5_mA6305FE1F89FEF2783F9DE478AFB982C8BA1EDC2_gshared_inline (const RuntimeMethod* method) 
{
	il2cpp_rgctx_method_init(method);
	{
		il2cpp_codegen_runtime_class_init_inline(il2cpp_rgctx_data(method->rgctx_data, 2));
		HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C* L_0 = ((EmptyArray_1_tB1385D6D2663B69CA2DB28A3D0AE3106C328A232_StaticFields*)il2cpp_codegen_static_fields_for(il2cpp_rgctx_data(method->rgctx_data, 2)))->___Value;
		return L_0;
	}
}
// Method Definition Index: 13818
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m2646A3BCF55B82973A1E7845EE19BC641CD4D35E_gshared_inline (Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B* __this, HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C* ___0_array, const RuntimeMethod* method) 
{
	HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t0DA3B860CC98B496B4F0EA5EE89B2D4C39016B3B));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5));
		goto IL_0037;
	}

IL_0037:
	{
		HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5* L_4;
		L_4 = il2cpp_unsafe_as_ref<HashEntry_1_t5AE25C72F3412B2BFC157F053A4BE627685A9ED5>(L_3);
		ByReference_1_t3422C9D8E6631CDE5881688C670AF43B7C9705D7 L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		HashEntry_1U5BU5D_t3F4A6629D30B7EFFFC9E589802513A7F8E45503C* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13833
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m92912F015428BEA4C4E33A78B846E6B3CDB11751_gshared_inline (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13729
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m4868E2A05AB294488A5AF17BC94F000C5A0F68F7_gshared_inline (ReadOnlySpan_1_t38A9F2C8804739CCDFBD7062CA1C47C44BAE5AC2* __this, Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_0 = ___0_ptr;
		ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mDA8E6598A3E9F174C4024C9915ACA9463BA35F22_gshared_inline (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3* __this, Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_0 = ___0_ptr;
		ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 14530
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* Array_Empty_TisNullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28_mFB61E6C1D57945549B403C13B1652F8CE5324A3C_gshared_inline (const RuntimeMethod* method) 
{
	il2cpp_rgctx_method_init(method);
	{
		il2cpp_codegen_runtime_class_init_inline(il2cpp_rgctx_data(method->rgctx_data, 2));
		Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* L_0 = ((EmptyArray_1_t5DEA403BAFDC5AC2F35C8826395BF35F76EAE720_StaticFields*)il2cpp_codegen_static_fields_for(il2cpp_rgctx_data(method->rgctx_data, 2)))->___Value;
		return L_0;
	}
}
// Method Definition Index: 13818
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m809249502A9C502A89D571353424C3BAC9610EFA_gshared_inline (Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3* __this, Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* ___0_array, const RuntimeMethod* method) 
{
	Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t13FC778C32B303EBD00D6D808D35F6A0FBD988E3));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28));
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28 L_1 = V_0;
		if (L_1.___hasValue)
		{
			goto IL_0037;
		}
	}
	{
		Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* L_2 = ___0_array;
		NullCheck((RuntimeObject*)L_2);
		Type_t* L_3;
		L_3 = Object_GetType_mE10A8FC1E57F3DF29972CCBC026C2DC3942263B3((RuntimeObject*)L_2, NULL);
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_4 = { reinterpret_cast<intptr_t> (il2cpp_rgctx_type(InitializedTypeInfo(method->klass)->rgctx_data, 3)) };
		il2cpp_codegen_runtime_class_init_inline(il2cpp_defaults.systemtype_class);
		Type_t* L_5;
		L_5 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_4, NULL);
		bool L_6;
		L_6 = Type_op_Inequality_m83209C7BB3C05DFBEA3B6199B0BEFE8037301172(L_3, L_5, NULL);
		if (!L_6)
		{
			goto IL_0037;
		}
	}
	{
		ThrowHelper_ThrowArrayTypeMismatchException_m781AD7A903FEA43FAE3137977E6BC5F9BAEBC590(NULL);
	}

IL_0037:
	{
		Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* L_7 = ___0_array;
		NullCheck((RuntimeArray*)L_7);
		uint8_t* L_8;
		L_8 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_7, NULL);
		Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28* L_9;
		L_9 = il2cpp_unsafe_as_ref<Nullable_1_tCF32C56A2641879C053C86F273C0C6EC1B40BC28>(L_8);
		ByReference_1_t442613A31118CABBBDFA511BC4B3E7B369A7720C L_10;
		memset((&L_10), 0, sizeof(L_10));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_10), L_9);
		__this->____pointer = L_10;
		Nullable_1U5BU5D_t4BBED9A00EC2D72318A66049CDB2B0B2553D4B43* L_11 = ___0_array;
		NullCheck(L_11);
		__this->____length = ((int32_t)(((RuntimeArray*)L_11)->max_length));
		return;
	}
}
// Method Definition Index: 13833
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mE6A401C96B3DFE2B3B68A13B3AE43400879A9981_gshared_inline (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13729
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_mFB782BAD5E6881410F56403A2B56A0ED54E3A1D8_gshared_inline (ReadOnlySpan_1_t4757BCCBBFA890A355792A3EB923FE9C101A9C76* __this, ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_0 = ___0_ptr;
		ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m12E72689FB9ED51748E944C2B3CF2E15EF0EB168_gshared_inline (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019* __this, ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_0 = ___0_ptr;
		ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 14530
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3* Array_Empty_TisValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA_mB7B36B66111D0BD07DEA806DA065B3F1EB37F96C_gshared_inline (const RuntimeMethod* method) 
{
	il2cpp_rgctx_method_init(method);
	{
		il2cpp_codegen_runtime_class_init_inline(il2cpp_rgctx_data(method->rgctx_data, 2));
		ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3* L_0 = ((EmptyArray_1_t3066E86777F40E3D88AD86B8F23145AC18BD3029_StaticFields*)il2cpp_codegen_static_fields_for(il2cpp_rgctx_data(method->rgctx_data, 2)))->___Value;
		return L_0;
	}
}
// Method Definition Index: 13818
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mF27289AA0FF72C4ED5AEE3D24C8A1052C9522A5F_gshared_inline (Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019* __this, ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3* ___0_array, const RuntimeMethod* method) 
{
	ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t97B298B1E5CB6041301BFF852C66E6DBAD30D019));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA));
		goto IL_0037;
	}

IL_0037:
	{
		ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA* L_4;
		L_4 = il2cpp_unsafe_as_ref<ValueTuple_4_t1F73182D6C35AB70117495F0B2EDFBB466034CFA>(L_3);
		ByReference_1_t8AA8E61496F18EDB03ECA37B79E2CD41FAB5958E L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		ValueTuple_4U5BU5D_t8CBA38C529FAD8254AEFED53722FAA22D50F99A3* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13833
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mFBFB62752766092200E6EFBA8017B4DD3A3CF901_gshared_inline (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13729
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m00664DD373A36D0E1E71A6B15C893AFB29E09686_gshared_inline (ReadOnlySpan_1_t32C8835D73D5F2ADCC5ED93FA71F63C5E9763632* __this, AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_0 = ___0_ptr;
		ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m62F7DD7486AA2DC1886916B354DF7CB30B2D12CC_gshared_inline (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352* __this, AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_0 = ___0_ptr;
		ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 14530
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61* Array_Empty_TisAABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A_mE73376DEDDDFB934E9003663820D201F6C9B9C41_gshared_inline (const RuntimeMethod* method) 
{
	il2cpp_rgctx_method_init(method);
	{
		il2cpp_codegen_runtime_class_init_inline(il2cpp_rgctx_data(method->rgctx_data, 2));
		AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61* L_0 = ((EmptyArray_1_tBFDECE1BC8155A63C1B370E203BBB8DF09C7751F_StaticFields*)il2cpp_codegen_static_fields_for(il2cpp_rgctx_data(method->rgctx_data, 2)))->___Value;
		return L_0;
	}
}
// Method Definition Index: 13818
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m9F49684FE52CEB46E463E4333810DF0E97299A75_gshared_inline (Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352* __this, AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61* ___0_array, const RuntimeMethod* method) 
{
	AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_tC171222F7F64AA43426D73BA85B1BCFCBCFA6352));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A));
		goto IL_0037;
	}

IL_0037:
	{
		AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A* L_4;
		L_4 = il2cpp_unsafe_as_ref<AABB_tE57BAC64D9F8465BBFCC4A9C66AF4E884166982A>(L_3);
		ByReference_1_tE5A12DC59D6D0638AF2CC848596190D109C95D58 L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		AABBU5BU5D_tFDBB6C3994463A4945D6AEC40557F4F7FB39BA61* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13833
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mAB8B8F731C3753B681689724D8C10D0F6706324A_gshared_inline (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13729
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m61974A48F0249F9BF95E2B30196D22127E7AD9CB_gshared_inline (ReadOnlySpan_1_tAE84767975672BD032424E74DD54F304DED0E2A7* __this, ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_0 = ___0_ptr;
		ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m51AEBDDF4FA3F8681B44273910D1645414D5B4AB_gshared_inline (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948* __this, ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_0 = ___0_ptr;
		ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 14530
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F* Array_Empty_TisATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E_m5293579F263F9A3750FDFA1AA45718B28873BFBB_gshared_inline (const RuntimeMethod* method) 
{
	il2cpp_rgctx_method_init(method);
	{
		il2cpp_codegen_runtime_class_init_inline(il2cpp_rgctx_data(method->rgctx_data, 2));
		ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F* L_0 = ((EmptyArray_1_t910BE047C1A6E9E334831BFBF9773D3C3852ED95_StaticFields*)il2cpp_codegen_static_fields_for(il2cpp_rgctx_data(method->rgctx_data, 2)))->___Value;
		return L_0;
	}
}
// Method Definition Index: 13818
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m41429CDEFEBC1254E9655F1E8E1020C0609E0919_gshared_inline (Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948* __this, ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F* ___0_array, const RuntimeMethod* method) 
{
	ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t14EC2E77DBAA48290810774B64FB790F124E6948));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E));
		goto IL_0037;
	}

IL_0037:
	{
		ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E* L_4;
		L_4 = il2cpp_unsafe_as_ref<ATGMeshInfo_t92E7F6713ADB43FC6EB49CEEC1DF4FAFC5C78A9E>(L_3);
		ByReference_1_tAAA6F8A1B2C11CBC8A0BD502702CEC3F265DC542 L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		ATGMeshInfoU5BU5D_tEA25ABAF8CAF15CC88901DD4F41D1CE7FF0BE05F* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13833
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m2275D4270FBF5A9F745DB11CE6CCE148D8C4672E_gshared_inline (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13729
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_mB2FA851A491C5709D4BAB63C5DA5958FFFEEAA07_gshared_inline (ReadOnlySpan_1_tBA07559B531DF8D3C488A21E738503C11F05E17A* __this, AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_0 = ___0_ptr;
		ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mFFA3823A31B573A005E307DD54A3C8EA4704B552_gshared_inline (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F* __this, AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_0 = ___0_ptr;
		ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 14530
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1* Array_Empty_TisAttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E_m0F7B255CF739B8C78F6D0663FFFD44753100FCAE_gshared_inline (const RuntimeMethod* method) 
{
	il2cpp_rgctx_method_init(method);
	{
		il2cpp_codegen_runtime_class_init_inline(il2cpp_rgctx_data(method->rgctx_data, 2));
		AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1* L_0 = ((EmptyArray_1_t01488E30FA2A207693FD84D9BAB7B4A73493EF80_StaticFields*)il2cpp_codegen_static_fields_for(il2cpp_rgctx_data(method->rgctx_data, 2)))->___Value;
		return L_0;
	}
}
// Method Definition Index: 13818
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m9923F08D09F1AF1763CE22C041DC55410B5A05B1_gshared_inline (Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F* __this, AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1* ___0_array, const RuntimeMethod* method) 
{
	AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t2C9AA907638CB470B09906158320B84B0A895F8F));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E));
		goto IL_0037;
	}

IL_0037:
	{
		AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E* L_4;
		L_4 = il2cpp_unsafe_as_ref<AttachmentDescriptor_tBAC9B26B50BB0838C5C0CC22BB296F9DFF41276E>(L_3);
		ByReference_1_t26243582FF89D42B585A7CF3EC7ACAE740843415 L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		AttachmentDescriptorU5BU5D_tC70107EBD955FE94BA31C7FDC146069EF9C547C1* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13833
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m739A39BB426B19B7583557F308B2BDA52E2C3DD9_gshared_inline (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13729
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m729E93C65964D6955F3A0794056B0A8FA3E1FB44_gshared_inline (ReadOnlySpan_1_tE73282FE39C46EDD2EE2F61A9E3E69BE943784B7* __this, BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_0 = ___0_ptr;
		ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mB2713B2424259A6D18038A2E29BD96DA081378D6_gshared_inline (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428* __this, BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_0 = ___0_ptr;
		ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 14530
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80* Array_Empty_TisBatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D_m652D1F78F0DEF31A60A1D09589A89DE06F12C3E4_gshared_inline (const RuntimeMethod* method) 
{
	il2cpp_rgctx_method_init(method);
	{
		il2cpp_codegen_runtime_class_init_inline(il2cpp_rgctx_data(method->rgctx_data, 2));
		BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80* L_0 = ((EmptyArray_1_tA6E339CF983886C190886835190061C2E3B679F2_StaticFields*)il2cpp_codegen_static_fields_for(il2cpp_rgctx_data(method->rgctx_data, 2)))->___Value;
		return L_0;
	}
}
// Method Definition Index: 13818
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m7126F92024E73EE43580A7A9D64C39CD973406CC_gshared_inline (Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428* __this, BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80* ___0_array, const RuntimeMethod* method) 
{
	BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t36B7E942A80FDA89A0043D5EDED0559212667428));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D));
		goto IL_0037;
	}

IL_0037:
	{
		BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D* L_4;
		L_4 = il2cpp_unsafe_as_ref<BatchCullingOutputDrawCommands_t61DD68A8D702A9468AF6D5DDF4529CE7BD5A636D>(L_3);
		ByReference_1_t6B219462DA620514530122E206E21B814BB5AE56 L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BatchCullingOutputDrawCommandsU5BU5D_t74BE7663EB19A8F858498AE303D905FBCCF54F80* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13833
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m9535293C1FDA720739072391EDE6F574C67C905D_gshared_inline (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13729
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m0D6A1AA77CFA40D8A9849AA4338A5A43DC6CF0C0_gshared_inline (ReadOnlySpan_1_t3D56DFE8743E7EF0665F38A59DA5B99E0F9425B0* __this, BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_0 = ___0_ptr;
		ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m38FB4416138CB97C429799865EA7C7156E442FCD_gshared_inline (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4* __this, BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_0 = ___0_ptr;
		ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 14530
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963* Array_Empty_TisBatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770_mEFC02ED7475540CF71CD66D177FEDA1EA4155C01_gshared_inline (const RuntimeMethod* method) 
{
	il2cpp_rgctx_method_init(method);
	{
		il2cpp_codegen_runtime_class_init_inline(il2cpp_rgctx_data(method->rgctx_data, 2));
		BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963* L_0 = ((EmptyArray_1_t4A2E4F90BFE6F5BF33923E2991998A37CB5A4291_StaticFields*)il2cpp_codegen_static_fields_for(il2cpp_rgctx_data(method->rgctx_data, 2)))->___Value;
		return L_0;
	}
}
// Method Definition Index: 13818
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mF34E7D8B1563ED8C47501C1BF37B17509D397F30_gshared_inline (Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4* __this, BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963* ___0_array, const RuntimeMethod* method) 
{
	BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_tB868D1067505FDF0417857AA95EA5D823B53B5A4));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770));
		goto IL_0037;
	}

IL_0037:
	{
		BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770* L_4;
		L_4 = il2cpp_unsafe_as_ref<BatchID_t884E3C204D1F4C18FFD2361FC14A1E64CFBD8770>(L_3);
		ByReference_1_tEF24E456D72D33C809936E2CB2A6D54892C8004E L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BatchIDU5BU5D_t24AE29CD511A8C8CBDEBA151173F1FD709792963* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13833
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m8E10CA1F785F77C56E8D665A3CE6AF1DC31200FA_gshared_inline (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13729
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m89203DEBD91443BD65C0CCC9EAFA52A1CDE4B184_gshared_inline (ReadOnlySpan_1_t92B77B310CC53A58C3C04B20E0B7F18C2EEFB130* __this, BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_0 = ___0_ptr;
		ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mA2CE8DBE18F6BB54702DAA56720F89DD968A5271_gshared_inline (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF* __this, BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_0 = ___0_ptr;
		ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 14530
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4* Array_Empty_TisBatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C_m0154337C8713B2FE58E471FD10F37CFF0E4D1537_gshared_inline (const RuntimeMethod* method) 
{
	il2cpp_rgctx_method_init(method);
	{
		il2cpp_codegen_runtime_class_init_inline(il2cpp_rgctx_data(method->rgctx_data, 2));
		BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4* L_0 = ((EmptyArray_1_t84EDA74A3D98A25186F3A07E5EA5775E4D2941B0_StaticFields*)il2cpp_codegen_static_fields_for(il2cpp_rgctx_data(method->rgctx_data, 2)))->___Value;
		return L_0;
	}
}
// Method Definition Index: 13818
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mCDB221C7D73874741828DA34B70D3B0AADC00136_gshared_inline (Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF* __this, BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4* ___0_array, const RuntimeMethod* method) 
{
	BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_tB76E894F042295F45DE7894F6C2292C4EA100EBF));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C));
		goto IL_0037;
	}

IL_0037:
	{
		BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C* L_4;
		L_4 = il2cpp_unsafe_as_ref<BatchMaterialID_tF9E95833BB1C35A6D14D47CF4EC8A6EB7D15D65C>(L_3);
		ByReference_1_t8259D993E2FEA587D7C8768B6C2E9B5F2BC6BAD7 L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BatchMaterialIDU5BU5D_t1BB9C44B8702FE3181CA6D28B265734E5FE7ABE4* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13833
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mDDD127BFC8F4A5DEEE8DBC6779C72A75229E2A08_gshared_inline (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13729
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m9A03AF77602C387B913C41E787C615B3D1C1610F_gshared_inline (ReadOnlySpan_1_tD9DB60FA9C52DCE56999EB3FEEA0CDE0FDFFFFE2* __this, BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_0 = ___0_ptr;
		ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m0B727F1A38BD125BA88E22B9518BBEA69CD9F192_gshared_inline (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1* __this, BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_0 = ___0_ptr;
		ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 14530
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E* Array_Empty_TisBatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0_m30D7C90FCBAF09BB4E8C21ED5FE267CFF2F47BA4_gshared_inline (const RuntimeMethod* method) 
{
	il2cpp_rgctx_method_init(method);
	{
		il2cpp_codegen_runtime_class_init_inline(il2cpp_rgctx_data(method->rgctx_data, 2));
		BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E* L_0 = ((EmptyArray_1_t6468C707526910A9B109C3B1C7EE4E98C7890339_StaticFields*)il2cpp_codegen_static_fields_for(il2cpp_rgctx_data(method->rgctx_data, 2)))->___Value;
		return L_0;
	}
}
// Method Definition Index: 13818
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m81637CB567960AF0B3F0471290B423B290FE1830_gshared_inline (Span_1_t3A087F8D965C32C63C3853E38E112343685765A1* __this, BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E* ___0_array, const RuntimeMethod* method) 
{
	BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t3A087F8D965C32C63C3853E38E112343685765A1));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0));
		goto IL_0037;
	}

IL_0037:
	{
		BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0* L_4;
		L_4 = il2cpp_unsafe_as_ref<BatchMeshID_t34167B7C1D0503C43A01A7BD213DFC367525D2A0>(L_3);
		ByReference_1_t0C13E009F6EC218F0D81A207CAEA83AD8606AC5B L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BatchMeshIDU5BU5D_t20B2C8DB5375176C55F0A9ED0976B4FAC866FB0E* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13833
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mF5DDBBF4783D0A55C88BFD79E1A1F8B5DDCF60C5_gshared_inline (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13729
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m1C72072A1F9FFD48A21C4C5F2A81954708D29495_gshared_inline (ReadOnlySpan_1_t3D251E6627DBB106D7D34F8DC234D102B691FD49* __this, BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_0 = ___0_ptr;
		ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m4F32D7D5653D612D237DDFD35C9850E16CA11C18_gshared_inline (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671* __this, BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_0 = ___0_ptr;
		ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 14530
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B* Array_Empty_TisBlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF_mCD144DB8231D7EA3AF1DB4FEB94681C9E580CA9D_gshared_inline (const RuntimeMethod* method) 
{
	il2cpp_rgctx_method_init(method);
	{
		il2cpp_codegen_runtime_class_init_inline(il2cpp_rgctx_data(method->rgctx_data, 2));
		BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B* L_0 = ((EmptyArray_1_tD61E793B4EDBEE4AA2790494DEAA1AFF267A1F35_StaticFields*)il2cpp_codegen_static_fields_for(il2cpp_rgctx_data(method->rgctx_data, 2)))->___Value;
		return L_0;
	}
}
// Method Definition Index: 13818
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mCA3E1FB18A1006842617ABD22464535398F4655D_gshared_inline (Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671* __this, BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B* ___0_array, const RuntimeMethod* method) 
{
	BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t7C5A19CE460E0E7CEF37F40BFC0A2EF653B13671));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF));
		goto IL_0037;
	}

IL_0037:
	{
		BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF* L_4;
		L_4 = il2cpp_unsafe_as_ref<BlittableCollider_tAC3A9ECD36B3C3C09DF00CB9E459790F774887CF>(L_3);
		ByReference_1_t0D4A129F50D2A249B4A6A7FA4B4A023FDCD5801F L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BlittableColliderU5BU5D_t3708611FCB7A23589D4E4BCFB767C7116D14CC6B* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13833
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m0A22C7814AA57E51DE4EDC241010E81DA05627F9_gshared_inline (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13729
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m143F99ABFB2902F2BE3AB6725D5F8AEAECDEC5F4_gshared_inline (ReadOnlySpan_1_tD81D75B0E0400301DC302920BCCBB9FBD97CA2A8* __this, BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_0 = ___0_ptr;
		ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m6C9685057F182B8896BBE6B894974625E6DA4221_gshared_inline (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C* __this, BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_0 = ___0_ptr;
		ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 14530
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515* Array_Empty_TisBlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6_m7D78EA1928C7379C27FE7337353FB51BEFD7476E_gshared_inline (const RuntimeMethod* method) 
{
	il2cpp_rgctx_method_init(method);
	{
		il2cpp_codegen_runtime_class_init_inline(il2cpp_rgctx_data(method->rgctx_data, 2));
		BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515* L_0 = ((EmptyArray_1_t080248E0CE94C9EDF86D6BBE84F395794E5E43C3_StaticFields*)il2cpp_codegen_static_fields_for(il2cpp_rgctx_data(method->rgctx_data, 2)))->___Value;
		return L_0;
	}
}
// Method Definition Index: 13818
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mF80BBD6B1E4103830CA7E538A9EE8ABA983D97EC_gshared_inline (Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C* __this, BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515* ___0_array, const RuntimeMethod* method) 
{
	BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_tC0FB6C8C1E516492E0FD9D0020AC47EFED4E3B1C));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6));
		goto IL_0037;
	}

IL_0037:
	{
		BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6* L_4;
		L_4 = il2cpp_unsafe_as_ref<BlittableJointImmutable_tD735AA7841E92D5AC537D3C3D1CB2CB544094EB6>(L_3);
		ByReference_1_t67265AE71FAD8A73FA3DD219D07BDC558B2C20B7 L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BlittableJointImmutableU5BU5D_t8BAA773AB20A42CC92A9CE0F12BBF7C12DB24515* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13833
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m3C5D2034819739D3DDB78A3D890CEE1C94529B01_gshared_inline (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13729
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_mD794B63B35B4B58E5337728A24E1998CFDD74810_gshared_inline (ReadOnlySpan_1_t70882B83461088DC23C3DB9AF59753CF91E1F3AA* __this, BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_0 = ___0_ptr;
		ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m773D9E2695DC1A1EAF17FDC94204CB7B1DE21582_gshared_inline (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B* __this, BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_0 = ___0_ptr;
		ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 14530
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A* Array_Empty_TisBlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C_m48CDEF41C2F032666E4312F197041C0757708AE3_gshared_inline (const RuntimeMethod* method) 
{
	il2cpp_rgctx_method_init(method);
	{
		il2cpp_codegen_runtime_class_init_inline(il2cpp_rgctx_data(method->rgctx_data, 2));
		BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A* L_0 = ((EmptyArray_1_tA290AEF3927090408C0E0B2EC14CB3DB2C5163A6_StaticFields*)il2cpp_codegen_static_fields_for(il2cpp_rgctx_data(method->rgctx_data, 2)))->___Value;
		return L_0;
	}
}
// Method Definition Index: 13818
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mB392265B6A34EA59542E156547D1143AC4B0EBD4_gshared_inline (Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B* __this, BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A* ___0_array, const RuntimeMethod* method) 
{
	BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t3AAB8E3BD6C3080DEC6B11819836A7F2E4CF575B));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C));
		goto IL_0037;
	}

IL_0037:
	{
		BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C* L_4;
		L_4 = il2cpp_unsafe_as_ref<BlittableJointMutable_t9FC7CE827D9553E7ED07375E079CCB1C9384E42C>(L_3);
		ByReference_1_t05F40BEA5BF1226828D26FE67C525849A48D27DF L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BlittableJointMutableU5BU5D_t82AA653AC7F7D3384D3500EE6EAC91CCED86B57A* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13833
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m088CD07D38433F7795ED549A2139E5D41EA16706_gshared_inline (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13729
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m1A260D918B5EB96D17E965BA7BA73DA0C3493361_gshared_inline (ReadOnlySpan_1_t2EFE9320FC985EB4C740E0FD04D094F25024C51D* __this, BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_0 = ___0_ptr;
		ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mEA9008985414217BB660044157B6458716A8AB1D_gshared_inline (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF* __this, BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_0 = ___0_ptr;
		ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 14530
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8* Array_Empty_TisBlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54_mDE388119C5F2CA6DED1F8895551BEDC81C379FF9_gshared_inline (const RuntimeMethod* method) 
{
	il2cpp_rgctx_method_init(method);
	{
		il2cpp_codegen_runtime_class_init_inline(il2cpp_rgctx_data(method->rgctx_data, 2));
		BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8* L_0 = ((EmptyArray_1_t353CE6E273731A6220E1AD1A702E6AF00E235055_StaticFields*)il2cpp_codegen_static_fields_for(il2cpp_rgctx_data(method->rgctx_data, 2)))->___Value;
		return L_0;
	}
}
// Method Definition Index: 13818
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mD521304CD3352077C22527BDFF5A3A6F5BBA2345_gshared_inline (Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF* __this, BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8* ___0_array, const RuntimeMethod* method) 
{
	BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_tD7465B7D52CA8A22E738742BC2A7F541938D94BF));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54));
		goto IL_0037;
	}

IL_0037:
	{
		BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54* L_4;
		L_4 = il2cpp_unsafe_as_ref<BlittableModelLevel_tA45F5F4A1ADAE0096ED4024725478A0960A2EB54>(L_3);
		ByReference_1_t0FBB72B56E4E4D978AA3A8F0918F1F557401D833 L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BlittableModelLevelU5BU5D_t9C38F6C16D611A297E63A24AA21097A661A5F2C8* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13833
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mE19749B8DE0A55279184BDD9C3F394605245F91A_gshared_inline (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13729
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_mE88D51223E12D11DE487A5E2C7AF2013C6413887_gshared_inline (ReadOnlySpan_1_t5EE3C4B6EA25BB8F3F956DFD28820F5666342758* __this, BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_0 = ___0_ptr;
		ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m18D55B2B52C0EE4D379FCBBC6EE16E72CE2928E1_gshared_inline (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C* __this, BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_0 = ___0_ptr;
		ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 14530
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F* Array_Empty_TisBlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901_m75A4A4FD904386887E2E78BA168595529AF13939_gshared_inline (const RuntimeMethod* method) 
{
	il2cpp_rgctx_method_init(method);
	{
		il2cpp_codegen_runtime_class_init_inline(il2cpp_rgctx_data(method->rgctx_data, 2));
		BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F* L_0 = ((EmptyArray_1_tF2E6E6E2B72D0BF7ECB89ECF4FD5717E4EDE97BF_StaticFields*)il2cpp_codegen_static_fields_for(il2cpp_rgctx_data(method->rgctx_data, 2)))->___Value;
		return L_0;
	}
}
// Method Definition Index: 13818
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mBC0A2A6F732CAD76B8E0B559AAF16DDDC7DA95FB_gshared_inline (Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C* __this, BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F* ___0_array, const RuntimeMethod* method) 
{
	BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t38ECE5022A30EC648B9E50357894011F14BC634C));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901));
		goto IL_0037;
	}

IL_0037:
	{
		BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901* L_4;
		L_4 = il2cpp_unsafe_as_ref<BlittableSpring_t649D9EB5E13BBB41790E0E576C46541596777901>(L_3);
		ByReference_1_tCED3D6988C1C98E3F3870586F43060298B8A60E8 L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BlittableSpringU5BU5D_t056F49A354355447C3DDABF11B429183CFEBD22F* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13833
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m27BEF44B2CCD3948DC00D344142E534E36BA7524_gshared_inline (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13729
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m83EAE3C4FCB23391B66637D0580733B85E12B21A_gshared_inline (ReadOnlySpan_1_t9DCA2EC81EE91A02D7C140CF3376183B663C483A* __this, BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_0 = ___0_ptr;
		ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m0635E38BE51343FEA108E4BAC304A66703B9E727_gshared_inline (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1* __this, BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_0 = ___0_ptr;
		ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 14530
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A* Array_Empty_TisBlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472_m6A8620E16588CCAE8CBA423DCB92B7E1D919A942_gshared_inline (const RuntimeMethod* method) 
{
	il2cpp_rgctx_method_init(method);
	{
		il2cpp_codegen_runtime_class_init_inline(il2cpp_rgctx_data(method->rgctx_data, 2));
		BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A* L_0 = ((EmptyArray_1_tA656C73B1CB77366F398AB7451929D79699A6181_StaticFields*)il2cpp_codegen_static_fields_for(il2cpp_rgctx_data(method->rgctx_data, 2)))->___Value;
		return L_0;
	}
}
// Method Definition Index: 13818
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mDAF271BFB6432EB8FCE7E7E95BB3A4918F1BCC92_gshared_inline (Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1* __this, BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A* ___0_array, const RuntimeMethod* method) 
{
	BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t8379C8AC81E90862037D484486A34DF4CA4A49D1));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472));
		goto IL_0037;
	}

IL_0037:
	{
		BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472* L_4;
		L_4 = il2cpp_unsafe_as_ref<BlittableTransform_t57879CB8848C36D08222D38699C09295D32C6472>(L_3);
		ByReference_1_tBA1F106ABCF90AFEC223DE139D1A742F122C03A0 L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BlittableTransformU5BU5D_t2DF3579998B5E3ACEA63BBFA1ECD473E7D28E35A* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13833
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mCF6D6F9A859D0DB86629D46B25379F2D9C9B8CBB_gshared_inline (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13729
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m0519BAD7DF271438138DD6AEC8CE28A60D0619CE_gshared_inline (ReadOnlySpan_1_t29FEC1E8134CF98858AC01DEE01F4B20811F583F* __this, BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_0 = ___0_ptr;
		ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mB74D4D3C2AD6B802DDB07920C132F554AED832BB_gshared_inline (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F* __this, BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_0 = ___0_ptr;
		ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 14530
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651* Array_Empty_TisBoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F_m327783287506F38234D68882E5BEE202618B5300_gshared_inline (const RuntimeMethod* method) 
{
	il2cpp_rgctx_method_init(method);
	{
		il2cpp_codegen_runtime_class_init_inline(il2cpp_rgctx_data(method->rgctx_data, 2));
		BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651* L_0 = ((EmptyArray_1_t200AEA0094BFCA9414824E328EC31EAB52F2142C_StaticFields*)il2cpp_codegen_static_fields_for(il2cpp_rgctx_data(method->rgctx_data, 2)))->___Value;
		return L_0;
	}
}
// Method Definition Index: 13818
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m1D909AF584BE28F3A4AD990A5D7BE41287E28B38_gshared_inline (Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F* __this, BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651* ___0_array, const RuntimeMethod* method) 
{
	BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t16DF83B87A21BF0C3F96F56FE8FB20C7E6E66F9F));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F));
		goto IL_0037;
	}

IL_0037:
	{
		BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F* L_4;
		L_4 = il2cpp_unsafe_as_ref<BoneWeight_t7E7DACA0D0F56698E05EBBD839D1D343502EA11F>(L_3);
		ByReference_1_t914FB161BC5CAF21F87B88155EB485C508375331 L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BoneWeightU5BU5D_t76F390D8CBA0ADCB87EB98B135C8886857DFE651* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13833
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mED1253429B93CB6D2928015A22105A16FF64C86B_gshared_inline (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13729
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m5818A0EC9B5A3628E69F90A3521BDE96E1FDC74F_gshared_inline (ReadOnlySpan_1_t1CABD9AC48FDD39B6D2C26BCA3D87FCA5F94F1C2* __this, bool* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		bool* L_0 = ___0_ptr;
		ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mC733F92ACB7C0D922180991442FE7A28DCB60772_gshared_inline (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51* __this, bool* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		bool* L_0 = ___0_ptr;
		ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 14530
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4* Array_Empty_TisBoolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_mAB215C445888719BD89809D99C3DBD3135C2B1E7_gshared_inline (const RuntimeMethod* method) 
{
	il2cpp_rgctx_method_init(method);
	{
		il2cpp_codegen_runtime_class_init_inline(il2cpp_rgctx_data(method->rgctx_data, 2));
		BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4* L_0 = ((EmptyArray_1_t7902087DD0C0221C9C7362ABAB7CB57D6D519A99_StaticFields*)il2cpp_codegen_static_fields_for(il2cpp_rgctx_data(method->rgctx_data, 2)))->___Value;
		return L_0;
	}
}
// Method Definition Index: 13818
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m5AF7377F267C08DB0D59CB23F59DF954F7DEE62C_gshared_inline (Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51* __this, BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4* ___0_array, const RuntimeMethod* method) 
{
	bool V_0 = false;
	{
		BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_t087F0E3724EBFD3A74A84E3F9E3F027249F37B51));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(bool));
		goto IL_0037;
	}

IL_0037:
	{
		BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		bool* L_4;
		L_4 = il2cpp_unsafe_as_ref<bool>(L_3);
		ByReference_1_t98C4399D749F9F8F828547057023CF78951E6126 L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BooleanU5BU5D_tD317D27C31DB892BE79FAE3AEBC0B3FFB73DE9B4* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13833
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mA3FAE42313871FB8DAC80A4C14DDE4CCE75F1713_gshared_inline (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13729
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m4C7DD11D4BCC31C1EC9F0EA7D34388DB0B14A07A_gshared_inline (ReadOnlySpan_1_t5D1B06A3C861E766B5669E3742152A803D0F24AA* __this, BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_0 = ___0_ptr;
		ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m58CD191F04E31F33A163B278675E0FA9DF59166D_gshared_inline (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68* __this, BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_0 = ___0_ptr;
		ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 14530
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47* Array_Empty_TisBoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010_mD26FFE584F3D72C1C64FD47A5D59D19E615D8050_gshared_inline (const RuntimeMethod* method) 
{
	il2cpp_rgctx_method_init(method);
	{
		il2cpp_codegen_runtime_class_init_inline(il2cpp_rgctx_data(method->rgctx_data, 2));
		BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47* L_0 = ((EmptyArray_1_tD654E393754C56C1AAE25F6EE3FF0DD03AE5E291_StaticFields*)il2cpp_codegen_static_fields_for(il2cpp_rgctx_data(method->rgctx_data, 2)))->___Value;
		return L_0;
	}
}
// Method Definition Index: 13818
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m81F10BC6C4033345B53A361FD5777CB15BA51592_gshared_inline (Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68* __this, BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47* ___0_array, const RuntimeMethod* method) 
{
	BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_tAAE5C58EA6D335DF90A9243EEA05A1CB40FA3F68));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010));
		goto IL_0037;
	}

IL_0037:
	{
		BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010* L_4;
		L_4 = il2cpp_unsafe_as_ref<BoundingSphere_t2DDB3D1711A6920C0ECA9217D3E4E14AFF03C010>(L_3);
		ByReference_1_t2BA459CB3584D7287AD5F14376202AB43B4D620A L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BoundingSphereU5BU5D_tD7485B1D5D5A0EE26537B093423FC3AC149BEA47* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13833
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_mF487895F53310FF6F399B7BD07038C49D29A6EAB_gshared_inline (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13729
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_mB60BC31800C0877424E21575E45AE6C8AA50ADA6_gshared_inline (ReadOnlySpan_1_tCA4391229D70967C5F52C769440BB78CBF80BE99* __this, Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_0 = ___0_ptr;
		ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_mC3D811BCA73C14AA673ADAB2E2CF39FE1385714B_gshared_inline (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634* __this, Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_0 = ___0_ptr;
		ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 14530
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5* Array_Empty_TisBounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3_mB6CFBB5D8AF33F0BAE72154209AB29B8D52FBCDA_gshared_inline (const RuntimeMethod* method) 
{
	il2cpp_rgctx_method_init(method);
	{
		il2cpp_codegen_runtime_class_init_inline(il2cpp_rgctx_data(method->rgctx_data, 2));
		BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5* L_0 = ((EmptyArray_1_t42D5099F7FCE2834141926DB372392B8C75ADBEC_StaticFields*)il2cpp_codegen_static_fields_for(il2cpp_rgctx_data(method->rgctx_data, 2)))->___Value;
		return L_0;
	}
}
// Method Definition Index: 13818
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m2F8451EBCA1900A8FF93D17DF8177C41EAA2E94B_gshared_inline (Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634* __this, BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5* ___0_array, const RuntimeMethod* method) 
{
	Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_tF1D09AE3581D2E6304B9FC753705C3FC5DF90634));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3));
		goto IL_0037;
	}

IL_0037:
	{
		BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3* L_4;
		L_4 = il2cpp_unsafe_as_ref<Bounds_t367E830C64BBF235ED8C3B2F8CF6254FDCAD39C3>(L_3);
		ByReference_1_t9FE639FD03570F793D25C119C09D9C344FD5577B L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		BoundsU5BU5D_t45563ED55B62FA0536E8117454C56C0CFA5B25F5* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
// Method Definition Index: 13833
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t Span_1_get_Length_m8E944E4954E037877A25B9FF6B901F1F901D4769_gshared_inline (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____length;
		return L_0;
	}
}
// Method Definition Index: 13729
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void ReadOnlySpan_1__ctor_m0FC0B92549C2968E80B5F75A85F28B96DBFCFD63_gshared_inline (ReadOnlySpan_1_tA850A6C0E88ABBA37646A078ACBC24D6D5FD9B4D* __this, uint8_t* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		uint8_t* L_0 = ___0_ptr;
		ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 13821
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m947BF95D54571BF3897F96822B7A8FDA5853497B_gshared_inline (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305* __this, uint8_t* ___0_ptr, int32_t ___1_length, const RuntimeMethod* method) 
{
	{
		uint8_t* L_0 = ___0_ptr;
		ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC L_1;
		memset((&L_1), 0, sizeof(L_1));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_1), L_0);
		__this->____pointer = L_1;
		int32_t L_2 = ___1_length;
		__this->____length = L_2;
		return;
	}
}
// Method Definition Index: 14530
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* Array_Empty_TisByte_t94D9231AC217BE4D2E004C4CD32DF6D099EA41A3_m6080CA526758F4FA182A066B2780D1761CD36ED5_gshared_inline (const RuntimeMethod* method) 
{
	il2cpp_rgctx_method_init(method);
	{
		il2cpp_codegen_runtime_class_init_inline(il2cpp_rgctx_data(method->rgctx_data, 2));
		ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* L_0 = ((EmptyArray_1_t7187E746F328254739F076CFBCAABB28D4B4554C_StaticFields*)il2cpp_codegen_static_fields_for(il2cpp_rgctx_data(method->rgctx_data, 2)))->___Value;
		return L_0;
	}
}
// Method Definition Index: 13818
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Span_1__ctor_m513968BDBFF3CFCE89F3F77FE44CAB22CA474EF9_gshared_inline (Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305* __this, ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* ___0_array, const RuntimeMethod* method) 
{
	uint8_t V_0 = 0x0;
	{
		ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* L_0 = ___0_array;
		if (L_0)
		{
			goto IL_000b;
		}
	}
	{
		il2cpp_codegen_initobj(__this, sizeof(Span_1_tDADAC65069DFE6B57C458109115ECD795ED39305));
		return;
	}

IL_000b:
	{
		il2cpp_codegen_initobj((&V_0), sizeof(uint8_t));
		goto IL_0037;
	}

IL_0037:
	{
		ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* L_2 = ___0_array;
		NullCheck((RuntimeArray*)L_2);
		uint8_t* L_3;
		L_3 = Array_GetRawSzArrayData_m2F8F5B2A381AEF971F12866D9C0A6C4FBA59F6BB_inline((RuntimeArray*)L_2, NULL);
		uint8_t* L_4;
		L_4 = il2cpp_unsafe_as_ref<uint8_t>(L_3);
		ByReference_1_t9C85BCCAAF8C525B6C06B07E922D8D217BE8D6FC L_5;
		memset((&L_5), 0, sizeof(L_5));
		il2cpp_codegen_by_reference_constructor((Il2CppByReference*)(&L_5), L_4);
		__this->____pointer = L_5;
		ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* L_6 = ___0_array;
		NullCheck(L_6);
		__this->____length = ((int32_t)(((RuntimeArray*)L_6)->max_length));
		return;
	}
}
