# Reflektion 

## Security
- Security is obviously a very important aspect of any application that we create as developers.
- If an application isn't created with security in mind, user information is vulnerable to outside elements.
- In order to ensure that user data is secure, it's important to think about the security of an application when it is being initially created.
  - If you create an application without considering security, you run the risk of that app not having any security, or the security being implemented as an afterthought, which can result in avoidable vulnerabilities.

## Design
- When it comes to design, regarding security, it's important for both front-end and back-end developers to consider how much access is going to be available.
- You don't want to create an application that has too many end-points. Partially this is because it's less efficient, but it's also because the more end-points you introduce the more potential there is for you as a developer to introduce a vulnerability that can be exploited.
- If possible, you want to ensure that your application is secure at multiple levels, that way if one level of security fails, the next one will hopefully make up for it. 

---
- When working on the individual lab, I used securityFilterChain inside SecurityConfig. This method of providing security was the one I was most comfortable, which is why I chose it. Regardless of any benefits using @PreAuthorize may have afforded me, I felt it better to use the system I was more comfortable in, as this would hopefully reduce the likelihood of errors that could lead to vulnerabilities in security.
- I think there are some things I could've implemented better, like the admin check on the "delete" endpoint. At the time I hadn't properly looked into @PreAuthorize and in retrospect that might've made things easier in that regard.
- One aspect where I know I could've improved was my mindset coming into the task. I tend to lean toward developing an application that is user-friendly, and while that is obviously important, I think I could've maybe spent less time focusing on that and more on things like security.