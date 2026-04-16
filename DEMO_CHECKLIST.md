# CourseReserve Demo Checklist

## 1. Startup
- Start backend: `cd backend && .\\mvnw.cmd spring-boot:run`
- Start frontend: `cd frontend && npm run dev`
- Open app at `http://localhost:5173`

## 2. Login Scenarios
- Login as admin: `admin@demo.com / admin123`
- Login as learner: `user@demo.com / user123`
- Verify animated auth card and CTA interactions on login/register pages

## 3. Admin Course Intelligence
- Open Admin Panel
- Create a new course with title and description only (leave scope/companies/trend blank)
- Save and verify backend auto-generates:
  - Scope
  - Job roles
  - Companies hiring
  - Company profiles
  - Hiring trend

## 4. Catalog Experience
- Go to Dashboard
- Search for a course
- Verify each course card shows:
  - Scope
  - Hiring trend badge
  - Clickable hiring companies with logo badges and external links
- Reserve a course and verify button loading animation

## 5. Enrolled Courses Experience
- Open My Courses
- Verify reserved course card shows:
  - Scope
  - Hiring trend badge
  - Clickable company logos/links
- Cancel one reservation and verify success message animation

## 6. Landing Page SaaS Walkthrough
- Validate hero 3D visual layers and product mock animation
- Validate animated KPI mini-demo
- Validate dashboard preview section
- Validate pricing cards and CTA interactions

## 7. Responsive QA
- Desktop: layout depth effects and spacing
- Tablet: section stacking, readable typography
- Mobile: cards stack correctly, no overlap, tap targets usable

## 8. Build Validation
- Frontend lint: `cd frontend && npm run lint`
- Frontend build: `cd frontend && npm run build`
- Backend test: `cd backend && .\\mvnw.cmd test`

## 9. Final Demo Narrative
- Problem: generic course platforms only list content
- Differentiator: CourseReserve maps each course to scope, role relevance, company demand, and hiring trend
- Outcome: users make smarter enrollment decisions with market-aware signals
