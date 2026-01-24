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

struct Dictionary_2_t9FA6D82CAFC18769F7515BB51D1C56DAE09381C3;
struct Dictionary_2_tE1603CE612C16451D1E56FF4D4859D4FE4087C28;
struct ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031;
struct CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB;
struct Int32U5BU5D_t19C97395396A72ECAF310612F0760F165060314C;
struct ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918;
struct StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248;
struct Attribute_tFDA8EFEFB0711976D22474794576DAF28F7440AA;
struct Calendar_t0A117CC7532A54C17188C2EFEA1F79DB20DF3A3B;
struct CompareInfo_t1B1A6AC3486B570C76ABA52149C9BD4CD82F9E57;
struct CultureData_tEEFDCF4ECA1BBF6C0C8C94EB3541657245598F9D;
struct CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0;
struct DateTimeFormatInfo_t0457520F9FA7B5C8EAAEB3AD50413B6AEEB7458A;
struct IFormatProvider_tC202922D43BFF3525109ABF3FB79625F5646AB52;
struct IntrinsicAttribute_tB9B07649CD111989AAD4463A0664A70B58B3A91B;
struct NumberFormatInfo_t8E26808B202927FEBF9064FCFEEA4D6E076E6472;
struct String_t;
struct StringBuilder_t;
struct TextInfo_tD3BAFCFD77418851E7D5CB8D2588F47019E414B4;
struct Void_t4861ACF8F4594C3437BB48B6E56783494B843915;

IL2CPP_EXTERN_C RuntimeClass* CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* HashHelpers_tDBADDD699E5C1C58ED9DF75B838DD3056A6EAF64_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* MathF_t54810B9E7503B5BD0850A3D6E6336922E1553C52_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* StringBuilder_t_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C String_t* _stringLiteral3DCC6243286938BE75C3FA773B9BA71160A2E869;
IL2CPP_EXTERN_C String_t* _stringLiteral4DC02E9DBA2BBA6B0B90CAE0980D70CE6132DCD1;
IL2CPP_EXTERN_C String_t* _stringLiteralD26F25683308CEE6235E8DD6C4C233601EB6FF0D;
struct CultureData_tEEFDCF4ECA1BBF6C0C8C94EB3541657245598F9D_marshaled_com;
struct CultureData_tEEFDCF4ECA1BBF6C0C8C94EB3541657245598F9D_marshaled_pinvoke;
struct CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0_marshaled_com;
struct CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0_marshaled_pinvoke;

struct ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918;

IL2CPP_EXTERN_C_BEGIN
IL2CPP_EXTERN_C_END

#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
struct U3CModuleU3E_t8BB3B0410A6FE73E075A5E8CD2F563089B45BBFC 
{
};
struct Attribute_tFDA8EFEFB0711976D22474794576DAF28F7440AA  : public RuntimeObject
{
};
struct CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0  : public RuntimeObject
{
	bool ___m_isReadOnly;
	int32_t ___cultureID;
	int32_t ___parent_lcid;
	int32_t ___datetime_index;
	int32_t ___number_index;
	int32_t ___default_calendar_type;
	bool ___m_useUserOverride;
	NumberFormatInfo_t8E26808B202927FEBF9064FCFEEA4D6E076E6472* ___numInfo;
	DateTimeFormatInfo_t0457520F9FA7B5C8EAAEB3AD50413B6AEEB7458A* ___dateTimeInfo;
	TextInfo_tD3BAFCFD77418851E7D5CB8D2588F47019E414B4* ___textInfo;
	String_t* ___m_name;
	String_t* ___englishname;
	String_t* ___nativename;
	String_t* ___iso3lang;
	String_t* ___iso2lang;
	String_t* ___win3lang;
	String_t* ___territory;
	StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* ___native_calendar_names;
	CompareInfo_t1B1A6AC3486B570C76ABA52149C9BD4CD82F9E57* ___compareInfo;
	void* ___textinfo_data;
	int32_t ___m_dataItem;
	Calendar_t0A117CC7532A54C17188C2EFEA1F79DB20DF3A3B* ___calendar;
	CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* ___parent_culture;
	bool ___constructed;
	ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* ___cached_serialized_form;
	CultureData_tEEFDCF4ECA1BBF6C0C8C94EB3541657245598F9D* ___m_cultureData;
	bool ___m_isInherited;
};
struct CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0_marshaled_pinvoke
{
	int32_t ___m_isReadOnly;
	int32_t ___cultureID;
	int32_t ___parent_lcid;
	int32_t ___datetime_index;
	int32_t ___number_index;
	int32_t ___default_calendar_type;
	int32_t ___m_useUserOverride;
	NumberFormatInfo_t8E26808B202927FEBF9064FCFEEA4D6E076E6472* ___numInfo;
	DateTimeFormatInfo_t0457520F9FA7B5C8EAAEB3AD50413B6AEEB7458A* ___dateTimeInfo;
	TextInfo_tD3BAFCFD77418851E7D5CB8D2588F47019E414B4* ___textInfo;
	char* ___m_name;
	char* ___englishname;
	char* ___nativename;
	char* ___iso3lang;
	char* ___iso2lang;
	char* ___win3lang;
	char* ___territory;
	char** ___native_calendar_names;
	CompareInfo_t1B1A6AC3486B570C76ABA52149C9BD4CD82F9E57* ___compareInfo;
	void* ___textinfo_data;
	int32_t ___m_dataItem;
	Calendar_t0A117CC7532A54C17188C2EFEA1F79DB20DF3A3B* ___calendar;
	CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0_marshaled_pinvoke* ___parent_culture;
	int32_t ___constructed;
	Il2CppSafeArray* ___cached_serialized_form;
	CultureData_tEEFDCF4ECA1BBF6C0C8C94EB3541657245598F9D_marshaled_pinvoke* ___m_cultureData;
	int32_t ___m_isInherited;
};
struct CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0_marshaled_com
{
	int32_t ___m_isReadOnly;
	int32_t ___cultureID;
	int32_t ___parent_lcid;
	int32_t ___datetime_index;
	int32_t ___number_index;
	int32_t ___default_calendar_type;
	int32_t ___m_useUserOverride;
	NumberFormatInfo_t8E26808B202927FEBF9064FCFEEA4D6E076E6472* ___numInfo;
	DateTimeFormatInfo_t0457520F9FA7B5C8EAAEB3AD50413B6AEEB7458A* ___dateTimeInfo;
	TextInfo_tD3BAFCFD77418851E7D5CB8D2588F47019E414B4* ___textInfo;
	Il2CppChar* ___m_name;
	Il2CppChar* ___englishname;
	Il2CppChar* ___nativename;
	Il2CppChar* ___iso3lang;
	Il2CppChar* ___iso2lang;
	Il2CppChar* ___win3lang;
	Il2CppChar* ___territory;
	Il2CppChar** ___native_calendar_names;
	CompareInfo_t1B1A6AC3486B570C76ABA52149C9BD4CD82F9E57* ___compareInfo;
	void* ___textinfo_data;
	int32_t ___m_dataItem;
	Calendar_t0A117CC7532A54C17188C2EFEA1F79DB20DF3A3B* ___calendar;
	CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0_marshaled_com* ___parent_culture;
	int32_t ___constructed;
	Il2CppSafeArray* ___cached_serialized_form;
	CultureData_tEEFDCF4ECA1BBF6C0C8C94EB3541657245598F9D_marshaled_com* ___m_cultureData;
	int32_t ___m_isInherited;
};
struct HashHelpers_tDBADDD699E5C1C58ED9DF75B838DD3056A6EAF64  : public RuntimeObject
{
};
struct NumberFormatInfo_t8E26808B202927FEBF9064FCFEEA4D6E076E6472  : public RuntimeObject
{
	Int32U5BU5D_t19C97395396A72ECAF310612F0760F165060314C* ___numberGroupSizes;
	Int32U5BU5D_t19C97395396A72ECAF310612F0760F165060314C* ___currencyGroupSizes;
	Int32U5BU5D_t19C97395396A72ECAF310612F0760F165060314C* ___percentGroupSizes;
	String_t* ___positiveSign;
	String_t* ___negativeSign;
	String_t* ___numberDecimalSeparator;
	String_t* ___numberGroupSeparator;
	String_t* ___currencyGroupSeparator;
	String_t* ___currencyDecimalSeparator;
	String_t* ___currencySymbol;
	String_t* ___ansiCurrencySymbol;
	String_t* ___nanSymbol;
	String_t* ___positiveInfinitySymbol;
	String_t* ___negativeInfinitySymbol;
	String_t* ___percentDecimalSeparator;
	String_t* ___percentGroupSeparator;
	String_t* ___percentSymbol;
	String_t* ___perMilleSymbol;
	StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* ___nativeDigits;
	int32_t ___m_dataItem;
	int32_t ___numberDecimalDigits;
	int32_t ___currencyDecimalDigits;
	int32_t ___currencyPositivePattern;
	int32_t ___currencyNegativePattern;
	int32_t ___numberNegativePattern;
	int32_t ___percentPositivePattern;
	int32_t ___percentNegativePattern;
	int32_t ___percentDecimalDigits;
	int32_t ___digitSubstitution;
	bool ___isReadOnly;
	bool ___m_useUserOverride;
	bool ___m_isInvariant;
	bool ___validForParseAsNumber;
	bool ___validForParseAsCurrency;
};
struct String_t  : public RuntimeObject
{
	int32_t ____stringLength;
	Il2CppChar ____firstChar;
};
struct StringBuilder_t  : public RuntimeObject
{
	CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* ___m_ChunkChars;
	StringBuilder_t* ___m_ChunkPrevious;
	int32_t ___m_ChunkLength;
	int32_t ___m_ChunkOffset;
	int32_t ___m_MaxCapacity;
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
struct Boolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22 
{
	bool ___m_value;
};
struct Char_t521A6F19B456D956AF452D926C32709DC03D6B17 
{
	Il2CppChar ___m_value;
};
struct Guid_t 
{
	int32_t ____a;
	int16_t ____b;
	int16_t ____c;
	uint8_t ____d;
	uint8_t ____e;
	uint8_t ____f;
	uint8_t ____g;
	uint8_t ____h;
	uint8_t ____i;
	uint8_t ____j;
	uint8_t ____k;
};
struct Int32_t680FF22E76F6EFAD4375103CBBFFA0421349384C 
{
	int32_t ___m_value;
};
struct IntrinsicAttribute_tB9B07649CD111989AAD4463A0664A70B58B3A91B  : public Attribute_tFDA8EFEFB0711976D22474794576DAF28F7440AA
{
};
struct Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 
{
	float ___M11;
	float ___M12;
	float ___M13;
	float ___M14;
	float ___M21;
	float ___M22;
	float ___M23;
	float ___M24;
	float ___M31;
	float ___M32;
	float ___M33;
	float ___M34;
	float ___M41;
	float ___M42;
	float ___M43;
	float ___M44;
};
struct Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F 
{
	float ___X;
	float ___Y;
	float ___Z;
	float ___W;
};
struct Single_t4530F2FF86FCB0DC29F35385CA1BD21BE294761C 
{
	float ___m_value;
};
struct Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D 
{
	float ___X;
	float ___Y;
	float ___Z;
};
struct Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 
{
	float ___X;
	float ___Y;
	float ___Z;
	float ___W;
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
struct CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0_StaticFields
{
	CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* ___invariant_culture_info;
	RuntimeObject* ___shared_table_lock;
	CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* ___default_current_culture;
	CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* ___s_DefaultThreadCurrentUICulture;
	CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* ___s_DefaultThreadCurrentCulture;
	Dictionary_2_t9FA6D82CAFC18769F7515BB51D1C56DAE09381C3* ___shared_by_number;
	Dictionary_2_tE1603CE612C16451D1E56FF4D4859D4FE4087C28* ___shared_by_name;
	CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* ___s_UserPreferredCultureInfoInAppX;
	bool ___IsTaiwanSku;
};
struct HashHelpers_tDBADDD699E5C1C58ED9DF75B838DD3056A6EAF64_StaticFields
{
	int32_t ___RandomSeed;
};
struct NumberFormatInfo_t8E26808B202927FEBF9064FCFEEA4D6E076E6472_StaticFields
{
	NumberFormatInfo_t8E26808B202927FEBF9064FCFEEA4D6E076E6472* ___invariantInfo;
};
struct String_t_StaticFields
{
	String_t* ___Empty;
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
struct Guid_t_StaticFields
{
	Guid_t ___Empty;
};
struct Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072_StaticFields
{
	Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 ____identity;
};
#ifdef __clang__
#pragma clang diagnostic pop
#endif
struct ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918  : public RuntimeArray
{
	ALIGN_FIELD (8) RuntimeObject* m_Items[1];

	inline RuntimeObject* GetAt(il2cpp_array_size_t index) const
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items[index];
	}
	inline RuntimeObject** GetAddressAt(il2cpp_array_size_t index)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items + index;
	}
	inline void SetAt(il2cpp_array_size_t index, RuntimeObject* value)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		m_Items[index] = value;
		Il2CppCodeGenWriteBarrier((void**)m_Items + index, (void*)value);
	}
	inline RuntimeObject* GetAtUnchecked(il2cpp_array_size_t index) const
	{
		return m_Items[index];
	}
	inline RuntimeObject** GetAddressAtUnchecked(il2cpp_array_size_t index)
	{
		return m_Items + index;
	}
	inline void SetAtUnchecked(il2cpp_array_size_t index, RuntimeObject* value)
	{
		m_Items[index] = value;
		Il2CppCodeGenWriteBarrier((void**)m_Items + index, (void*)value);
	}
};



IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Matrix4x4__ctor_m4044D15D7E84A1D30E038A963DF0FB0EB6736C1D (Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072* __this, float ___0_m11, float ___1_m12, float ___2_m13, float ___3_m14, float ___4_m21, float ___5_m22, float ___6_m23, float ___7_m24, float ___8_m31, float ___9_m32, float ___10_m33, float ___11_m34, float ___12_m41, float ___13_m42, float ___14_m43, float ___15_m44, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Matrix4x4_Equals_m6381E804B9AF9FDFB16CC29B8FC6F93E12BBAC03 (Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072* __this, Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 ___0_other, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Matrix4x4_Equals_mEAA6F169F8CA748FF70B34F1171C17636D725B05 (Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* CultureInfo_get_CurrentCulture_m8A4580F49DDD7E9DB34C699965423DB8E3BBA9A5 (const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Single_ToString_m534852BD7949AA972435783D7B96D0FFB09F6D6A (float* __this, RuntimeObject* ___0_provider, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* String_Format_m447B585713E5EB3EBF5D9D0710706D01E8A56D75 (RuntimeObject* ___0_provider, String_t* ___1_format, ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* ___2_args, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Matrix4x4_ToString_m957EA1B830DF84179D41E500B0C983127BACF023 (Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2 (float* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Matrix4x4_GetHashCode_m3C7792A897310FE1FF1D893DF9A1E3A50CC3DB88 (Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Quaternion_Equals_m1B7B3C6E864B978BAFC9AF0DC2115A8A93D1AFA1 (Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F* __this, Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F ___0_other, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Quaternion_Equals_m9634C9FE716B037E38FF4AB7DA4BC8B9F8CC21F7 (Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Quaternion_ToString_mF7DE37CE381D91BAE78F60618A0881428FF10B69 (Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Quaternion_GetHashCode_m9C732EF144D4B9A806F553F7F983F9E66A3AA77F (Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Vector3__ctor_m7C7B6A0DD0B1E729ECE05CD46AAD62DC2B71BD86 (Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D* __this, float ___0_x, float ___1_y, float ___2_z, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t HashHelpers_Combine_mA94C6B78FF8602DF43B2236B37E07EAD3E40861E (int32_t ___0_h1, int32_t ___1_h2, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Vector3_GetHashCode_m6EA39E7A65A09E669C48F62771442A9936A81665 (Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Vector3_Equals_m5EAC07DAA75E2A84A3997BC27F36BE19698CC3FE (Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D* __this, Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D ___0_other, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR bool Vector3_Equals_m81F9E36BE544963229CFED0561670E20DDCAABCE_inline (Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Vector3_ToString_m2048240DCF9C4CED3DE9A4C4C1589786BB3B1D99 (Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D* __this, String_t* ___0_format, RuntimeObject* ___1_formatProvider, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Vector3_ToString_m84244C7A59EC1E85A6D00453D2CF6190ED511CBC (Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void StringBuilder__ctor_m1D99713357DE05DAFA296633639DB55F8C30587D (StringBuilder_t* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR NumberFormatInfo_t8E26808B202927FEBF9064FCFEEA4D6E076E6472* NumberFormatInfo_GetInstance_m705987E5E7D3E5EC5C5DD2D088FBC9BCBA0FC31F (RuntimeObject* ___0_formatProvider, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR String_t* NumberFormatInfo_get_NumberGroupSeparator_m0556B092AA471513B1EDC31C047712226D39BEB6_inline (NumberFormatInfo_t8E26808B202927FEBF9064FCFEEA4D6E076E6472* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR StringBuilder_t* StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1 (StringBuilder_t* __this, Il2CppChar ___0_value, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Single_ToString_mF468A56B3A746EFD805E0604EE7A2873DA157ADE (float* __this, String_t* ___0_format, RuntimeObject* ___1_provider, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR StringBuilder_t* StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D (StringBuilder_t* __this, String_t* ___0_value, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Vector4_GetHashCode_m33C3E640020BD14992DA0EBBE8A365FA10C3FDAB (Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Vector4_Equals_m0FF82F8146C7DFCC7B3BCBA18AF1694992A132F6 (Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6* __this, Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 ___0_other, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR bool Vector4_Equals_mD6B3F8BACD1BC326AF6FF6F83FDDF6BA90A9CF10_inline (Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Vector4_ToString_m00657EAF2A6840CB077AFCF5317A3AC738648833 (Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6* __this, String_t* ___0_format, RuntimeObject* ___1_formatProvider, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Vector4_ToString_m64F83A00FB32819E687AFD35979F270102E4447B (Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Vector_get_IsHardwareAccelerated_m783509258751EBED64CBD9F387EC1BB4A15088AA (const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR float Vector4_Dot_mE2526C44DFCF9BFF1E22F265B9F24F2BB18FE27B_inline (Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 ___0_vector1, Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 ___1_vector2, const RuntimeMethod* method) ;
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR float Vector4_Length_m988A0BD2995D4011A1E8D915232859ADAD2C5462_inline (Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Vector4__ctor_m22210D6F83F4B5D6CFA82DF01AA1435A4121AC8C (Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6* __this, float ___0_x, float ___1_y, float ___2_z, float ___3_w, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Guid_t Guid_NewGuid_m1F4894E8DC089811D6252148AD5858E58D43A7BD (const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Guid_GetHashCode_m239B7679BB9ED5A207B3D2F858B5F30FFC455408 (Guid_t* __this, const RuntimeMethod* method) ;
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Attribute__ctor_m79ED1BF1EE36D1E417BA89A0D9F91F8AAD8D19E2 (Attribute_tFDA8EFEFB0711976D22474794576DAF28F7440AA* __this, const RuntimeMethod* method) ;
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
// Method Definition Index: 75112
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Matrix4x4__ctor_m4044D15D7E84A1D30E038A963DF0FB0EB6736C1D (Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072* __this, float ___0_m11, float ___1_m12, float ___2_m13, float ___3_m14, float ___4_m21, float ___5_m22, float ___6_m23, float ___7_m24, float ___8_m31, float ___9_m32, float ___10_m33, float ___11_m34, float ___12_m41, float ___13_m42, float ___14_m43, float ___15_m44, const RuntimeMethod* method) 
{
	{
		float L_0 = ___0_m11;
		__this->___M11 = L_0;
		float L_1 = ___1_m12;
		__this->___M12 = L_1;
		float L_2 = ___2_m13;
		__this->___M13 = L_2;
		float L_3 = ___3_m14;
		__this->___M14 = L_3;
		float L_4 = ___4_m21;
		__this->___M21 = L_4;
		float L_5 = ___5_m22;
		__this->___M22 = L_5;
		float L_6 = ___6_m23;
		__this->___M23 = L_6;
		float L_7 = ___7_m24;
		__this->___M24 = L_7;
		float L_8 = ___8_m31;
		__this->___M31 = L_8;
		float L_9 = ___9_m32;
		__this->___M32 = L_9;
		float L_10 = ___10_m33;
		__this->___M33 = L_10;
		float L_11 = ___11_m34;
		__this->___M34 = L_11;
		float L_12 = ___12_m41;
		__this->___M41 = L_12;
		float L_13 = ___13_m42;
		__this->___M42 = L_13;
		float L_14 = ___14_m43;
		__this->___M43 = L_14;
		float L_15 = ___15_m44;
		__this->___M44 = L_15;
		return;
	}
}
IL2CPP_EXTERN_C  void Matrix4x4__ctor_m4044D15D7E84A1D30E038A963DF0FB0EB6736C1D_AdjustorThunk (RuntimeObject* __this, float ___0_m11, float ___1_m12, float ___2_m13, float ___3_m14, float ___4_m21, float ___5_m22, float ___6_m23, float ___7_m24, float ___8_m31, float ___9_m32, float ___10_m33, float ___11_m34, float ___12_m41, float ___13_m42, float ___14_m43, float ___15_m44, const RuntimeMethod* method)
{
	Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072* _thisAdjusted;
	int32_t _offset = 1;
	_thisAdjusted = reinterpret_cast<Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072*>(__this + _offset);
	Matrix4x4__ctor_m4044D15D7E84A1D30E038A963DF0FB0EB6736C1D(_thisAdjusted, ___0_m11, ___1_m12, ___2_m13, ___3_m14, ___4_m21, ___5_m22, ___6_m23, ___7_m24, ___8_m31, ___9_m32, ___10_m33, ___11_m34, ___12_m41, ___13_m42, ___14_m43, ___15_m44, method);
}
// Method Definition Index: 75113
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 Matrix4x4_CreateTranslation_m6A779A76C570B3DF2DCD981EBC26EAD75B7A5AFA (Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D ___0_position, const RuntimeMethod* method) 
{
	Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		(&V_0)->___M11 = (1.0f);
		(&V_0)->___M12 = (0.0f);
		(&V_0)->___M13 = (0.0f);
		(&V_0)->___M14 = (0.0f);
		(&V_0)->___M21 = (0.0f);
		(&V_0)->___M22 = (1.0f);
		(&V_0)->___M23 = (0.0f);
		(&V_0)->___M24 = (0.0f);
		(&V_0)->___M31 = (0.0f);
		(&V_0)->___M32 = (0.0f);
		(&V_0)->___M33 = (1.0f);
		(&V_0)->___M34 = (0.0f);
		Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D L_0 = ___0_position;
		float L_1 = L_0.___X;
		(&V_0)->___M41 = L_1;
		Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D L_2 = ___0_position;
		float L_3 = L_2.___Y;
		(&V_0)->___M42 = L_3;
		Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D L_4 = ___0_position;
		float L_5 = L_4.___Z;
		(&V_0)->___M43 = L_5;
		(&V_0)->___M44 = (1.0f);
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_6 = V_0;
		return L_6;
	}
}
// Method Definition Index: 75114
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 Matrix4x4_CreateScale_m3698DA0F4D8364E5225EF9488FC0C108F7DE0CF5 (Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D ___0_scales, const RuntimeMethod* method) 
{
	Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D L_0 = ___0_scales;
		float L_1 = L_0.___X;
		(&V_0)->___M11 = L_1;
		(&V_0)->___M12 = (0.0f);
		(&V_0)->___M13 = (0.0f);
		(&V_0)->___M14 = (0.0f);
		(&V_0)->___M21 = (0.0f);
		Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D L_2 = ___0_scales;
		float L_3 = L_2.___Y;
		(&V_0)->___M22 = L_3;
		(&V_0)->___M23 = (0.0f);
		(&V_0)->___M24 = (0.0f);
		(&V_0)->___M31 = (0.0f);
		(&V_0)->___M32 = (0.0f);
		Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D L_4 = ___0_scales;
		float L_5 = L_4.___Z;
		(&V_0)->___M33 = L_5;
		(&V_0)->___M34 = (0.0f);
		(&V_0)->___M41 = (0.0f);
		(&V_0)->___M42 = (0.0f);
		(&V_0)->___M43 = (0.0f);
		(&V_0)->___M44 = (1.0f);
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_6 = V_0;
		return L_6;
	}
}
// Method Definition Index: 75115
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 Matrix4x4_CreateFromQuaternion_m411E691C4C4FA8E23A23A189EF2E3E86A2AB73E4 (Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F ___0_quaternion, const RuntimeMethod* method) 
{
	Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 V_0;
	memset((&V_0), 0, sizeof(V_0));
	float V_1 = 0.0f;
	float V_2 = 0.0f;
	float V_3 = 0.0f;
	float V_4 = 0.0f;
	float V_5 = 0.0f;
	float V_6 = 0.0f;
	float V_7 = 0.0f;
	float V_8 = 0.0f;
	float V_9 = 0.0f;
	{
		Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F L_0 = ___0_quaternion;
		float L_1 = L_0.___X;
		Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F L_2 = ___0_quaternion;
		float L_3 = L_2.___X;
		V_1 = ((float)il2cpp_codegen_multiply(L_1, L_3));
		Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F L_4 = ___0_quaternion;
		float L_5 = L_4.___Y;
		Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F L_6 = ___0_quaternion;
		float L_7 = L_6.___Y;
		V_2 = ((float)il2cpp_codegen_multiply(L_5, L_7));
		Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F L_8 = ___0_quaternion;
		float L_9 = L_8.___Z;
		Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F L_10 = ___0_quaternion;
		float L_11 = L_10.___Z;
		V_3 = ((float)il2cpp_codegen_multiply(L_9, L_11));
		Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F L_12 = ___0_quaternion;
		float L_13 = L_12.___X;
		Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F L_14 = ___0_quaternion;
		float L_15 = L_14.___Y;
		V_4 = ((float)il2cpp_codegen_multiply(L_13, L_15));
		Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F L_16 = ___0_quaternion;
		float L_17 = L_16.___Z;
		Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F L_18 = ___0_quaternion;
		float L_19 = L_18.___W;
		V_5 = ((float)il2cpp_codegen_multiply(L_17, L_19));
		Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F L_20 = ___0_quaternion;
		float L_21 = L_20.___Z;
		Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F L_22 = ___0_quaternion;
		float L_23 = L_22.___X;
		V_6 = ((float)il2cpp_codegen_multiply(L_21, L_23));
		Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F L_24 = ___0_quaternion;
		float L_25 = L_24.___Y;
		Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F L_26 = ___0_quaternion;
		float L_27 = L_26.___W;
		V_7 = ((float)il2cpp_codegen_multiply(L_25, L_27));
		Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F L_28 = ___0_quaternion;
		float L_29 = L_28.___Y;
		Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F L_30 = ___0_quaternion;
		float L_31 = L_30.___Z;
		V_8 = ((float)il2cpp_codegen_multiply(L_29, L_31));
		Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F L_32 = ___0_quaternion;
		float L_33 = L_32.___X;
		Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F L_34 = ___0_quaternion;
		float L_35 = L_34.___W;
		V_9 = ((float)il2cpp_codegen_multiply(L_33, L_35));
		float L_36 = V_2;
		float L_37 = V_3;
		(&V_0)->___M11 = ((float)il2cpp_codegen_subtract((1.0f), ((float)il2cpp_codegen_multiply((2.0f), ((float)il2cpp_codegen_add(L_36, L_37))))));
		float L_38 = V_4;
		float L_39 = V_5;
		(&V_0)->___M12 = ((float)il2cpp_codegen_multiply((2.0f), ((float)il2cpp_codegen_add(L_38, L_39))));
		float L_40 = V_6;
		float L_41 = V_7;
		(&V_0)->___M13 = ((float)il2cpp_codegen_multiply((2.0f), ((float)il2cpp_codegen_subtract(L_40, L_41))));
		(&V_0)->___M14 = (0.0f);
		float L_42 = V_4;
		float L_43 = V_5;
		(&V_0)->___M21 = ((float)il2cpp_codegen_multiply((2.0f), ((float)il2cpp_codegen_subtract(L_42, L_43))));
		float L_44 = V_3;
		float L_45 = V_1;
		(&V_0)->___M22 = ((float)il2cpp_codegen_subtract((1.0f), ((float)il2cpp_codegen_multiply((2.0f), ((float)il2cpp_codegen_add(L_44, L_45))))));
		float L_46 = V_8;
		float L_47 = V_9;
		(&V_0)->___M23 = ((float)il2cpp_codegen_multiply((2.0f), ((float)il2cpp_codegen_add(L_46, L_47))));
		(&V_0)->___M24 = (0.0f);
		float L_48 = V_6;
		float L_49 = V_7;
		(&V_0)->___M31 = ((float)il2cpp_codegen_multiply((2.0f), ((float)il2cpp_codegen_add(L_48, L_49))));
		float L_50 = V_8;
		float L_51 = V_9;
		(&V_0)->___M32 = ((float)il2cpp_codegen_multiply((2.0f), ((float)il2cpp_codegen_subtract(L_50, L_51))));
		float L_52 = V_2;
		float L_53 = V_1;
		(&V_0)->___M33 = ((float)il2cpp_codegen_subtract((1.0f), ((float)il2cpp_codegen_multiply((2.0f), ((float)il2cpp_codegen_add(L_52, L_53))))));
		(&V_0)->___M34 = (0.0f);
		(&V_0)->___M41 = (0.0f);
		(&V_0)->___M42 = (0.0f);
		(&V_0)->___M43 = (0.0f);
		(&V_0)->___M44 = (1.0f);
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_54 = V_0;
		return L_54;
	}
}
// Method Definition Index: 75116
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 Matrix4x4_op_Multiply_m2D27443847A7D5A6A81AB6235B7DA2A75CEDAF9E (Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 ___0_value1, Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 ___1_value2, const RuntimeMethod* method) 
{
	Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_0 = ___0_value1;
		float L_1 = L_0.___M11;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_2 = ___1_value2;
		float L_3 = L_2.___M11;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_4 = ___0_value1;
		float L_5 = L_4.___M12;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_6 = ___1_value2;
		float L_7 = L_6.___M21;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_8 = ___0_value1;
		float L_9 = L_8.___M13;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_10 = ___1_value2;
		float L_11 = L_10.___M31;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_12 = ___0_value1;
		float L_13 = L_12.___M14;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_14 = ___1_value2;
		float L_15 = L_14.___M41;
		(&V_0)->___M11 = ((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_multiply(L_1, L_3)), ((float)il2cpp_codegen_multiply(L_5, L_7)))), ((float)il2cpp_codegen_multiply(L_9, L_11)))), ((float)il2cpp_codegen_multiply(L_13, L_15))));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_16 = ___0_value1;
		float L_17 = L_16.___M11;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_18 = ___1_value2;
		float L_19 = L_18.___M12;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_20 = ___0_value1;
		float L_21 = L_20.___M12;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_22 = ___1_value2;
		float L_23 = L_22.___M22;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_24 = ___0_value1;
		float L_25 = L_24.___M13;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_26 = ___1_value2;
		float L_27 = L_26.___M32;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_28 = ___0_value1;
		float L_29 = L_28.___M14;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_30 = ___1_value2;
		float L_31 = L_30.___M42;
		(&V_0)->___M12 = ((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_multiply(L_17, L_19)), ((float)il2cpp_codegen_multiply(L_21, L_23)))), ((float)il2cpp_codegen_multiply(L_25, L_27)))), ((float)il2cpp_codegen_multiply(L_29, L_31))));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_32 = ___0_value1;
		float L_33 = L_32.___M11;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_34 = ___1_value2;
		float L_35 = L_34.___M13;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_36 = ___0_value1;
		float L_37 = L_36.___M12;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_38 = ___1_value2;
		float L_39 = L_38.___M23;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_40 = ___0_value1;
		float L_41 = L_40.___M13;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_42 = ___1_value2;
		float L_43 = L_42.___M33;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_44 = ___0_value1;
		float L_45 = L_44.___M14;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_46 = ___1_value2;
		float L_47 = L_46.___M43;
		(&V_0)->___M13 = ((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_multiply(L_33, L_35)), ((float)il2cpp_codegen_multiply(L_37, L_39)))), ((float)il2cpp_codegen_multiply(L_41, L_43)))), ((float)il2cpp_codegen_multiply(L_45, L_47))));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_48 = ___0_value1;
		float L_49 = L_48.___M11;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_50 = ___1_value2;
		float L_51 = L_50.___M14;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_52 = ___0_value1;
		float L_53 = L_52.___M12;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_54 = ___1_value2;
		float L_55 = L_54.___M24;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_56 = ___0_value1;
		float L_57 = L_56.___M13;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_58 = ___1_value2;
		float L_59 = L_58.___M34;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_60 = ___0_value1;
		float L_61 = L_60.___M14;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_62 = ___1_value2;
		float L_63 = L_62.___M44;
		(&V_0)->___M14 = ((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_multiply(L_49, L_51)), ((float)il2cpp_codegen_multiply(L_53, L_55)))), ((float)il2cpp_codegen_multiply(L_57, L_59)))), ((float)il2cpp_codegen_multiply(L_61, L_63))));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_64 = ___0_value1;
		float L_65 = L_64.___M21;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_66 = ___1_value2;
		float L_67 = L_66.___M11;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_68 = ___0_value1;
		float L_69 = L_68.___M22;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_70 = ___1_value2;
		float L_71 = L_70.___M21;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_72 = ___0_value1;
		float L_73 = L_72.___M23;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_74 = ___1_value2;
		float L_75 = L_74.___M31;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_76 = ___0_value1;
		float L_77 = L_76.___M24;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_78 = ___1_value2;
		float L_79 = L_78.___M41;
		(&V_0)->___M21 = ((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_multiply(L_65, L_67)), ((float)il2cpp_codegen_multiply(L_69, L_71)))), ((float)il2cpp_codegen_multiply(L_73, L_75)))), ((float)il2cpp_codegen_multiply(L_77, L_79))));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_80 = ___0_value1;
		float L_81 = L_80.___M21;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_82 = ___1_value2;
		float L_83 = L_82.___M12;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_84 = ___0_value1;
		float L_85 = L_84.___M22;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_86 = ___1_value2;
		float L_87 = L_86.___M22;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_88 = ___0_value1;
		float L_89 = L_88.___M23;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_90 = ___1_value2;
		float L_91 = L_90.___M32;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_92 = ___0_value1;
		float L_93 = L_92.___M24;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_94 = ___1_value2;
		float L_95 = L_94.___M42;
		(&V_0)->___M22 = ((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_multiply(L_81, L_83)), ((float)il2cpp_codegen_multiply(L_85, L_87)))), ((float)il2cpp_codegen_multiply(L_89, L_91)))), ((float)il2cpp_codegen_multiply(L_93, L_95))));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_96 = ___0_value1;
		float L_97 = L_96.___M21;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_98 = ___1_value2;
		float L_99 = L_98.___M13;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_100 = ___0_value1;
		float L_101 = L_100.___M22;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_102 = ___1_value2;
		float L_103 = L_102.___M23;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_104 = ___0_value1;
		float L_105 = L_104.___M23;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_106 = ___1_value2;
		float L_107 = L_106.___M33;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_108 = ___0_value1;
		float L_109 = L_108.___M24;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_110 = ___1_value2;
		float L_111 = L_110.___M43;
		(&V_0)->___M23 = ((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_multiply(L_97, L_99)), ((float)il2cpp_codegen_multiply(L_101, L_103)))), ((float)il2cpp_codegen_multiply(L_105, L_107)))), ((float)il2cpp_codegen_multiply(L_109, L_111))));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_112 = ___0_value1;
		float L_113 = L_112.___M21;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_114 = ___1_value2;
		float L_115 = L_114.___M14;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_116 = ___0_value1;
		float L_117 = L_116.___M22;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_118 = ___1_value2;
		float L_119 = L_118.___M24;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_120 = ___0_value1;
		float L_121 = L_120.___M23;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_122 = ___1_value2;
		float L_123 = L_122.___M34;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_124 = ___0_value1;
		float L_125 = L_124.___M24;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_126 = ___1_value2;
		float L_127 = L_126.___M44;
		(&V_0)->___M24 = ((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_multiply(L_113, L_115)), ((float)il2cpp_codegen_multiply(L_117, L_119)))), ((float)il2cpp_codegen_multiply(L_121, L_123)))), ((float)il2cpp_codegen_multiply(L_125, L_127))));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_128 = ___0_value1;
		float L_129 = L_128.___M31;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_130 = ___1_value2;
		float L_131 = L_130.___M11;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_132 = ___0_value1;
		float L_133 = L_132.___M32;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_134 = ___1_value2;
		float L_135 = L_134.___M21;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_136 = ___0_value1;
		float L_137 = L_136.___M33;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_138 = ___1_value2;
		float L_139 = L_138.___M31;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_140 = ___0_value1;
		float L_141 = L_140.___M34;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_142 = ___1_value2;
		float L_143 = L_142.___M41;
		(&V_0)->___M31 = ((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_multiply(L_129, L_131)), ((float)il2cpp_codegen_multiply(L_133, L_135)))), ((float)il2cpp_codegen_multiply(L_137, L_139)))), ((float)il2cpp_codegen_multiply(L_141, L_143))));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_144 = ___0_value1;
		float L_145 = L_144.___M31;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_146 = ___1_value2;
		float L_147 = L_146.___M12;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_148 = ___0_value1;
		float L_149 = L_148.___M32;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_150 = ___1_value2;
		float L_151 = L_150.___M22;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_152 = ___0_value1;
		float L_153 = L_152.___M33;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_154 = ___1_value2;
		float L_155 = L_154.___M32;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_156 = ___0_value1;
		float L_157 = L_156.___M34;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_158 = ___1_value2;
		float L_159 = L_158.___M42;
		(&V_0)->___M32 = ((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_multiply(L_145, L_147)), ((float)il2cpp_codegen_multiply(L_149, L_151)))), ((float)il2cpp_codegen_multiply(L_153, L_155)))), ((float)il2cpp_codegen_multiply(L_157, L_159))));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_160 = ___0_value1;
		float L_161 = L_160.___M31;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_162 = ___1_value2;
		float L_163 = L_162.___M13;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_164 = ___0_value1;
		float L_165 = L_164.___M32;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_166 = ___1_value2;
		float L_167 = L_166.___M23;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_168 = ___0_value1;
		float L_169 = L_168.___M33;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_170 = ___1_value2;
		float L_171 = L_170.___M33;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_172 = ___0_value1;
		float L_173 = L_172.___M34;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_174 = ___1_value2;
		float L_175 = L_174.___M43;
		(&V_0)->___M33 = ((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_multiply(L_161, L_163)), ((float)il2cpp_codegen_multiply(L_165, L_167)))), ((float)il2cpp_codegen_multiply(L_169, L_171)))), ((float)il2cpp_codegen_multiply(L_173, L_175))));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_176 = ___0_value1;
		float L_177 = L_176.___M31;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_178 = ___1_value2;
		float L_179 = L_178.___M14;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_180 = ___0_value1;
		float L_181 = L_180.___M32;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_182 = ___1_value2;
		float L_183 = L_182.___M24;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_184 = ___0_value1;
		float L_185 = L_184.___M33;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_186 = ___1_value2;
		float L_187 = L_186.___M34;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_188 = ___0_value1;
		float L_189 = L_188.___M34;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_190 = ___1_value2;
		float L_191 = L_190.___M44;
		(&V_0)->___M34 = ((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_multiply(L_177, L_179)), ((float)il2cpp_codegen_multiply(L_181, L_183)))), ((float)il2cpp_codegen_multiply(L_185, L_187)))), ((float)il2cpp_codegen_multiply(L_189, L_191))));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_192 = ___0_value1;
		float L_193 = L_192.___M41;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_194 = ___1_value2;
		float L_195 = L_194.___M11;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_196 = ___0_value1;
		float L_197 = L_196.___M42;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_198 = ___1_value2;
		float L_199 = L_198.___M21;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_200 = ___0_value1;
		float L_201 = L_200.___M43;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_202 = ___1_value2;
		float L_203 = L_202.___M31;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_204 = ___0_value1;
		float L_205 = L_204.___M44;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_206 = ___1_value2;
		float L_207 = L_206.___M41;
		(&V_0)->___M41 = ((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_multiply(L_193, L_195)), ((float)il2cpp_codegen_multiply(L_197, L_199)))), ((float)il2cpp_codegen_multiply(L_201, L_203)))), ((float)il2cpp_codegen_multiply(L_205, L_207))));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_208 = ___0_value1;
		float L_209 = L_208.___M41;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_210 = ___1_value2;
		float L_211 = L_210.___M12;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_212 = ___0_value1;
		float L_213 = L_212.___M42;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_214 = ___1_value2;
		float L_215 = L_214.___M22;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_216 = ___0_value1;
		float L_217 = L_216.___M43;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_218 = ___1_value2;
		float L_219 = L_218.___M32;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_220 = ___0_value1;
		float L_221 = L_220.___M44;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_222 = ___1_value2;
		float L_223 = L_222.___M42;
		(&V_0)->___M42 = ((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_multiply(L_209, L_211)), ((float)il2cpp_codegen_multiply(L_213, L_215)))), ((float)il2cpp_codegen_multiply(L_217, L_219)))), ((float)il2cpp_codegen_multiply(L_221, L_223))));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_224 = ___0_value1;
		float L_225 = L_224.___M41;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_226 = ___1_value2;
		float L_227 = L_226.___M13;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_228 = ___0_value1;
		float L_229 = L_228.___M42;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_230 = ___1_value2;
		float L_231 = L_230.___M23;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_232 = ___0_value1;
		float L_233 = L_232.___M43;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_234 = ___1_value2;
		float L_235 = L_234.___M33;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_236 = ___0_value1;
		float L_237 = L_236.___M44;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_238 = ___1_value2;
		float L_239 = L_238.___M43;
		(&V_0)->___M43 = ((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_multiply(L_225, L_227)), ((float)il2cpp_codegen_multiply(L_229, L_231)))), ((float)il2cpp_codegen_multiply(L_233, L_235)))), ((float)il2cpp_codegen_multiply(L_237, L_239))));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_240 = ___0_value1;
		float L_241 = L_240.___M41;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_242 = ___1_value2;
		float L_243 = L_242.___M14;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_244 = ___0_value1;
		float L_245 = L_244.___M42;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_246 = ___1_value2;
		float L_247 = L_246.___M24;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_248 = ___0_value1;
		float L_249 = L_248.___M43;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_250 = ___1_value2;
		float L_251 = L_250.___M34;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_252 = ___0_value1;
		float L_253 = L_252.___M44;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_254 = ___1_value2;
		float L_255 = L_254.___M44;
		(&V_0)->___M44 = ((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_multiply(L_241, L_243)), ((float)il2cpp_codegen_multiply(L_245, L_247)))), ((float)il2cpp_codegen_multiply(L_249, L_251)))), ((float)il2cpp_codegen_multiply(L_253, L_255))));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_256 = V_0;
		return L_256;
	}
}
// Method Definition Index: 75117
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Matrix4x4_Equals_m6381E804B9AF9FDFB16CC29B8FC6F93E12BBAC03 (Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072* __this, Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 ___0_other, const RuntimeMethod* method) 
{
	{
		float L_0 = __this->___M11;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_1 = ___0_other;
		float L_2 = L_1.___M11;
		if ((!(((float)L_0) == ((float)L_2))))
		{
			goto IL_00f3;
		}
	}
	{
		float L_3 = __this->___M22;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_4 = ___0_other;
		float L_5 = L_4.___M22;
		if ((!(((float)L_3) == ((float)L_5))))
		{
			goto IL_00f3;
		}
	}
	{
		float L_6 = __this->___M33;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_7 = ___0_other;
		float L_8 = L_7.___M33;
		if ((!(((float)L_6) == ((float)L_8))))
		{
			goto IL_00f3;
		}
	}
	{
		float L_9 = __this->___M44;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_10 = ___0_other;
		float L_11 = L_10.___M44;
		if ((!(((float)L_9) == ((float)L_11))))
		{
			goto IL_00f3;
		}
	}
	{
		float L_12 = __this->___M12;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_13 = ___0_other;
		float L_14 = L_13.___M12;
		if ((!(((float)L_12) == ((float)L_14))))
		{
			goto IL_00f3;
		}
	}
	{
		float L_15 = __this->___M13;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_16 = ___0_other;
		float L_17 = L_16.___M13;
		if ((!(((float)L_15) == ((float)L_17))))
		{
			goto IL_00f3;
		}
	}
	{
		float L_18 = __this->___M14;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_19 = ___0_other;
		float L_20 = L_19.___M14;
		if ((!(((float)L_18) == ((float)L_20))))
		{
			goto IL_00f3;
		}
	}
	{
		float L_21 = __this->___M21;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_22 = ___0_other;
		float L_23 = L_22.___M21;
		if ((!(((float)L_21) == ((float)L_23))))
		{
			goto IL_00f3;
		}
	}
	{
		float L_24 = __this->___M23;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_25 = ___0_other;
		float L_26 = L_25.___M23;
		if ((!(((float)L_24) == ((float)L_26))))
		{
			goto IL_00f3;
		}
	}
	{
		float L_27 = __this->___M24;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_28 = ___0_other;
		float L_29 = L_28.___M24;
		if ((!(((float)L_27) == ((float)L_29))))
		{
			goto IL_00f3;
		}
	}
	{
		float L_30 = __this->___M31;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_31 = ___0_other;
		float L_32 = L_31.___M31;
		if ((!(((float)L_30) == ((float)L_32))))
		{
			goto IL_00f3;
		}
	}
	{
		float L_33 = __this->___M32;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_34 = ___0_other;
		float L_35 = L_34.___M32;
		if ((!(((float)L_33) == ((float)L_35))))
		{
			goto IL_00f3;
		}
	}
	{
		float L_36 = __this->___M34;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_37 = ___0_other;
		float L_38 = L_37.___M34;
		if ((!(((float)L_36) == ((float)L_38))))
		{
			goto IL_00f3;
		}
	}
	{
		float L_39 = __this->___M41;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_40 = ___0_other;
		float L_41 = L_40.___M41;
		if ((!(((float)L_39) == ((float)L_41))))
		{
			goto IL_00f3;
		}
	}
	{
		float L_42 = __this->___M42;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_43 = ___0_other;
		float L_44 = L_43.___M42;
		if ((!(((float)L_42) == ((float)L_44))))
		{
			goto IL_00f3;
		}
	}
	{
		float L_45 = __this->___M43;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_46 = ___0_other;
		float L_47 = L_46.___M43;
		return (bool)((((float)L_45) == ((float)L_47))? 1 : 0);
	}

IL_00f3:
	{
		return (bool)0;
	}
}
IL2CPP_EXTERN_C  bool Matrix4x4_Equals_m6381E804B9AF9FDFB16CC29B8FC6F93E12BBAC03_AdjustorThunk (RuntimeObject* __this, Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 ___0_other, const RuntimeMethod* method)
{
	Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072* _thisAdjusted;
	int32_t _offset = 1;
	_thisAdjusted = reinterpret_cast<Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072*>(__this + _offset);
	bool _returnValue;
	_returnValue = Matrix4x4_Equals_m6381E804B9AF9FDFB16CC29B8FC6F93E12BBAC03(_thisAdjusted, ___0_other, method);
	return _returnValue;
}
// Method Definition Index: 75118
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Matrix4x4_Equals_mEAA6F169F8CA748FF70B34F1171C17636D725B05 (Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		RuntimeObject* L_0 = ___0_obj;
		if (!((RuntimeObject*)IsInstSealed((RuntimeObject*)L_0, Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072_il2cpp_TypeInfo_var)))
		{
			goto IL_0015;
		}
	}
	{
		RuntimeObject* L_1 = ___0_obj;
		il2cpp_codegen_runtime_class_init_inline(Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072_il2cpp_TypeInfo_var);
		bool L_2;
		L_2 = Matrix4x4_Equals_m6381E804B9AF9FDFB16CC29B8FC6F93E12BBAC03(__this, ((*(Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072*)UnBox(L_1, Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072_il2cpp_TypeInfo_var))), NULL);
		return L_2;
	}

IL_0015:
	{
		return (bool)0;
	}
}
IL2CPP_EXTERN_C  bool Matrix4x4_Equals_mEAA6F169F8CA748FF70B34F1171C17636D725B05_AdjustorThunk (RuntimeObject* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method)
{
	Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072* _thisAdjusted;
	int32_t _offset = 1;
	_thisAdjusted = reinterpret_cast<Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072*>(__this + _offset);
	bool _returnValue;
	_returnValue = Matrix4x4_Equals_mEAA6F169F8CA748FF70B34F1171C17636D725B05(_thisAdjusted, ___0_obj, method);
	return _returnValue;
}
// Method Definition Index: 75119
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Matrix4x4_ToString_m957EA1B830DF84179D41E500B0C983127BACF023 (Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralD26F25683308CEE6235E8DD6C4C233601EB6FF0D);
		s_Il2CppMethodInitialized = true;
	}
	CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* V_0 = NULL;
	{
		il2cpp_codegen_runtime_class_init_inline(CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0_il2cpp_TypeInfo_var);
		CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* L_0;
		L_0 = CultureInfo_get_CurrentCulture_m8A4580F49DDD7E9DB34C699965423DB8E3BBA9A5(NULL);
		V_0 = L_0;
		CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* L_1 = V_0;
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_2 = (ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918*)(ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918*)SZArrayNew(ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918_il2cpp_TypeInfo_var, (uint32_t)((int32_t)16));
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_3 = L_2;
		float* L_4 = (float*)(&__this->___M11);
		CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* L_5 = V_0;
		String_t* L_6;
		L_6 = Single_ToString_m534852BD7949AA972435783D7B96D0FFB09F6D6A(L_4, L_5, NULL);
		NullCheck(L_3);
		ArrayElementTypeCheck (L_3, L_6);
		(L_3)->SetAt(static_cast<il2cpp_array_size_t>(0), (RuntimeObject*)L_6);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_7 = L_3;
		float* L_8 = (float*)(&__this->___M12);
		CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* L_9 = V_0;
		String_t* L_10;
		L_10 = Single_ToString_m534852BD7949AA972435783D7B96D0FFB09F6D6A(L_8, L_9, NULL);
		NullCheck(L_7);
		ArrayElementTypeCheck (L_7, L_10);
		(L_7)->SetAt(static_cast<il2cpp_array_size_t>(1), (RuntimeObject*)L_10);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_11 = L_7;
		float* L_12 = (float*)(&__this->___M13);
		CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* L_13 = V_0;
		String_t* L_14;
		L_14 = Single_ToString_m534852BD7949AA972435783D7B96D0FFB09F6D6A(L_12, L_13, NULL);
		NullCheck(L_11);
		ArrayElementTypeCheck (L_11, L_14);
		(L_11)->SetAt(static_cast<il2cpp_array_size_t>(2), (RuntimeObject*)L_14);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_15 = L_11;
		float* L_16 = (float*)(&__this->___M14);
		CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* L_17 = V_0;
		String_t* L_18;
		L_18 = Single_ToString_m534852BD7949AA972435783D7B96D0FFB09F6D6A(L_16, L_17, NULL);
		NullCheck(L_15);
		ArrayElementTypeCheck (L_15, L_18);
		(L_15)->SetAt(static_cast<il2cpp_array_size_t>(3), (RuntimeObject*)L_18);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_19 = L_15;
		float* L_20 = (float*)(&__this->___M21);
		CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* L_21 = V_0;
		String_t* L_22;
		L_22 = Single_ToString_m534852BD7949AA972435783D7B96D0FFB09F6D6A(L_20, L_21, NULL);
		NullCheck(L_19);
		ArrayElementTypeCheck (L_19, L_22);
		(L_19)->SetAt(static_cast<il2cpp_array_size_t>(4), (RuntimeObject*)L_22);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_23 = L_19;
		float* L_24 = (float*)(&__this->___M22);
		CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* L_25 = V_0;
		String_t* L_26;
		L_26 = Single_ToString_m534852BD7949AA972435783D7B96D0FFB09F6D6A(L_24, L_25, NULL);
		NullCheck(L_23);
		ArrayElementTypeCheck (L_23, L_26);
		(L_23)->SetAt(static_cast<il2cpp_array_size_t>(5), (RuntimeObject*)L_26);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_27 = L_23;
		float* L_28 = (float*)(&__this->___M23);
		CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* L_29 = V_0;
		String_t* L_30;
		L_30 = Single_ToString_m534852BD7949AA972435783D7B96D0FFB09F6D6A(L_28, L_29, NULL);
		NullCheck(L_27);
		ArrayElementTypeCheck (L_27, L_30);
		(L_27)->SetAt(static_cast<il2cpp_array_size_t>(6), (RuntimeObject*)L_30);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_31 = L_27;
		float* L_32 = (float*)(&__this->___M24);
		CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* L_33 = V_0;
		String_t* L_34;
		L_34 = Single_ToString_m534852BD7949AA972435783D7B96D0FFB09F6D6A(L_32, L_33, NULL);
		NullCheck(L_31);
		ArrayElementTypeCheck (L_31, L_34);
		(L_31)->SetAt(static_cast<il2cpp_array_size_t>(7), (RuntimeObject*)L_34);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_35 = L_31;
		float* L_36 = (float*)(&__this->___M31);
		CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* L_37 = V_0;
		String_t* L_38;
		L_38 = Single_ToString_m534852BD7949AA972435783D7B96D0FFB09F6D6A(L_36, L_37, NULL);
		NullCheck(L_35);
		ArrayElementTypeCheck (L_35, L_38);
		(L_35)->SetAt(static_cast<il2cpp_array_size_t>(8), (RuntimeObject*)L_38);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_39 = L_35;
		float* L_40 = (float*)(&__this->___M32);
		CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* L_41 = V_0;
		String_t* L_42;
		L_42 = Single_ToString_m534852BD7949AA972435783D7B96D0FFB09F6D6A(L_40, L_41, NULL);
		NullCheck(L_39);
		ArrayElementTypeCheck (L_39, L_42);
		(L_39)->SetAt(static_cast<il2cpp_array_size_t>(((int32_t)9)), (RuntimeObject*)L_42);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_43 = L_39;
		float* L_44 = (float*)(&__this->___M33);
		CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* L_45 = V_0;
		String_t* L_46;
		L_46 = Single_ToString_m534852BD7949AA972435783D7B96D0FFB09F6D6A(L_44, L_45, NULL);
		NullCheck(L_43);
		ArrayElementTypeCheck (L_43, L_46);
		(L_43)->SetAt(static_cast<il2cpp_array_size_t>(((int32_t)10)), (RuntimeObject*)L_46);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_47 = L_43;
		float* L_48 = (float*)(&__this->___M34);
		CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* L_49 = V_0;
		String_t* L_50;
		L_50 = Single_ToString_m534852BD7949AA972435783D7B96D0FFB09F6D6A(L_48, L_49, NULL);
		NullCheck(L_47);
		ArrayElementTypeCheck (L_47, L_50);
		(L_47)->SetAt(static_cast<il2cpp_array_size_t>(((int32_t)11)), (RuntimeObject*)L_50);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_51 = L_47;
		float* L_52 = (float*)(&__this->___M41);
		CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* L_53 = V_0;
		String_t* L_54;
		L_54 = Single_ToString_m534852BD7949AA972435783D7B96D0FFB09F6D6A(L_52, L_53, NULL);
		NullCheck(L_51);
		ArrayElementTypeCheck (L_51, L_54);
		(L_51)->SetAt(static_cast<il2cpp_array_size_t>(((int32_t)12)), (RuntimeObject*)L_54);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_55 = L_51;
		float* L_56 = (float*)(&__this->___M42);
		CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* L_57 = V_0;
		String_t* L_58;
		L_58 = Single_ToString_m534852BD7949AA972435783D7B96D0FFB09F6D6A(L_56, L_57, NULL);
		NullCheck(L_55);
		ArrayElementTypeCheck (L_55, L_58);
		(L_55)->SetAt(static_cast<il2cpp_array_size_t>(((int32_t)13)), (RuntimeObject*)L_58);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_59 = L_55;
		float* L_60 = (float*)(&__this->___M43);
		CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* L_61 = V_0;
		String_t* L_62;
		L_62 = Single_ToString_m534852BD7949AA972435783D7B96D0FFB09F6D6A(L_60, L_61, NULL);
		NullCheck(L_59);
		ArrayElementTypeCheck (L_59, L_62);
		(L_59)->SetAt(static_cast<il2cpp_array_size_t>(((int32_t)14)), (RuntimeObject*)L_62);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_63 = L_59;
		float* L_64 = (float*)(&__this->___M44);
		CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* L_65 = V_0;
		String_t* L_66;
		L_66 = Single_ToString_m534852BD7949AA972435783D7B96D0FFB09F6D6A(L_64, L_65, NULL);
		NullCheck(L_63);
		ArrayElementTypeCheck (L_63, L_66);
		(L_63)->SetAt(static_cast<il2cpp_array_size_t>(((int32_t)15)), (RuntimeObject*)L_66);
		String_t* L_67;
		L_67 = String_Format_m447B585713E5EB3EBF5D9D0710706D01E8A56D75(L_1, _stringLiteralD26F25683308CEE6235E8DD6C4C233601EB6FF0D, L_63, NULL);
		return L_67;
	}
}
IL2CPP_EXTERN_C  String_t* Matrix4x4_ToString_m957EA1B830DF84179D41E500B0C983127BACF023_AdjustorThunk (RuntimeObject* __this, const RuntimeMethod* method)
{
	Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072* _thisAdjusted;
	int32_t _offset = 1;
	_thisAdjusted = reinterpret_cast<Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072*>(__this + _offset);
	String_t* _returnValue;
	_returnValue = Matrix4x4_ToString_m957EA1B830DF84179D41E500B0C983127BACF023(_thisAdjusted, method);
	return _returnValue;
}
// Method Definition Index: 75120
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Matrix4x4_GetHashCode_m3C7792A897310FE1FF1D893DF9A1E3A50CC3DB88 (Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072* __this, const RuntimeMethod* method) 
{
	{
		float* L_0 = (float*)(&__this->___M11);
		int32_t L_1;
		L_1 = Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2(L_0, NULL);
		float* L_2 = (float*)(&__this->___M12);
		int32_t L_3;
		L_3 = Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2(L_2, NULL);
		float* L_4 = (float*)(&__this->___M13);
		int32_t L_5;
		L_5 = Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2(L_4, NULL);
		float* L_6 = (float*)(&__this->___M14);
		int32_t L_7;
		L_7 = Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2(L_6, NULL);
		float* L_8 = (float*)(&__this->___M21);
		int32_t L_9;
		L_9 = Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2(L_8, NULL);
		float* L_10 = (float*)(&__this->___M22);
		int32_t L_11;
		L_11 = Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2(L_10, NULL);
		float* L_12 = (float*)(&__this->___M23);
		int32_t L_13;
		L_13 = Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2(L_12, NULL);
		float* L_14 = (float*)(&__this->___M24);
		int32_t L_15;
		L_15 = Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2(L_14, NULL);
		float* L_16 = (float*)(&__this->___M31);
		int32_t L_17;
		L_17 = Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2(L_16, NULL);
		float* L_18 = (float*)(&__this->___M32);
		int32_t L_19;
		L_19 = Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2(L_18, NULL);
		float* L_20 = (float*)(&__this->___M33);
		int32_t L_21;
		L_21 = Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2(L_20, NULL);
		float* L_22 = (float*)(&__this->___M34);
		int32_t L_23;
		L_23 = Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2(L_22, NULL);
		float* L_24 = (float*)(&__this->___M41);
		int32_t L_25;
		L_25 = Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2(L_24, NULL);
		float* L_26 = (float*)(&__this->___M42);
		int32_t L_27;
		L_27 = Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2(L_26, NULL);
		float* L_28 = (float*)(&__this->___M43);
		int32_t L_29;
		L_29 = Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2(L_28, NULL);
		float* L_30 = (float*)(&__this->___M44);
		int32_t L_31;
		L_31 = Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2(L_30, NULL);
		return ((int32_t)il2cpp_codegen_add(((int32_t)il2cpp_codegen_add(((int32_t)il2cpp_codegen_add(((int32_t)il2cpp_codegen_add(((int32_t)il2cpp_codegen_add(((int32_t)il2cpp_codegen_add(((int32_t)il2cpp_codegen_add(((int32_t)il2cpp_codegen_add(((int32_t)il2cpp_codegen_add(((int32_t)il2cpp_codegen_add(((int32_t)il2cpp_codegen_add(((int32_t)il2cpp_codegen_add(((int32_t)il2cpp_codegen_add(((int32_t)il2cpp_codegen_add(((int32_t)il2cpp_codegen_add(L_1, L_3)), L_5)), L_7)), L_9)), L_11)), L_13)), L_15)), L_17)), L_19)), L_21)), L_23)), L_25)), L_27)), L_29)), L_31));
	}
}
IL2CPP_EXTERN_C  int32_t Matrix4x4_GetHashCode_m3C7792A897310FE1FF1D893DF9A1E3A50CC3DB88_AdjustorThunk (RuntimeObject* __this, const RuntimeMethod* method)
{
	Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072* _thisAdjusted;
	int32_t _offset = 1;
	_thisAdjusted = reinterpret_cast<Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072*>(__this + _offset);
	int32_t _returnValue;
	_returnValue = Matrix4x4_GetHashCode_m3C7792A897310FE1FF1D893DF9A1E3A50CC3DB88(_thisAdjusted, method);
	return _returnValue;
}
// Method Definition Index: 75121
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Matrix4x4__cctor_m72B56F08FD5C24FA35775FE1F6F0188663E58612 (const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_0;
		memset((&L_0), 0, sizeof(L_0));
		Matrix4x4__ctor_m4044D15D7E84A1D30E038A963DF0FB0EB6736C1D((&L_0), (1.0f), (0.0f), (0.0f), (0.0f), (0.0f), (1.0f), (0.0f), (0.0f), (0.0f), (0.0f), (1.0f), (0.0f), (0.0f), (0.0f), (0.0f), (1.0f), NULL);
		((Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072_StaticFields*)il2cpp_codegen_static_fields_for(Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072_il2cpp_TypeInfo_var))->____identity = L_0;
		return;
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
// Method Definition Index: 75122
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F Quaternion_CreateFromAxisAngle_mD04F41EF8DD39CBA2797A46CD6ABA3CA408E218E (Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D ___0_axis, float ___1_angle, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&MathF_t54810B9E7503B5BD0850A3D6E6336922E1553C52_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F V_0;
	memset((&V_0), 0, sizeof(V_0));
	float V_1 = 0.0f;
	float V_2 = 0.0f;
	{
		float L_0 = ___1_angle;
		float L_1 = ((float)il2cpp_codegen_multiply(L_0, (0.5f)));
		il2cpp_codegen_runtime_class_init_inline(MathF_t54810B9E7503B5BD0850A3D6E6336922E1553C52_il2cpp_TypeInfo_var);
		float L_2;
		L_2 = sinf(L_1);
		V_1 = L_2;
		float L_3;
		L_3 = cosf(L_1);
		V_2 = L_3;
		Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D L_4 = ___0_axis;
		float L_5 = L_4.___X;
		float L_6 = V_1;
		(&V_0)->___X = ((float)il2cpp_codegen_multiply(L_5, L_6));
		Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D L_7 = ___0_axis;
		float L_8 = L_7.___Y;
		float L_9 = V_1;
		(&V_0)->___Y = ((float)il2cpp_codegen_multiply(L_8, L_9));
		Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D L_10 = ___0_axis;
		float L_11 = L_10.___Z;
		float L_12 = V_1;
		(&V_0)->___Z = ((float)il2cpp_codegen_multiply(L_11, L_12));
		float L_13 = V_2;
		(&V_0)->___W = L_13;
		Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F L_14 = V_0;
		return L_14;
	}
}
// Method Definition Index: 75123
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F Quaternion_CreateFromRotationMatrix_m9360AA5944AEB803472E5BF77F18541A0E57A432 (Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 ___0_matrix, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&MathF_t54810B9E7503B5BD0850A3D6E6336922E1553C52_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	float V_0 = 0.0f;
	Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F V_1;
	memset((&V_1), 0, sizeof(V_1));
	float V_2 = 0.0f;
	float V_3 = 0.0f;
	float V_4 = 0.0f;
	float V_5 = 0.0f;
	float V_6 = 0.0f;
	float V_7 = 0.0f;
	float V_8 = 0.0f;
	{
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_0 = ___0_matrix;
		float L_1 = L_0.___M11;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_2 = ___0_matrix;
		float L_3 = L_2.___M22;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_4 = ___0_matrix;
		float L_5 = L_4.___M33;
		V_0 = ((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(L_1, L_3)), L_5));
		il2cpp_codegen_initobj((&V_1), sizeof(Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F));
		float L_6 = V_0;
		if ((!(((float)L_6) > ((float)(0.0f)))))
		{
			goto IL_008f;
		}
	}
	{
		float L_7 = V_0;
		il2cpp_codegen_runtime_class_init_inline(MathF_t54810B9E7503B5BD0850A3D6E6336922E1553C52_il2cpp_TypeInfo_var);
		float L_8;
		L_8 = sqrtf(((float)il2cpp_codegen_add(L_7, (1.0f))));
		V_2 = L_8;
		float L_9 = V_2;
		(&V_1)->___W = ((float)il2cpp_codegen_multiply(L_9, (0.5f)));
		float L_10 = V_2;
		V_2 = ((float)((0.5f)/L_10));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_11 = ___0_matrix;
		float L_12 = L_11.___M23;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_13 = ___0_matrix;
		float L_14 = L_13.___M32;
		float L_15 = V_2;
		(&V_1)->___X = ((float)il2cpp_codegen_multiply(((float)il2cpp_codegen_subtract(L_12, L_14)), L_15));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_16 = ___0_matrix;
		float L_17 = L_16.___M31;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_18 = ___0_matrix;
		float L_19 = L_18.___M13;
		float L_20 = V_2;
		(&V_1)->___Y = ((float)il2cpp_codegen_multiply(((float)il2cpp_codegen_subtract(L_17, L_19)), L_20));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_21 = ___0_matrix;
		float L_22 = L_21.___M12;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_23 = ___0_matrix;
		float L_24 = L_23.___M21;
		float L_25 = V_2;
		(&V_1)->___Z = ((float)il2cpp_codegen_multiply(((float)il2cpp_codegen_subtract(L_22, L_24)), L_25));
		goto IL_0243;
	}

IL_008f:
	{
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_26 = ___0_matrix;
		float L_27 = L_26.___M11;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_28 = ___0_matrix;
		float L_29 = L_28.___M22;
		if ((!(((float)L_27) >= ((float)L_29))))
		{
			goto IL_0132;
		}
	}
	{
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_30 = ___0_matrix;
		float L_31 = L_30.___M11;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_32 = ___0_matrix;
		float L_33 = L_32.___M33;
		if ((!(((float)L_31) >= ((float)L_33))))
		{
			goto IL_0132;
		}
	}
	{
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_34 = ___0_matrix;
		float L_35 = L_34.___M11;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_36 = ___0_matrix;
		float L_37 = L_36.___M22;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_38 = ___0_matrix;
		float L_39 = L_38.___M33;
		il2cpp_codegen_runtime_class_init_inline(MathF_t54810B9E7503B5BD0850A3D6E6336922E1553C52_il2cpp_TypeInfo_var);
		float L_40;
		L_40 = sqrtf(((float)il2cpp_codegen_subtract(((float)il2cpp_codegen_subtract(((float)il2cpp_codegen_add((1.0f), L_35)), L_37)), L_39)));
		V_3 = L_40;
		float L_41 = V_3;
		V_4 = ((float)((0.5f)/L_41));
		float L_42 = V_3;
		(&V_1)->___X = ((float)il2cpp_codegen_multiply((0.5f), L_42));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_43 = ___0_matrix;
		float L_44 = L_43.___M12;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_45 = ___0_matrix;
		float L_46 = L_45.___M21;
		float L_47 = V_4;
		(&V_1)->___Y = ((float)il2cpp_codegen_multiply(((float)il2cpp_codegen_add(L_44, L_46)), L_47));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_48 = ___0_matrix;
		float L_49 = L_48.___M13;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_50 = ___0_matrix;
		float L_51 = L_50.___M31;
		float L_52 = V_4;
		(&V_1)->___Z = ((float)il2cpp_codegen_multiply(((float)il2cpp_codegen_add(L_49, L_51)), L_52));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_53 = ___0_matrix;
		float L_54 = L_53.___M23;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_55 = ___0_matrix;
		float L_56 = L_55.___M32;
		float L_57 = V_4;
		(&V_1)->___W = ((float)il2cpp_codegen_multiply(((float)il2cpp_codegen_subtract(L_54, L_56)), L_57));
		goto IL_0243;
	}

IL_0132:
	{
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_58 = ___0_matrix;
		float L_59 = L_58.___M22;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_60 = ___0_matrix;
		float L_61 = L_60.___M33;
		if ((!(((float)L_59) > ((float)L_61))))
		{
			goto IL_01c4;
		}
	}
	{
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_62 = ___0_matrix;
		float L_63 = L_62.___M22;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_64 = ___0_matrix;
		float L_65 = L_64.___M11;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_66 = ___0_matrix;
		float L_67 = L_66.___M33;
		il2cpp_codegen_runtime_class_init_inline(MathF_t54810B9E7503B5BD0850A3D6E6336922E1553C52_il2cpp_TypeInfo_var);
		float L_68;
		L_68 = sqrtf(((float)il2cpp_codegen_subtract(((float)il2cpp_codegen_subtract(((float)il2cpp_codegen_add((1.0f), L_63)), L_65)), L_67)));
		V_5 = L_68;
		float L_69 = V_5;
		V_6 = ((float)((0.5f)/L_69));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_70 = ___0_matrix;
		float L_71 = L_70.___M21;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_72 = ___0_matrix;
		float L_73 = L_72.___M12;
		float L_74 = V_6;
		(&V_1)->___X = ((float)il2cpp_codegen_multiply(((float)il2cpp_codegen_add(L_71, L_73)), L_74));
		float L_75 = V_5;
		(&V_1)->___Y = ((float)il2cpp_codegen_multiply((0.5f), L_75));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_76 = ___0_matrix;
		float L_77 = L_76.___M32;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_78 = ___0_matrix;
		float L_79 = L_78.___M23;
		float L_80 = V_6;
		(&V_1)->___Z = ((float)il2cpp_codegen_multiply(((float)il2cpp_codegen_add(L_77, L_79)), L_80));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_81 = ___0_matrix;
		float L_82 = L_81.___M31;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_83 = ___0_matrix;
		float L_84 = L_83.___M13;
		float L_85 = V_6;
		(&V_1)->___W = ((float)il2cpp_codegen_multiply(((float)il2cpp_codegen_subtract(L_82, L_84)), L_85));
		goto IL_0243;
	}

IL_01c4:
	{
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_86 = ___0_matrix;
		float L_87 = L_86.___M33;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_88 = ___0_matrix;
		float L_89 = L_88.___M11;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_90 = ___0_matrix;
		float L_91 = L_90.___M22;
		il2cpp_codegen_runtime_class_init_inline(MathF_t54810B9E7503B5BD0850A3D6E6336922E1553C52_il2cpp_TypeInfo_var);
		float L_92;
		L_92 = sqrtf(((float)il2cpp_codegen_subtract(((float)il2cpp_codegen_subtract(((float)il2cpp_codegen_add((1.0f), L_87)), L_89)), L_91)));
		V_7 = L_92;
		float L_93 = V_7;
		V_8 = ((float)((0.5f)/L_93));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_94 = ___0_matrix;
		float L_95 = L_94.___M31;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_96 = ___0_matrix;
		float L_97 = L_96.___M13;
		float L_98 = V_8;
		(&V_1)->___X = ((float)il2cpp_codegen_multiply(((float)il2cpp_codegen_add(L_95, L_97)), L_98));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_99 = ___0_matrix;
		float L_100 = L_99.___M32;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_101 = ___0_matrix;
		float L_102 = L_101.___M23;
		float L_103 = V_8;
		(&V_1)->___Y = ((float)il2cpp_codegen_multiply(((float)il2cpp_codegen_add(L_100, L_102)), L_103));
		float L_104 = V_7;
		(&V_1)->___Z = ((float)il2cpp_codegen_multiply((0.5f), L_104));
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_105 = ___0_matrix;
		float L_106 = L_105.___M12;
		Matrix4x4_t5F5E1A25D90A40678520AA0DA0C8C4D25E9DD072 L_107 = ___0_matrix;
		float L_108 = L_107.___M21;
		float L_109 = V_8;
		(&V_1)->___W = ((float)il2cpp_codegen_multiply(((float)il2cpp_codegen_subtract(L_106, L_108)), L_109));
	}

IL_0243:
	{
		Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F L_110 = V_1;
		return L_110;
	}
}
// Method Definition Index: 75124
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Quaternion_Equals_m1B7B3C6E864B978BAFC9AF0DC2115A8A93D1AFA1 (Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F* __this, Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F ___0_other, const RuntimeMethod* method) 
{
	{
		float L_0 = __this->___X;
		Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F L_1 = ___0_other;
		float L_2 = L_1.___X;
		if ((!(((float)L_0) == ((float)L_2))))
		{
			goto IL_0039;
		}
	}
	{
		float L_3 = __this->___Y;
		Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F L_4 = ___0_other;
		float L_5 = L_4.___Y;
		if ((!(((float)L_3) == ((float)L_5))))
		{
			goto IL_0039;
		}
	}
	{
		float L_6 = __this->___Z;
		Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F L_7 = ___0_other;
		float L_8 = L_7.___Z;
		if ((!(((float)L_6) == ((float)L_8))))
		{
			goto IL_0039;
		}
	}
	{
		float L_9 = __this->___W;
		Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F L_10 = ___0_other;
		float L_11 = L_10.___W;
		return (bool)((((float)L_9) == ((float)L_11))? 1 : 0);
	}

IL_0039:
	{
		return (bool)0;
	}
}
IL2CPP_EXTERN_C  bool Quaternion_Equals_m1B7B3C6E864B978BAFC9AF0DC2115A8A93D1AFA1_AdjustorThunk (RuntimeObject* __this, Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F ___0_other, const RuntimeMethod* method)
{
	Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F* _thisAdjusted;
	int32_t _offset = 1;
	_thisAdjusted = reinterpret_cast<Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F*>(__this + _offset);
	bool _returnValue;
	_returnValue = Quaternion_Equals_m1B7B3C6E864B978BAFC9AF0DC2115A8A93D1AFA1(_thisAdjusted, ___0_other, method);
	return _returnValue;
}
// Method Definition Index: 75125
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Quaternion_Equals_m9634C9FE716B037E38FF4AB7DA4BC8B9F8CC21F7 (Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		RuntimeObject* L_0 = ___0_obj;
		if (!((RuntimeObject*)IsInstSealed((RuntimeObject*)L_0, Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F_il2cpp_TypeInfo_var)))
		{
			goto IL_0015;
		}
	}
	{
		RuntimeObject* L_1 = ___0_obj;
		bool L_2;
		L_2 = Quaternion_Equals_m1B7B3C6E864B978BAFC9AF0DC2115A8A93D1AFA1(__this, ((*(Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F*)UnBox(L_1, Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F_il2cpp_TypeInfo_var))), NULL);
		return L_2;
	}

IL_0015:
	{
		return (bool)0;
	}
}
IL2CPP_EXTERN_C  bool Quaternion_Equals_m9634C9FE716B037E38FF4AB7DA4BC8B9F8CC21F7_AdjustorThunk (RuntimeObject* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method)
{
	Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F* _thisAdjusted;
	int32_t _offset = 1;
	_thisAdjusted = reinterpret_cast<Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F*>(__this + _offset);
	bool _returnValue;
	_returnValue = Quaternion_Equals_m9634C9FE716B037E38FF4AB7DA4BC8B9F8CC21F7(_thisAdjusted, ___0_obj, method);
	return _returnValue;
}
// Method Definition Index: 75126
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Quaternion_ToString_mF7DE37CE381D91BAE78F60618A0881428FF10B69 (Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral4DC02E9DBA2BBA6B0B90CAE0980D70CE6132DCD1);
		s_Il2CppMethodInitialized = true;
	}
	CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* V_0 = NULL;
	{
		il2cpp_codegen_runtime_class_init_inline(CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0_il2cpp_TypeInfo_var);
		CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* L_0;
		L_0 = CultureInfo_get_CurrentCulture_m8A4580F49DDD7E9DB34C699965423DB8E3BBA9A5(NULL);
		V_0 = L_0;
		CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* L_1 = V_0;
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_2 = (ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918*)(ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918*)SZArrayNew(ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918_il2cpp_TypeInfo_var, (uint32_t)4);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_3 = L_2;
		float* L_4 = (float*)(&__this->___X);
		CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* L_5 = V_0;
		String_t* L_6;
		L_6 = Single_ToString_m534852BD7949AA972435783D7B96D0FFB09F6D6A(L_4, L_5, NULL);
		NullCheck(L_3);
		ArrayElementTypeCheck (L_3, L_6);
		(L_3)->SetAt(static_cast<il2cpp_array_size_t>(0), (RuntimeObject*)L_6);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_7 = L_3;
		float* L_8 = (float*)(&__this->___Y);
		CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* L_9 = V_0;
		String_t* L_10;
		L_10 = Single_ToString_m534852BD7949AA972435783D7B96D0FFB09F6D6A(L_8, L_9, NULL);
		NullCheck(L_7);
		ArrayElementTypeCheck (L_7, L_10);
		(L_7)->SetAt(static_cast<il2cpp_array_size_t>(1), (RuntimeObject*)L_10);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_11 = L_7;
		float* L_12 = (float*)(&__this->___Z);
		CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* L_13 = V_0;
		String_t* L_14;
		L_14 = Single_ToString_m534852BD7949AA972435783D7B96D0FFB09F6D6A(L_12, L_13, NULL);
		NullCheck(L_11);
		ArrayElementTypeCheck (L_11, L_14);
		(L_11)->SetAt(static_cast<il2cpp_array_size_t>(2), (RuntimeObject*)L_14);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_15 = L_11;
		float* L_16 = (float*)(&__this->___W);
		CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* L_17 = V_0;
		String_t* L_18;
		L_18 = Single_ToString_m534852BD7949AA972435783D7B96D0FFB09F6D6A(L_16, L_17, NULL);
		NullCheck(L_15);
		ArrayElementTypeCheck (L_15, L_18);
		(L_15)->SetAt(static_cast<il2cpp_array_size_t>(3), (RuntimeObject*)L_18);
		String_t* L_19;
		L_19 = String_Format_m447B585713E5EB3EBF5D9D0710706D01E8A56D75(L_1, _stringLiteral4DC02E9DBA2BBA6B0B90CAE0980D70CE6132DCD1, L_15, NULL);
		return L_19;
	}
}
IL2CPP_EXTERN_C  String_t* Quaternion_ToString_mF7DE37CE381D91BAE78F60618A0881428FF10B69_AdjustorThunk (RuntimeObject* __this, const RuntimeMethod* method)
{
	Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F* _thisAdjusted;
	int32_t _offset = 1;
	_thisAdjusted = reinterpret_cast<Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F*>(__this + _offset);
	String_t* _returnValue;
	_returnValue = Quaternion_ToString_mF7DE37CE381D91BAE78F60618A0881428FF10B69(_thisAdjusted, method);
	return _returnValue;
}
// Method Definition Index: 75127
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Quaternion_GetHashCode_m9C732EF144D4B9A806F553F7F983F9E66A3AA77F (Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F* __this, const RuntimeMethod* method) 
{
	{
		float* L_0 = (float*)(&__this->___X);
		int32_t L_1;
		L_1 = Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2(L_0, NULL);
		float* L_2 = (float*)(&__this->___Y);
		int32_t L_3;
		L_3 = Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2(L_2, NULL);
		float* L_4 = (float*)(&__this->___Z);
		int32_t L_5;
		L_5 = Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2(L_4, NULL);
		float* L_6 = (float*)(&__this->___W);
		int32_t L_7;
		L_7 = Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2(L_6, NULL);
		return ((int32_t)il2cpp_codegen_add(((int32_t)il2cpp_codegen_add(((int32_t)il2cpp_codegen_add(L_1, L_3)), L_5)), L_7));
	}
}
IL2CPP_EXTERN_C  int32_t Quaternion_GetHashCode_m9C732EF144D4B9A806F553F7F983F9E66A3AA77F_AdjustorThunk (RuntimeObject* __this, const RuntimeMethod* method)
{
	Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F* _thisAdjusted;
	int32_t _offset = 1;
	_thisAdjusted = reinterpret_cast<Quaternion_tF78017216EDB74038AE5C9696AF7C1C922EE788F*>(__this + _offset);
	int32_t _returnValue;
	_returnValue = Quaternion_GetHashCode_m9C732EF144D4B9A806F553F7F983F9E66A3AA77F(_thisAdjusted, method);
	return _returnValue;
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// Method Definition Index: 75128
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D Vector3_get_UnitX_m62668C092DA5FBBEB75A326E315FDB7D463E9EA0 (const RuntimeMethod* method) 
{
	{
		Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D L_0;
		memset((&L_0), 0, sizeof(L_0));
		Vector3__ctor_m7C7B6A0DD0B1E729ECE05CD46AAD62DC2B71BD86((&L_0), (1.0f), (0.0f), (0.0f), NULL);
		return L_0;
	}
}
// Method Definition Index: 75129
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Vector3_GetHashCode_m6EA39E7A65A09E669C48F62771442A9936A81665 (Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&HashHelpers_tDBADDD699E5C1C58ED9DF75B838DD3056A6EAF64_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		float* L_0 = (float*)(&__this->___X);
		int32_t L_1;
		L_1 = Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2(L_0, NULL);
		float* L_2 = (float*)(&__this->___Y);
		int32_t L_3;
		L_3 = Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2(L_2, NULL);
		il2cpp_codegen_runtime_class_init_inline(HashHelpers_tDBADDD699E5C1C58ED9DF75B838DD3056A6EAF64_il2cpp_TypeInfo_var);
		int32_t L_4;
		L_4 = HashHelpers_Combine_mA94C6B78FF8602DF43B2236B37E07EAD3E40861E(L_1, L_3, NULL);
		float* L_5 = (float*)(&__this->___Z);
		int32_t L_6;
		L_6 = Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2(L_5, NULL);
		int32_t L_7;
		L_7 = HashHelpers_Combine_mA94C6B78FF8602DF43B2236B37E07EAD3E40861E(L_4, L_6, NULL);
		return L_7;
	}
}
IL2CPP_EXTERN_C  int32_t Vector3_GetHashCode_m6EA39E7A65A09E669C48F62771442A9936A81665_AdjustorThunk (RuntimeObject* __this, const RuntimeMethod* method)
{
	Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D* _thisAdjusted;
	int32_t _offset = 1;
	_thisAdjusted = reinterpret_cast<Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D*>(__this + _offset);
	int32_t _returnValue;
	_returnValue = Vector3_GetHashCode_m6EA39E7A65A09E669C48F62771442A9936A81665(_thisAdjusted, method);
	return _returnValue;
}
// Method Definition Index: 75130
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Vector3_Equals_m81F9E36BE544963229CFED0561670E20DDCAABCE (Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		RuntimeObject* L_0 = ___0_obj;
		if (((RuntimeObject*)IsInstSealed((RuntimeObject*)L_0, Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D_il2cpp_TypeInfo_var)))
		{
			goto IL_000a;
		}
	}
	{
		return (bool)0;
	}

IL_000a:
	{
		RuntimeObject* L_1 = ___0_obj;
		bool L_2;
		L_2 = Vector3_Equals_m5EAC07DAA75E2A84A3997BC27F36BE19698CC3FE(__this, ((*(Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D*)UnBox(L_1, Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D_il2cpp_TypeInfo_var))), NULL);
		return L_2;
	}
}
IL2CPP_EXTERN_C  bool Vector3_Equals_m81F9E36BE544963229CFED0561670E20DDCAABCE_AdjustorThunk (RuntimeObject* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method)
{
	Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D* _thisAdjusted;
	int32_t _offset = 1;
	_thisAdjusted = reinterpret_cast<Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D*>(__this + _offset);
	bool _returnValue;
	_returnValue = Vector3_Equals_m81F9E36BE544963229CFED0561670E20DDCAABCE_inline(_thisAdjusted, ___0_obj, method);
	return _returnValue;
}
// Method Definition Index: 75131
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Vector3_ToString_m84244C7A59EC1E85A6D00453D2CF6190ED511CBC (Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral3DCC6243286938BE75C3FA773B9BA71160A2E869);
		s_Il2CppMethodInitialized = true;
	}
	{
		il2cpp_codegen_runtime_class_init_inline(CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0_il2cpp_TypeInfo_var);
		CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* L_0;
		L_0 = CultureInfo_get_CurrentCulture_m8A4580F49DDD7E9DB34C699965423DB8E3BBA9A5(NULL);
		String_t* L_1;
		L_1 = Vector3_ToString_m2048240DCF9C4CED3DE9A4C4C1589786BB3B1D99(__this, _stringLiteral3DCC6243286938BE75C3FA773B9BA71160A2E869, L_0, NULL);
		return L_1;
	}
}
IL2CPP_EXTERN_C  String_t* Vector3_ToString_m84244C7A59EC1E85A6D00453D2CF6190ED511CBC_AdjustorThunk (RuntimeObject* __this, const RuntimeMethod* method)
{
	Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D* _thisAdjusted;
	int32_t _offset = 1;
	_thisAdjusted = reinterpret_cast<Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D*>(__this + _offset);
	String_t* _returnValue;
	_returnValue = Vector3_ToString_m84244C7A59EC1E85A6D00453D2CF6190ED511CBC(_thisAdjusted, method);
	return _returnValue;
}
// Method Definition Index: 75132
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Vector3_ToString_m2048240DCF9C4CED3DE9A4C4C1589786BB3B1D99 (Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D* __this, String_t* ___0_format, RuntimeObject* ___1_formatProvider, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&StringBuilder_t_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	String_t* V_0 = NULL;
	{
		StringBuilder_t* L_0 = (StringBuilder_t*)il2cpp_codegen_object_new(StringBuilder_t_il2cpp_TypeInfo_var);
		StringBuilder__ctor_m1D99713357DE05DAFA296633639DB55F8C30587D(L_0, NULL);
		RuntimeObject* L_1 = ___1_formatProvider;
		NumberFormatInfo_t8E26808B202927FEBF9064FCFEEA4D6E076E6472* L_2;
		L_2 = NumberFormatInfo_GetInstance_m705987E5E7D3E5EC5C5DD2D088FBC9BCBA0FC31F(L_1, NULL);
		NullCheck(L_2);
		String_t* L_3;
		L_3 = NumberFormatInfo_get_NumberGroupSeparator_m0556B092AA471513B1EDC31C047712226D39BEB6_inline(L_2, NULL);
		V_0 = L_3;
		StringBuilder_t* L_4 = L_0;
		NullCheck(L_4);
		StringBuilder_t* L_5;
		L_5 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_4, ((int32_t)60), NULL);
		StringBuilder_t* L_6 = L_4;
		float L_7 = __this->___X;
		float L_8 = L_7;
		RuntimeObject* L_9 = Box(il2cpp_defaults.single_class, &L_8);
		String_t* L_10 = ___0_format;
		RuntimeObject* L_11 = ___1_formatProvider;
		String_t* L_12;
		L_12 = Single_ToString_mF468A56B3A746EFD805E0604EE7A2873DA157ADE((float*)UnBox(L_9, il2cpp_defaults.single_class), L_10, L_11, NULL);
		NullCheck(L_6);
		StringBuilder_t* L_13;
		L_13 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_6, L_12, NULL);
		StringBuilder_t* L_14 = L_6;
		String_t* L_15 = V_0;
		NullCheck(L_14);
		StringBuilder_t* L_16;
		L_16 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_14, L_15, NULL);
		StringBuilder_t* L_17 = L_14;
		NullCheck(L_17);
		StringBuilder_t* L_18;
		L_18 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_17, ((int32_t)32), NULL);
		StringBuilder_t* L_19 = L_17;
		float L_20 = __this->___Y;
		float L_21 = L_20;
		RuntimeObject* L_22 = Box(il2cpp_defaults.single_class, &L_21);
		String_t* L_23 = ___0_format;
		RuntimeObject* L_24 = ___1_formatProvider;
		String_t* L_25;
		L_25 = Single_ToString_mF468A56B3A746EFD805E0604EE7A2873DA157ADE((float*)UnBox(L_22, il2cpp_defaults.single_class), L_23, L_24, NULL);
		NullCheck(L_19);
		StringBuilder_t* L_26;
		L_26 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_19, L_25, NULL);
		StringBuilder_t* L_27 = L_19;
		String_t* L_28 = V_0;
		NullCheck(L_27);
		StringBuilder_t* L_29;
		L_29 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_27, L_28, NULL);
		StringBuilder_t* L_30 = L_27;
		NullCheck(L_30);
		StringBuilder_t* L_31;
		L_31 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_30, ((int32_t)32), NULL);
		StringBuilder_t* L_32 = L_30;
		float L_33 = __this->___Z;
		float L_34 = L_33;
		RuntimeObject* L_35 = Box(il2cpp_defaults.single_class, &L_34);
		String_t* L_36 = ___0_format;
		RuntimeObject* L_37 = ___1_formatProvider;
		String_t* L_38;
		L_38 = Single_ToString_mF468A56B3A746EFD805E0604EE7A2873DA157ADE((float*)UnBox(L_35, il2cpp_defaults.single_class), L_36, L_37, NULL);
		NullCheck(L_32);
		StringBuilder_t* L_39;
		L_39 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_32, L_38, NULL);
		StringBuilder_t* L_40 = L_32;
		NullCheck(L_40);
		StringBuilder_t* L_41;
		L_41 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_40, ((int32_t)62), NULL);
		NullCheck(L_40);
		String_t* L_42;
		L_42 = VirtualFuncInvoker0< String_t* >::Invoke(3, L_40);
		return L_42;
	}
}
IL2CPP_EXTERN_C  String_t* Vector3_ToString_m2048240DCF9C4CED3DE9A4C4C1589786BB3B1D99_AdjustorThunk (RuntimeObject* __this, String_t* ___0_format, RuntimeObject* ___1_formatProvider, const RuntimeMethod* method)
{
	Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D* _thisAdjusted;
	int32_t _offset = 1;
	_thisAdjusted = reinterpret_cast<Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D*>(__this + _offset);
	String_t* _returnValue;
	_returnValue = Vector3_ToString_m2048240DCF9C4CED3DE9A4C4C1589786BB3B1D99(_thisAdjusted, ___0_format, ___1_formatProvider, method);
	return _returnValue;
}
// Method Definition Index: 75133
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Vector3__ctor_m7C7B6A0DD0B1E729ECE05CD46AAD62DC2B71BD86 (Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D* __this, float ___0_x, float ___1_y, float ___2_z, const RuntimeMethod* method) 
{
	{
		float L_0 = ___0_x;
		__this->___X = L_0;
		float L_1 = ___1_y;
		__this->___Y = L_1;
		float L_2 = ___2_z;
		__this->___Z = L_2;
		return;
	}
}
IL2CPP_EXTERN_C  void Vector3__ctor_m7C7B6A0DD0B1E729ECE05CD46AAD62DC2B71BD86_AdjustorThunk (RuntimeObject* __this, float ___0_x, float ___1_y, float ___2_z, const RuntimeMethod* method)
{
	Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D* _thisAdjusted;
	int32_t _offset = 1;
	_thisAdjusted = reinterpret_cast<Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D*>(__this + _offset);
	Vector3__ctor_m7C7B6A0DD0B1E729ECE05CD46AAD62DC2B71BD86(_thisAdjusted, ___0_x, ___1_y, ___2_z, method);
}
// Method Definition Index: 75134
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Vector3_Equals_m5EAC07DAA75E2A84A3997BC27F36BE19698CC3FE (Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D* __this, Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D ___0_other, const RuntimeMethod* method) 
{
	{
		float L_0 = __this->___X;
		Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D L_1 = ___0_other;
		float L_2 = L_1.___X;
		if ((!(((float)L_0) == ((float)L_2))))
		{
			goto IL_002b;
		}
	}
	{
		float L_3 = __this->___Y;
		Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D L_4 = ___0_other;
		float L_5 = L_4.___Y;
		if ((!(((float)L_3) == ((float)L_5))))
		{
			goto IL_002b;
		}
	}
	{
		float L_6 = __this->___Z;
		Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D L_7 = ___0_other;
		float L_8 = L_7.___Z;
		return (bool)((((float)L_6) == ((float)L_8))? 1 : 0);
	}

IL_002b:
	{
		return (bool)0;
	}
}
IL2CPP_EXTERN_C  bool Vector3_Equals_m5EAC07DAA75E2A84A3997BC27F36BE19698CC3FE_AdjustorThunk (RuntimeObject* __this, Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D ___0_other, const RuntimeMethod* method)
{
	Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D* _thisAdjusted;
	int32_t _offset = 1;
	_thisAdjusted = reinterpret_cast<Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D*>(__this + _offset);
	bool _returnValue;
	_returnValue = Vector3_Equals_m5EAC07DAA75E2A84A3997BC27F36BE19698CC3FE(_thisAdjusted, ___0_other, method);
	return _returnValue;
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// Method Definition Index: 75135
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Vector4_GetHashCode_m33C3E640020BD14992DA0EBBE8A365FA10C3FDAB (Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&HashHelpers_tDBADDD699E5C1C58ED9DF75B838DD3056A6EAF64_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		float* L_0 = (float*)(&__this->___X);
		int32_t L_1;
		L_1 = Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2(L_0, NULL);
		float* L_2 = (float*)(&__this->___Y);
		int32_t L_3;
		L_3 = Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2(L_2, NULL);
		il2cpp_codegen_runtime_class_init_inline(HashHelpers_tDBADDD699E5C1C58ED9DF75B838DD3056A6EAF64_il2cpp_TypeInfo_var);
		int32_t L_4;
		L_4 = HashHelpers_Combine_mA94C6B78FF8602DF43B2236B37E07EAD3E40861E(L_1, L_3, NULL);
		float* L_5 = (float*)(&__this->___Z);
		int32_t L_6;
		L_6 = Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2(L_5, NULL);
		int32_t L_7;
		L_7 = HashHelpers_Combine_mA94C6B78FF8602DF43B2236B37E07EAD3E40861E(L_4, L_6, NULL);
		float* L_8 = (float*)(&__this->___W);
		int32_t L_9;
		L_9 = Single_GetHashCode_mC3F1E099D1CF165C2D71FBCC5EF6A6792F9021D2(L_8, NULL);
		int32_t L_10;
		L_10 = HashHelpers_Combine_mA94C6B78FF8602DF43B2236B37E07EAD3E40861E(L_7, L_9, NULL);
		return L_10;
	}
}
IL2CPP_EXTERN_C  int32_t Vector4_GetHashCode_m33C3E640020BD14992DA0EBBE8A365FA10C3FDAB_AdjustorThunk (RuntimeObject* __this, const RuntimeMethod* method)
{
	Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6* _thisAdjusted;
	int32_t _offset = 1;
	_thisAdjusted = reinterpret_cast<Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6*>(__this + _offset);
	int32_t _returnValue;
	_returnValue = Vector4_GetHashCode_m33C3E640020BD14992DA0EBBE8A365FA10C3FDAB(_thisAdjusted, method);
	return _returnValue;
}
// Method Definition Index: 75136
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Vector4_Equals_mD6B3F8BACD1BC326AF6FF6F83FDDF6BA90A9CF10 (Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		RuntimeObject* L_0 = ___0_obj;
		if (((RuntimeObject*)IsInstSealed((RuntimeObject*)L_0, Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6_il2cpp_TypeInfo_var)))
		{
			goto IL_000a;
		}
	}
	{
		return (bool)0;
	}

IL_000a:
	{
		RuntimeObject* L_1 = ___0_obj;
		bool L_2;
		L_2 = Vector4_Equals_m0FF82F8146C7DFCC7B3BCBA18AF1694992A132F6(__this, ((*(Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6*)UnBox(L_1, Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6_il2cpp_TypeInfo_var))), NULL);
		return L_2;
	}
}
IL2CPP_EXTERN_C  bool Vector4_Equals_mD6B3F8BACD1BC326AF6FF6F83FDDF6BA90A9CF10_AdjustorThunk (RuntimeObject* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method)
{
	Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6* _thisAdjusted;
	int32_t _offset = 1;
	_thisAdjusted = reinterpret_cast<Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6*>(__this + _offset);
	bool _returnValue;
	_returnValue = Vector4_Equals_mD6B3F8BACD1BC326AF6FF6F83FDDF6BA90A9CF10_inline(_thisAdjusted, ___0_obj, method);
	return _returnValue;
}
// Method Definition Index: 75137
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Vector4_ToString_m64F83A00FB32819E687AFD35979F270102E4447B (Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral3DCC6243286938BE75C3FA773B9BA71160A2E869);
		s_Il2CppMethodInitialized = true;
	}
	{
		il2cpp_codegen_runtime_class_init_inline(CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0_il2cpp_TypeInfo_var);
		CultureInfo_t9BA817D41AD55AC8BD07480DD8AC22F8FFA378E0* L_0;
		L_0 = CultureInfo_get_CurrentCulture_m8A4580F49DDD7E9DB34C699965423DB8E3BBA9A5(NULL);
		String_t* L_1;
		L_1 = Vector4_ToString_m00657EAF2A6840CB077AFCF5317A3AC738648833(__this, _stringLiteral3DCC6243286938BE75C3FA773B9BA71160A2E869, L_0, NULL);
		return L_1;
	}
}
IL2CPP_EXTERN_C  String_t* Vector4_ToString_m64F83A00FB32819E687AFD35979F270102E4447B_AdjustorThunk (RuntimeObject* __this, const RuntimeMethod* method)
{
	Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6* _thisAdjusted;
	int32_t _offset = 1;
	_thisAdjusted = reinterpret_cast<Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6*>(__this + _offset);
	String_t* _returnValue;
	_returnValue = Vector4_ToString_m64F83A00FB32819E687AFD35979F270102E4447B(_thisAdjusted, method);
	return _returnValue;
}
// Method Definition Index: 75138
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Vector4_ToString_m00657EAF2A6840CB077AFCF5317A3AC738648833 (Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6* __this, String_t* ___0_format, RuntimeObject* ___1_formatProvider, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&StringBuilder_t_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	String_t* V_0 = NULL;
	{
		StringBuilder_t* L_0 = (StringBuilder_t*)il2cpp_codegen_object_new(StringBuilder_t_il2cpp_TypeInfo_var);
		StringBuilder__ctor_m1D99713357DE05DAFA296633639DB55F8C30587D(L_0, NULL);
		RuntimeObject* L_1 = ___1_formatProvider;
		NumberFormatInfo_t8E26808B202927FEBF9064FCFEEA4D6E076E6472* L_2;
		L_2 = NumberFormatInfo_GetInstance_m705987E5E7D3E5EC5C5DD2D088FBC9BCBA0FC31F(L_1, NULL);
		NullCheck(L_2);
		String_t* L_3;
		L_3 = NumberFormatInfo_get_NumberGroupSeparator_m0556B092AA471513B1EDC31C047712226D39BEB6_inline(L_2, NULL);
		V_0 = L_3;
		StringBuilder_t* L_4 = L_0;
		NullCheck(L_4);
		StringBuilder_t* L_5;
		L_5 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_4, ((int32_t)60), NULL);
		StringBuilder_t* L_6 = L_4;
		float* L_7 = (float*)(&__this->___X);
		String_t* L_8 = ___0_format;
		RuntimeObject* L_9 = ___1_formatProvider;
		String_t* L_10;
		L_10 = Single_ToString_mF468A56B3A746EFD805E0604EE7A2873DA157ADE(L_7, L_8, L_9, NULL);
		NullCheck(L_6);
		StringBuilder_t* L_11;
		L_11 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_6, L_10, NULL);
		StringBuilder_t* L_12 = L_6;
		String_t* L_13 = V_0;
		NullCheck(L_12);
		StringBuilder_t* L_14;
		L_14 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_12, L_13, NULL);
		StringBuilder_t* L_15 = L_12;
		NullCheck(L_15);
		StringBuilder_t* L_16;
		L_16 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_15, ((int32_t)32), NULL);
		StringBuilder_t* L_17 = L_15;
		float* L_18 = (float*)(&__this->___Y);
		String_t* L_19 = ___0_format;
		RuntimeObject* L_20 = ___1_formatProvider;
		String_t* L_21;
		L_21 = Single_ToString_mF468A56B3A746EFD805E0604EE7A2873DA157ADE(L_18, L_19, L_20, NULL);
		NullCheck(L_17);
		StringBuilder_t* L_22;
		L_22 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_17, L_21, NULL);
		StringBuilder_t* L_23 = L_17;
		String_t* L_24 = V_0;
		NullCheck(L_23);
		StringBuilder_t* L_25;
		L_25 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_23, L_24, NULL);
		StringBuilder_t* L_26 = L_23;
		NullCheck(L_26);
		StringBuilder_t* L_27;
		L_27 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_26, ((int32_t)32), NULL);
		StringBuilder_t* L_28 = L_26;
		float* L_29 = (float*)(&__this->___Z);
		String_t* L_30 = ___0_format;
		RuntimeObject* L_31 = ___1_formatProvider;
		String_t* L_32;
		L_32 = Single_ToString_mF468A56B3A746EFD805E0604EE7A2873DA157ADE(L_29, L_30, L_31, NULL);
		NullCheck(L_28);
		StringBuilder_t* L_33;
		L_33 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_28, L_32, NULL);
		StringBuilder_t* L_34 = L_28;
		String_t* L_35 = V_0;
		NullCheck(L_34);
		StringBuilder_t* L_36;
		L_36 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_34, L_35, NULL);
		StringBuilder_t* L_37 = L_34;
		NullCheck(L_37);
		StringBuilder_t* L_38;
		L_38 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_37, ((int32_t)32), NULL);
		StringBuilder_t* L_39 = L_37;
		float* L_40 = (float*)(&__this->___W);
		String_t* L_41 = ___0_format;
		RuntimeObject* L_42 = ___1_formatProvider;
		String_t* L_43;
		L_43 = Single_ToString_mF468A56B3A746EFD805E0604EE7A2873DA157ADE(L_40, L_41, L_42, NULL);
		NullCheck(L_39);
		StringBuilder_t* L_44;
		L_44 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_39, L_43, NULL);
		StringBuilder_t* L_45 = L_39;
		NullCheck(L_45);
		StringBuilder_t* L_46;
		L_46 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_45, ((int32_t)62), NULL);
		NullCheck(L_45);
		String_t* L_47;
		L_47 = VirtualFuncInvoker0< String_t* >::Invoke(3, L_45);
		return L_47;
	}
}
IL2CPP_EXTERN_C  String_t* Vector4_ToString_m00657EAF2A6840CB077AFCF5317A3AC738648833_AdjustorThunk (RuntimeObject* __this, String_t* ___0_format, RuntimeObject* ___1_formatProvider, const RuntimeMethod* method)
{
	Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6* _thisAdjusted;
	int32_t _offset = 1;
	_thisAdjusted = reinterpret_cast<Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6*>(__this + _offset);
	String_t* _returnValue;
	_returnValue = Vector4_ToString_m00657EAF2A6840CB077AFCF5317A3AC738648833(_thisAdjusted, ___0_format, ___1_formatProvider, method);
	return _returnValue;
}
// Method Definition Index: 75139
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR float Vector4_Length_m988A0BD2995D4011A1E8D915232859ADAD2C5462 (Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&MathF_t54810B9E7503B5BD0850A3D6E6336922E1553C52_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		bool L_0;
		L_0 = Vector_get_IsHardwareAccelerated_m783509258751EBED64CBD9F387EC1BB4A15088AA(NULL);
		if (!L_0)
		{
			goto IL_001e;
		}
	}
	{
		Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 L_1 = (*(Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6*)__this);
		Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 L_2 = (*(Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6*)__this);
		float L_3;
		L_3 = Vector4_Dot_mE2526C44DFCF9BFF1E22F265B9F24F2BB18FE27B_inline(L_1, L_2, NULL);
		il2cpp_codegen_runtime_class_init_inline(MathF_t54810B9E7503B5BD0850A3D6E6336922E1553C52_il2cpp_TypeInfo_var);
		float L_4;
		L_4 = sqrtf(L_3);
		return L_4;
	}

IL_001e:
	{
		float L_5 = __this->___X;
		float L_6 = __this->___X;
		float L_7 = __this->___Y;
		float L_8 = __this->___Y;
		float L_9 = __this->___Z;
		float L_10 = __this->___Z;
		float L_11 = __this->___W;
		float L_12 = __this->___W;
		il2cpp_codegen_runtime_class_init_inline(MathF_t54810B9E7503B5BD0850A3D6E6336922E1553C52_il2cpp_TypeInfo_var);
		float L_13;
		L_13 = sqrtf(((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_multiply(L_5, L_6)), ((float)il2cpp_codegen_multiply(L_7, L_8)))), ((float)il2cpp_codegen_multiply(L_9, L_10)))), ((float)il2cpp_codegen_multiply(L_11, L_12)))));
		return L_13;
	}
}
IL2CPP_EXTERN_C  float Vector4_Length_m988A0BD2995D4011A1E8D915232859ADAD2C5462_AdjustorThunk (RuntimeObject* __this, const RuntimeMethod* method)
{
	Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6* _thisAdjusted;
	int32_t _offset = 1;
	_thisAdjusted = reinterpret_cast<Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6*>(__this + _offset);
	float _returnValue;
	_returnValue = Vector4_Length_m988A0BD2995D4011A1E8D915232859ADAD2C5462_inline(_thisAdjusted, method);
	return _returnValue;
}
// Method Definition Index: 75140
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Vector4__ctor_m22210D6F83F4B5D6CFA82DF01AA1435A4121AC8C (Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6* __this, float ___0_x, float ___1_y, float ___2_z, float ___3_w, const RuntimeMethod* method) 
{
	{
		float L_0 = ___3_w;
		__this->___W = L_0;
		float L_1 = ___0_x;
		__this->___X = L_1;
		float L_2 = ___1_y;
		__this->___Y = L_2;
		float L_3 = ___2_z;
		__this->___Z = L_3;
		return;
	}
}
IL2CPP_EXTERN_C  void Vector4__ctor_m22210D6F83F4B5D6CFA82DF01AA1435A4121AC8C_AdjustorThunk (RuntimeObject* __this, float ___0_x, float ___1_y, float ___2_z, float ___3_w, const RuntimeMethod* method)
{
	Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6* _thisAdjusted;
	int32_t _offset = 1;
	_thisAdjusted = reinterpret_cast<Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6*>(__this + _offset);
	Vector4__ctor_m22210D6F83F4B5D6CFA82DF01AA1435A4121AC8C(_thisAdjusted, ___0_x, ___1_y, ___2_z, ___3_w, method);
}
// Method Definition Index: 75141
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Vector4_Equals_m0FF82F8146C7DFCC7B3BCBA18AF1694992A132F6 (Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6* __this, Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 ___0_other, const RuntimeMethod* method) 
{
	{
		float L_0 = __this->___X;
		Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 L_1 = ___0_other;
		float L_2 = L_1.___X;
		if ((!(((float)L_0) == ((float)L_2))))
		{
			goto IL_0039;
		}
	}
	{
		float L_3 = __this->___Y;
		Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 L_4 = ___0_other;
		float L_5 = L_4.___Y;
		if ((!(((float)L_3) == ((float)L_5))))
		{
			goto IL_0039;
		}
	}
	{
		float L_6 = __this->___Z;
		Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 L_7 = ___0_other;
		float L_8 = L_7.___Z;
		if ((!(((float)L_6) == ((float)L_8))))
		{
			goto IL_0039;
		}
	}
	{
		float L_9 = __this->___W;
		Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 L_10 = ___0_other;
		float L_11 = L_10.___W;
		return (bool)((((float)L_9) == ((float)L_11))? 1 : 0);
	}

IL_0039:
	{
		return (bool)0;
	}
}
IL2CPP_EXTERN_C  bool Vector4_Equals_m0FF82F8146C7DFCC7B3BCBA18AF1694992A132F6_AdjustorThunk (RuntimeObject* __this, Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 ___0_other, const RuntimeMethod* method)
{
	Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6* _thisAdjusted;
	int32_t _offset = 1;
	_thisAdjusted = reinterpret_cast<Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6*>(__this + _offset);
	bool _returnValue;
	_returnValue = Vector4_Equals_m0FF82F8146C7DFCC7B3BCBA18AF1694992A132F6(_thisAdjusted, ___0_other, method);
	return _returnValue;
}
// Method Definition Index: 75142
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR float Vector4_Dot_mE2526C44DFCF9BFF1E22F265B9F24F2BB18FE27B (Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 ___0_vector1, Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 ___1_vector2, const RuntimeMethod* method) 
{
	{
		Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 L_0 = ___0_vector1;
		float L_1 = L_0.___X;
		Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 L_2 = ___1_vector2;
		float L_3 = L_2.___X;
		Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 L_4 = ___0_vector1;
		float L_5 = L_4.___Y;
		Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 L_6 = ___1_vector2;
		float L_7 = L_6.___Y;
		Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 L_8 = ___0_vector1;
		float L_9 = L_8.___Z;
		Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 L_10 = ___1_vector2;
		float L_11 = L_10.___Z;
		Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 L_12 = ___0_vector1;
		float L_13 = L_12.___W;
		Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 L_14 = ___1_vector2;
		float L_15 = L_14.___W;
		return ((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_multiply(L_1, L_3)), ((float)il2cpp_codegen_multiply(L_5, L_7)))), ((float)il2cpp_codegen_multiply(L_9, L_11)))), ((float)il2cpp_codegen_multiply(L_13, L_15))));
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
// Method Definition Index: 75143
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t HashHelpers_Combine_mA94C6B78FF8602DF43B2236B37E07EAD3E40861E (int32_t ___0_h1, int32_t ___1_h2, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = ___0_h1;
		int32_t L_1 = ___0_h1;
		int32_t L_2 = ___0_h1;
		int32_t L_3 = ___1_h2;
		return ((int32_t)(((int32_t)il2cpp_codegen_add(((int32_t)(((int32_t)(L_0<<5))|((int32_t)((uint32_t)L_1>>((int32_t)27))))), L_2))^L_3));
	}
}
// Method Definition Index: 75144
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void HashHelpers__cctor_m093A42F233326EC4108E2E8ECCACB6B9F7C8D58C (const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&HashHelpers_tDBADDD699E5C1C58ED9DF75B838DD3056A6EAF64_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Guid_t V_0;
	memset((&V_0), 0, sizeof(V_0));
	{
		Guid_t L_0;
		L_0 = Guid_NewGuid_m1F4894E8DC089811D6252148AD5858E58D43A7BD(NULL);
		V_0 = L_0;
		int32_t L_1;
		L_1 = Guid_GetHashCode_m239B7679BB9ED5A207B3D2F858B5F30FFC455408((&V_0), NULL);
		((HashHelpers_tDBADDD699E5C1C58ED9DF75B838DD3056A6EAF64_StaticFields*)il2cpp_codegen_static_fields_for(HashHelpers_tDBADDD699E5C1C58ED9DF75B838DD3056A6EAF64_il2cpp_TypeInfo_var))->___RandomSeed = L_1;
		return;
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
// Method Definition Index: 75145
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void IntrinsicAttribute__ctor_m13ED82DECF448852B2E39C178637138A13A87459 (IntrinsicAttribute_tB9B07649CD111989AAD4463A0664A70B58B3A91B* __this, const RuntimeMethod* method) 
{
	{
		Attribute__ctor_m79ED1BF1EE36D1E417BA89A0D9F91F8AAD8D19E2(__this, NULL);
		return;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
// Method Definition Index: 75130
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR bool Vector3_Equals_m81F9E36BE544963229CFED0561670E20DDCAABCE_inline (Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		RuntimeObject* L_0 = ___0_obj;
		if (((RuntimeObject*)IsInstSealed((RuntimeObject*)L_0, Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D_il2cpp_TypeInfo_var)))
		{
			goto IL_000a;
		}
	}
	{
		return (bool)0;
	}

IL_000a:
	{
		RuntimeObject* L_1 = ___0_obj;
		bool L_2;
		L_2 = Vector3_Equals_m5EAC07DAA75E2A84A3997BC27F36BE19698CC3FE(__this, ((*(Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D*)UnBox(L_1, Vector3_tCC8562BAFFDC06A7846FD7D7B13397E3E8041E3D_il2cpp_TypeInfo_var))), NULL);
		return L_2;
	}
}
// Method Definition Index: 20052
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR String_t* NumberFormatInfo_get_NumberGroupSeparator_m0556B092AA471513B1EDC31C047712226D39BEB6_inline (NumberFormatInfo_t8E26808B202927FEBF9064FCFEEA4D6E076E6472* __this, const RuntimeMethod* method) 
{
	{
		String_t* L_0 = __this->___numberGroupSeparator;
		return L_0;
	}
}
// Method Definition Index: 75136
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR bool Vector4_Equals_mD6B3F8BACD1BC326AF6FF6F83FDDF6BA90A9CF10_inline (Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		RuntimeObject* L_0 = ___0_obj;
		if (((RuntimeObject*)IsInstSealed((RuntimeObject*)L_0, Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6_il2cpp_TypeInfo_var)))
		{
			goto IL_000a;
		}
	}
	{
		return (bool)0;
	}

IL_000a:
	{
		RuntimeObject* L_1 = ___0_obj;
		bool L_2;
		L_2 = Vector4_Equals_m0FF82F8146C7DFCC7B3BCBA18AF1694992A132F6(__this, ((*(Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6*)UnBox(L_1, Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6_il2cpp_TypeInfo_var))), NULL);
		return L_2;
	}
}
// Method Definition Index: 75142
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR float Vector4_Dot_mE2526C44DFCF9BFF1E22F265B9F24F2BB18FE27B_inline (Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 ___0_vector1, Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 ___1_vector2, const RuntimeMethod* method) 
{
	{
		Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 L_0 = ___0_vector1;
		float L_1 = L_0.___X;
		Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 L_2 = ___1_vector2;
		float L_3 = L_2.___X;
		Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 L_4 = ___0_vector1;
		float L_5 = L_4.___Y;
		Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 L_6 = ___1_vector2;
		float L_7 = L_6.___Y;
		Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 L_8 = ___0_vector1;
		float L_9 = L_8.___Z;
		Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 L_10 = ___1_vector2;
		float L_11 = L_10.___Z;
		Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 L_12 = ___0_vector1;
		float L_13 = L_12.___W;
		Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 L_14 = ___1_vector2;
		float L_15 = L_14.___W;
		return ((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_multiply(L_1, L_3)), ((float)il2cpp_codegen_multiply(L_5, L_7)))), ((float)il2cpp_codegen_multiply(L_9, L_11)))), ((float)il2cpp_codegen_multiply(L_13, L_15))));
	}
}
// Method Definition Index: 75139
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR float Vector4_Length_m988A0BD2995D4011A1E8D915232859ADAD2C5462_inline (Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&MathF_t54810B9E7503B5BD0850A3D6E6336922E1553C52_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		bool L_0;
		L_0 = Vector_get_IsHardwareAccelerated_m783509258751EBED64CBD9F387EC1BB4A15088AA(NULL);
		if (!L_0)
		{
			goto IL_001e;
		}
	}
	{
		Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 L_1 = (*(Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6*)__this);
		Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6 L_2 = (*(Vector4_tF4F5C74E5DAF01F48A6C081ECCF18A33861C52A6*)__this);
		float L_3;
		L_3 = Vector4_Dot_mE2526C44DFCF9BFF1E22F265B9F24F2BB18FE27B_inline(L_1, L_2, NULL);
		il2cpp_codegen_runtime_class_init_inline(MathF_t54810B9E7503B5BD0850A3D6E6336922E1553C52_il2cpp_TypeInfo_var);
		float L_4;
		L_4 = sqrtf(L_3);
		return L_4;
	}

IL_001e:
	{
		float L_5 = __this->___X;
		float L_6 = __this->___X;
		float L_7 = __this->___Y;
		float L_8 = __this->___Y;
		float L_9 = __this->___Z;
		float L_10 = __this->___Z;
		float L_11 = __this->___W;
		float L_12 = __this->___W;
		il2cpp_codegen_runtime_class_init_inline(MathF_t54810B9E7503B5BD0850A3D6E6336922E1553C52_il2cpp_TypeInfo_var);
		float L_13;
		L_13 = sqrtf(((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_add(((float)il2cpp_codegen_multiply(L_5, L_6)), ((float)il2cpp_codegen_multiply(L_7, L_8)))), ((float)il2cpp_codegen_multiply(L_9, L_10)))), ((float)il2cpp_codegen_multiply(L_11, L_12)))));
		return L_13;
	}
}
