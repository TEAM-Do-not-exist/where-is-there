<template>
<v-app id="inspire" app prop>
    <v-content>
        <v-responsive :aspect-ratio="16/9">
            <v-container>
                <v-layout>
                    <v-row>
                        <v-col align="center" justify="center" cols="12" xs="12" sm="12" md="12">
                            <v-card class="elevation-8" height="550" max-height="650" width="490">
                                <v-toolbar color="white" flat extended>
                                  <v-row align="center" justify="center">
                                   <v-toolbar-title class="mt-9 headline font-weight-black">회원가입</v-toolbar-title>
                                  </v-row>
                                </v-toolbar>   
                                <v-card-text class="mt-n4">
                                    <v-form
                                    ref= "form"
                                    v-model= "form"
                                    class= "pa-3">
                                        <v-text-field
                                        v-model= "id"
                                        :rules= "[rules.length(7)]"
                                        label="ID INSERT"
                                        name="id"
                                        type="text"
                                        
                                        outlined />

                                        <v-text-field
                                        label="PASS WORD INSERT"
                                        v-model= "password"
                                        :rules= "[rules.ppassword(14), rules.password]"
                                        name="password"
                                        type="password"
                                        outlined />

                                        <v-text-field
                                        label="E-MAIL INSERT"
                                        :rules= "[rules.email]"
                                        name="email"
                                        type="email"
                                        outlined />
                                      
                                      <v-checkbox
                                      class="mt-n1"
                                      v-model= "agreement" 
                                      :disabled= '!agreement'
                                      display = "inline"
                                      >
                                    </v-checkbox>
                                        <p class="mt-n12 subtitle-1" style="margin-left : 32px, margin-top: 30px" align ="left">
                                        개인정보 이용에 
                                          <a  ref="#" @click.stop.prevent="dialog = true">동의</a>
                                        &nbsp; 하십니까?</p>
                                        
                                </v-form>
                            </v-card-text>  
                                <v-divider></v-divider>
                                <v-card-actions>
                                    <v-row align="center" justify="center"> 
                                    <v-btn
                                    text
                                    @click= "$refs.form.reset()"
                                    >취소
                                    </v-btn>
                                    <v-btn
                                    text
                                    :disabled= "!form"
                                    :loading= "isLoading"
                                    class= "black--text"
                                    depressed
                                    >
                                    가입하기
                                    </v-btn>
                                    </v-row>
                                </v-card-actions>
                                <v-dialog
                                v-model= "dialog"
                                absolute
                                max-width="400"
                                persistent
                                >
                                <v-card>
                                    <v-card-title class= "title grey lighten-5">개인정보 동의서</v-card-title>
                                    <v-card-text align="center" justify="center">
                                        꼭 지키세요
                                    </v-card-text>
                                    <v-divider></v-divider>
                                    <v-card-actions>
                                        <v-btn
                                        text
                                        @click= "agreement = false, dialog = false"
                                        class="font-regular black--text"
                                        >
                                        아니오
                                        </v-btn>
                                        <v-spacer/>
                                        <v-btn
                                        class="black--text font-regular"
                                        @click= "agreement = true, dialog = false"
                                        >네
                                        </v-btn>
                                    </v-card-actions>
                                </v-card>
                                </v-dialog>
                            </v-card>
                        </v-col>
                    </v-row>
                </v-layout>
            </v-container>
        </v-responsive>
    </v-content>
</v-app>
</template>
    
<script>
  export default {
    data: () => ({
      agreement: false,
      dialog: false,
      email: undefined,
      form: false,
      isLoading: false,
      password: undefined,
      rules: {
        email: v => (v || '').match(/@/) || 'E-MAIL 을 입력하세요',

        length: len => v => (v || '').length >= len || `7자 이상 입력하세요` 
        ,

        password: v => (v || '').match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*(_|[^\w])).+$/) ||
          'Password must contain an upper case letter, a numeric character, and a special character' ,

        ppassword: len => v => ((v || '').length >= len || `10자 이상 입력하세요`)
        ,
        required: v => !!v || 'This field is required',
      },
    }),
    
  }
</script>

<style scoped>
</style>