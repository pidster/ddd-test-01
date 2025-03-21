# React Application Template for Insurance Claims UI

This template provides a starting point for creating React applications within the Insurance Claims System, following best practices and established patterns.

## Features

- **TypeScript**: Strong typing for better developer experience and code quality
- **Component Library**: Reusable UI components based on Material UI
- **Routing**: Setup with React Router for navigation
- **State Management**: React Query for server state, Context API for application state
- **API Communication**: Axios for HTTP requests with interceptors for auth and error handling
- **Form Handling**: Formik and Yup for form management and validation
- **Testing**: Jest and React Testing Library for unit and component testing
- **Code Quality**: ESLint, Prettier, and TypeScript for code quality
- **Project Structure**: Organized folder structure following best practices

## Project Structure

```
src/
├── components/         # Reusable UI components
│   ├── common/         # Shared UI elements
│   ├── layout/         # Layout components
│   └── domain/         # Domain-specific components
├── contexts/           # React contexts for global state
├── hooks/              # Custom React hooks
├── pages/              # Page components
├── services/           # API services
├── types/              # TypeScript type definitions
├── utils/              # Utility functions
├── App.tsx             # Main application component
└── index.tsx           # Application entry point
```

## Getting Started

1. **Copy the template**:
   ```
   cp -r service-templates/react my-new-app
   ```

2. **Update project details**:
   - Update the project name and description in `package.json`
   - Update the `README.md` with information about your application

3. **Install dependencies**:
   ```
   cd my-new-app
   npm install
   ```

4. **Start the development server**:
   ```
   npm start
   ```

5. **Build for production**:
   ```
   npm run build
   ```

## Development Guidelines

### Component Development

- Use functional components with hooks
- Keep components small and focused on a single responsibility
- Use TypeScript interfaces to define component props
- Keep state as local as possible
- Use custom hooks to share logic between components

### Styling Approach

- Use Material UI's styling system for consistent theming
- Use CSS-in-JS with emotion for custom styling
- Follow the project's design system and component library

### State Management

- Use React Query for server state (data fetching, caching, synchronization)
- Use Context API for global application state
- Use local state for component-specific state
- Avoid prop drilling by using context or custom hooks

### API Communication

- Use the centralized API service for all HTTP requests
- Handle errors consistently using API service interceptors
- Use React Query for data fetching, caching, and synchronization
- Use TypeScript interfaces to define request and response types

## Available Scripts

- `npm start`: Starts the development server
- `npm run build`: Builds the app for production
- `npm test`: Runs tests
- `npm run lint`: Lints the code
- `npm run type-check`: Checks TypeScript types
- `npm run format`: Formats code with Prettier

## Common Components

The template includes several common components:

- `Button`: Enhanced Material UI button with consistent styling
- `Card`: Enhanced Material UI card with consistent styling
- `TextField`: Enhanced Material UI text field with error handling
- `DataTable`: Data table component for displaying tabular data
- `LoadingSpinner`: Loading indicator
- `ErrorBoundary`: Error boundary component
- `PageLayout`: Page layout with header, footer, and sidebar

## Testing

- Write unit tests for utility functions
- Write component tests for UI components
- Use React Testing Library for testing components
- Use MSW for mocking API requests in tests
- Aim for high test coverage

## Deployment

The application can be deployed as a static site or as a containerized application. See the main documentation for deployment details.

## Related Documentation

- [Architecture Overview](../../ARCHITECTURE.md)
- [React Frontend ADR](../../docs/adr/0003-react-frontend-architecture.md)
- [UI Component Guidelines](../../docs/ui-component-guidelines.md)
- [Backend API Documentation](https://api-docs.insurance-claims.com) 