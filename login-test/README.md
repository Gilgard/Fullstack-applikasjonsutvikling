# Goals - add simple unit test

## Compile, Run the project and inspect the page

Note - This project is a Frontend app and need a backend API to work (see next section about using a backend) 

Checkout this locally https://gitlab.com/sysdev-tutorials/vuejs/02-my-vue-project/-/tree/12-final

```
# download dependencies (one time operation or do it again when new libraries are added)
npm install 
# compile and hot-reload for development
npm run serve
# inspect page contents
```

## Run a backend demo API locally 

Locally checkout this repo: https://gitlab.com/sysdev-tutorials/springboot/auth_demo/-/tree/test.
Switch to 'test' branch. Then from the project folder, run

```
mvn spring-boot:run
```

Note the context path and port number given src/main/resources/application.properties file.
Correct port should be used from frontend part.

## Add unit-jest plugin (to the frontend project)
In the project folder,

```
vue add unit-jest

```

This will add ```tests/unit``` directory in the project directory. In addition,  ```jest.config.js``` file is added, and ```package.json``` file is modified.

Now, remove the example test file ```tests/unit/example.spec.js``` added by the above command.

Add ```tests/unit/Login.spec.js``` test file. This has one test that tests that the LoginComponent renders correctly.

```
import { shallowMount } from '@vue/test-utils'
import LoginComponent from '@/components/LoginComponent.vue'

describe('LoginComponent.vue', () => {
  it('check that LoginComponent renders', () => {
    const loginTitle = 'Please login!'
    const wrapper = shallowMount(LoginComponent)
    expect(wrapper.text()).toMatch(loginTitle)
  })
})
```
Execute the tests.
```
npm run test:unit
```
 