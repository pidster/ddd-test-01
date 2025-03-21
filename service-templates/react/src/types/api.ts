/**
 * Standard API response wrapper
 */
export interface ApiResponse<T> {
  success: boolean;
  data: T;
  error?: string;
}

/**
 * Pagination metadata
 */
export interface PageMetadata {
  page: number;
  size: number;
  totalElements: number;
  totalPages: number;
}

/**
 * Paginated response
 */
export interface PagedResponse<T> {
  content: T[];
  metadata: PageMetadata;
}

/**
 * ClaimStatus enum
 */
export enum ClaimStatus {
  SUBMITTED = 'SUBMITTED',
  DOCUMENT_REQUESTED = 'DOCUMENT_REQUESTED',
  UNDER_REVIEW = 'UNDER_REVIEW',
  ACCEPTED = 'ACCEPTED',
  REJECTED = 'REJECTED',
  PAID = 'PAID'
}

/**
 * Claim DTO
 */
export interface ClaimDto {
  id: string;
  policyId: string;
  customerId: string;
  customerName: string;
  claimDate: string;
  incidentDate: string;
  status: ClaimStatus;
  description: string;
  amount: number;
  createdAt: string;
  updatedAt: string;
}

/**
 * Policy DTO
 */
export interface PolicyDto {
  id: string;
  policyNumber: string;
  customerId: string;
  customerName: string;
  type: string;
  startDate: string;
  endDate: string;
  status: string;
  coverageAmount: number;
}

/**
 * Customer DTO
 */
export interface CustomerDto {
  id: string;
  name: string;
  email: string;
  phone: string;
  address: string;
}

/**
 * Document DTO
 */
export interface DocumentDto {
  id: string;
  claimId: string;
  name: string;
  contentType: string;
  size: number;
  uploadedAt: string;
  url: string;
}

/**
 * Payment DTO
 */
export interface PaymentDto {
  id: string;
  claimId: string;
  amount: number;
  status: string;
  scheduledDate: string;
  executedDate?: string;
  method: string;
  reference: string;
}

/**
 * Notification DTO
 */
export interface NotificationDto {
  id: string;
  recipientId: string;
  recipientEmail: string;
  subject: string;
  content: string;
  sentAt: string;
  status: string;
}

/**
 * ClaimSubmissionDto
 */
export interface ClaimSubmissionDto {
  policyId: string;
  incidentDate: string;
  description: string;
  amount: number;
  documents?: File[];
}

/**
 * Error response with validation details
 */
export interface ValidationErrorResponse {
  status: number;
  message: string;
  timestamp: string;
  errors: Record<string, string>;
} 